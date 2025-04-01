package com.example.applicationservice.controller;


import com.example.applicationservice.domain.DigitalDocument;
import com.example.applicationservice.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class StorageController {

    private StorageService storageService;

    @Autowired
    public StorageController(StorageService storageService) {
        this.storageService = storageService;
    }

    @PostMapping("/file")
    @PreAuthorize("hasAnyAuthority('EMPLOYEE','HR')")
    public ResponseEntity<DigitalDocument> uploadFile(@RequestParam(value = "file") MultipartFile file,
                                                      @RequestParam("type") String type,
                                                      @RequestParam("description") String description,
                                                      @RequestParam("title") String title,
                                                      @RequestParam("isRequired") Boolean isRequired) {

        DigitalDocument savedDigitalDocument = storageService.uploadFile(file, type, description, title, isRequired);

        return new ResponseEntity<>(savedDigitalDocument, HttpStatus.OK);
    }

    @GetMapping("/file/{fileName}")
    @PreAuthorize("hasAnyAuthority('EMPLOYEE','HR')")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String fileName) {
        byte[] data = storageService.downloadFile(fileName);
        ByteArrayResource resource = new ByteArrayResource(data);
        return ResponseEntity
                .ok()
                .contentLength(data.length)
                .header("Content-type", "application/octet-stream")
                .header("Content-disposition", "attachment; filename=\"" + fileName + "\"")
                .body(resource);
    }

    @DeleteMapping("/delete/{fileName}")
    @PreAuthorize("hasAnyAuthority('EMPLOYEE','HR')")
    public ResponseEntity<String> deleteFile(@PathVariable String fileName) {
        return new ResponseEntity<>(storageService.deleteFile(fileName), HttpStatus.OK);
    }
}