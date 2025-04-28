package com.smartcity.traffic.service;




import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.smartcity.traffic.dto.TrafficIncidentDTO;
import com.smartcity.traffic.dto.TrafficIncidentHeatmapDTO;
import com.smartcity.traffic.entity.TrafficIncident;
import com.smartcity.traffic.repository.TrafficIncidentRepository;
import com.smartcity.traffic.simulation.SimulationControl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import com.smartcity.traffic.model.Location;

@Service
public class TrafficIncidentService {

    private final TrafficIncidentRepository repo;
    private final SimpMessagingTemplate messagingTemplate;
   // private static volatile boolean simulationEnabled = true; // start enabled
    private final SimulationControl simulationControl;
    private static final Map<String, double[]> CITY_COORDINATES = Map.of(
    	    "Kansas City", new double[]{39.0997, -94.5786},
    	    "Madison", new double[]{43.0731, -89.4012},
    	    "Boulder", new double[]{40.0150, -105.2705},
    	    "Charlottesville", new double[]{38.0293, -78.4767},
    	    "New York", new double[] { 40.75, -73.925 },
    	    "Los Angeles", new double[]{ 34.05, -118.3 }
    	);

    	private static final double RADIUS_DEGREES = 0.01; // Approximately ~1.1 km

    
    public TrafficIncidentService(TrafficIncidentRepository repo, SimpMessagingTemplate messagingTemplate, SimulationControl simulationControl) {
        this.repo = repo;
        this.messagingTemplate = messagingTemplate;
        this.simulationControl=simulationControl;
    }

    public List<TrafficIncident> getAllIncidents() {
        System.out.println("📦 Fetching all traffic incidents...");
        List<TrafficIncident> list = repo.findAll();
        System.out.println("✅ Found " + list.size() + " incidents.");
        return list;
    }
           //w/o DTO class and w/o WebSocket
  //  public TrafficIncident addIncident(TrafficIncident incident) {
  //      return repo.save(incident);
   // }
    
     // with DTO class and w/o WebSocket
    // public TrafficIncident addIncident(TrafficIncidentDTO dto) {
    //    TrafficIncident incident = new TrafficIncident();
    //    incident.setLocation(dto.getLocation());
    //    incident.setSeverity(dto.getSeverity());
    //    incident.setDescription(dto.getDescription());
    //    incident.setTimestamp(dto.getTimestamp());
    //    return repo.save(incident);
   // }
    
    public List<TrafficIncident> getIncidentsByCity(String city) {
        return repo.findByCityIgnoreCase(city);
    }


    public TrafficIncident addIncident(TrafficIncidentDTO dto) {
        TrafficIncident incident = new TrafficIncident();
        incident.setDescription(dto.getDescription());
        incident.setLocation(dto.getLocation());
        incident.setSeverity(dto.getSeverity());
        incident.setTimestamp(dto.getTimestamp());

        // Determine city based on coordinates
        Location location = dto.getLocation();
        if (location != null) {
            String city = determineCityFromCoordinates(location.getLatitude(), location.getLongitude());
            incident.setCity(city);
        } else {
            incident.setCity(null); // or handle as per your application's requirement
        }

        TrafficIncident saved = repo.save(incident);
        messagingTemplate.convertAndSend("/topic/traffic", saved);
        return saved;
    }



//public TrafficIncident addIncident(TrafficIncidentDTO dto) {
 //   TrafficIncident incident = new TrafficIncident();
  //  incident.setDescription(dto.getDescription());
  // incident.setLocation(dto.getLocation());;
  //  incident.setSeverity(dto.getSeverity());; 
  //  incident.setTimestamp(dto.getTimestamp());
  //  return repo.save(incident);
//}
    
   // public TrafficIncident updateIncident(Long id, TrafficIncident updatedIncident) {
    //    return repo.findById(id)
     //           .map(existing -> {
     //               existing.setLocation(updatedIncident.getLocation());
     //               existing.setSeverity(updatedIncident.getSeverity());
      //              existing.setDescription(updatedIncident.getDescription());
       //             existing.setTimestamp(updatedIncident.getTimestamp());
       //             return repo.save(existing);
       //         })
       //         .orElseThrow(() -> new RuntimeException("Incident not found with id " + id));
  //  }
    
    public TrafficIncident updateIncident(Long id, TrafficIncidentDTO dto) {
        return repo.findById(id)
                .map(existing -> {
                    existing.setLocation(dto.getLocation());
                    existing.setSeverity(dto.getSeverity());
                    existing.setDescription(dto.getDescription());
                    existing.setTimestamp(dto.getTimestamp());
                    existing.setCity(dto.getCity()); // 🆕 Add this line

                    TrafficIncident updated = repo.save(existing);
                    messagingTemplate.convertAndSend("/topic/traffic", updated);
                    return updated;
                })
                .orElseThrow(() -> new RuntimeException("Incident not found with id " + id));
    }


    public void deleteIncident(Long id) {
        repo.deleteById(id);
        messagingTemplate.convertAndSend("/topic/traffic-delete", id); // 🔥 notify deletion
    }

    
    public List<TrafficIncidentHeatmapDTO> getHeatmapData(Optional<String> city) {
        List<TrafficIncident> incidents = city
            .map(repo::findByCityIgnoreCase)
            .orElseGet(repo::findAll);

        return incidents.stream()
            .map(incident -> {
                double lat = incident.getLocation().getLatitude();
                double lng = incident.getLocation().getLongitude();

                double intensity;
                switch (incident.getSeverity().toUpperCase()) {
                    case "LOW" -> intensity = 5;
                    case "MEDIUM" -> intensity = 15;
                    case "HIGH" -> intensity = 30;
                    default -> intensity = 1;
                }

                return new TrafficIncidentHeatmapDTO(lat, lng, intensity);
            })
            .toList();
    }


   
    private String determineCityFromCoordinates(double latitude, double longitude) {
    	if (latitude >= 38.9 && latitude <= 39.3 && longitude >= -94.7 && longitude <= -94.4) {
            return "Kansas City";
        } else if (latitude >= 39.9 && latitude <= 40.1 && longitude >= -105.3 && longitude <= -105.2) {
            return "Boulder";
        } else if (latitude >= 38.0 && latitude <= 38.1 && longitude >= -78.6 && longitude <= -78.4) {
            return "Charlottesville";
        } else if (latitude >= 43.0 && latitude <= 43.2 && longitude >= -89.5 && longitude <= -89.3) {
            return "Madison";
        }
        else if (latitude >= 34.0 && latitude <= 34.2 && longitude >= -118.5 && longitude <= -118.2) {
            return "Los Angeles";
        } else if (latitude >= 40.7 && latitude <= 40.9 && longitude >= -74.1 && longitude <= -73.9) {
            return "New York";
        }
        return null; // Outside supported cities
    }
   

    
    @Scheduled(fixedRate = 50000) // every 50 seconds
    public void generateRandomIncident() {
        if (!simulationControl.isSimulationEnabled()) return;
        Random rand = new Random();

        // Randomly select a city
        List<String> cities = new ArrayList<>(CITY_COORDINATES.keySet());
        String city = cities.get(rand.nextInt(cities.size()));
        double[] center = CITY_COORDINATES.get(city);

        // Generate random coordinates around the city's center
        double lat = center[0] + (rand.nextDouble() - 0.5) * 2 * RADIUS_DEGREES;
        double lng = center[1] + (rand.nextDouble() - 0.5) * 2 * RADIUS_DEGREES;

        // Pick random severity and description
        String[] severities = {"LOW", "MEDIUM", "HIGH"};
        String severity = severities[rand.nextInt(severities.length)];

        String[] reasons = {"accident", "road work", "congestion", "event", "construction"};
        String description = "Reported " + reasons[rand.nextInt(reasons.length)];

        TrafficIncident incident = new TrafficIncident();
        incident.setLocation(new Location(lat, lng));
        incident.setCity(city);
        incident.setSeverity(severity);
        incident.setDescription(description);
        incident.setTimestamp(LocalDateTime.now());

        TrafficIncident saved = repo.save(incident);
        System.out.println("🆕 Simulated incident: " + description + " at (" + lat + "," + lng + ") in " + city);

        messagingTemplate.convertAndSend("/topic/traffic", saved);
    }
    
}
