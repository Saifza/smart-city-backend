package com.smartcity.emergency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.smartcity.emergency.entity.EmergencyIncident;
import java.util.List;

public interface EmergencyIncidentRepository extends JpaRepository<EmergencyIncident, Long> {
    List<EmergencyIncident> findByCityIgnoreCase(String city);
}
