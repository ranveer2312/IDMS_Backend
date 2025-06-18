package com.example.storemanagementbackend.controller;
 
import com.example.storemanagementbackend.dto.EmployeeDocumentDTO;
import com.example.storemanagementbackend.model.EmployeeDocument;
import com.example.storemanagementbackend.repository.EmployeeDocumentRepository;
import com.example.storemanagementbackend.service.FileStorageService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
 
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
 
@RestController
@RequestMapping("/api/hr")
@CrossOrigin(origins = "http://localhost:3000")
public class EmployeeDocumentController {
 
    @Autowired
    private FileStorageService fileStorageService;
 
    @Autowired
    private EmployeeDocumentRepository documentRepository;

    // Get all documents
    @GetMapping("/documents")
    public ResponseEntity<List<EmployeeDocumentDTO>> getAllDocuments() {
        List<EmployeeDocument> documents = documentRepository.findAll();
        List<EmployeeDocumentDTO> documentDTOs = documents.stream()
            .map(doc -> new EmployeeDocumentDTO(
                doc.getId(),
                doc.getEmployeeId(),
                doc.getDocumentType(),
                doc.getFileName(),
                doc.getFileDownloadUri(),
                doc.getFileType(),
                doc.getSize()
            ))
            .collect(Collectors.toList());
        return new ResponseEntity<>(documentDTOs, HttpStatus.OK);
    }

    // Get documents by employee ID
    @GetMapping("/documents/employee/{employeeId}")
    public ResponseEntity<List<EmployeeDocumentDTO>> getDocumentsByEmployeeId(@PathVariable String employeeId) {
        List<EmployeeDocument> documents = documentRepository.findByEmployeeId(employeeId);
        List<EmployeeDocumentDTO> documentDTOs = documents.stream()
            .map(doc -> new EmployeeDocumentDTO(
                doc.getId(),
                doc.getEmployeeId(),
                doc.getDocumentType(),
                doc.getFileName(),
                doc.getFileDownloadUri(),
                doc.getFileType(),
                doc.getSize()
            ))
            .collect(Collectors.toList());
        return new ResponseEntity<>(documentDTOs, HttpStatus.OK);
    }

    // Get documents by document type
    @GetMapping("/documents/type/{documentType}")
    public ResponseEntity<List<EmployeeDocumentDTO>> getDocumentsByType(@PathVariable String documentType) {
        List<EmployeeDocument> documents = documentRepository.findByDocumentType(documentType.toUpperCase());
        List<EmployeeDocumentDTO> documentDTOs = documents.stream()
            .map(doc -> new EmployeeDocumentDTO(
                doc.getId(),
                doc.getEmployeeId(),
                doc.getDocumentType(),
                doc.getFileName(),
                doc.getFileDownloadUri(),
                doc.getFileType(),
                doc.getSize()
            ))
            .collect(Collectors.toList());
        return new ResponseEntity<>(documentDTOs, HttpStatus.OK);
    }

    // Get document by ID
    @GetMapping("/documents/{id}")
    public ResponseEntity<EmployeeDocumentDTO> getDocumentById(@PathVariable Long id) {
        Optional<EmployeeDocument> document = documentRepository.findById(id);
        if (document.isPresent()) {
            EmployeeDocument doc = document.get();
            EmployeeDocumentDTO dto = new EmployeeDocumentDTO(
                doc.getId(),
                doc.getEmployeeId(),
                doc.getDocumentType(),
                doc.getFileName(),
                doc.getFileDownloadUri(),
                doc.getFileType(),
                doc.getSize()
            );
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
 
        document = documentRepository.save(document);
 
        EmployeeDocumentDTO dto = new EmployeeDocumentDTO(
                document.getId(),
                document.getEmployeeId(),
                document.getDocumentType(),
                document.getFileName(),
                document.getFileDownloadUri(),
                document.getFileType(),
                document.getSize()
        );
 
        return ResponseEntity.ok(dto);
    }
 
    @GetMapping("/download/{employeeId}/{docType}")
    public ResponseEntity<Resource> downloadDocument(
            @PathVariable String employeeId,
            @PathVariable String docType,
            HttpServletRequest request) {
 
        String expectedFilePrefix = docType.toLowerCase();
 
        Optional<EmployeeDocument> fileEntry = documentRepository
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

    @DeleteMapping("/documents/{id}")
    public ResponseEntity<HttpStatus> deleteDocument(@PathVariable Long id) {
        Optional<EmployeeDocument> document = documentRepository.findById(id);
        if (document.isPresent()) {
            EmployeeDocument doc = document.get();
            // Delete the physical file
            try {
                String fileName = doc.getDocumentType().toLowerCase() + "_" + doc.getFileName();
                Path filePath = fileStorageService.getFileStorageLocation().resolve("employee_" + doc.getEmployeeId()).resolve(fileName);
                Files.deleteIfExists(filePath);
            } catch (IOException e) {
                // Log the error but continue with database deletion
                e.printStackTrace();
            }
            // Delete from database
            documentRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
 
 