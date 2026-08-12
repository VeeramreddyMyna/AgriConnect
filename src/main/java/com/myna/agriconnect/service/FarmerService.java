package com.myna.agriconnect.service;

import com.myna.agriconnect.model.Farmer;
import com.myna.agriconnect.repository.FarmerRepository;
import org.springframework.stereotype.Service;
import com.myna.agriconnect.exception.FarmerNotFoundException;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import com.myna.agriconnect.dto.FarmerRequestDTO;
import com.myna.agriconnect.dto.FarmerResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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

        return toResponseDTO(savedFarmer);
    }
    public List<FarmerResponseDTO> getAllFarmers() {

        List<Farmer> farmers = farmerRepository.findAll();

        List<FarmerResponseDTO> responseList = new ArrayList<>();
        for (Farmer farmer : farmers) {

            responseList.add(toResponseDTO(farmer));
        }

        return responseList;
    }
    public FarmerResponseDTO getFarmerById(Long id) {

        Optional<Farmer> farmer = farmerRepository.findById(id);

        if (farmer.isPresent()) {
            return toResponseDTO(farmer.get());
        }

        throw new FarmerNotFoundException(
                "Farmer not found with id: " + id
        );
    }
    public List<FarmerResponseDTO> getFarmersByCrop(String crop) {

        List<Farmer> farmers = farmerRepository.findByCrop(crop);

        List<FarmerResponseDTO> responseList = new ArrayList<>();

        for (Farmer farmer : farmers) {

            responseList.add(toResponseDTO(farmer));
        }

        return responseList;
    }
    public List<FarmerResponseDTO> searchFarmersByCrop(String crop) {

        List<Farmer> farmers =
                farmerRepository.findByCropContainingIgnoreCase(crop);

        List<FarmerResponseDTO> responseList = new ArrayList<>();

        for (Farmer farmer : farmers) {
            responseList.add(toResponseDTO(farmer));
        }

        return responseList;
    }
    public Page<FarmerResponseDTO> searchFarmersByCropWithPagination(
            String crop, Pageable pageable) {

        Page<Farmer> farmerPage;

        if (crop == null || crop.isBlank()) {
            farmerPage = farmerRepository.findAll(pageable);
        } else {
            farmerPage =
                    farmerRepository.findByCropContainingIgnoreCase(crop, pageable);
        }

        return farmerPage.map(this::toResponseDTO);
    }
    public List<FarmerResponseDTO> searchFarmersByName(String name) {

        List<Farmer> farmers =
                farmerRepository.findByNameContainingIgnoreCase(name);

        List<FarmerResponseDTO> responseList = new ArrayList<>();

        for (Farmer farmer : farmers) {
            responseList.add(toResponseDTO(farmer));
        }

        return responseList;
    }
    public List<FarmerResponseDTO> searchFarmersByVillage(String village) {

        List<Farmer> farmers =
                farmerRepository.findByVillageContainingIgnoreCase(village);

        List<FarmerResponseDTO> responseList = new ArrayList<>();

        for (Farmer farmer : farmers) {
            responseList.add(toResponseDTO(farmer));
        }

        return responseList;
    }
    public List<FarmerResponseDTO> getFarmersByVillage(String village) {

        List<Farmer> farmers = farmerRepository.findByVillage(village);

        List<FarmerResponseDTO> responseList = new ArrayList<>();

        for (Farmer farmer : farmers) {
            responseList.add(toResponseDTO(farmer));
        }

        return responseList;
    }
    public List<FarmerResponseDTO> getFarmersByAgeAscending() {

        List<Farmer> farmers = farmerRepository.findAllByOrderByAgeAsc();

        List<FarmerResponseDTO> responseList = new ArrayList<>();

        for (Farmer farmer : farmers) {
            responseList.add(toResponseDTO(farmer));
        }

        return responseList;
    }
    public List<FarmerResponseDTO> getFarmersByAgeDescending() {

        List<Farmer> farmers = farmerRepository.findAllByOrderByAgeDesc();

        List<FarmerResponseDTO> responseList = new ArrayList<>();

        for (Farmer farmer : farmers) {
            responseList.add(toResponseDTO(farmer));
        }

        return responseList;
    }
    public List<FarmerResponseDTO> getFarmersByCropAndMinimumAge(
            String crop, Integer age) {

        List<Farmer> farmers =
                farmerRepository.findByCropAndAgeGreaterThanEqual(crop, age);

        List<FarmerResponseDTO> responseList = new ArrayList<>();

        for (Farmer farmer : farmers) {
            responseList.add(toResponseDTO(farmer));
        }

        return responseList;
    }
    public Page<FarmerResponseDTO> getFarmersWithPagination(Pageable pageable) {

        Page<Farmer> farmerPage = farmerRepository.findAll(pageable);

        return farmerPage.map(this::toResponseDTO);
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

            return toResponseDTO(savedFarmer);
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
    private FarmerResponseDTO toResponseDTO(Farmer farmer) {

        FarmerResponseDTO responseDTO = new FarmerResponseDTO();

        responseDTO.setId(farmer.getId());
        responseDTO.setName(farmer.getName());
        responseDTO.setAge(farmer.getAge());
        responseDTO.setVillage(farmer.getVillage());
        responseDTO.setCrop(farmer.getCrop());

        return responseDTO;
    }
}