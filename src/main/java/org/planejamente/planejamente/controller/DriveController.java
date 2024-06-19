package org.planejamente.planejamente.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.planejamente.planejamente.configuration.Response;
import org.planejamente.planejamente.service.GoogleService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.UUID;

@RestController
@RequestMapping("/drive")
@SecurityRequirement(name = "auth-api")
@CrossOrigin
public class DriveController {
    private final GoogleService service;

    public DriveController(GoogleService service) {
        this.service = service;
    }

    @PostMapping("/subir-arquivo")
    public ResponseEntity<Object> enviarArquivoDrive(@RequestParam("file") MultipartFile file, UUID idPsi) throws IOException, GeneralSecurityException {
        if(file.isEmpty()){
            return ResponseEntity.badRequest().body("Nenhum arquivo foi fornecido.");
        }

        File tempFile = File.createTempFile("anamenese-teste", null);
        file.transferTo(tempFile);
        Response res = (Response) service.uploadFileDrive(tempFile, idPsi);

        System.out.println(res);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/download-arquivo")
    public ResponseEntity<Object> downloadArquivoDrive(@RequestParam String fileId) {
        try {
            File file = service.downloadFileDrive(fileId);
            if (file == null) {
                return ResponseEntity.status(404).body("Arquivo não encontrado.");
            }

            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
            headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
            headers.add("Pragma", "no-cache");
            headers.add("Expires", "0");

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(file.length())
                    .contentType(MediaType.parseMediaType("application/octet-stream"))
                    .body(resource);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao fazer o download do arquivo.");
        }
    }
}
