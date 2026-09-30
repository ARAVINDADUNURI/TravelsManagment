package com.aravi.dto;

import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingsResponseDTO {
	
	private int bookingId;
	
	private String passengerName;
	
	private String travelName;
	
	private String startingPoint;
	
	private String destination;
	
	private LocalTime depatureTime;
	
	private LocalTime arrivalTime;
	
	private int numberOfSeats;
	
	private double totalFare;
	
	private String bookingStatus;
	
	private String paymentStatus;
	

}
 