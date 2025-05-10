package com.smartcity.waste.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.smartcity.waste.dto.WasteIncidentDTO;
import com.smartcity.waste.entity.Location;
import com.smartcity.waste.entity.WasteIncident;
import com.smartcity.waste.repository.WasteIncidentRepository;

import java.time.LocalDateTime;

@Service
public class WasteIncidentService {

    private final WasteIncidentRepository repository;

    public WasteIncidentService(WasteIncidentRepository repository) {
        this.repository = repository;
    }

    public WasteIncident reportIncident(WasteIncidentDTO dto) {
        WasteIncident incident = new WasteIncident();
        incident.setType(dto.getType());
        incident.setLocation(new Location(dto.getLatitude(), dto.getLongitude()));
        incident.setStatus(dto.getStatus());
        incident.setDescription(dto.getDescription());
        incident.setCity(dto.getCity());
        incident.setTimestamp(dto.getTimestamp() != null ? dto.getTimestamp() : LocalDateTime.now());

        return repository.save(incident);
    }

    public List<WasteIncident> getAllIncidents() {
        return repository.findAll();
    }

    public List<WasteIncident> getIncidentsByCity(String city) {
        return repository.findByCityIgnoreCase(city);
    }

    public void deleteIncident(Long id) {
        repository.deleteById(id);
    }
}

