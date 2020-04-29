package com.gp.cspd.userInformation;

public class Address {
    private String governorate;  //mohafatha
    private String banner;       //lewa
    private String neighborhood; //alhay
    private String nearestNeighborhood; //aqrab malam sakani
    private String complex;      //tajamo sokani
    private String streetName;
    private int buildingNO;

    public Address() {
        this.governorate = "";
        this.banner = "";
        this.neighborhood = "";
        this.nearestNeighborhood = "";
        this.complex = "";
        this.streetName = "";
        this.buildingNO = 0;
    }

    public void setGovernorate(String governorate) {
        this.governorate = governorate;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public void setNearestNeighborhood(String nearestNeighborhood) {
        this.nearestNeighborhood = nearestNeighborhood;
    }

    public void setComplex(String complex) {
        this.complex = complex;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public void setBuildingNO(int buildingNO) {
        this.buildingNO = buildingNO;
    }

    public String getGovernorate() {
        return governorate;
    }

    public String getBanner() {
        return banner;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public String getNearestNeighborhood() {
        return nearestNeighborhood;
    }

    public String getComplex() {
        return complex;
    }

    public String getStreetName() {
        return streetName;
    }

    public int getBuildingNO() {
        return buildingNO;
    }
}
