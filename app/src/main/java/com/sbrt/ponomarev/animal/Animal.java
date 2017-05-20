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

    @Override
    public int hashCode() {
        int result = mName != null ? mName.hashCode() : 0;
        result = 31 * result + (mSpecies != null ? mSpecies.hashCode() : 0);
        result = 31 * result + (mWeight != null ? mWeight.hashCode() : 0);
        result = 31 * result + (mHeight != null ? mHeight.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Animal)) return false;

        Animal animal = (Animal) o;

        if (mName != null ? !mName.equals(animal.mName) : animal.mName != null) return false;
        if (mSpecies != null ? !mSpecies.equals(animal.mSpecies) : animal.mSpecies != null) return false;
        if (mWeight != null ? !mWeight.equals(animal.mWeight) : animal.mWeight != null) return false;
        return mHeight != null ? mHeight.equals(animal.mHeight) : animal.mHeight == null;

    }
}
