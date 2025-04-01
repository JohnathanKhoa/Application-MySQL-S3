package com.example.applicationservice.remote.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonalDocument {

	private String id;
	private String path;
	private String title;
	private String comment;
	private String createDate;

}
