package com.aravi.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aravi.Exception.BusNotFoundException;
import com.aravi.dao.BusRepository;
import com.aravi.dao.BusScheduledRepository;
import com.aravi.dto.AddBusDetailsResponseDTO;
import com.aravi.dto.AddScheduleRequestDTO;
import com.aravi.dto.AddScheduleResponseDTO;
import com.aravi.model.BusDetails;
import com.aravi.model.BusSchedule;
import com.aravi.service.BusScheduledService;

@Service
public class AddScheduleServiceImpl implements BusScheduledService {

	@Autowired
	BusScheduledRepository scheduledRepository;
	
	@Autowired
	BusRepository busRepository;
	
	@Override
	public AddScheduleResponseDTO addSchedule(AddScheduleRequestDTO scheduleRequestDTO) {
		
		BusSchedule schedule = new BusSchedule();
		
	
		schedule.setArrivalTime(scheduleRequestDTO.getArrivalTime());
		schedule.setDepartureTime(scheduleRequestDTO.getDepartureTime());
		schedule.setStartingPoint(scheduleRequestDTO.getStartingPoint());
		schedule.setDestination(scheduleRequestDTO.getDestination());
		schedule.setFare(scheduleRequestDTO.getFare());
		schedule.setAvailableSeats(scheduleRequestDTO.getAvailableSeats());
		schedule.setTravelName(scheduleRequestDTO.getTravelName());
		
		BusDetails busDetails = busRepository.findById(scheduleRequestDTO.getBusId())
		        .orElseThrow(() -> new BusNotFoundException("Bus Not Found"));

		schedule.setBusDetails(busDetails);
		
		
		BusSchedule savedSchedules = scheduledRepository.save(schedule);
		
		AddScheduleResponseDTO schedulesResponseDTO = new AddScheduleResponseDTO();
		
		BeanUtils.copyProperties(savedSchedules, schedulesResponseDTO);
		
		return schedulesResponseDTO;
	}

	@Override
	public List<AddScheduleResponseDTO> getAllSchedules() {

	    List<BusSchedule> allSchedules = scheduledRepository.findAll();

	    List<AddScheduleResponseDTO> responseList = new ArrayList<>();

	    for (BusSchedule schedule : allSchedules) {

	        AddScheduleResponseDTO responseDTO = new AddScheduleResponseDTO();

	        BeanUtils.copyProperties(schedule, responseDTO);

	        responseList.add(responseDTO);
	    }

	    return responseList;
	}

	@Override
	public AddScheduleResponseDTO getScheduleById(int id) {
		
		Optional<BusSchedule> SchedulebyId = scheduledRepository.findById(id);
		
		BusSchedule busSchedule = SchedulebyId.get();
		
		AddScheduleResponseDTO responseDTO = new AddScheduleResponseDTO();
		
		BeanUtils.copyProperties(busSchedule, responseDTO);
		
		return responseDTO;
	}

	@Override
	public void deletebyId(int id) {
		
		 scheduledRepository.deleteById(id);
		
	}
	
	public List<AddScheduleResponseDTO> getBusesByStartingPoint(String startingPoint){
		
		List<BusSchedule> byStartingPoint = scheduledRepository.findByStartingPoint(startingPoint);
			ArrayList<AddScheduleResponseDTO> arrayList = new ArrayList<>();
			
			if(byStartingPoint != null && !byStartingPoint.isEmpty()) {
				for(BusSchedule bus : byStartingPoint) {
					AddScheduleResponseDTO dto = new AddScheduleResponseDTO();
					BeanUtils.copyProperties(bus, dto);
					arrayList.add(dto);
				}
			}else {
				throw new BusNotFoundException("No Buses Are Found From Starting Point : " + startingPoint);
			}
			return arrayList;
		}

	@Override
	public List<AddScheduleResponseDTO> getBusesByDestination(String destination) {
		List<BusSchedule> byDestination = scheduledRepository.findByDestination(destination);
		ArrayList<AddScheduleResponseDTO> arrayList = new ArrayList<>();
		
		if(byDestination != null && !byDestination.isEmpty()) {
			for(BusSchedule bus : byDestination) {
				AddScheduleResponseDTO dto = new AddScheduleResponseDTO();
				BeanUtils.copyProperties(bus, dto);
				arrayList.add(dto);
			}
		}else {
			throw new BusNotFoundException("No Buses Are Found From the destination point : "+ destination);
			
		}
		return  arrayList;
	}
			
	


}
