package com.aravi.model;

import java.util.List;

import com.aravi.dto.AddPassengerRequestDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "passengers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Passengers {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "passenger_id")
	private int passengerId;
	
	@Column(name = "passenger_name", nullable = false)
	private String passengerName;
	
	private double age;
	
	private String gender;
	
	@Column(name  = "mobile_No", nullable = false)
	private long mobileNumber;
	
	@Column(unique = false)
	private String email;
	
	@OneToMany(mappedBy = "passengers")
	private List<Bookings> bookings;
	
	public Passengers(String name, double age,String gender, long mobileNo, String email) {
		this.passengerName = name;
		this.age = age;
		this.gender = gender;
		this.mobileNumber = mobileNo;
		this.email = email;
	}

	public Passengers(String passengerName, double age, String gender, long mobileNumber, String email,
			List<Bookings> bookings) {
		super();
		this.passengerName = passengerName;
		this.age = age;
		this.gender = gender;
		this.mobileNumber = mobileNumber;
		this.email = email;
		this.bookings = bookings;
	}
	
	

}
