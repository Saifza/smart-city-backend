package com.smartcity.emergency.emergency.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import com.smartcity.emergency.dto.EmergencyIncidentDTO;
import com.smartcity.emergency.entity.EmergencyIncident;
import com.smartcity.emergency.service.EmergencyIncidentService;


@RestController
@RequestMapping("/emergency")
@CrossOrigin(origins = "*")
public class EmergencyIncidentController {

    private final EmergencyIncidentService service;

    public EmergencyIncidentController(EmergencyIncidentService service) {
        this.service = service;
    }

    @PostMapping("/incidents")
    public EmergencyIncidentDTO addIncident(@RequestBody EmergencyIncidentDTO dto) {
        return service.addIncident(dto);
    }

    @GetMapping("/incidents")
    public List<EmergencyIncidentDTO> getAll() {
        return service.getAllIncidentDTOs();
    }

    @GetMapping("/incidents/city/{city}")
    public List<EmergencyIncidentDTO> getByCity(@PathVariable String city) {
        return service.getIncidentsByCity(city);
    }
    
    @DeleteMapping("/incidents/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteIncident(id);
    }
    
    @PutMapping("/incidents/{id}")
    public EmergencyIncidentDTO update(@PathVariable Long id, @RequestBody EmergencyIncidentDTO dto) {
        return service.updateIncident(id, dto);
    }
    
}
