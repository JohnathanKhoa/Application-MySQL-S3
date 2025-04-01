package com.example.applicationservice.service;

import com.example.applicationservice.dao.DigitalDocumentDao;
import com.example.applicationservice.domain.DigitalDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DigitalDocumentService {
    private DigitalDocumentDao digitalDocumentDao;

    @Autowired
    public DigitalDocumentService(DigitalDocumentDao digitalDocumentDao) {
        this.digitalDocumentDao = digitalDocumentDao;
    }

    public DigitalDocument getDigitalDocumentById (int id) {
        return digitalDocumentDao.getDigitalDocumentById(id);
    }

    public DigitalDocument createDigitalDocument(DigitalDocument digitalDocument) {
        return digitalDocumentDao.createDigitalDocument(digitalDocument);
    }

    public List<DigitalDocument> getAllDigitalDocuments () {
        return digitalDocumentDao.getAllDigitalDocuments();
    }

    public DigitalDocument updateDigitalDocumentPath(DigitalDocument digitalDocument) {
        return digitalDocumentDao.updateDigitalDocumentPath(digitalDocument);
    }

}
