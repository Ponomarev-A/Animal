package com.sbrt.ponomarev.animal.bean;

import com.sbrt.ponomarev.animal.UI.AnimalLoader;
import com.sbrt.ponomarev.animal.db.AnimalsDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user15 on 18.05.2017.
 */

public class AnimalsStorage {

    private static final String TAG = AnimalsStorage.class.getSimpleName();
    private final List<AnimalLoader> mOnContentChangeListeners = new ArrayList<>();
    private AnimalsDAO dao;

    public AnimalsStorage(AnimalsDAO dao) {
        this.dao = dao;
    }

    public void addAnimal(Animal animal) {
        dao.insertAnimal(animal);
        for (AnimalLoader listener : mOnContentChangeListeners) {
            listener.onAnimalChanged();
        }
    }

    public List<Animal> getAnimals() {
        return new ArrayList<>(dao.getAnimals());
    }

    public void updateAnimal(Animal animal) {
        dao.updateAnimal(animal);
        for (AnimalLoader listener : mOnContentChangeListeners) {
            listener.onAnimalChanged();
        }
    }

    public void deleteAnimal(long id) {
        dao.deleteAnimal(id);
        for (AnimalLoader listener : mOnContentChangeListeners) {
            listener.onAnimalChanged();
        }
    }

    public Animal getAnimal(long id) {
        return dao.getAnimal(id);
    }

    public void addOnContentChangeListener(AnimalLoader listener) {
        mOnContentChangeListeners.add(listener);
    }

    public void removeOnContentChangeListener(AnimalLoader listener) {
        mOnContentChangeListeners.remove(listener);
    }
}
