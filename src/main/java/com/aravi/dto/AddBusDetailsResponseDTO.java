package com.aravi.dto;

import java.time.LocalDateTime;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddBusDetailsResponseDTO {
	
	private int busId;
		
	private String regestrationNo;
	
	private String driverName;
	
	private int seatingCapacity;
	
	private String busCategory;
	
	

}
