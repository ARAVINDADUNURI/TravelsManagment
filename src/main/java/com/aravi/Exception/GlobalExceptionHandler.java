package com.aravi.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(BusNotFoundException.class)
	public ResponseEntity<String> handleBusNotFoundException(BusNotFoundException busNotFoundException){
		return new ResponseEntity<>(busNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	public ResponseEntity<String> handlePassengerNotFoundException(PassengerNotFoundException passengerNotFound){
		return new ResponseEntity<>(passengerNotFound.getMessage(), HttpStatus.NOT_FOUND);
		
	}
	public ResponseEntity<String> handleBusSchedulenotFoundException(BusScheduleNotFoundException busScheduleNotFoundException){
		return new ResponseEntity<>(busScheduleNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	public ResponseEntity<String> handleNoAvalibleSeatsException(NoAvalibleSeatsFoundException noAvalibleSeatsFoundException){
		return new ResponseEntity<>(noAvalibleSeatsFoundException.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	public ResponseEntity<String>handleBookingDetailsNotFoundException(BookingDetaisNotFoundException bookingDetaisNotFoundException){
		return new ResponseEntity<>(bookingDetaisNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
	}

}
