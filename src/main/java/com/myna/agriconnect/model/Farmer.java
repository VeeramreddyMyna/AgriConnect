package com.myna.agriconnect.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import java.util.List;
@Entity
public class Farmer {
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    public void setName(String name) {
        this.name = name;
    }

    public void setVillage(String village) {
        this.village = village;
    }


    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }


    public String getVillage() {
        return village;
    }

    public String getName() {
        return name;
    }

    private String name;
    private String village;
    private int age;
    @OneToMany(mappedBy = "farmer")
    private List<Crop> crops;
}
