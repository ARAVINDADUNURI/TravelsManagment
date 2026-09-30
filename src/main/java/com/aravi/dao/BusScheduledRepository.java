package com.aravi.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aravi.model.BusDetails;
import com.aravi.model.BusSchedule;
@Repository
public interface BusScheduledRepository extends JpaRepository<BusSchedule, Integer>{

	
	 List<BusSchedule> findByStartingPoint(String startingPoint);
	 
	 List<BusSchedule> findByDestination(String destination);
}
