package com.example.applicationservice.remote.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VisaStatus {

	private String id;
	private String visaType;
	private boolean activeFlag;
	private String startDate;
	private String endDate;
	private String lastModificationDate;

}
