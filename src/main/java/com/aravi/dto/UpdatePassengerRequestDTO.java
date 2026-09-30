package com.aravi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor@NoArgsConstructor
public class UpdatePassengerRequestDTO {

	private int passengerId;
	
	private String passengerName;
	
	private double age;
	
	private String gender;
	
	private long mobileNumber;
	
	private String email;
}
