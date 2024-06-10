package org.planejamente.planejamente.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.planejamente.planejamente.service.BlobService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/blob")
@SecurityRequirement(name = "auth-api")
@CrossOrigin
public class BlobController {
    private final BlobService azureBlobService;

    public BlobController(BlobService azureBlobService) {
        this.azureBlobService = azureBlobService;
    }

    @PostMapping("/foto-de-usuario")
    public ResponseEntity<Object> uploadFotoPerfil(@RequestParam("images") MultipartFile[] files, @RequestParam UUID id) throws IOException {
        if (files.length == 0) {
            return ResponseEntity.badRequest().body("No files were provided.");
        }

        try {
            List<String> fileUrls = azureBlobService.uploadFotoPerfil(files, id);
            return ResponseEntity.ok(fileUrls);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error uploading files.");
        }

    }

    @PostMapping("/foto-de-fundo")
    public ResponseEntity<Object> uploadFotoFundo(@RequestParam("images") MultipartFile[] files, @RequestParam UUID id) throws IOException {
        if (files.length == 0) {
            return ResponseEntity.badRequest().body("No files were provided.");
        }

        try {
            List<String> fileUrls = azureBlobService.uploadFotoPerfil(files, id);
            return ResponseEntity.ok(fileUrls);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error uploading files.");
        }

    }
}
