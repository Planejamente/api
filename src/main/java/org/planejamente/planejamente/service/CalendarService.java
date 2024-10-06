package org.planejamente.planejamente.service;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.*;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.AccessToken;
import com.google.auth.oauth2.GoogleCredentials;
import org.planejamente.planejamente.dto.AuthCalendarId;
import org.planejamente.planejamente.entity.Consulta;
import org.planejamente.planejamente.repository.PacienteRepository;
import org.planejamente.planejamente.repository.PsicologoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class CalendarService {

    private static final Logger logger = Logger.getLogger(CalendarService.class.getName());
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String USER_EMAIL = "servicosplanejamente@gmail.com";

    private final PacienteRepository pacienteRepository;
    private final PsicologoRepository psicologoRepository;

    @Autowired
    public CalendarService(PacienteRepository pacienteRepository, PsicologoRepository psicologoRepository) {
        this.pacienteRepository = pacienteRepository;
        this.psicologoRepository = psicologoRepository;
    }

    public AuthCalendarId createCalendars(String accessToken) throws IOException, GeneralSecurityException {
        try {
            HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
            GoogleCredentials credentials = GoogleCredentials.create(new AccessToken(accessToken, null));

            Calendar service = new Calendar.Builder(httpTransport, JSON_FACTORY, new HttpCredentialsAdapter(credentials))
                    .setApplicationName("planejamentoCalendario")
                    .build();

            String workHoursCalendarId = createCalendar(service, "Horário de trabalho");
            String consultationCalendarId = createCalendar(service, "Consulta");
            GoogleService.addCalendarToServiceAccount(List.of(workHoursCalendarId, consultationCalendarId));

            return new AuthCalendarId(workHoursCalendarId, consultationCalendarId);

        } catch (IOException | GeneralSecurityException e) {
            logger.log(Level.SEVERE, "Erro ao criar calendário", e);
            throw e;
        }
    }

    private String createCalendar(Calendar service, String summary) throws IOException {
        com.google.api.services.calendar.model.Calendar calendar = new com.google.api.services.calendar.model.Calendar();
        calendar.setSummary(summary);
    
        com.google.api.services.calendar.model.Calendar createdCalendar = service.calendars().insert(calendar).execute();
    
        AclRule ruleUserEmail = new AclRule();
        AclRule.Scope scopeUserEmail = new AclRule.Scope();
        scopeUserEmail.setType("user").setValue(USER_EMAIL);
        ruleUserEmail.setScope(scopeUserEmail).setRole("owner");
        service.acl().insert(createdCalendar.getId(), ruleUserEmail).execute();
    
        AclRule ruleServiceAccount = new AclRule();
        AclRule.Scope scopeServiceAccount = new AclRule.Scope();
        scopeServiceAccount.setType("user").setValue("planejamento@alpine-agent-360003.iam.gserviceaccount.com");
        ruleServiceAccount.setScope(scopeServiceAccount).setRole("owner");
        service.acl().insert(createdCalendar.getId(), ruleServiceAccount).execute();
    
        return createdCalendar.getId();
    }

    public String createEventWithMeetLink(String accessToken, String calendarId, Consulta consulta) throws IOException, GeneralSecurityException {
        if (!checkAvailability(accessToken, calendarId, consulta)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Participantes não estão disponíveis neste horário.");
        }

        HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        GoogleCredentials credentials = GoogleCredentials.create(new AccessToken(accessToken, null));

        Calendar service = new Calendar.Builder(httpTransport, JSON_FACTORY, new HttpCredentialsAdapter(credentials))
                .setApplicationName("planejamentoCalendario")
                .build();

        Event event = new Event()
                .setSummary("Consulta com " + consulta.getPaciente().getNome())
                .setStart(new EventDateTime()
                        .setDateTime(new com.google.api.client.util.DateTime(consulta.getInicio().toString()))
                        .setTimeZone("America/Los_Angeles"))
                .setEnd(new EventDateTime()
                        .setDateTime(new com.google.api.client.util.DateTime(consulta.getFim().toString()))
                        .setTimeZone("America/Los_Angeles"))
                .setConferenceData(new ConferenceData()
                        .setCreateRequest(new CreateConferenceRequest()
                                .setRequestId("consulta-" + consulta.getIdAnamnese())
                                .setConferenceSolutionKey(new ConferenceSolutionKey()
                                        .setType("hangoutsMeet"))))
                .setHtmlLink("https://meet.google.com/new");

        // Adicionar o e-mail do paciente como participante
                String pacienteEmail = consulta.getPaciente().getEmail();
                EventAttendee attendee = new EventAttendee().setEmail(pacienteEmail);
                List<EventAttendee> attendees = new ArrayList<>();
                attendees.add(attendee);
                event.setAttendees(attendees);

                Event createdEvent = service.events().insert(calendarId, event)
                        .setConferenceDataVersion(1)
                        .execute();

                logger.info("Evento criado: " + createdEvent.getHtmlLink());

                return createdEvent.getHangoutLink();
    }


    private boolean checkAvailability(String accessToken, String calendarId, Consulta consulta) throws IOException, GeneralSecurityException {
        HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        GoogleCredentials credentials = GoogleCredentials.create(new AccessToken(accessToken, null));

        Calendar service = new Calendar.Builder(httpTransport, JSON_FACTORY, new HttpCredentialsAdapter(credentials))
                .setApplicationName("planejamentoCalendario")
                .build();

        FreeBusyRequest freeBusyRequest = new FreeBusyRequest();
        TimePeriod timePeriod = new TimePeriod();
        timePeriod.setStart(new DateTime(consulta.getInicio().toString()));
        timePeriod.setEnd(new DateTime(consulta.getFim().toString()));

        List<FreeBusyRequestItem> items = new ArrayList<>();
        items.add(new FreeBusyRequestItem().setId("primary"));
        items.add(new FreeBusyRequestItem().setId(calendarId));

        freeBusyRequest.setItems(items);
        freeBusyRequest.setTimeMin(new DateTime(consulta.getInicio().toString()));
        freeBusyRequest.setTimeMax(new DateTime(consulta.getFim().toString()));

        FreeBusyResponse freeBusyResponse = service.freebusy().query(freeBusyRequest).execute();
        Map<String, FreeBusyCalendar> calendars = freeBusyResponse.getCalendars();

        for (FreeBusyCalendar calendar : calendars.values()) {
            if (!calendar.getBusy().isEmpty()) {
                return false;
            }
        }
        return true;
    }
}
