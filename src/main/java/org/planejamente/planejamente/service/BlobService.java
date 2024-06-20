package org.planejamente.planejamente.service;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.models.BlobItem;
import org.planejamente.planejamente.entity.usuario.Psicologo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class BlobService {
    private final BlobContainerClient blobContainerClient;

    private final PsicologoService service;

    public BlobService(BlobContainerClient blobContainerClient, PsicologoService service) {
        this.blobContainerClient = blobContainerClient;
        this.service = service;
    }

    public List<String> uploadFotoPerfil(MultipartFile[] files, UUID idPsicologo) throws IOException {
        List<String> fileUrls = new ArrayList<>();

        for (MultipartFile file : files) {
            String blobName = file.getOriginalFilename() + idPsicologo;
            BlobClient blobClient = blobContainerClient.getBlobClient(blobName);
            blobClient.upload(file.getInputStream(), file.getSize(), true);
            String fileUrl = blobClient.getBlobUrl();
            fileUrls.add(fileUrl);
        }

        Psicologo psicologo = this.service.buscarPorId(idPsicologo);

        if(psicologo == null){
            return null;
        }

        service.salvaFotoDePerfil(fileUrls.get(0), idPsicologo);

        return fileUrls;
    }

    public byte[] downloadFile(String fileName) {
        BlobClient blobClient = blobContainerClient.getBlobClient(fileName);
        return blobClient.downloadContent().toBytes();
    }

    public List<String> listFiles() {
        List<String> fileList = new ArrayList<>();
        for (BlobItem blobItem : blobContainerClient.listBlobs()) {
            fileList.add(blobItem.getName());
        }
        return fileList;
    }

    public List<String> uploadFotoFundo(MultipartFile[] files, UUID idPsicologo) throws IOException {
        List<String> fileUrls = new ArrayList<>();

        for (MultipartFile file : files) {
            String blobName = file.getOriginalFilename() + idPsicologo;
            BlobClient blobClient = blobContainerClient.getBlobClient(blobName);
            blobClient.upload(file.getInputStream(), file.getSize(), true);
            String fileUrl = blobClient.getBlobUrl();
            fileUrls.add(fileUrl);
        }

        Psicologo psicologo = this.service.buscarPorId(idPsicologo);

        if(psicologo == null){
            return null;
        }

        service.salvaFotoDeFundo(fileUrls.get(0), idPsicologo);

        return fileUrls;
    }
}
