package com.pinpoint.models;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

@Entity
@Table(name = "census")
public class Census {

    @Id
    @GeneratedValue
    private long id;
    
    private String zip;
    private int population;
    private float medianAge;
    private int totalMales;
    private int totalFemales;
    private int totalHouseholds;
    private float averageHousehold;


    public Census() {
    }

    public Census(String zip, int population, float medianAge, int totalMales, 
                  int totalFemales, int totalHouseholds, float averageHousehold){
        this.zip = zip;
        this.population = population;
        this.medianAge = medianAge;
        this.totalMales = totalMales;
        this.totalFemales = totalFemales;
        this.totalHouseholds = totalHouseholds;
        this.averageHousehold = averageHousehold;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public float getMedianAge() {
        return medianAge;
    }

    public void setMedianAge(float medianAge) {
        this.medianAge = medianAge;
    }

    public int getTotalMales() {
        return totalMales;
    }

    public void setTotalMales(int totalMales) {
        this.totalMales = totalMales;
    }

    public int getTotalFemales() {
        return totalFemales;
    }

    public void setTotalFemales(int totalFemales) {
        this.totalFemales = totalFemales;
    }

    public int getTotalHouseholds() {
        return totalHouseholds;
    }

    public void setTotalHouseholds(int totalHouseholds) {
        this.totalHouseholds = totalHouseholds;
    }

    public float getAverageHousehold() {
        return averageHousehold;
    }

    public void setAverageHousehold(float averageHousehold) {
        this.averageHousehold = averageHousehold;
    }
}