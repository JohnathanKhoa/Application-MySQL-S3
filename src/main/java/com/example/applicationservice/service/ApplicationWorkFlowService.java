package com.example.applicationservice.service;

import com.example.applicationservice.dao.ApplicationWorkFlowDao;
import com.example.applicationservice.domain.ApplicationWorkFlow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationWorkFlowService {

    private ApplicationWorkFlowDao applicationWorkFlowDao;

    @Autowired
    public ApplicationWorkFlowService(@Qualifier("applicationWorkFlowDaoImpl") ApplicationWorkFlowDao applicationWorkFlowDao) {
        this.applicationWorkFlowDao = applicationWorkFlowDao;
    }

    public ApplicationWorkFlow getApplicationById(int id) {
        return applicationWorkFlowDao.getApplicationById(id);
    }

    public ApplicationWorkFlow createApplication(ApplicationWorkFlow applicationWorkFlow) {
        return applicationWorkFlowDao.createApplication(applicationWorkFlow);
    }

    public List<ApplicationWorkFlow> getAllApplications() {
        return applicationWorkFlowDao.getAllApplications();
    }

    public ApplicationWorkFlow updateApplication(ApplicationWorkFlow applicationWorkFlow) {
        return applicationWorkFlowDao.updateApplication(applicationWorkFlow);
    }
}
