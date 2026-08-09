package com.myna.agriconnect.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
public class FarmerRequestDTO {
    @NotBlank(message = "Name cannot be blank")
    private String name;

    @Min(value = 18, message = "Age must be at least 18")
    @Max(value = 100, message = "Age cannot exceed 100")
    private int age;

    @NotBlank(message = "Village cannot be blank")
    private String village;

    @NotBlank(message = "Crop cannot be blank")
    private String crop;
    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public void setCrop(String crop) {
        this.crop = crop;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getVillage() {
        return village;
    }

    public String getCrop() {
        return crop;
    }
    }

