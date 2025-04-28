package com.smartcity.traffic.controller;



import jakarta.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.smartcity.traffic.dto.TrafficIncidentDTO;
import com.smartcity.traffic.dto.TrafficIncidentHeatmapDTO;
import com.smartcity.traffic.entity.TrafficIncident;
import com.smartcity.traffic.service.TrafficIncidentService;
import com.smartcity.traffic.simulation.SimulationControl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/traffic")
@CrossOrigin(origins = "*")
@Validated
public class TrafficIncidentController {

    private final TrafficIncidentService service;
   
    private final SimulationControl simulationControl; // 👈 add this

    public TrafficIncidentController(TrafficIncidentService service,
                                     SimulationControl simulationControl) {
        this.service = service;
        this.simulationControl = simulationControl; // 👈 set this
    }

   // @GetMapping("/incidents")
   // public List<TrafficIncident> getAll() {
    //    return service.getAllIncidents().stream()
    //            .filter(incident -> incident.getCity() != null)
    //            .collect(Collectors.toList());
   // }
    
    @GetMapping("/incidents")
    public List<TrafficIncident> getAll(@RequestParam(required = false) String city) {
        if (city != null) {
            return service.getIncidentsByCity(city);
        }
        return service.getAllIncidents();
    }


    //@PostMapping("/incidents")
    //public TrafficIncident addIncident(@RequestBody TrafficIncident incident) {
    //    return service.addIncident(incident);
   // }
    
    @PostMapping("/incidents")
    public TrafficIncident addIncident(@Valid @RequestBody TrafficIncidentDTO dto) {
    	System.out.println("🚨 Incoming DTO: " + dto);

        return service.addIncident(dto); // 
    }
    
    @GetMapping("/incidents/city/{city}")
    public List<TrafficIncident> getIncidentsByCity(@PathVariable String city) {
        return service.getIncidentsByCity(city);
    }

    
    
    @GetMapping("/test-db")
    public List<TrafficIncident> testDb() {
        return service.getAllIncidents();
}
    
   // @PutMapping("/incidents/{id}")
   // public TrafficIncident updateIncident(@PathVariable Long id, @RequestBody TrafficIncident updatedIncident) {
   //     return service.updateIncident(id, updatedIncident);
   // }
    
    @PutMapping("/incidents/{id}")
    public TrafficIncident updateIncident(@PathVariable Long id, @Valid @RequestBody TrafficIncidentDTO dto) {
        return service.updateIncident(id, dto);
    }

    @DeleteMapping("/incidents/{id}")
    public void deleteIncident(@PathVariable Long id) {
        service.deleteIncident(id);
    }

    
    
    @GetMapping("/incidents/heatmap")
    public List<TrafficIncidentHeatmapDTO> getHeatmapData(@RequestParam Optional<String> city) {
        return service.getHeatmapData(city);
    }


    @PostMapping("/simulation/on")
    public void enableSimulation() {
        simulationControl.setSimulationEnabled(true); // ✅ Clean access
    }

    @PostMapping("/simulation/off")
    public void disableSimulation() {
        simulationControl.setSimulationEnabled(false); // ✅ Clean access
    }

    @GetMapping("/simulation/status")
    public boolean getSimulationStatus() {
        return simulationControl.isSimulationEnabled(); // ✅ Clean access
    }
}

 


