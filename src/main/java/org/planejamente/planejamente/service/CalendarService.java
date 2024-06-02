package org.planejamente.planejamente.service;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.CalendarListEntry;

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

    public AuthCalendarId createCalendars(String accessToken) throws IOException, GeneralSecurityException {
        try {
            HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
            Credential credentials = new GoogleCredential().setAccessToken(accessToken);

            Calendar service = new Calendar.Builder(httpTransport, JSON_FACTORY, credentials).build();

            String CreateCalendar = createCalendar(service, "Horário de trabalho");
            String createCalendar2 =  createCalendar(service, "Consultas");

           return new AuthCalendarId(CreateCalendar, createCalendar2);

        } catch (IOException | GeneralSecurityException e) {
            logger.log(Level.SEVERE, "Erro ao criar calendário", e);
            throw e;
        }
    }

    private String createCalendar(Calendar service, String summary) throws IOException {
        com.google.api.services.calendar.model.Calendar calendar = new com.google.api.services.calendar.model.Calendar();
        calendar.setSummary(summary);

        com.google.api.services.calendar.model.Calendar createdCalendar = service.calendars().insert(calendar).execute();


        com.google.api.services.calendar.model.AclRule rule = new com.google.api.services.calendar.model.AclRule();
        com.google.api.services.calendar.model.AclRule.Scope scope = new com.google.api.services.calendar.model.AclRule.Scope();
        scope.setType("user").setValue("servicosplanejamente@gmail.com");
        rule.setScope(scope).setRole("owner");
        service.acl().insert(createdCalendar.getId(), rule).execute();

        return createdCalendar.getId();
    }

}
