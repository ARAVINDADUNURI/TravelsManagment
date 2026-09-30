=package com.aravi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aravi.dto.AddBusDetailsResponseDTO;
import com.aravi.dto.AddScheduleRequestDTO;
import com.aravi.dto.AddScheduleResponseDTO;
import com.aravi.service.BusScheduledService;

@Controller
@RequestMapping("/schedules")
public class BusSchedulesController {
	
	@Autowired
	BusScheduledService scheduledService;
	
	
	@PostMapping("/add")
	public ResponseEntity addSchedule(@RequestBody AddScheduleRequestDTO addScheduleRequestDTO) {
		
		AddScheduleResponseDTO responseDTO = scheduledService.addSchedule(addScheduleRequestDTO);
		
		return  new ResponseEntity(responseDTO, HttpStatus.CREATED);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity getAllSchedules() {
		List<AddScheduleResponseDTO> allSchedules = scheduledService.getAllSchedules();
		return ResponseEntity.ok(allSchedules);
	}
	
	@GetMapping("/byId/{id}")
	public ResponseEntity getScheduleById(@PathVariable int id) {
		
		AddScheduleResponseDTO scheduleById = scheduledService.getScheduleById(id);
		
		return ResponseEntity.ok(scheduleById);
		
	}
	
	@DeleteMapping("/del/{id}")
	public ResponseEntity deleteScheduleById(@PathVariable int id) {
		
		scheduledService.deletebyId(id);
		
		return ResponseEntity.ok("Schedule Deleted Sucessfully..!!");
	}

	@GetMapping("/start/{startingPoint}")
	public ResponseEntity<List<AddScheduleResponseDTO>> getBusesByStartingPoint(
	        @PathVariable String startingPoint) {

	    List<AddScheduleResponseDTO>dto = scheduledService.getBusesByStartingPoint(startingPoint);

	    return ResponseEntity.ok(dto);
	}
	
	@GetMapping("/dest/{destination}")
	public ResponseEntity<List<AddScheduleResponseDTO>> getBusesByDestination(@PathVariable String destination) {
		List<AddScheduleResponseDTO>dto = scheduledService.getBusesByDestination(destination);
		return ResponseEntity.ok(dto);
	}
	
	
}
