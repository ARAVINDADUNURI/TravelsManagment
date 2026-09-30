package com.aravi.service;

import java.util.List;

import com.aravi.dto.AddPassengerRequestDTO;
import com.aravi.dto.AddPassengerResponseDTO;
import com.aravi.dto.UpdateBusDetailsRequestDTO;
import com.aravi.dto.UpdatePassengerRequestDTO;

public interface PassengersService {
	
     public AddPassengerResponseDTO addPassenger(AddPassengerRequestDTO addPassengerRequestDTO);

     public List<AddPassengerResponseDTO> getAllPassengers();
     
     public String deleteBuyId(int id);
     
     public AddPassengerResponseDTO updatePassenger(int id, UpdatePassengerRequestDTO passengerRequestDto);


	
}