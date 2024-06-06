package org.planejamente.planejamente.service;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.AclRule;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.planejamente.planejamente.dto.AuthCalendarId;
import org.springframework.stereotype.Service;

@Service
public class CalendarService {

    private static final Logger logger = Logger.getLogger(CalendarService.class.getName());

    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String USER_EMAIL = "servicosplanejamente@gmail.com";

    public AuthCalendarId createCalendars(String accessToken) throws IOException, GeneralSecurityException {
        try {
            HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
            Credential credentials = new GoogleCredential().setAccessToken(accessToken);

            Calendar service = new Calendar.Builder(httpTransport, JSON_FACTORY, credentials).setApplicationName("planejamenoCalendario").build();
            String createCalendar1 = createCalendar(service, "Horário de trabalho");
            String createCalendar2 = createCalendar(service, "Consulta");

            return new AuthCalendarId(createCalendar1, createCalendar2);

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

    //private void createEventWithMeetLink(Calendar service, String calendarId, String summary) throws IOException {
    //        Event event = new Event()
    //                .setSummary(summary)
    //                .setStart(new EventDateTime()
    //                        .setDateTime(new com.google.api.client.util.DateTime("2024-06-05T10:00:00-07:00"))
    //                        .setTimeZone("America/Los_Angeles"))
    //                .setEnd(new EventDateTime()
    //                        .setDateTime(new com.google.api.client.util.DateTime("2024-06-05T11:00:00-07:00"))
    //                        .setTimeZone("America/Los_Angeles"))
    //                .setConferenceData(new ConferenceData()
    //                        .setCreateRequest(new CreateConferenceRequest()
    //                                .setRequestId("sample123")
    //                                .setConferenceSolutionKey(new ConferenceSolutionKey()
    //                                        .setType("hangoutsMeet"))));
    //
    //        Event createdEvent = service.events().insert(calendarId, event)
    //                .setConferenceDataVersion(1)
    //                .execute();
    //
    //        logger.info("Evento criado: " + createdEvent.getHtmlLink());
    //    }
}
