package com.myna.agriconnect.repository;

import com.myna.agriconnect.model.Farmer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FarmerRepository extends JpaRepository<Farmer, Long> {
    List<Farmer> findByCrop(String crop);
    List<Farmer> findByVillage(String village);
    List<Farmer> findByCropAndAgeGreaterThanEqual(String crop, Integer age);
    List<Farmer> findAllByOrderByAgeAsc();
    List<Farmer> findAllByOrderByAgeDesc();
}