package com.sbrt.ponomarev.animal;

/**
 * Created by user15 on 18.05.2017.
 */

public class Animal {

    private String name;
    private String species;
    private Float weight;
    private Float height;


    public Animal(String name, String species, Float weight, Float height) {
        this.name = name;
        this.species = species;
        this.weight = weight;
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public String getSpecies() {
        return species;
    }

    public Float getWeight() {
        return weight;
    }

    public Float getHeight() {
        return height;
    }
}
