package com.myna.agriconnect.repository;

import com.myna.agriconnect.model.Farmer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FarmerRepository extends JpaRepository<Farmer, Long> {
    List<Farmer> findByCrops_CropName(String cropName);
    Page<Farmer> findByCrops_CropNameContainingIgnoreCase(
            String cropName,
            Pageable pageable
    );
    List<Farmer> findByVillage(String village);

    List<Farmer> findAllByOrderByAgeAsc();
    List<Farmer> findAllByOrderByAgeDesc();

    List<Farmer> findByNameContainingIgnoreCase(String name);
    List<Farmer> findByVillageContainingIgnoreCase(String village);

    List<Farmer> findByCrops_CropNameContainingIgnoreCase(String cropName);
    List<Farmer> findByCrops_CropNameAndAgeGreaterThanEqual(
            String cropName,
            Integer age
    );
}