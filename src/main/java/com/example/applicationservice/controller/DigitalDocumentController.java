package com.example.applicationservice.controller;

import com.example.applicationservice.domain.DigitalDocument;
import com.example.applicationservice.service.DigitalDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DigitalDocumentController {
    private static final String STATUS_OPEN = "OPEN";

    private DigitalDocumentService digitalDocumentService;

    @Autowired
    public DigitalDocumentController(DigitalDocumentService digitalDocumentService) {
        this.digitalDocumentService = digitalDocumentService;
    }

    @GetMapping("/digital-documents")
    @PreAuthorize("hasAnyAuthority('EMPLOYEE','HR')")
    public ResponseEntity<List<DigitalDocument>> getDigitalDocuments() {

        //  TODO: If Get DigitalDocuments succeed, return the list;
        //   if failed, let exception handler to deal with the response
        List<DigitalDocument> digitalDocuments = digitalDocumentService.getAllDigitalDocuments();

        return ResponseEntity.ok(digitalDocuments);
    }


    @GetMapping("/digital-document/{id}")
    @PreAuthorize("hasAnyAuthority('EMPLOYEE','HR')")
    public ResponseEntity<DigitalDocument> getDigitalDocumentById(@PathVariable int id) throws Exception {
        DigitalDocument digitalDocument = digitalDocumentService.getDigitalDocumentById(id);
        if (digitalDocument == null) {
            throw new Exception("cannot find this digital document");
        }

        return ResponseEntity.ok(digitalDocument);
    }


    @PostMapping("/digital-document")
    @PreAuthorize("hasAnyAuthority('EMPLOYEE','HR')")
    public ResponseEntity<DigitalDocument> createDigitalDocument(@RequestBody DigitalDocument digitalDocument) {

        DigitalDocument createdDigitalDocument = digitalDocumentService.createDigitalDocument(digitalDocument);

        return ResponseEntity.ok(createdDigitalDocument);
    }

    @PutMapping("/digital-document")
    @PreAuthorize("hasAnyAuthority('EMPLOYEE','HR')")
    public ResponseEntity<DigitalDocument> updateDigitalDocumentPath(@RequestBody DigitalDocument digitalDocument) {

        DigitalDocument updatedDigitalDocument = digitalDocumentService.updateDigitalDocumentPath(digitalDocument);

        return ResponseEntity.ok(updatedDigitalDocument);
    }
}
