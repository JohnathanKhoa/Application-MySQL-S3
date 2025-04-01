package com.example.applicationservice.dao;

import com.example.applicationservice.domain.ApplicationWorkFlow;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ApplicationWorkFlowDaoImpl implements ApplicationWorkFlowDao {

    SessionFactory sessionFactory;

    @Autowired
    public ApplicationWorkFlowDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public ApplicationWorkFlow getApplicationById(int id) {
        Session session;
        try {
            session = sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }

        Transaction transaction = session.beginTransaction();

        ApplicationWorkFlow applicationWorkFlow =session.get(ApplicationWorkFlow.class, id);

        transaction.commit();

        return applicationWorkFlow;
    }

    @Override
    public ApplicationWorkFlow createApplication(ApplicationWorkFlow applicationWorkFlow) {
        Session session;
        try {
            session = sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }

        Transaction transaction = session.beginTransaction();

        int id = (int) session.save(applicationWorkFlow);

        transaction.commit();

        return getApplicationById(id);
    }

    @Override
    public List<ApplicationWorkFlow> getAllApplications() {
        Session session;

        try {
            session = sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }

        Transaction transaction = session.beginTransaction();

        List<ApplicationWorkFlow> applications = session.createQuery("SELECT a FROM ApplicationWorkFlow a", ApplicationWorkFlow.class).getResultList();

        transaction.commit();

        return applications;
    }

    @Override
    public ApplicationWorkFlow updateApplication(ApplicationWorkFlow applicationWorkFlow) {
        Session session;
        try {
            session = sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }

        Transaction transaction = session.beginTransaction();

        session.update(applicationWorkFlow);

        transaction.commit();

        return getApplicationById(applicationWorkFlow.getId());
    }
}
