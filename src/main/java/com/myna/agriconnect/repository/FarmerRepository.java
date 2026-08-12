package com.myna.agriconnect.repository;

import com.myna.agriconnect.model.Farmer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FarmerRepository extends JpaRepository<Farmer, Long> {
    List<Farmer> findByCrop(String crop);
    List<Farmer> findByVillage(String village);
    List<Farmer> findByCropAndAgeGreaterThanEqual(String crop, Integer age);
    List<Farmer> findAllByOrderByAgeAsc();
    List<Farmer> findAllByOrderByAgeDesc();
    List<Farmer> findByCropContainingIgnoreCase(String crop);
    List<Farmer> findByNameContainingIgnoreCase(String name);
    List<Farmer> findByVillageContainingIgnoreCase(String village);
    Page<Farmer> findByCropContainingIgnoreCase(
            String crop,
            Pageable pageable
    );
}