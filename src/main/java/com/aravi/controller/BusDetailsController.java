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

import com.aravi.dto.AddBusDetailsRequestDTO;
import com.aravi.dto.AddBusDetailsResponseDTO;
import com.aravi.dto.UpdateBusDetailsRequestDTO;
import com.aravi.service.BusDetailsService;

@RestController
@RequestMapping("/bus")
public class BusDetailsController {
	
	@Autowired
	BusDetailsService busDetailsService;
	
	@PostMapping("/add")
	public  ResponseEntity addBus(@RequestBody AddBusDetailsRequestDTO addBusDetailsRequestDTO) {
		
		AddBusDetailsResponseDTO busDetailsResponseDTO = busDetailsService.addBus(addBusDetailsRequestDTO);
		
		return new ResponseEntity(busDetailsResponseDTO, HttpStatus.CREATED);
		
	}
	
	@GetMapping("/get")
	public ResponseEntity getAllBuses() {
		List<AddBusDetailsResponseDTO> allBuses = busDetailsService.getAllBuses();
		return ResponseEntity.ok(allBuses);
}
	@GetMapping("/{id}")
	public ResponseEntity getBusById(@PathVariable int id) {
		AddBusDetailsResponseDTO busById = busDetailsService.getBusById(id);
		return ResponseEntity.ok(busById);
	}
	
	
	@GetMapping("/ctg/{busCategory}")
	public ResponseEntity<List<AddBusDetailsResponseDTO>> getBusByCategory(@PathVariable String busCategory){
		List<AddBusDetailsResponseDTO> busByCategory = busDetailsService.getBusByCategory(busCategory);
		return ResponseEntity.ok(busByCategory);
	}
	
	@DeleteMapping("/del/{busId}")
	public ResponseEntity deleteBusById(@PathVariable int busId) {
			String busById = busDetailsService.deleteById(busId);
			return ResponseEntity.ok(busById);
			
	}
	
	@PutMapping("/update/{busId}")
	public AddBusDetailsResponseDTO updateBusById(@PathVariable int busId, @RequestBody UpdateBusDetailsRequestDTO busDetailsRequestDTO) {
		return busDetailsService.updateBusById(busId,busDetailsRequestDTO);
		
	}
	
	
	
	
}
	