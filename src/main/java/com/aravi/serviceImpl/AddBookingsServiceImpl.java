package com.aravi.serviceimpl;

import com.aravi.TravelsManagmentApplication;
import java.awt.print.Book;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aravi.Exception.BookingDetaisNotFoundException;
import com.aravi.Exception.BusScheduleNotFoundException;
import com.aravi.Exception.NoAvalibleSeatsFoundException;
import com.aravi.Exception.PassengerNotFoundException;
import com.aravi.dao.BookingsRepository;
import com.aravi.dao.BusRepository;
import com.aravi.dao.BusScheduledRepository;
import com.aravi.dao.PassengerRepository;
import com.aravi.dto.AddPassengerRequestDTO;
import com.aravi.dto.BookingsRequestDTO;
import com.aravi.dto.BookingsResponseDTO;
import com.aravi.model.Bookings;
import com.aravi.model.BusDetails;
import com.aravi.model.BusSchedule;
import com.aravi.model.Passengers;
import com.aravi.service.BookingsService;

@Service
public class AddBookingsServiceImpl implements BookingsService {
	
	private final AddPassengersServiceImpl addPassengersServiceImpl;

	private final TravelsManagmentApplication travelsManagmentApplication;

	private final AddBusDetailsServiceImpl addBusDetailsServiceImpl;

	@Autowired
	PassengerRepository passengerRepository;
	
	@Autowired
	BookingsRepository bookingsRepository;
	
	@Autowired
	BusScheduledRepository scheduledRepository;
	
	@Autowired
	BusRepository busRepository;



	AddBookingsServiceImpl(AddBusDetailsServiceImpl addBusDetailsServiceImpl, TravelsManagmentApplication travelsManagmentApplication, AddPassengersServiceImpl addPassengersServiceImpl) {
		this.addBusDetailsServiceImpl = addBusDetailsServiceImpl;
		this.travelsManagmentApplication = travelsManagmentApplication;
		this.addPassengersServiceImpl = addPassengersServiceImpl;
	}
	


	@Override
	public BookingsResponseDTO addBooking(BookingsRequestDTO bookingsRequestDTO) {
		
//		Passengers passengers = new Passengers();
//		BusSchedule busSchedule = new BusSchedule();
		Bookings bookings = new Bookings();
		
		Passengers passenger = passengerRepository.findById(bookingsRequestDTO.getPassengerId())
				.orElseThrow(() -> new PassengerNotFoundException("passenger Not Found"));
		bookings.setPassengers(passenger);
		BusSchedule busSchedule = scheduledRepository.findById(bookingsRequestDTO.getScheduledId())
				.orElseThrow(() -> new BusScheduleNotFoundException("Bus Schedule Not Found"));
		bookings.setBusSchedule(busSchedule);
		
		
		
		int avalibleSeats =  busSchedule.getAvailableSeats();
		int requestedSeats = bookingsRequestDTO.getNumberOfSeats();
				if(avalibleSeats >= requestedSeats) {
					double fare = busSchedule.getFare();
					double totalFare = requestedSeats * fare;
					bookings.setTotalFare(totalFare);
					bookings.setBookingDateAndTime(LocalDateTime.now());
					bookings.setBookingStatus("Booked Sucessfully");
					bookings.setPaymentStatus("paid");
					bookings.setNumberOfSeats(requestedSeats);
					int avalibleSeats1 = avalibleSeats - requestedSeats; 
					busSchedule.setAvailableSeats(avalibleSeats1);
					scheduledRepository.save(busSchedule);
					
					Bookings savedBookings = bookingsRepository.save(bookings);
					BookingsResponseDTO bookingsResponseDTO = new BookingsResponseDTO();
					BeanUtils.copyProperties(savedBookings, bookingsResponseDTO);
					bookingsResponseDTO.setPassengerName(savedBookings.getPassengers().getPassengerName());
					bookingsResponseDTO.setStartingPoint(savedBookings.getBusSchedule().getStartingPoint());
					bookingsResponseDTO.setDestination(savedBookings.getBusSchedule().getDestination());
					bookingsResponseDTO.setDepatureTime(savedBookings.getBusSchedule().getDepartureTime());
					bookingsResponseDTO.setArrivalTime(savedBookings.getBusSchedule().getArrivalTime());
					bookingsResponseDTO.setTravelName(savedBookings.getBusSchedule().getTravelName());
					return bookingsResponseDTO;
					
			}else {
				throw new NoAvalibleSeatsFoundException("No Seats Found");
			}
	}

	@Override
	public BookingsResponseDTO getBookingsById(int id) {
		Optional<Bookings> bookingById = bookingsRepository.findById(id);
		if(bookingById.isPresent()) {
		Bookings savedBookings = bookingById.get();
		BookingsResponseDTO responseDTO = new BookingsResponseDTO();
		BeanUtils.copyProperties(savedBookings, responseDTO);
		responseDTO.setPassengerName(savedBookings.getPassengers().getPassengerName());
		responseDTO.setStartingPoint(savedBookings.getBusSchedule().getStartingPoint());
		responseDTO.setDestination(savedBookings.getBusSchedule().getDestination());
		responseDTO.setDepatureTime(savedBookings.getBusSchedule().getDepartureTime());
		responseDTO.setArrivalTime(savedBookings.getBusSchedule().getArrivalTime());
		responseDTO.setTravelName(savedBookings.getBusSchedule().getTravelName());
		
		return responseDTO;
	}else {
		throw new BookingDetaisNotFoundException("Booking Not Found With the Given id : " + id);
		
	}
}

	@Override
	public List<BookingsResponseDTO> getAllBookings() {
		List<Bookings> allBookings = bookingsRepository.findAll();
		ArrayList<BookingsResponseDTO> responseList = new ArrayList<>();
		for(Bookings bookings : allBookings) {
			BookingsResponseDTO bookingsResponseDTO = new BookingsResponseDTO();
			BeanUtils.copyProperties(bookings, bookingsResponseDTO);
			bookingsResponseDTO.setPassengerName(bookings.getPassengers().getPassengerName());
			bookingsResponseDTO.setStartingPoint(bookings.getBusSchedule().getStartingPoint());
			bookingsResponseDTO.setDestination(bookings.getBusSchedule().getDestination());
			bookingsResponseDTO.setDepatureTime(bookings.getBusSchedule().getDepartureTime());
			bookingsResponseDTO.setArrivalTime(bookings.getBusSchedule().getArrivalTime());
			bookingsResponseDTO.setTravelName(bookings.getBusSchedule().getTravelName());
			responseList.add(bookingsResponseDTO);
		}
		return responseList;
		
	}

	@Override
	public List<BookingsResponseDTO> getBookingsByPassengerId(int id) {
		List<Bookings>bookingsList = bookingsRepository.findByPassengers_PassengerId(id);
		List<BookingsResponseDTO> responseList = new ArrayList<>();
		for(Bookings bookings : bookingsList) {
			BookingsResponseDTO bookingsResponseDTO = new BookingsResponseDTO();
			BeanUtils.copyProperties(bookings, bookingsResponseDTO);
			responseList.add(bookingsResponseDTO);
			bookingsResponseDTO.setPassengerName(bookings.getPassengers().getPassengerName());
			bookingsResponseDTO.setStartingPoint(bookings.getBusSchedule().getStartingPoint());
			bookingsResponseDTO.setDepatureTime(bookings.getBusSchedule().getDepartureTime());
			bookingsResponseDTO.setDestination(bookings.getBusSchedule().getDestination());
			bookingsResponseDTO.setArrivalTime(bookings.getBusSchedule().getArrivalTime());
			bookingsResponseDTO.setTravelName(bookings.getBusSchedule().getTravelName());
			
		}
		return responseList;
	}

	@Override
	public List<BookingsResponseDTO> getBookingsByScheduleId(int id) {
		List<Bookings> bookingList = bookingsRepository.findByBusSchedule_ScheduleId(id);
		List<BookingsResponseDTO> responseList = new ArrayList<>();
		for(Bookings bookings : bookingList) {
		BookingsResponseDTO bookingsResponseDTO = new BookingsResponseDTO();
		BeanUtils.copyProperties(bookings, bookingsResponseDTO);
		responseList.add(bookingsResponseDTO);
		bookingsResponseDTO.setPassengerName(bookings.getPassengers().getPassengerName());
		bookingsResponseDTO.setStartingPoint(bookings.getBusSchedule().getStartingPoint());
		bookingsResponseDTO.setDepatureTime(bookings.getBusSchedule().getDepartureTime());
		bookingsResponseDTO.setDestination(bookings.getBusSchedule().getDestination());
		bookingsResponseDTO.setArrivalTime(bookings.getBusSchedule().getArrivalTime());
		bookingsResponseDTO.setTravelName(bookings.getBusSchedule().getTravelName());
		
		
	}
		return responseList;
	}

	@Override
	public List<BookingsResponseDTO> getBookingsByStatusOfBookings(String bookingStaStatus) {
		List<Bookings> bookingList = bookingsRepository.findByBookingStatus(bookingStaStatus);
		ArrayList<BookingsResponseDTO> responseList = new ArrayList<>();
		for(Bookings bookings : bookingList) {
			BookingsResponseDTO bookingsResponseDTO = new BookingsResponseDTO();
			BeanUtils.copyProperties(bookings, bookingsResponseDTO);
			bookingsResponseDTO.setPassengerName(bookings.getPassengers().getPassengerName());
			bookingsResponseDTO.setStartingPoint(bookings.getBusSchedule().getStartingPoint());
			bookingsResponseDTO.setDepatureTime(bookings.getBusSchedule().getDepartureTime());
			bookingsResponseDTO.setDestination(bookings.getBusSchedule().getDestination());
			bookingsResponseDTO.setArrivalTime(bookings.getBusSchedule().getArrivalTime());
			bookingsResponseDTO.setTravelName(bookings.getBusSchedule().getTravelName());
			
			responseList.add(bookingsResponseDTO);
		}
		return responseList;
	}

	@Override
	public String updatePaymentStatus(String paymentStatus, int id) {
		Bookings bookings = bookingsRepository.findById(id)
				.orElseThrow(() -> new BookingDetaisNotFoundException("Booking Not Found"));
		if(bookings.getPaymentStatus().equalsIgnoreCase(paymentStatus)) {
			return "Payment Status is Already : " + paymentStatus;
		}
		bookings.setPaymentStatus(paymentStatus);
		bookingsRepository.save(bookings);
		return "Payment Status Updated Sucessfully..!!";
	}
	
	
}
