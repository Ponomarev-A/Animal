package com.sbrt.ponomarev.animal;

/**
 * Created by user15 on 18.05.2017.
 */

public class Animal {

    private String mName;
    private String mSpecies;
    private Float mWeight;
    private Float mHeight;


    public Animal(String name, String species, Float weight, Float height) {
        this.mName = name;
        this.mSpecies = species;
        this.mWeight = weight;
        this.mHeight = height;
    }

    public String getName() {
        return mName;
    }

    public String getSpecies() {
        return mSpecies;
    }

    public Float getWeight() {
        return mWeight;
    }

    public Float getHeight() {
        return mHeight;
    }
}
