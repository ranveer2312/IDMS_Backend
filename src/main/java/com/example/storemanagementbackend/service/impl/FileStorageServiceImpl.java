package com.example.storemanagementbackend.service.impl;
 
import com.example.storemanagementbackend.service.FileStorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
 
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.*;
 
@Service
public class FileStorageServiceImpl implements FileStorageService {
 
    private final Path fileStorageLocation;
 
    public FileStorageServiceImpl(@Value("${file.upload-dir}") String uploadDir) {
        this.fileStorageLocation = Paths.get(uploadDir).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new RuntimeException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }
 
    @Override
    public String storeFile(MultipartFile file, String employeeId, String docType) {
        String originalFileName = StringUtils.cleanPath(file.getOriginalFilename());
        String fileName = docType.toLowerCase() + "_" + originalFileName;
 
        try {
            Path employeeDir = this.fileStorageLocation.resolve("employee_" + employeeId);
            Files.createDirectories(employeeDir);
            Path targetLocation = employeeDir.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return "/api/hr/download/" + employeeId + "/" + docType;
        } catch (IOException ex) {
            throw new RuntimeException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }
 
    @Override
    public Resource loadFileAsResource(String employeeId, String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve("employee_" + employeeId).resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new RuntimeException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new RuntimeException("File not found " + fileName, ex);
        }
    }
}
 
 
 