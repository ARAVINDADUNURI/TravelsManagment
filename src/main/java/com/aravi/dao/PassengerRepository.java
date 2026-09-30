package com.aravi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aravi.model.Passengers;

@Repository
public interface PassengerRepository extends JpaRepository<Passengers, Integer> {

}
