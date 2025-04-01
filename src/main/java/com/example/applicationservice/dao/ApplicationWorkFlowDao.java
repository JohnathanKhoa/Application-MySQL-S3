package com.example.applicationservice.dao;

import com.example.applicationservice.domain.ApplicationWorkFlow;

import java.util.List;

public interface ApplicationWorkFlowDao {

    ApplicationWorkFlow getApplicationById(int id);

    ApplicationWorkFlow createApplication(ApplicationWorkFlow applicationWorkFlow);

    List<ApplicationWorkFlow> getAllApplications();

    ApplicationWorkFlow updateApplication(ApplicationWorkFlow applicationWorkFlow);

}
