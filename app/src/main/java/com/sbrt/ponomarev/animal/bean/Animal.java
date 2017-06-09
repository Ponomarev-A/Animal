package com.sbrt.ponomarev.animal.bean;

import java.io.Serializable;

/**
 * Created by user15 on 18.05.2017.
 */

public class Animal implements Serializable {

    private long mId;
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


    public Animal(long id, String name, String species, Float weight, Float height) {
        this.mId = id;
        this.mName = name;
        this.mSpecies = species;
        this.mWeight = weight;
        this.mHeight = height;
    }


    public Animal(Animal animal) {
        this.mId = animal.mId;
        this.mName = animal.mName;
        this.mSpecies = animal.mSpecies;
        this.mWeight = animal.mWeight;
        this.mHeight = animal.mHeight;
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

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        this.mId = id;
    }

    @Override
    public int hashCode() {
        int result = (int) (mId ^ (mId >>> 32));
        result = 31 * result + (mName != null ? mName.hashCode() : 0);
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

        if (mId != animal.mId) return false;
        if (mName != null ? !mName.equals(animal.mName) : animal.mName != null) return false;
        if (mSpecies != null ? !mSpecies.equals(animal.mSpecies) : animal.mSpecies != null) return false;
        if (mWeight != null ? !mWeight.equals(animal.mWeight) : animal.mWeight != null) return false;
        return mHeight != null ? mHeight.equals(animal.mHeight) : animal.mHeight == null;

    }

    @Override
    public String toString() {
        return "Animal{" +
                "mId=" + mId +
                ", mName='" + mName + '\'' +
                ", mSpecies='" + mSpecies + '\'' +
                ", mWeight=" + mWeight +
                ", mHeight=" + mHeight +
                '}';
    }
}
