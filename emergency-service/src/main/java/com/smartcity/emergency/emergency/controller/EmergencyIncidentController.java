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
    public EmergencyIncident addIncident(@RequestBody EmergencyIncidentDTO dto) {
        return service.addIncident(dto);
    }

    @GetMapping("/incidents")
    public List<EmergencyIncident> getAll() {
        return service.getAllIncidents();
    }

    @GetMapping("/incidents/city/{city}")
    public List<EmergencyIncident> getByCity(@PathVariable String city) {
        return service.getIncidentsByCity(city);
    }
}
