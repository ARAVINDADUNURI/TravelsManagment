package com.aravi.service;

import java.util.List;

import com.aravi.dto.AddBusDetailsResponseDTO;
import com.aravi.dto.AddScheduleRequestDTO;
import com.aravi.dto.AddScheduleResponseDTO;

public interface BusScheduledService {
	
	public AddScheduleResponseDTO addSchedule(AddScheduleRequestDTO addScheduleRequestDTO);
	
	List<AddScheduleResponseDTO> getAllSchedules();
	
	public AddScheduleResponseDTO getScheduleById(int id);

	public void deletebyId(int id);
	
	public List<AddScheduleResponseDTO> getBusesByStartingPoint(String startingPoint);
	
	public List<AddScheduleResponseDTO> getBusesByDestination(String destination);
}
