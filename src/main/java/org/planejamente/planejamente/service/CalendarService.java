package org.planejamente.planejamente.service;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.*;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.AccessToken;
import com.google.auth.oauth2.GoogleCredentials;
import org.apache.http.protocol.HTTP;
import org.planejamente.planejamente.dto.AuthCalendarId;
import org.planejamente.planejamente.dto.dtoCriar.ConsultaDto;
import org.planejamente.planejamente.entity.Consulta;
import org.planejamente.planejamente.entity.usuario.Paciente;
import org.planejamente.planejamente.entity.usuario.Psicologo;
import org.planejamente.planejamente.mapper.ConsultaMapper;
import org.planejamente.planejamente.repository.PacienteRepository;
import org.planejamente.planejamente.repository.PsicologoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CalendarService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private PsicologoRepository psicologoRepository;

    private static final Logger logger = Logger.getLogger(CalendarService.class.getName());

    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String USER_EMAIL = "servicosplanejamente@gmail.com";

    public AuthCalendarId createCalendars(String accessToken) throws IOException, GeneralSecurityException {
        try {
            HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
            GoogleCredentials credentials = GoogleCredentials.create(new AccessToken(accessToken, null));

            Calendar service = new Calendar.Builder(httpTransport, JSON_FACTORY, new HttpCredentialsAdapter(credentials))
                    .setApplicationName("planejamenoCalendario")
                    .build();

            String workHoursCalendarId = createCalendar(service, "Horário de trabalho");
            String consultationCalendarId = createCalendar(service, "Consulta");

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

        // Configurar regras de ACL
        AclRule rule = new AclRule();
        AclRule.Scope scope = new AclRule.Scope();
        scope.setType("user").setValue(USER_EMAIL);
        rule.setScope(scope).setRole("owner");
        service.acl().insert(createdCalendar.getId(), rule).execute();

        return createdCalendar.getId();
    }

    public String createEventWithMeetLink(String accessToken, String calendarId, Consulta consulta) throws IOException, GeneralSecurityException {
        try {
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

            Event createdEvent = service.events().insert(calendarId, event)
                    .setConferenceDataVersion(1)
                    .execute();

            logger.info("Evento criado: " + createdEvent.getHtmlLink());

            return createdEvent.getHangoutLink();
        } catch (IOException | GeneralSecurityException e) {
            logger.log(Level.SEVERE, "Erro ao criar evento com link do Meet", e);
            throw e;
        }
    }
}
