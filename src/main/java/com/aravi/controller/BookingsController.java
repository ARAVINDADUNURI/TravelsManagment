package com.aravi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aravi.dto.BookingsRequestDTO;
import com.aravi.dto.BookingsResponseDTO;
import com.aravi.dto.UpdateBusDetailsRequestDTO;
import com.aravi.service.BookingsService;

@RestController
@RequestMapping("/bookings")
public class BookingsController {
	
	@Autowired
	BookingsService bookingsService;
	
	@PostMapping("/addbooking")
	public ResponseEntity addBookings(@RequestBody BookingsRequestDTO bookingsRequestDTO) {
		BookingsResponseDTO responseDTO = bookingsService.addBooking(bookingsRequestDTO);
		return new ResponseEntity(responseDTO, HttpStatus.CREATED);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity getAllBookings() {
		List<BookingsResponseDTO> allBookings = bookingsService.getAllBookings();	
		return ResponseEntity.ok(allBookings);
	}
	
	@GetMapping("/getById/{id}")
	public ResponseEntity getBookingsById(@PathVariable int id) {
		BookingsResponseDTO bookingsById = bookingsService.getBookingsById(id);
		return ResponseEntity.ok(bookingsById);
	}
	
	@GetMapping("/getByPassId/{id}")
	public ResponseEntity getBookingsByPassengerId(@PathVariable int id) {
		List<BookingsResponseDTO> bookingsByPassengerId = bookingsService.getBookingsByPassengerId(id);
		return ResponseEntity.ok(bookingsByPassengerId);
	}
	
	@GetMapping("/getBySchId/{id}")
	public  ResponseEntity getBookingsByScheduledId(@PathVariable int id) {
		List<BookingsResponseDTO> bookingsByScheduleId = bookingsService.getBookingsByScheduleId(id);
		return ResponseEntity.ok(bookingsByScheduleId);
	}
	
	@GetMapping("/getByStatus/{bookingStatus}")
	public ResponseEntity getBookingsByStatusOfBookings(@PathVariable String bookingStatus) {
		List<BookingsResponseDTO> bookingsByStatusOfBookings = bookingsService.getBookingsByStatusOfBookings(bookingStatus);
		return ResponseEntity.ok(bookingsByStatusOfBookings);
	}
	
@PutMapping("/updatePaymentStatus/{id}/{status}")
public ResponseEntity<String> updatePaymentStatus(@PathVariable int id,
                                                  @PathVariable String status) {

    String response = bookingsService.updatePaymentStatus(status, id);

    return new ResponseEntity<>(response, HttpStatus.OK);
}}
