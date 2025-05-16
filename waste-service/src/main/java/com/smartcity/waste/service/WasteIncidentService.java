package com.smartcity.waste.service;

import com.smartcity.waste.dto.WasteIncidentDTO;
import com.smartcity.waste.entity.Location;
import com.smartcity.waste.entity.WasteIncident;
import com.smartcity.waste.repository.WasteIncidentRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WasteIncidentService {

    private final WasteIncidentRepository repository;

    public WasteIncidentService(WasteIncidentRepository repository) {
        this.repository = repository;
    }

    // Convert entity to DTO
    private WasteIncidentDTO toDTO(WasteIncident incident) {
        return new WasteIncidentDTO(
            incident.getId(),
            incident.getType(),
            incident.getStatus(),
            incident.getCity(),
            incident.getDescription(),
            incident.getLocation().getLatitude(),
            incident.getLocation().getLongitude(),
            incident.getTimestamp()
        );
    }


    // Convert DTO to entity
    private WasteIncident toEntity(WasteIncidentDTO dto) {
        return new WasteIncident(
            dto.getId(),
            dto.getType(),
            new Location(dto.getLatitude(), dto.getLongitude()),
            dto.getStatus(),
            dto.getDescription(),
            dto.getCity(),
            dto.getTimestamp() != null ? dto.getTimestamp() : LocalDateTime.now()
        );
    }

    public WasteIncidentDTO reportIncident(WasteIncidentDTO dto) {
        WasteIncident saved = repository.save(toEntity(dto));
        return toDTO(saved);
    }

    public List<WasteIncidentDTO> getAllIncidents() {
        return repository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<WasteIncidentDTO> getIncidentsByCity(String city) {
        return repository.findByCityIgnoreCase(city).stream().map(this::toDTO).collect(Collectors.toList());
    }

    public void deleteIncident(Long id) {
        repository.deleteById(id);
    }
    
    public WasteIncidentDTO updateIncident(Long id, WasteIncidentDTO dto) {
        WasteIncident existing = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Incident not found"));

        existing.setType(dto.getType());
        existing.setStatus(dto.getStatus());
        existing.setCity(dto.getCity());
        existing.setDescription(dto.getDescription());
        existing.setTimestamp(dto.getTimestamp());
        existing.setLocation(new Location(dto.getLatitude(), dto.getLongitude()));

        return toDTO(repository.save(existing));
    }

}
