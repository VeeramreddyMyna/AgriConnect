package com.myna.agriconnect.service;

import com.myna.agriconnect.model.Farmer;
import com.myna.agriconnect.repository.FarmerRepository;
import org.springframework.stereotype.Service;
import com.myna.agriconnect.exception.FarmerNotFoundException;
import java.util.List;
import java.util.Optional;
import com.myna.agriconnect.dto.FarmerRequestDTO;
import com.myna.agriconnect.dto.FarmerResponseDTO;
import java.util.ArrayList;
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
    public List<FarmerResponseDTO> getAllFarmers() {

        List<Farmer> farmers = farmerRepository.findAll();

        List<FarmerResponseDTO> responseList = new java.util.ArrayList<>();

        for (Farmer farmer : farmers) {

            FarmerResponseDTO responseDTO = new FarmerResponseDTO();

            responseDTO.setId(farmer.getId());
            responseDTO.setName(farmer.getName());
            responseDTO.setAge(farmer.getAge());
            responseDTO.setVillage(farmer.getVillage());
            responseDTO.setCrop(farmer.getCrop());

            responseList.add(responseDTO);
        }

        return responseList;
    }
    public FarmerResponseDTO getFarmerById(Long id) {

        Optional<Farmer> farmer = farmerRepository.findById(id);

        if (farmer.isPresent()) {
            Farmer existingFarmer = farmer.get();

            FarmerResponseDTO responseDTO = new FarmerResponseDTO();

            responseDTO.setId(existingFarmer.getId());
            responseDTO.setName(existingFarmer.getName());
            responseDTO.setAge(existingFarmer.getAge());
            responseDTO.setVillage(existingFarmer.getVillage());
            responseDTO.setCrop(existingFarmer.getCrop());

            return responseDTO;
        }

        throw new FarmerNotFoundException(
                "Farmer not found with id: " + id
        );
    }
    public FarmerResponseDTO updateFarmer(Long id, FarmerRequestDTO farmerRequestDTO) {

        Optional<Farmer> farmer = farmerRepository.findById(id);

        if (farmer.isPresent()) {

            Farmer existingFarmer = farmer.get();

            existingFarmer.setName(farmerRequestDTO.getName());
            existingFarmer.setVillage(farmerRequestDTO.getVillage());
            existingFarmer.setCrop(farmerRequestDTO.getCrop());
            existingFarmer.setAge(farmerRequestDTO.getAge());

            Farmer savedFarmer = farmerRepository.save(existingFarmer);

            FarmerResponseDTO responseDTO = new FarmerResponseDTO();

            responseDTO.setId(savedFarmer.getId());
            responseDTO.setName(savedFarmer.getName());
            responseDTO.setAge(savedFarmer.getAge());
            responseDTO.setVillage(savedFarmer.getVillage());
            responseDTO.setCrop(savedFarmer.getCrop());

            return responseDTO;
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