package com.myna.agriconnect.repository;

import com.myna.agriconnect.model.Crop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CropRepository extends JpaRepository<Crop, Long> {
}