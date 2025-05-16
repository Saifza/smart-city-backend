package com.smartcity.emergency.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Service;
import com.smartcity.emergency.dto.EmergencyIncidentDTO;
import com.smartcity.emergency.entity.EmergencyIncident;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import com.smartcity.emergency.entity.Location;
import com.smartcity.emergency.repository.EmergencyIncidentRepository;



@Service
public class EmergencyIncidentService {

	    private final EmergencyIncidentRepository repo;
	  
	    @Autowired
	    private final SimpMessagingTemplate messagingTemplate;
	  

	    public EmergencyIncidentService(EmergencyIncidentRepository repo, SimpMessagingTemplate messagingTemplate) {
	        this.repo = repo;
	        this.messagingTemplate = messagingTemplate;
	    }
	    
	    private static final Map<String, double[]> CITY_COORDINATES = Map.of(
	            "Kansas City", new double[]{39.0997, -94.5786},
	            "Madison",     new double[]{43.0731, -89.4012},
	            "Boulder",     new double[]{40.0150, -105.2705},
	            "Charlottesville", new double[]{38.0293, -78.4767},
	            "New York",    new double[]{40.7128, -74.0060},
	            "Los Angeles", new double[]{34.0522, -118.2437}
	        );

	    private static final List<String> TYPES = List.of("Medical", "Fire", "Police", "Rescue");
	    private static final List<String> SEVERITIES = List.of("LOW", "MEDIUM", "HIGH");
	    private static final List<String> STATUSES = List.of("Active", "Resolved", "In Progress");
	    private static final List<String> DESCRIPTIONS = List.of(
	        "911 call received", "fire reported", "accident on main street", "public disturbance", "medical emergency"
	    );

	    private final Random random = new Random();

	    @Scheduled(fixedRate = 45000) // Every 45 seconds
	    public void generateRandomIncident() {
	        List<String> cities = CITY_COORDINATES.keySet().stream().toList();
	        String city = cities.get(random.nextInt(cities.size()));
	        double[] baseCoords = CITY_COORDINATES.get(city);

	        double lat = baseCoords[0] + (random.nextDouble() - 0.5) * 0.05;
	        double lng = baseCoords[1] + (random.nextDouble() - 0.5) * 0.05;

	        EmergencyIncident incident = new EmergencyIncident(
	            null,
	            TYPES.get(random.nextInt(TYPES.size())),
	            SEVERITIES.get(random.nextInt(SEVERITIES.size())),
	            DESCRIPTIONS.get(random.nextInt(DESCRIPTIONS.size())),
	            city,
	            new Location(lat, lng),
	            LocalDateTime.now(),
	            STATUSES.get(random.nextInt(STATUSES.size()))
	        );

	        EmergencyIncident saved = repo.save(incident);
	        messagingTemplate.convertAndSend("/topic/emergency", saved);
	        System.out.println("🚨 Simulated emergency incident: " + incident.getDescription() + " in " + city);
	    }
	    
	    private EmergencyIncidentDTO toDTO(EmergencyIncident incident) {
	        EmergencyIncidentDTO dto = new EmergencyIncidentDTO();
	        dto.setId(incident.getId());
	        dto.setType(incident.getType());
	        dto.setSeverity(incident.getSeverity());
	        dto.setDescription(incident.getDescription());
	        dto.setCity(incident.getCity());
	        dto.setTimestamp(incident.getTimestamp());
	        dto.setStatus(incident.getStatus());
            dto.setLocation(incident.getLocation());
	        
	   

	        return dto;
	    };

	    private EmergencyIncident fromDTO(EmergencyIncidentDTO dto) {
	        EmergencyIncident incident = new EmergencyIncident();
	        incident.setId(dto.getId()); // Only needed for update, not for new creation
	        incident.setType(dto.getType());
	        incident.setSeverity(dto.getSeverity());
	        incident.setDescription(dto.getDescription());
	        incident.setCity(dto.getCity());
	        incident.setTimestamp(dto.getTimestamp());
	        incident.setStatus(dto.getStatus());
	        incident.setLocation(dto.getLocation());
	        return incident;
	    }
  
	    


	    public EmergencyIncidentDTO addIncident(EmergencyIncidentDTO dto) {
	        EmergencyIncident incident = fromDTO(dto);
	        EmergencyIncident saved = repo.save(incident);
	        return toDTO(saved);
	    }

	    public List<EmergencyIncidentDTO> getAllIncidentDTOs() {
	        return repo.findAll()
	                   .stream()
	                   .map(this::toDTO)
	                   .toList();
	    }

	    public List<EmergencyIncidentDTO> getIncidentsByCity(String city) {
	        return repo.findByCityIgnoreCase(city)
	                   .stream()
	                   .map(this::toDTO)
	                   .toList();
	    }
        
	    public void deleteIncident(Long id) {
	        repo.deleteById(id);
	    }
	    
	    public EmergencyIncidentDTO updateIncident(Long id, EmergencyIncidentDTO dto) {
	        EmergencyIncident existing = repo.findById(id)
	            .orElseThrow(() -> new RuntimeException("Incident not found"));

	        existing.setType(dto.getType());
	        existing.setSeverity(dto.getSeverity());
	        existing.setDescription(dto.getDescription());
	        existing.setCity(dto.getCity());
	        existing.setTimestamp(dto.getTimestamp());
	        existing.setStatus(dto.getStatus());
	        existing.setLocation(dto.getLocation());
	        
	        return toDTO(repo.save(existing));
	    }
}
