package com.aravi.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aravi.model.BusDetails;
@Repository
public interface BusRepository extends JpaRepository<BusDetails, Integer> {
	
	 List<BusDetails> findByBusCategory(String busCategory);

}
