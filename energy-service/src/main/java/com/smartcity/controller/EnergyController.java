package com.smartcity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartcity.dto.EnergyUsageDTO;
import com.smartcity.entity.EnergyUsage;
import com.smartcity.service.EnergyUsageService;

import jakarta.validation.Valid;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/energy")
@Validated
public class EnergyController {

   //@GetMapping("/usage")
   //public Map<String, Object> getUsage() {
	//    Map<String, Object> response = new HashMap<>();
	 //   response.put("consumption_kwh", 11111);
	 //   response.put("timestamp", LocalDateTime.now());
	  //  response.put("status", "Normal");
	  //  return response;
//	}
  
	
	    @Autowired
	    private EnergyUsageService service;

	    @GetMapping("/usage")
	    public List<EnergyUsage> getAllUsage() {
	        return service.getAllUsage();
	    }

	 //   @PostMapping("/usage")
	  //  public EnergyUsage addUsage(@Valid @RequestBody EnergyUsage usage) {
	   // 	System.out.println("Received: " + usage);
	    //    return service.addUsage(usage);
	   // }
	    
	    
	    @PostMapping("/usage")
	    public EnergyUsage addUsage(@Valid @RequestBody EnergyUsageDTO dto) {
	        EnergyUsage entity = new EnergyUsage();
	        entity.setConsumptionKwh(dto.getConsumptionKwh());
	        entity.setTimestamp(dto.getTimestamp());
	        entity.setStatus(dto.getStatus());

	        return service.addUsage(dto);
	    }
	    
	    @GetMapping("/usage/{id}")
	    public EnergyUsage getOne(@PathVariable Long id) {
	        return service.getUsageById(id)
	                .orElseThrow(() -> new RuntimeException("Not found with id " + id));
	    }
	    
	   // @PutMapping("/usage/{id}")
	  //  public EnergyUsage update(@PathVariable Long id, @Valid @RequestBody EnergyUsage usage) {
	 //       return service.updateUsage(id, usage);
	//    }
	    
	    @PutMapping("/usage/{id}")
	    public EnergyUsage update(@PathVariable Long id, @Valid @RequestBody EnergyUsageDTO dto) {
	    	return service.updateUsage(id, dto);
	    }

	    @DeleteMapping("/usage/{id}")
	    public void delete(@PathVariable Long id) {
	        service.deleteUsage(id);
	    }
	   

	}
