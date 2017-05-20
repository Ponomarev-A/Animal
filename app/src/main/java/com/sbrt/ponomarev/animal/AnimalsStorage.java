package com.sbrt.ponomarev.animal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by user15 on 18.05.2017.
 */

public class AnimalsStorage {

    //    private final List<Animal> sAnimals = new ArrayList<>();
    private static final List<Animal> sAnimals = Arrays.asList(
            new Animal("Bob", "Dog", 10f, 120f),
            new Animal("Jack", "Cat", 1.2f, 10f),
            new Animal("Mercy", "Cat", 9f, 60f),
            new Animal("Dilan", "Dog", 32f, 45f),
            new Animal("Bars", "Cat", 5f, 30f),
            new Animal("Moris", "Dog", 50f, 150f),
            new Animal("Benny", "Horse", 450.5f, 250f),
            new Animal("Milka", "Caw", 623.4f, 90.5f)
    );
    private List<Animal> mAnimals;
    private List<OnContentChangeListener> mOnContentChangeListeners = new ArrayList<>();

    public AnimalsStorage() {
        mAnimals = new ArrayList<>(sAnimals);
    }

    public void addAnimal(Animal animal) {
        mAnimals.add(animal);
        for (OnContentChangeListener listener : mOnContentChangeListeners) {
            listener.onAnimalAdded(this, animal);
        }
    }

    public List<Animal> getAnimals() {
        return new ArrayList<>(mAnimals);
    }

    public void addOnContentChangeListener(OnContentChangeListener listener) {
        mOnContentChangeListeners.add(listener);
    }

    public void removeOnContentChangeListener(OnContentChangeListener listener) {
        mOnContentChangeListeners.remove(listener);
    }
}
