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



