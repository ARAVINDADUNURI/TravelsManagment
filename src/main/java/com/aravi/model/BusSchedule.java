package com.aravi.model;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Bus_Schedules")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusSchedule {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "schedule_id")
	private int scheduleId;
		
	@Column(name = "start_point", nullable = false)
	private String startingPoint;
	
	@Column(name = "destination")
	private String destination;
	
	@Column(name = "dept_time")
	private LocalTime departureTime;
	
	@Column(name = "arrival_Time")
	private LocalTime arrivalTime;
	
	@Column(nullable = false)
	private double fare;
	
	@Column(nullable = false)
	private int availableSeats;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bus_id")
	private BusDetails busDetails;
	
	@OneToMany(mappedBy = "busSchedule")
	private List<Bookings> bookings;
	
	@Column(name = "travels_Name")
	private String travelName;
	
	
	public BusSchedule(String startingPoint, String destination, LocalTime depatureTime,
			LocalTime arrivalTime, double fare, int availableSeats, String travelName) {
		super();
		
		this.startingPoint = startingPoint;
		this.destination = destination;
		this.departureTime = depatureTime;
		this.arrivalTime = arrivalTime;
		this.fare = fare;
		this.availableSeats = availableSeats;
		this.travelName = travelName;
		
	}


	public BusSchedule(String startingPoint, String destination, LocalTime departureTime, LocalTime arrivalTime,
			double fare, int availableSeats, BusDetails busDetails, List<Bookings> bookings, String travelName) {
		super();
		this.startingPoint = startingPoint;
		this.destination = destination;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.fare = fare;
		this.availableSeats = availableSeats;
		this.busDetails = busDetails;
		this.bookings = bookings;
		this.travelName = travelName;
	}
	
	


	

}
