package com.manivchuk.customers.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;

public interface UploadFileService {

    Resource load(String fileName) throws MalformedURLException;

    String copy(MultipartFile file) throws IOException;

    boolean remove(String fileName);

    Path getPath(String fileName);
}
