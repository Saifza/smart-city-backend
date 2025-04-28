package com.smartcity.traffic.repository;

import com.smartcity.traffic.entity.TrafficIncident;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TrafficIncidentRepository extends JpaRepository<TrafficIncident, Long> {
	List<TrafficIncident> findByCityIgnoreCase(String city);
    List<TrafficIncident> findByCityIsNull();  
   

}
