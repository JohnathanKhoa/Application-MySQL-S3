package com.example.applicationservice.remote.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Contact {

	private String id;
	private String firstName;
	private String lastName;
	private String cellPhone;
	private String alternatePhone;
	private String email;
	private String relationship;
	private String type;

}
