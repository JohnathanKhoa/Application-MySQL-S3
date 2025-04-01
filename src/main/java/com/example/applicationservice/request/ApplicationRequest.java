package com.example.applicationservice.request;

import com.example.applicationservice.remote.request.Employee;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
// Request object used for Employee Application Service endpoints
public class ApplicationRequest {
    // This is depends on Employee Service POST Request object for create Employee

    private int id;
    private String status;
    Employee employee;
}
