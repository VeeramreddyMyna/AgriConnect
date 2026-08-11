package com.myna.agriconnect.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.myna.agriconnect.model.Farmer;
import com.myna.agriconnect.service.FarmerService;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import com.myna.agriconnect.exception.FarmerNotFoundException;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import com.myna.agriconnect.dto.FarmerRequestDTO;
import com.myna.agriconnect.dto.FarmerResponseDTO;
import jakarta.validation.Valid;
import org.springframework.web.bind.MethodArgumentNotValidException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

    @RestController
    @RequestMapping("/farmers")
    public class FarmerController {
        private final FarmerService farmerService;

        public FarmerController(FarmerService farmerService) {
            this.farmerService = farmerService;
        }
        @GetMapping("/get")
        public List<FarmerResponseDTO> getFarmers() {
            return farmerService.getAllFarmers();
        }
        @GetMapping("/{id}")
        public FarmerResponseDTO getFarmerById(@PathVariable Long id) {
            return farmerService.getFarmerById(id);
        }
        @GetMapping("/crop/{crop}")
        public List<FarmerResponseDTO> getFarmersByCrop(
                @PathVariable String crop) {

            return farmerService.getFarmersByCrop(crop);
        }
        @GetMapping("/village/{village}")
        public List<FarmerResponseDTO> getFarmersByVillage(
                @PathVariable String village) {

            return farmerService.getFarmersByVillage(village);
        }
        @GetMapping("/crop/{crop}/age/{age}")
        public List<FarmerResponseDTO> getFarmersByCropAndMinimumAge(
                @PathVariable String crop,
                @PathVariable Integer age) {

            return farmerService.getFarmersByCropAndMinimumAge(crop, age);
        }
        @GetMapping("/sort/age")
        public List<FarmerResponseDTO> getFarmersByAgeAscending() {

            return farmerService.getFarmersByAgeAscending();
        }
        @GetMapping("/sort/age-desc")
        public List<FarmerResponseDTO> getFarmersByAgeDescending() {

            return farmerService.getFarmersByAgeDescending();
        }
        @GetMapping("/page")
        public Page<FarmerResponseDTO> getFarmersWithPagination(
                Pageable pageable) {

            return farmerService.getFarmersWithPagination(pageable);
        }

        @PostMapping("/add")
        public ResponseEntity<FarmerResponseDTO> addFarmer(
                @RequestBody @Valid FarmerRequestDTO farmerRequestDTO) {

            FarmerResponseDTO responseDTO = farmerService.addFarmer(farmerRequestDTO);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(responseDTO);
        }
        @PutMapping("/update/{id}")
        public ResponseEntity<FarmerResponseDTO> updateFarmer(
                @PathVariable Long id,
                @RequestBody @Valid FarmerRequestDTO farmerRequestDTO) {

            FarmerResponseDTO responseDTO =
                    farmerService.updateFarmer(id, farmerRequestDTO);

            return ResponseEntity.ok(responseDTO);
        }
        @DeleteMapping("/delete/{id}")
        public ResponseEntity<Void> deleteFarmer(@PathVariable Long id) {

            farmerService.deleteFarmer(id);

            return ResponseEntity.noContent().build();
        }

        }



