package com.aravi.serviceimpl;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aravi.Exception.PassengerNotFoundException;
import com.aravi.dao.PassengerRepository;
import com.aravi.dto.AddPassengerRequestDTO;
import com.aravi.dto.AddPassengerResponseDTO;
import com.aravi.dto.UpdatePassengerRequestDTO;
import com.aravi.model.Passengers;
import com.aravi.service.PassengersService;

@Service
public class AddPassengersServiceImpl implements PassengersService {
	
	@Autowired
	PassengerRepository passengerRepository;
	
	
	@Override
	public AddPassengerResponseDTO addPassenger(AddPassengerRequestDTO addPassengerRequestDTO) {
		
		Passengers passengers = new Passengers();

		passengers.setPassengerName(addPassengerRequestDTO.getPassengerName());
		passengers.setAge(addPassengerRequestDTO.getAge());
		passengers.setGender(addPassengerRequestDTO.getGender());
		passengers.setMobileNumber(addPassengerRequestDTO.getMobileNumber());
		passengers.setEmail(addPassengerRequestDTO.getEmail());
		
		Passengers savePassengers = passengerRepository.save(passengers);
		
		AddPassengerResponseDTO passengerResponseDTO = new AddPassengerResponseDTO();
		
		BeanUtils.copyProperties( savePassengers,passengerResponseDTO);
		
		return passengerResponseDTO;
	}


	@Override
	public List<AddPassengerResponseDTO> getAllPassengers() {
		List<Passengers> allPassengers = passengerRepository.findAll();
		List<AddPassengerResponseDTO> list = (List<AddPassengerResponseDTO>) allPassengers
				.stream()
				.map(passenger -> {
					AddPassengerResponseDTO addPassengerResponseDTO = new AddPassengerResponseDTO();
					BeanUtils.copyProperties(passenger, addPassengerResponseDTO);
					return addPassengerResponseDTO;
					
				})
				.toList();
		return list;
	}


	@Override
	public String deleteBuyId(int id) {
		if(!passengerRepository.existsById(id)) {
			throw new PassengerNotFoundException("Passenger Not Found with the GivenId : " + id);
		}
		passengerRepository.deleteById(id);
		return "Passenger Deleted Sucessfully with id : " + id;
		
	}


	@Override
	public AddPassengerResponseDTO updatePassenger(
	        int id, UpdatePassengerRequestDTO passengerRequestDto) {

	    Optional<Passengers> optionalPassenger = passengerRepository.findById(id);

	    if (optionalPassenger.isPresent()) {

	        Passengers passenger = optionalPassenger.get();

	        BeanUtils.copyProperties(passengerRequestDto, passenger);

	        Passengers savedPassenger = passengerRepository.save(passenger);

	        AddPassengerResponseDTO passengerResponseDTO =
	                new AddPassengerResponseDTO();

	        BeanUtils.copyProperties(savedPassenger, passengerResponseDTO);

	        return passengerResponseDTO;
	    }else {
	    	throw new PassengerNotFoundException("Passenger Not Found with Id : " + id);
	    }

	    	}
	}
