package com.myna.agriconnect.repository;

import com.myna.agriconnect.model.Farmer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FarmerRepository extends JpaRepository<Farmer, Long> {
}