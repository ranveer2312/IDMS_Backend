package com.example.storemanagementbackend.controller;
 
import com.example.storemanagementbackend.dto.EmployeeDocumentDTO;
import com.example.storemanagementbackend.model.EmployeeDocument;
import com.example.storemanagementbackend.repository.EmploeeDocumentRepository;
import com.example.storemanagementbackend.service.FileStorageService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
 
import java.io.IOException;
import java.util.List;
import java.util.Optional;
 
@RestController
@RequestMapping("/api/employee-documents")
public class EmployeeDocumentController {
 
    @Autowired
    private FileStorageService fileStorageService;
 
    @Autowired
    private EmploeeDocumentRepository employeeDocumentRepository;
 
    @GetMapping
    public List<EmployeeDocument> getAllDocuments() {
        return employeeDocumentRepository.findAll();
    }
 
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDocument> getDocumentById(@PathVariable Long id) {
        return employeeDocumentRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
 
    @PostMapping
    public EmployeeDocument createDocument(@RequestBody EmployeeDocument document) {
        return employeeDocumentRepository.save(document);
    }
 
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDocument> updateDocument(@PathVariable Long id, @RequestBody EmployeeDocument document) {
        return employeeDocumentRepository.findById(id)
                .map(existingDocument -> {
                    document.setId(id);
                    return ResponseEntity.ok(employeeDocumentRepository.save(document));
                })
                .orElse(ResponseEntity.notFound().build());
    }
 
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDocument(@PathVariable Long id) {
        return employeeDocumentRepository.findById(id)
                .map(document -> {
                    employeeDocumentRepository.delete(document);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
 
    @PostMapping("/upload/{docType}/{employeeId}")
    public ResponseEntity<EmployeeDocumentDTO> uploadDocument(
            @PathVariable String docType,
            @PathVariable String employeeId,
            @RequestParam("file") MultipartFile file) {
 
        String downloadUri = fileStorageService.storeFile(file, employeeId, docType);
 
        EmployeeDocument document = new EmployeeDocument();
        document.setEmployeeId(employeeId);
        document.setDocumentType(docType.toUpperCase());
        document.setFileName(file.getOriginalFilename());
        document.setFileType(file.getContentType());
        document.setSize(file.getSize());
        document.setFileDownloadUri(downloadUri);
 
        EmployeeDocument savedDocument = employeeDocumentRepository.save(document);
 
        EmployeeDocumentDTO dto = new EmployeeDocumentDTO(
                savedDocument.getEmployeeId(),
                savedDocument.getDocumentType(),
                savedDocument.getFileName(),
                savedDocument.getFileDownloadUri(),
                savedDocument.getFileType(),
                savedDocument.getSize()
        );
 
        return ResponseEntity.ok(dto);
    }
 
    @GetMapping("/download/{employeeId}/{docType}")
    public ResponseEntity<Resource> downloadDocument(
            @PathVariable String employeeId,
            @PathVariable String docType,
            HttpServletRequest request) {
 
        String expectedFilePrefix = docType.toLowerCase();
 
        Optional<EmployeeDocument> fileEntry = employeeDocumentRepository
                .findByEmployeeIdAndDocumentType(employeeId, docType.toUpperCase())
                .stream()
                .findFirst();
 
        if (fileEntry.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
 
        String fullFileName = expectedFilePrefix + "_" + fileEntry.get().getFileName();
        Resource resource = fileStorageService.loadFileAsResource(employeeId, fullFileName);
 
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            contentType = "application/octet-stream";
        }
 
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
 
 