package com.example.applicationservice.remote.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    private String id;
    private int userId;
    private String firstName;
    private String lastName;
    private String middleName; //**
    private String preferredName; //**
    private String cellPhone;
    private String alternatePhone; //**
    private String email;
    private String ssn;
    private String dob;
    private String gender; //**
    private String startDate;
    private String endDate;
    private String driversLicense;
    private String driversLicenseExpiration;
    private String houseID;
    private List<VisaStatus> visaStatus;
    private List<Contact> contact;
    private List<Address> currentAddress;
    private List<PersonalDocument> personalDocument;
}