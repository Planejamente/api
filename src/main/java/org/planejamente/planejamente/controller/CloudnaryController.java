package org.planejamente.planejamente.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.planejamente.planejamente.service.CloudnaryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/cloudnary")
@SecurityRequirement(name = "auth-api")
@CrossOrigin
public class CloudnaryController {
    private final CloudnaryService cloudnaryService;

    public CloudnaryController(CloudnaryService cloudnaryService) {
        this.cloudnaryService = cloudnaryService;
    }

    // src/main/java/org/planejamente/planejamente/controller/CloudnaryController.java
    @PostMapping("/foto-de-perfil")
    public ResponseEntity<Object> uploadFotoPerfil(@RequestParam("images") MultipartFile files, @RequestParam UUID id) throws IOException {
        if (files == null) {
            return ResponseEntity.badRequest().body("No files were provided.");
        }

        try {
            String fileUrls = cloudnaryService.uploadFotoPerfil(files, id);
            return ResponseEntity.ok(fileUrls);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error uploading files.");
        }
    }

    @PostMapping("/foto-de-fundo")
    public ResponseEntity<Object> uploadFotoFundo(@RequestParam("images") MultipartFile files, @RequestParam UUID id) throws IOException {
        if (files == null) {
            return ResponseEntity.badRequest().body("No files were provided.");
        }

        try {
            String fileUrls = cloudnaryService.uploadFotoFundo(files, id);
            return ResponseEntity.ok(fileUrls);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error uploading files.");
        }
    }
}

