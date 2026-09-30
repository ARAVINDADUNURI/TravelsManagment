package com.aravi.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bookings")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bookings {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "booking_id")
	private int bookingId;
	
	@ManyToOne
	@JoinColumn(name = "passenger_id")
	private Passengers passengers;
	
	@ManyToOne
	@JoinColumn(name = "bus_id")
	private BusSchedule busSchedule;
	
	@Column(name = "date&time_of_booking")
	private LocalDateTime bookingDateAndTime;
	
	@Column(name = "no_Of_Seats", nullable = false)
	private int numberOfSeats;
	
	private double totalFare;
	
	@Column(name = "booking_status")
	private String bookingStatus;
	
	private String paymentStatus;

	public Bookings(Passengers passengers, BusSchedule busSchedule, LocalDateTime bookingDateAndTime, int numberOfSeats,
			double totalFare, String bookingStatus, String paymentStatus) {
		super();
		this.passengers = passengers;
		this.busSchedule = busSchedule;
		this.bookingDateAndTime = bookingDateAndTime;
		this.numberOfSeats = numberOfSeats;
		this.totalFare = totalFare;
		this.bookingStatus = bookingStatus;
		this.paymentStatus = paymentStatus;
	}
	
	

}
