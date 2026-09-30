package com.aravi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aravi.dto.AddPassengerRequestDTO;
import com.aravi.dto.AddPassengerResponseDTO;
import com.aravi.dto.UpdatePassengerRequestDTO;
import com.aravi.service.PassengersService;

@RestController
@RequestMapping("/passengers")
public class PassengerController {
	
	@Autowired
	PassengersService passengersService;

	@PostMapping("/add")
	public ResponseEntity addPassenger(@RequestBody AddPassengerRequestDTO addPassengerRequestDTO) {
		AddPassengerResponseDTO passenger = passengersService.addPassenger(addPassengerRequestDTO);
		
		return new ResponseEntity(passenger, HttpStatus.CREATED);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<AddPassengerResponseDTO>> getAllPassengers() {
		List<AddPassengerResponseDTO> allPassengers = passengersService.getAllPassengers();
		return ResponseEntity.ok(allPassengers);
	}
	
	@DeleteMapping("/byId/{id}")
	public ResponseEntity deletePassengerById(@PathVariable int id) {
		String deleteById = passengersService.deleteBuyId(id);
		return ResponseEntity.ok(deleteById);
	}
	
	@PutMapping("/update/{id}")
	public AddPassengerResponseDTO updatePassengerById(@PathVariable int id, @RequestBody UpdatePassengerRequestDTO passengerRequestDto) {
		return passengersService.updatePassenger(id, passengerRequestDto);
	}
	
}
