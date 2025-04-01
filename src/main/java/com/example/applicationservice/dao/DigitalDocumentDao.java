package com.example.applicationservice.dao;

import com.example.applicationservice.domain.DigitalDocument;

import java.util.List;

public interface DigitalDocumentDao {

    DigitalDocument getDigitalDocumentById(int id);

    DigitalDocument createDigitalDocument(DigitalDocument digitalDocument);

    List<DigitalDocument> getAllDigitalDocuments();

    DigitalDocument updateDigitalDocumentPath(DigitalDocument digitalDocument);
}
