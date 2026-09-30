package com.aravi.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddBusDetailsRequestDTO {
	
	
	private String regestrationNo;
	
	private String driverName;
	
	private int seatingCapacity;
	
	private String busCategory;
	

	
	
}
