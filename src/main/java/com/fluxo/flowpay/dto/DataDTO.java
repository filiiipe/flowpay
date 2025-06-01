package com.fluxo.flowpay.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DataDTO {

    private String year;

    @JsonAlias({"generation", "Generation"})
    private String generation;

    @JsonAlias({"price", "Price"})
    private String price;

    @JsonAlias({"color", "Color"})
    private String color;

    @JsonAlias({"capacity", "Capacity"})
    private String capacity;

    @JsonAlias("CPU model")
    private String cpuModel;

    @JsonAlias("Hard disk size")
    private String hardDiskSize;

    @JsonAlias("Strap Colour")
    private String strapColour;

    @JsonAlias("Case Size")
    private String caseSize;

    @JsonAlias("Screen size")
    private String screenSize;

    @JsonAlias({"description", "Description"})
    private String description;


    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getGeneration() {
        return generation;
    }

    public void setGeneration(String generation) {
        this.generation = generation;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getCpuModel() {
        return cpuModel;
    }

    public void setCpuModel(String cpuModel) {
        this.cpuModel = cpuModel;
    }

    public String getHardDiskSize() {
        return hardDiskSize;
    }

    public void setHardDiskSize(String hardDiskSize) {
        this.hardDiskSize = hardDiskSize;
    }

    public String getStrapColour() {
        return strapColour;
    }

    public void setStrapColour(String strapColour) {
        this.strapColour = strapColour;
    }

    public String getCaseSize() {
        return caseSize;
    }

    public void setCaseSize(String caseSize) {
        this.caseSize = caseSize;
    }

    public String getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(String screenSize) {
        this.screenSize = screenSize;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}