package com.example.applicationservice.controller;

import com.example.applicationservice.domain.ApplicationWorkFlow;
import com.example.applicationservice.domain.EmailDetails;
import com.example.applicationservice.remote.RemoteEmployeeService;
import com.example.applicationservice.remote.request.Employee;
import com.example.applicationservice.request.ApplicationRequest;
import com.example.applicationservice.service.ApplicationWorkFlowService;
import com.example.applicationservice.service.RabbitMQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeApplicationController {
    private static final String STATUS_OPEN = "OPEN";
    private static final String STATUS_COMPLETED = "COMPLETED";
    private static final String STATUS_REJECTED = "REJECTED";
    private static final String APPROVED_SUBJECT = "Welcome to BeaconFire!";
    private static final String REJECTED_SUBJECT = "Application Rejected.";

    private static final String APPROVED_MSG_BODY= "Congratulations, welcome to BeaconFire!";
    private static final String REJECTED_MSG_BODY= "Unfortunately, your application has been rejected.";

    private ApplicationWorkFlowService applicationWorkFlowService;

    private RemoteEmployeeService remoteEmployeeService;

    private RabbitMQService rabbitMQService;

    @Autowired
    public EmployeeApplicationController(ApplicationWorkFlowService applicationWorkFlowService, RemoteEmployeeService remoteEmployeeService, RabbitMQService rabbitMQService) {
        this.applicationWorkFlowService = applicationWorkFlowService;
        this.remoteEmployeeService = remoteEmployeeService;
        this.rabbitMQService = rabbitMQService;
    }

    @PostMapping("/application")
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    public ResponseEntity<ApplicationWorkFlow> createApplication(@RequestBody ApplicationRequest applicationRequest) {

        // TODO: Make rest call to Employee service to save Employee information

        // TODO: Employee POST call succeed, create ApplicationWorkFlow using the employeeId in response,
        //  if Employee POST call return status code != 200, then throw exception
        //  and let exception handler to deal with the response
        Employee employee = applicationRequest.getEmployee();
        Employee createdEmployee = remoteEmployeeService.createEmployee(employee).getBody();

        String employeeId = createdEmployee.getId();
        //  TODO: If create ApplicationWorkFlow succeed, return created ApplicationWorkFlow
        //   if failed, let exception handler to deal with the response
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String createdTime = dtf.format(now);

        ApplicationWorkFlow applicationWorkFlow = new ApplicationWorkFlow();
        applicationWorkFlow.setEmployeeId(employeeId);
        applicationWorkFlow.setCreateDate(createdTime);
        applicationWorkFlow.setLastModificationDate(createdTime);
        applicationWorkFlow.setStatus(STATUS_OPEN);
        ApplicationWorkFlow createdApplicationWorkFlow = applicationWorkFlowService.createApplication(applicationWorkFlow);
        return ResponseEntity.ok(createdApplicationWorkFlow);
    }

    @PutMapping("/application/status")
    @PreAuthorize("hasAnyAuthority('HR')")
    public ResponseEntity<ApplicationWorkFlow> updateApplicationStatus(@RequestParam int id, @RequestParam String status) {

        //  TODO: If update ApplicationWorkFlow succeed, return updated ApplicationWorkFlow
        //   if failed, let exception handler to deal with the response
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String updateTime = dtf.format(now);

        ApplicationWorkFlow fetchedApplicationWorkFlow = applicationWorkFlowService.getApplicationById(id);
        fetchedApplicationWorkFlow.setStatus(status);
        fetchedApplicationWorkFlow.setLastModificationDate(updateTime);
        ApplicationWorkFlow updatedApplicationWorkFlow = applicationWorkFlowService.updateApplication(fetchedApplicationWorkFlow);

        Employee fetchedEmployee = remoteEmployeeService.getEmployee(updatedApplicationWorkFlow.getEmployeeId()).getBody();
        String subject = STATUS_COMPLETED.equals(status) ? APPROVED_SUBJECT : REJECTED_SUBJECT;
        String msgBody = STATUS_COMPLETED.equals(status) ? APPROVED_MSG_BODY : REJECTED_MSG_BODY;
        rabbitMQService.send(EmailDetails.builder().recipient(fetchedEmployee.getEmail()).msgBody(msgBody).subject(subject).build());

        return ResponseEntity.ok(updatedApplicationWorkFlow);
    }

    @PutMapping("/application/comment")
    @PreAuthorize("hasAnyAuthority('EMPLOYEE','HR')")
    public ResponseEntity<ApplicationWorkFlow> updateApplicationComment(@RequestBody ApplicationWorkFlow applicationWorkFlow) {

        //  TODO: If update ApplicationWorkFlow succeed, return updated ApplicationWorkFlow
        //   if failed, let exception handler to deal with the response
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String updateTime = dtf.format(now);

        ApplicationWorkFlow fetchedApplicationWorkFlow = applicationWorkFlowService.getApplicationById(applicationWorkFlow.getId());
        fetchedApplicationWorkFlow.setComment(applicationWorkFlow.getComment());
        fetchedApplicationWorkFlow.setLastModificationDate(updateTime);
        ApplicationWorkFlow updatedApplicationWorkFlow = applicationWorkFlowService.updateApplication(fetchedApplicationWorkFlow);
        return ResponseEntity.ok(updatedApplicationWorkFlow);
    }

    @GetMapping("/application/{id}")
    @PreAuthorize("hasAnyAuthority('EMPLOYEE','HR')")
    public ResponseEntity<ApplicationWorkFlow> getApplicationById(@PathVariable int id) throws Exception {

        //  TODO: If Get ApplicationWorkFlow succeed, return fetched ApplicationWorkFlow
        //   if failed, let exception handler to deal with the response
        ApplicationWorkFlow applicationWorkFlow =  applicationWorkFlowService.getApplicationById(id);
        if (applicationWorkFlow == null) {
            throw new Exception("applicationWorkFlow not found!");
        }
        return ResponseEntity.ok(applicationWorkFlow);
    }

    @GetMapping("/applications")
    @PreAuthorize("hasAuthority('HR')")
    public ResponseEntity<List<ApplicationWorkFlow>> getApplications() {

        //  TODO: If Get ApplicationWorkFlow succeed, return fetched list of ApplicationWorkFlow
        //   if failed, let exception handler to deal with the response

        List<ApplicationWorkFlow> applicationWorkFlows = applicationWorkFlowService.getAllApplications();

        return ResponseEntity.ok(applicationWorkFlows);
    }
}
