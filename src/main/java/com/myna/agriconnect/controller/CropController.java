package com.myna.agriconnect.controller;

import com.myna.agriconnect.dto.CropRequestDTO;
import com.myna.agriconnect.dto.CropResponseDTO;
import com.myna.agriconnect.service.CropService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.ResponseEntity;
@RestController
@RequestMapping("/crops")
public class CropController {

    private final CropService cropService;

    public CropController(CropService cropService) {
        this.cropService = cropService;
    }
    @PostMapping("/add")
    public CropResponseDTO addCrop(
            @RequestBody CropRequestDTO requestDTO) {

        return cropService.addCrop(requestDTO);
    }
    @GetMapping("/get")
    public List<CropResponseDTO> getAllCrops() {

        return cropService.getAllCrops();
    }
    @GetMapping("/{id}")
    public CropResponseDTO getCropById(@PathVariable Long id) {

        return cropService.getCropById(id);
    }
    @PutMapping("/{id}")
    public CropResponseDTO updateCrop(
            @PathVariable Long id,
            @RequestBody CropRequestDTO requestDTO) {

        return cropService.updateCrop(id, requestDTO);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCrop(@PathVariable Long id) {

        cropService.deleteCrop(id);

        return ResponseEntity
                .ok("Crop deleted successfully with id: " + id);
    }
}