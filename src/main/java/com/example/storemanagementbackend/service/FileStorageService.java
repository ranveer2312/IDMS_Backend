package com.example.storemanagementbackend.service;
 
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Path;
 
public interface FileStorageService {
    String storeFile(MultipartFile file, String employeeId, String docType);
    Resource loadFileAsResource(String employeeId, String fileName);
    Path getFileStorageLocation();
}
 