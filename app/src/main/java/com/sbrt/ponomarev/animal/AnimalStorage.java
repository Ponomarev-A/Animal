package com.sbrt.ponomarev.animal;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by user15 on 18.05.2017.
 */

public class AnimalStorage {

    private static final List<Animal> mAnimals = Arrays.asList(
            new Animal("Bob", "Dog", 10f, 120f),
            new Animal("Jack", "Cat", 1.2f, 10f),
            new Animal("Mercy", "Cat", 9f, 60f),
            new Animal("Dilan", "Dog", 32f, 45f),
            new Animal("Bars", "Cat", 5f, 30f),
            new Animal("Moris", "Dog", 50f, 150f),
            new Animal("Benny", "Horse", 450.5f, 250f),
            new Animal("Milka", "Caw", 623.4f, 90.5f)
    );

    public static Animal getAnimal() {
        int ramdomAnimalIndex = new Random().nextInt(mAnimals.size());
        return mAnimals.get(ramdomAnimalIndex);
    }
}
