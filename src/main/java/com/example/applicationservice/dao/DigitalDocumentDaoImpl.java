package com.example.applicationservice.dao;

import com.example.applicationservice.domain.ApplicationWorkFlow;
import com.example.applicationservice.domain.DigitalDocument;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DigitalDocumentDaoImpl implements DigitalDocumentDao{
    SessionFactory sessionFactory;

    @Autowired
    public DigitalDocumentDaoImpl (SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public DigitalDocument getDigitalDocumentById(int id) {
        Session session;
        try {
            session = sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }

        Transaction transaction = session.beginTransaction();

        DigitalDocument digitalDocument =session.get(DigitalDocument.class, id);

        transaction.commit();

        return digitalDocument;
    }

    @Override
    public DigitalDocument createDigitalDocument(DigitalDocument digitalDocument) {
        Session session;
        try {
            session = sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }

        Transaction transaction = session.beginTransaction();

        int id = (int) session.save(digitalDocument);

        transaction.commit();

        return getDigitalDocumentById(id);
    }

    @Override
    public List<DigitalDocument> getAllDigitalDocuments() {
        Session session;

        try {
            session = sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }

        Transaction transaction = session.beginTransaction();

        List<DigitalDocument> digitalDocuments = session.createQuery("SELECT a FROM DigitalDocument a", DigitalDocument.class).getResultList();

        transaction.commit();

        return digitalDocuments;
    }

    @Override
    public DigitalDocument updateDigitalDocumentPath(DigitalDocument digitalDocument) {
        Session session;
        try {
            session = sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }

        Transaction transaction = session.beginTransaction();

        DigitalDocument loadedDigitalDocument = session.get(DigitalDocument.class, digitalDocument.getId());

        if (loadedDigitalDocument == null) {
            //TODO: Need to update to throw exception that digitalDocument   not found
            return loadedDigitalDocument;
        }
        loadedDigitalDocument.setPath(digitalDocument.getPath());

        session.update(loadedDigitalDocument);

        transaction.commit();

        return loadedDigitalDocument;
    }

}
