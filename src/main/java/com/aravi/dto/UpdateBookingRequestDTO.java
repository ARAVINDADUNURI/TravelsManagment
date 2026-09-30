package com.aravi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBookingRequestDTO {
	
	private int bookingId;
	
	private int numberOfSeats;
	
	private String paymentStatus;
	
	private String bookingStatus;

}
