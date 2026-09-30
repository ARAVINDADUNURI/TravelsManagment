package com.aravi.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.aravi.Exception.BusNotFoundException;
import com.aravi.dao.BusRepository;
import com.aravi.dto.AddBusDetailsRequestDTO;
import com.aravi.dto.AddBusDetailsResponseDTO;
import com.aravi.dto.UpdateBusDetailsRequestDTO;
import com.aravi.model.BusDetails;
import com.aravi.service.BusDetailsService;



@Service
public class AddBusDetailsServiceImpl implements BusDetailsService {
	
	
	@Autowired
	BusRepository busRepository;

	@Override
	public AddBusDetailsResponseDTO addBus(AddBusDetailsRequestDTO busDetailsRequestDTO) {
		
		BusDetails details = new BusDetails();
		
		details.setRegestrationNo(busDetailsRequestDTO.getRegestrationNo());
		details.setDriverName(busDetailsRequestDTO.getDriverName());
		details.setBusCategory(busDetailsRequestDTO.getBusCategory());
		details.setSeatingCapacity(busDetailsRequestDTO.getSeatingCapacity());
		
		
//		The data will be saved in the database
		BusDetails savedBuses = busRepository.save(details);
		
//		Creation of empty object to send responseDTO
		AddBusDetailsResponseDTO detailsResponseDTO = new AddBusDetailsResponseDTO();
//		Used to copy all the properties from Entity to DTO's (API ki response gaa velthundhii)
		BeanUtils.copyProperties(savedBuses, detailsResponseDTO);
		
		
		return detailsResponseDTO;
	}


	@Override
	public List<AddBusDetailsResponseDTO> getAllBuses() {
		List<BusDetails> allBuses = busRepository.findAll();
		List<AddBusDetailsResponseDTO>list = allBuses
				.stream()
				.map(Bus -> {
					AddBusDetailsResponseDTO busDetailsResponseDTO = new AddBusDetailsResponseDTO();
					BeanUtils.copyProperties(Bus, busDetailsResponseDTO);
					return busDetailsResponseDTO;
					
				})
				.toList();

		
		return list;
	}


	@Override
	public AddBusDetailsResponseDTO getBusById(int id) {
		Optional<BusDetails> busId = busRepository.findById(id);
				
		if(busId.isPresent()) {
			  BusDetails bus = busId.get();
			AddBusDetailsResponseDTO dto = new AddBusDetailsResponseDTO();
			BeanUtils.copyProperties(bus, dto);
			return dto;
		}else {
			throw new BusNotFoundException("BusDetails Not Found With id : " + id);
		}
		
	
	}

	
	@Override
	public List<AddBusDetailsResponseDTO> getBusByCategory(String busCategory) {
		List<BusDetails> buses = busRepository.findByBusCategory(busCategory);
		ArrayList<AddBusDetailsResponseDTO> list = new ArrayList<AddBusDetailsResponseDTO>();
		
		if(buses != null && !buses.isEmpty()) {
			for(BusDetails bus : buses) {

				AddBusDetailsResponseDTO dto = new AddBusDetailsResponseDTO();
//				Here dto kakunda Yedhii copy chesinna Data Null ga thirigi Osthundhii Manakii
				BeanUtils.copyProperties(bus, dto);
				list.add(dto);
			}
		}else {
			throw new BusNotFoundException("No Buses Are Found by the given Category : " + busCategory);
		}
		
		return list;
	}
	
	@Override
	public String deleteById(int busId) {
		if(!busRepository.existsById(busId)) {
			throw new BusNotFoundException("Bus Not With Given Id " + busId);
		}
	
		busRepository.deleteById(busId);
		return "Deleted Bus Sucessfully From List with provided Id " + busId;
	
		
		
	}

	
	@Override
	public AddBusDetailsResponseDTO updateBusById(int id, UpdateBusDetailsRequestDTO updateBusDetailsRequestDTO) {
		BusDetails bus = busRepository.findById(id).get();
		
		BeanUtils.copyProperties(updateBusDetailsRequestDTO, bus);
		
		BusDetails savedItem = busRepository.save(bus);
		
		 AddBusDetailsResponseDTO busResponseDTO = new AddBusDetailsResponseDTO();
		    BeanUtils.copyProperties(savedItem, busResponseDTO);

		    return busResponseDTO;
	}


	



}