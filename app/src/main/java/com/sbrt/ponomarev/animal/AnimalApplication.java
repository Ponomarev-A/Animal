package com.sbrt.ponomarev.animal;

import android.app.Application;

/**
 * Created by user15 on 20.05.2017.
 */

public class AnimalApplication extends Application implements AnimalsStorageProvider {

    private AnimalsStorage mAnimalsStorage = new AnimalsStorage();

    @Override
    public AnimalsStorage getAnimalsStorage() {
        return mAnimalsStorage;
    }
}
