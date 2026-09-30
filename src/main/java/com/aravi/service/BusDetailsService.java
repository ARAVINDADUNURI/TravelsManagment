package com.aravi.service;



import java.util.List;

import org.springframework.http.ResponseEntity;

import com.aravi.dto.AddBusDetailsRequestDTO;
import com.aravi.dto.AddBusDetailsResponseDTO;
import com.aravi.dto.UpdateBusDetailsRequestDTO;

public interface BusDetailsService {
	
	public AddBusDetailsResponseDTO addBus(AddBusDetailsRequestDTO busDetailsRequestDTO);
	
	public List<AddBusDetailsResponseDTO> getAllBuses();
	
	public  AddBusDetailsResponseDTO getBusById(int id);
	
	public List<AddBusDetailsResponseDTO> getBusByCategory(String busCategory);
	
	public String deleteById(int id);
	
	public AddBusDetailsResponseDTO updateBusById(int id, UpdateBusDetailsRequestDTO updateBusDetailsRequestDTO);
	
	
	
}
