package com.example.applicationservice.remote;

import com.example.applicationservice.remote.request.Employee;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("employee-service")
public interface RemoteEmployeeService {

    @PostMapping("employee-service/api/employee")
    ResponseEntity<Employee> createEmployee(Employee employee);

    @GetMapping("employee-service/api/employee/{id}")
    ResponseEntity<Employee> getEmployee(@PathVariable("id") String id);
}