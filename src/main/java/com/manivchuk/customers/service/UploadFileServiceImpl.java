package com.manivchuk.customers.service;

import com.manivchuk.customers.controller.CustomerController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Map;
import java.util.UUID;

@Service
public class UploadFileServiceImpl implements UploadFileService {

    private final Logger log = LoggerFactory.getLogger(UploadFileServiceImpl.class);

    private final static String FOLDER_UPLOAD = "uploads";

    @Override
    public Resource load(String fileName) throws MalformedURLException {

        Path filePath = getPath(fileName);
        log.info(filePath.toString());

        Resource resource = new UrlResource(filePath.toUri());

        if (!resource.exists() && !resource.isReadable()) {
            filePath = Paths.get("src/main/resources/static/images").resolve("not_interested.png").toAbsolutePath();

            resource = new UrlResource(filePath.toUri());

            log.error("Error load image: " + fileName);
        }

        return resource;
    }

    @Override
    public String copy(MultipartFile file) throws IOException {
        String archiveName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename().replace(" ", "");
        Path rootArchive = getPath(archiveName);
        log.info(rootArchive.toString());

        Files.copy(file.getInputStream(), rootArchive, StandardCopyOption.REPLACE_EXISTING);

        return archiveName;
    }

    @Override
    public boolean remove(String fileName) {
        if(fileName != null && fileName.length() > 0){
            Path previousPathPhoto = Paths.get("uploads").resolve(fileName).toAbsolutePath();
            File previousFilePhoto = previousPathPhoto.toFile();
            if(previousFilePhoto.exists() && previousFilePhoto.canRead()){
                previousFilePhoto.delete();
                return true;
            }
        }
        return false;
    }

    @Override
    public Path getPath(String fileName) {
        return Paths.get(FOLDER_UPLOAD).resolve(fileName).toAbsolutePath();
    }
}
