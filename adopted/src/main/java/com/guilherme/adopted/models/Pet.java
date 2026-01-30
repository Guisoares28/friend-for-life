package com.guilherme.adopted.models;

import com.guilherme.adopted.enums.SizeEnum;
import com.guilherme.adopted.enums.SpeciesEnum;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_pet")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer approximateAge;

    private Float weight;

    private String microchip;

    @Enumerated(EnumType.STRING)
    private SpeciesEnum especieEnum;

    @Enumerated(EnumType.STRING)
    private SizeEnum sizeEnum;
    
    private String race;

    private String address;

    private String description;

    public Pet() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getApproximateAge() {
        return approximateAge;
    }

    public void setApproximateAge(Integer approximateAge) {
        this.approximateAge = approximateAge;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public String getmicrochip() {
        return microchip;
    }

    public void setmicrochip(String microchip) {
        this.microchip = microchip;
    }

    public SpeciesEnum getEspecieEnum() {
        return especieEnum;
    }

    public void setEspecieEnum(SpeciesEnum especieEnum) {
        this.especieEnum = especieEnum;
    }

    public SizeEnum getSizeEnum() {
        return sizeEnum;
    }

    public void setSizeEnum(SizeEnum sizeEnum) {
        this.sizeEnum = sizeEnum;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
}
