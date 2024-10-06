package org.planejamente.planejamente.configuration;

import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.ServiceAccountCredentials;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

public class GoogleServiceAuthConfig {


    // COMPONENTES DE AUTENTICAÇÃO___________________________________________________________________________________________
    // Nome da aplicação que será registrada na API do Google Calendar
    private static final String APPLICATION_NAME = "Google Calendar API Java Integration";

    // Instância global da fábrica de JSON usada para serialização/deserialização
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();

    // Caminho para o arquivo de credenciais da conta de serviço
    private static final String CREDENTIALS_FILE_PATH = System.getProperty("user.home") + "/creds/credentials.json";

    // Escopos necessários para acessar o Google Calendar
    private static final List<String> SCOPES = Collections.singletonList(CalendarScopes.CALENDAR);

    public static Calendar authenticateCalendarService() throws GeneralSecurityException, IOException {
            final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            HttpRequestInitializer requestInitializer = new HttpCredentialsAdapter(ServiceAccountCredentials.fromStream(new FileInputStream(CREDENTIALS_FILE_PATH))
                    .createScoped(SCOPES));
            Calendar calendarService = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, requestInitializer)
                    .setApplicationName(APPLICATION_NAME)
                    .build();
            return calendarService;
        }
}
