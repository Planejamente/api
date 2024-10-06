package org.planejamente.planejamente.service;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.FileContent;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;

import org.planejamente.planejamente.configuration.GoogleServiceAuthConfig;
import org.planejamente.planejamente.configuration.Response;

import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.CalendarListEntry;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import org.springframework.stereotype.Service;


import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
@Service
public class GoogleService {
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final String SERVICE_ACCOUNT_KEY_PATH = getGoogleCredentials();
    private static  PsicologoService service;

    public GoogleService(PsicologoService service) {
        this.service = service;
    }

    private static String getGoogleCredentials() {
        String currentDirectory = System.getProperty("user.dir");
        Path filepath = Paths.get(currentDirectory, "credential.json");
        return filepath.toString();
    }

    public Object uploadFileDrive(File file, UUID idPsi) throws GeneralSecurityException, IOException {
         Response res = new Response();

        try {
            String folderId = "1avJWTY8N71aR_aHhix3LEBx3ep0tnvlG";
            Drive drive = createDriveService();
            com.google.api.services.drive.model.File fileMetaData = new com.google.api.services.drive.model.File();
            fileMetaData.setName(file.getName() + idPsi);
            fileMetaData.setParents(Collections.singletonList(folderId));
            FileContent mediaContent = new FileContent("application/octet-stream", file); // Ajustado para um mime type genérico
            com.google.api.services.drive.model.File fileUploaded = drive.files().create(fileMetaData, mediaContent)
                    .setFields("id")
                    .execute();
            System.out.println(fileUploaded.getId());
            String fileUrl = "https://drive.google.com/uc?export=view&id=" + fileUploaded.getId();
            System.out.println("Url para acesso do arquivo: " + fileUrl);
            file.delete();

            service.salvarAnamenese(fileUrl, fileUploaded.getId(), idPsi);

            res.setStatus(200);
            res.setMessage("Arquivo enviado com sucesso ao drive!");
            res.setUrl(fileUrl);
            res.setId((fileUploaded.getId())); // Definindo o ID do arquivo
        } catch (GoogleJsonResponseException e) {
            System.out.println(e.getDetails());
            res.setMessage(e.getMessage());
            res.setStatus(500);
        }
        return res;
    }

    public File downloadFileDrive(String fileId) throws IOException, GeneralSecurityException {
        Drive driveService = createDriveService();

        // Obter metadados do arquivo para conseguir o nome original do arquivo
        com.google.api.services.drive.model.File fileMetaData = driveService.files().get(fileId).execute();
        String fileName = fileMetaData.getName();

        // Criar arquivo temporário
        File tempFile = File.createTempFile("download-", fileName);
        OutputStream outputStream = new FileOutputStream(tempFile);

        // Baixar conteúdo do arquivo
        driveService.files().get(fileId).executeMediaAndDownloadTo(outputStream);

        outputStream.flush();
        outputStream.close();

        return tempFile;
    }


    private Drive createDriveService() throws GeneralSecurityException, IOException {
        GoogleCredential credential = GoogleCredential.fromStream(new FileInputStream(SERVICE_ACCOUNT_KEY_PATH))
                .createScoped(Collections.singleton(DriveScopes.DRIVE));

        return new Drive.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                JSON_FACTORY,
                credential)
                .build();
    }

    public static void addCalendarToServiceAccount(List<String> calendarsId) throws GeneralSecurityException, IOException {

            Calendar calendarService = GoogleServiceAuthConfig.authenticateCalendarService();
            for (String calendarId : calendarsId) {
                CalendarListEntry newCalendarEntry = new CalendarListEntry();
                newCalendarEntry.setId(calendarId);
                calendarService.calendarList().insert(newCalendarEntry).execute();
            }
    }
}
