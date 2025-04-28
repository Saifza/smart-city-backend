package com.smartcity.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartcity.dto.EnergyUsageDTO;
import com.smartcity.entity.EnergyUsage;
import com.smartcity.repository.EnergyUsageRepository;

@Service
public class EnergyUsageService {
    @Autowired
    private EnergyUsageRepository repository;

    public List<EnergyUsage> getAllUsage() {
        return repository.findAll();
    }

    //public EnergyUsage addUsage(EnergyUsage usage) {
     //   usage.setTimestamp(LocalDateTime.now());
    //    return repository.save(usage);
    //}
    
    public EnergyUsage addUsage(EnergyUsageDTO dto) {
        EnergyUsage usage = new EnergyUsage();
        usage.setConsumptionKwh(dto.getConsumptionKwh());
        usage.setStatus(dto.getStatus());
        usage.setTimestamp(LocalDateTime.now()); // You could also allow dto.getTimestamp() if needed
        return repository.save(usage);
    }
    
    
        
    public Optional<EnergyUsage> getUsageById(Long id) {
        return repository.findById(id);
    }
    
  //  public EnergyUsage updateUsage(Long id, EnergyUsage usage) {
    //    return repository.findById(id)
     //           .map(existing -> {
      //              existing.setConsumptionKwh(usage.getConsumptionKwh());
       //             existing.setTimestamp(usage.getTimestamp());
       //             existing.setStatus(usage.getStatus());
       //             return repository.save(existing);
       //         })
       //         .orElseThrow(() -> new RuntimeException("Usage not found with id " + id));
    //}
    public EnergyUsage updateUsage(Long id, EnergyUsageDTO dto) {
        return repository.findById(id)
                .map(existing -> {
                    existing.setConsumptionKwh(dto.getConsumptionKwh());
                    existing.setTimestamp(dto.getTimestamp());
                    existing.setStatus(dto.getStatus());
                    return repository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Usage not found with id " + id));
    }

    public void deleteUsage(Long id) {
        repository.deleteById(id);
    }

}
