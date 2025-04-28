package com.smartcity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smartcity.entity.EnergyUsage;

@Repository
public interface EnergyUsageRepository extends JpaRepository<EnergyUsage, Long> {
}
