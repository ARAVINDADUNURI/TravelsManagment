package com.aravi.dto;

import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBusDetailsRequestDTO {
	
	private String startingPoint;
	
	private String destination;
	
	private String regestrationNumber;
	
	private String driverName;
	
	private LocalTime departureTime ;
	
	private LocalTime arrivalTime;
	
	private String busCategory;

}
