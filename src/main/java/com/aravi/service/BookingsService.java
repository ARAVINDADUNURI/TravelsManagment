package com.aravi.service;

import java.util.List;

import com.aravi.dto.BookingsRequestDTO;
import com.aravi.dto.BookingsResponseDTO;

public interface BookingsService {
	
	public BookingsResponseDTO addBooking(BookingsRequestDTO bookingsRequestDTO);
	
	public BookingsResponseDTO getBookingsById(int id);
	
	public List<BookingsResponseDTO> getAllBookings();
	
	public List<BookingsResponseDTO> getBookingsByPassengerId(int id);
	
	public List<BookingsResponseDTO> getBookingsByScheduleId(int id);
	
	public List<BookingsResponseDTO> getBookingsByStatusOfBookings(String bookingStaStatus);
	
	public String updatePaymentStatus(String paymentStatus, int id);

}