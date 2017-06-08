package com.sbrt.ponomarev.animal;

import com.sbrt.ponomarev.animal.db.AnimalsDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user15 on 18.05.2017.
 */

public class AnimalsStorage {

    private static final String TAG = AnimalsStorage.class.getSimpleName();
    private final List<OnContentChangeListener> mOnContentChangeListeners = new ArrayList<>();
    private AnimalsDAO dao;

    public AnimalsStorage(AnimalsDAO dao) {
        this.dao = dao;
    }

    public void addAnimal(Animal animal) {
        dao.insertAnimal(animal);
        for (OnContentChangeListener listener : mOnContentChangeListeners) {
            listener.onAnimalAdded(this, animal);
        }
    }

    public List<Animal> getAnimals() {
        return new ArrayList<>(dao.getAnimals());
    }


    public void addOnContentChangeListener(OnContentChangeListener listener) {
        mOnContentChangeListeners.add(listener);
    }

    public void removeOnContentChangeListener(OnContentChangeListener listener) {
        mOnContentChangeListeners.remove(listener);
    }
}
