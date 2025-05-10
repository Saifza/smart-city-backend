package com.smartcity.waste.repository;



import com.smartcity.waste.entity.WasteIncident;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WasteIncidentRepository extends JpaRepository<WasteIncident, Long> {

	List<WasteIncident> findByCityIgnoreCase(String city);
    // Add custom queries if needed
}
