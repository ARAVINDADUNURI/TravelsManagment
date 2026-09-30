package com.aravi.model;
import java.time.LocalTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bus")
public class BusDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bus_id")
	private int busId;
	
	@Column(name = "reg_no")
	private String regestrationNo;
	
	@Column(name = "dr_name")
	private String driverName;
	
	@Column(name = "bus_category")
	private String busCategory;
	
	@Column(name = "seating_capasity")
	private int seatingCapacity;
	
	@OneToMany(mappedBy = "busDetails", cascade  =  CascadeType.ALL, fetch = FetchType.LAZY)
	private List<BusSchedule> busSchedules;
	
	public BusDetails(String regestrationNo, String driverName,
			 String Category, int seatingCapacity) {
		super();
		this.busCategory = Category;
		this.regestrationNo = regestrationNo;
		this.driverName = driverName;
		this.seatingCapacity = seatingCapacity;
		
	
	}



	
	
	

}
