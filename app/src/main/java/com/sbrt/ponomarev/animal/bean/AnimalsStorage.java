package com.sbrt.ponomarev.animal.bean;

import com.sbrt.ponomarev.animal.db.AnimalsDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user15 on 18.05.2017.
 */

public class AnimalsStorage {

    private static final String TAG = AnimalsStorage.class.getSimpleName();

    private final List<OnContentChangeListener> mOnContentChangeListeners = new ArrayList<>();

    private AnimalsDAO mDao;
    public AnimalsStorage(AnimalsDAO dao) {
        this.mDao = dao;
    }

    public List<OnContentChangeListener> getContentChangeListeners() {
        return mOnContentChangeListeners;
    }

    public void addAnimal(Animal animal) {
        mDao.insertAnimal(animal);
        for (OnContentChangeListener listener : mOnContentChangeListeners) {
            listener.onAnimalChanged();
        }
    }

    public List<Animal> getAnimals() {
        return new ArrayList<>(mDao.getAnimals());
    }

    public void updateAnimal(Animal animal) {
        mDao.updateAnimal(animal);
        for (OnContentChangeListener listener : mOnContentChangeListeners) {
            listener.onAnimalChanged();
        }
    }

    public void deleteAnimal(long id) {
        mDao.deleteAnimal(id);
        for (OnContentChangeListener listener : mOnContentChangeListeners) {
            listener.onAnimalChanged();
        }
    }

    public Animal getAnimal(long id) {
        return mDao.getAnimal(id);
    }

    public void addOnContentChangeListener(OnContentChangeListener listener) {
        mOnContentChangeListeners.add(listener);
    }

    public void removeOnContentChangeListener(OnContentChangeListener listener) {
        mOnContentChangeListeners.remove(listener);
    }
}
