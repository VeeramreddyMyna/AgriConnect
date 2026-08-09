package com.myna.agriconnect.service;

import com.myna.agriconnect.model.Farmer;
import com.myna.agriconnect.repository.FarmerRepository;
import org.springframework.stereotype.Service;
import com.myna.agriconnect.exception.FarmerNotFoundException;
import java.util.List;
import java.util.Optional;
import com.myna.agriconnect.dto.FarmerRequestDTO;
import com.myna.agriconnect.dto.FarmerResponseDTO;

@Service
public class FarmerService {

    private final FarmerRepository farmerRepository;

    public FarmerService(FarmerRepository farmerRepository) {
        this.farmerRepository = farmerRepository;
    }
    public FarmerResponseDTO addFarmer(FarmerRequestDTO farmerRequestDTO) {

        Farmer farmer = new Farmer();

        farmer.setName(farmerRequestDTO.getName());
        farmer.setAge(farmerRequestDTO.getAge());
        farmer.setVillage(farmerRequestDTO.getVillage());
        farmer.setCrop(farmerRequestDTO.getCrop());

        Farmer savedFarmer = farmerRepository.save(farmer);

        FarmerResponseDTO responseDTO = new FarmerResponseDTO();

        responseDTO.setId(savedFarmer.getId());
        responseDTO.setName(savedFarmer.getName());
        responseDTO.setAge(savedFarmer.getAge());
        responseDTO.setVillage(savedFarmer.getVillage());
        responseDTO.setCrop(savedFarmer.getCrop());

        return responseDTO;
    }
    public List<Farmer> getAllFarmers() {
        return farmerRepository.findAll();
    }
    public Farmer getFarmerById(Long id) {

        Optional<Farmer> farmer = farmerRepository.findById(id);

        if (farmer.isPresent()) {
            return farmer.get();
        }

        throw new FarmerNotFoundException(
                "Farmer not found with id: " + id
        );
    }
    public Farmer updateFarmer(Long id, Farmer updatedFarmer) {

        Optional<Farmer> farmer = farmerRepository.findById(id);

        if (farmer.isPresent()) {

            Farmer existingFarmer = farmer.get();

            existingFarmer.setName(updatedFarmer.getName());
            existingFarmer.setVillage(updatedFarmer.getVillage());
            existingFarmer.setCrop(updatedFarmer.getCrop());
            existingFarmer.setAge(updatedFarmer.getAge());

            return farmerRepository.save(existingFarmer);
        }

        throw new FarmerNotFoundException(
                "Farmer not found with id: " + id
        );
    }
    public void deleteFarmer(Long id) {

        Optional<Farmer> farmer = farmerRepository.findById(id);

        if (farmer.isPresent()) {
            farmerRepository.delete(farmer.get());
            return;
        }

        throw new FarmerNotFoundException(
                "Farmer not found with id: " + id
        );
    }
}