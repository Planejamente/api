package org.planejamente.planejamente.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.planejamente.planejamente.dto.dtoConsultar.PsicologoDtoExibir;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.util.Map;
import java.util.UUID;

@Service
public class CloudnaryService {


    private final PsicologoService service;

    public CloudnaryService(PsicologoService service){
        this.service = service;
    }

    Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", "",
            "api_key", "",
            "api_secret", ""
    ));

    public String uploadFotoPerfil(MultipartFile file, UUID idPsicologo) {
        try {
            // Validando se o arquivo foi enviado
            if (file == null || file.isEmpty()) {
                throw new IllegalArgumentException("O arquivo enviado está vazio ou nulo");
            }

            // Convertendo MultipartFile para File
            File tempFile = File.createTempFile("temp", file.getOriginalFilename());
            file.transferTo(tempFile);

            try {
                // Fazendo o upload para o Cloudinary
                Map uploadResult = cloudinary.uploader().upload(tempFile, ObjectUtils.emptyMap());

                // Obtendo a URL segura da imagem
                String secureUrl = (String) uploadResult.get("secure_url");

                if (secureUrl == null || secureUrl.isEmpty()) {
                    throw new RuntimeException("O upload para o Cloudinary não retornou uma URL válida");
                }

                // Verificando se o psicólogo existe
                PsicologoDtoExibir psicologo = this.service.buscarPorId(idPsicologo);
                if (psicologo == null) {
                    throw new RuntimeException("Psicólogo não encontrado para o ID fornecido");
                }

                // Salvando a URL da foto de perfil
                service.salvaFotoDePerfil(secureUrl, idPsicologo);

                return secureUrl; // Retornando a URL da foto
            } finally {
                // Limpando o arquivo temporário
                if (tempFile.exists()) {
                    tempFile.delete();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao fazer upload", e);
        }
    }

    public String uploadFotoFundo(MultipartFile file, UUID idPsicologo) {
        try {
            // Validando se o arquivo foi enviado
            if (file == null || file.isEmpty()) {
                throw new IllegalArgumentException("O arquivo enviado está vazio ou nulo");
            }

            // Convertendo MultipartFile para File
            File tempFile = File.createTempFile("temp", file.getOriginalFilename());
            file.transferTo(tempFile);

            try {
                // Fazendo o upload para o Cloudinary
                Map uploadResult = cloudinary.uploader().upload(tempFile, ObjectUtils.emptyMap());

                // Obtendo a URL segura da imagem
                String secureUrl = (String) uploadResult.get("secure_url");

                if (secureUrl == null || secureUrl.isEmpty()) {
                    throw new RuntimeException("O upload para o Cloudinary não retornou uma URL válida");
                }

                // Verificando se o psicólogo existe
                PsicologoDtoExibir psicologo = this.service.buscarPorId(idPsicologo);
                if (psicologo == null) {
                    throw new RuntimeException("Psicólogo não encontrado para o ID fornecido");
                }

                // Salvando a URL da foto de perfil
                service.salvaFotoDeFundo(secureUrl, idPsicologo);

                return secureUrl; // Retornando a URL da foto
            } finally {
                // Limpando o arquivo temporário
                if (tempFile.exists()) {
                    tempFile.delete();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao fazer upload", e);
        }
    }


}
