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
	    
	    
    public EmergencyIncident addIncident(EmergencyIncidentDTO dto) {
        EmergencyIncident incident = new EmergencyIncident();
        incident.setCity(dto.getCity());
        incident.setType(dto.getType());
        incident.setStatus(dto.getStatus());
        incident.setTimestamp(dto.getTimestamp());
        return repo.save(incident);
    }

    public List<EmergencyIncident> getAllIncidents() {
        return repo.findAll();
    }

    public List<EmergencyIncident> getIncidentsByCity(String city) {
        return repo.findByCityIgnoreCase(city);
    }
}
