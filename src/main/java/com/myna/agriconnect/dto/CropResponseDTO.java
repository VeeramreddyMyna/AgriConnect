package com.myna.agriconnect.dto;

public class CropResponseDTO {
    private Long id;
    private String cropName;
    private String season;
    private Double area;
    private Long farmerId;
    public void setId(Long id) {
        this.id = id;
    }

    public void setCropName(String cropName) {
        this.cropName = cropName;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public void setFarmerId(Long farmerId) {
        this.farmerId = farmerId;
    }

    public Long getId() {
        return id;
    }

    public String getCropName() {
        return cropName;
    }

    public String getSeason() {
        return season;
    }

    public Double getArea() {
        return area;
    }

    public Long getFarmerId() {
        return farmerId;
    }
}
