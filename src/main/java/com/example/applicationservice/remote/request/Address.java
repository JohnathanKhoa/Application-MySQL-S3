package com.example.applicationservice.remote.request;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {
	private String id;
	private String addressLine1;
	private String addressLine2;
	private String city;
	private String state;
	private int zipCode;
}
