package com.smartcity.waste.controller;

import com.smartcity.waste.dto.WasteIncidentDTO;
import com.smartcity.waste.entity.Location;
import com.smartcity.waste.entity.WasteIncident;
import com.smartcity.waste.repository.WasteIncidentRepository;
import com.smartcity.waste.service.WasteIncidentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/waste/incidents")
public class WasteIncidentController {

    @Autowired
    private WasteIncidentRepository repository;
    @Autowired
    private WasteIncidentService service;

  /*  @PostMapping
    public WasteIncident reportIncident(@RequestBody WasteIncidentDTO dto) {
        WasteIncident incident = new WasteIncident();
        incident.setType(dto.getType());
        incident.setLocation(new Location(dto.getLatitude(), dto.getLongitude()));
        incident.setStatus(dto.getStatus());
        incident.setDescription(dto.getDescription());
        incident.setCity(dto.getCity());
        incident.setTimestamp(dto.getTimestamp() != null ? dto.getTimestamp() : LocalDateTime.now());

        return repository.save(incident);
    }  */

   /* @GetMapping
    public List<WasteIncident> getAllIncidents() {
        return repository.findAll();
    }  */
    
    

    @PostMapping
    public WasteIncidentDTO reportIncident(@RequestBody WasteIncidentDTO dto) {
        return service.reportIncident(dto);
    }
    
    @GetMapping
    public List<WasteIncidentDTO> getAll() {
        return service.getAllIncidents();
    }

    @GetMapping("/city/{city}")
    public List<WasteIncidentDTO> getByCity(@PathVariable String city) {
        return service.getIncidentsByCity(city);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteIncident(id);
    }
    
    @PutMapping("/{id}")
    public WasteIncidentDTO update(@PathVariable Long id, @RequestBody WasteIncidentDTO dto) {
        return service.updateIncident(id, dto);
    }

}
