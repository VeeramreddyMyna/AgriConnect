package com.myna.agriconnect.service;

import com.myna.agriconnect.repository.CropRepository;
import com.myna.agriconnect.repository.FarmerRepository;
import org.springframework.stereotype.Service;
import com.myna.agriconnect.dto.CropRequestDTO;
import com.myna.agriconnect.dto.CropResponseDTO;
import com.myna.agriconnect.exception.FarmerNotFoundException;
import com.myna.agriconnect.model.Crop;
import com.myna.agriconnect.model.Farmer;
import java.util.List;
import java.util.ArrayList;
import com.myna.agriconnect.exception.CropNotFoundException;

@Service
public class CropService {

    private final CropRepository cropRepository;
    private final FarmerRepository farmerRepository;

    public CropService(
            CropRepository cropRepository,
            FarmerRepository farmerRepository) {

        this.cropRepository = cropRepository;
        this.farmerRepository = farmerRepository;
    }
    public CropResponseDTO addCrop(CropRequestDTO requestDTO) {

        Farmer farmer = farmerRepository.findById(requestDTO.getFarmerId())
                .orElseThrow(() ->
                        new FarmerNotFoundException(
                                "Farmer not found with id: "
                                        + requestDTO.getFarmerId()
                        )
                );

        Crop crop = new Crop();

        crop.setCropName(requestDTO.getCropName());
        crop.setSeason(requestDTO.getSeason());
        crop.setArea(requestDTO.getArea());
        crop.setFarmer(farmer);

        Crop savedCrop = cropRepository.save(crop);

        return toResponseDTO(savedCrop);
    }
    public List<CropResponseDTO> getAllCrops() {

        List<Crop> crops = cropRepository.findAll();

        List<CropResponseDTO> responseList = new ArrayList<>();

        for (Crop crop : crops) {
            responseList.add(toResponseDTO(crop));
        }

        return responseList;
    }
    public CropResponseDTO getCropById(Long id) {

        Crop crop = cropRepository.findById(id)
                .orElseThrow(() ->
                        new CropNotFoundException(
                                "Crop not found with id: " + id
                        )
                );

        return toResponseDTO(crop);
    }
    public CropResponseDTO updateCrop(Long id, CropRequestDTO requestDTO) {

        Crop crop = cropRepository.findById(id)
                .orElseThrow(() ->
                        new CropNotFoundException(
                                "Crop not found with id: " + id
                        )
                );

        Farmer farmer = farmerRepository.findById(requestDTO.getFarmerId())
                .orElseThrow(() ->
                        new FarmerNotFoundException(
                                "Farmer not found with id: "
                                        + requestDTO.getFarmerId()
                        )
                );

        crop.setCropName(requestDTO.getCropName());
        crop.setSeason(requestDTO.getSeason());
        crop.setArea(requestDTO.getArea());
        crop.setFarmer(farmer);

        Crop updatedCrop = cropRepository.save(crop);

        return toResponseDTO(updatedCrop);
    }
    public void deleteCrop(Long id) {

        Crop crop = cropRepository.findById(id)
                .orElseThrow(() ->
                        new CropNotFoundException(
                                "Crop not found with id: " + id
                        )
                );

        cropRepository.delete(crop);
    }
    private CropResponseDTO toResponseDTO(Crop crop) {

        CropResponseDTO responseDTO = new CropResponseDTO();

        responseDTO.setId(crop.getId());
        responseDTO.setCropName(crop.getCropName());
        responseDTO.setSeason(crop.getSeason());
        responseDTO.setArea(crop.getArea());
        responseDTO.setFarmerId(crop.getFarmer().getId());

        return responseDTO;
    }

}