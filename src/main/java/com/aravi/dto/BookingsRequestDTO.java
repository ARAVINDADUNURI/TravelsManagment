package com.aravi.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingsRequestDTO {
	
	private int passengerId;
	
	private int scheduledId;
	
	private int numberOfSeats;
	
//	private LocalDateTime bookingDateAndTime;
	
	

}
