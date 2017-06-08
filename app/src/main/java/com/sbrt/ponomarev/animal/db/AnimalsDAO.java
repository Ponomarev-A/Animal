package com.sbrt.ponomarev.animal.db;

import com.sbrt.ponomarev.animal.Animal;

import java.util.List;

/**
 * Created by user15 on 08.06.2017.
 */

public interface AnimalsDAO {

    List<Animal> getAnimals();

    Animal getAnimal(long id);

    long insertAnimal(Animal animal);

    int updateAnimal(Animal animal);

    int deleteAnimal(Animal animal);
}
