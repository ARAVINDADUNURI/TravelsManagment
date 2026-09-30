package com.aravi.dto;

import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddScheduleResponseDTO {
	
	private int scheduleId;
	
	private int busId;
	
	private String startingPoint;
	
	private String destination;
	
	private LocalTime departureTime;
	
	private LocalTime arrivalTime;
	
	private double fare;
	
	private int availableSeats;
	
	private String travelName;

}
