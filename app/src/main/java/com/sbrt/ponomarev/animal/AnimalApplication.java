package com.sbrt.ponomarev.animal;

import android.app.Application;
import com.sbrt.ponomarev.animal.bean.AnimalsStorage;
import com.sbrt.ponomarev.animal.bean.AnimalsStorageProvider;
import com.sbrt.ponomarev.animal.db.SQLiteAnimalDao;

/**
 * Created by user15 on 20.05.2017.
 */

public class AnimalApplication extends Application implements AnimalsStorageProvider {

    private AnimalsStorage mAnimalsStorage;

    public AnimalApplication() {
        SQLiteAnimalDao dao = new SQLiteAnimalDao(this);
        mAnimalsStorage = new AnimalsStorage(dao);
    }

    @Override
    public AnimalsStorage getAnimalsStorage() {
        return mAnimalsStorage;
    }
}
