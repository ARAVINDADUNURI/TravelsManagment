package com.aravi.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aravi.model.Bookings;

	public interface BookingsRepository extends JpaRepository<Bookings, Integer> {

	    List<Bookings> findByPassengers_PassengerId(int passengerId);

	    List<Bookings> findByBusSchedule_ScheduleId(int scheduleId);

	    List<Bookings> findByBookingStatus(String bookingStatus);

	    List<Bookings> findByPaymentStatus(String paymentStatus);

	

	
	


}
