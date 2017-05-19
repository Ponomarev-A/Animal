package com.sbrt.ponomarev.animal;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

/**
 * Created by user15 on 18.05.2017.
 */

public class AnimalLoader extends AsyncTaskLoader<Animal> {

    private static final String LOG = AnimalLoader.class.getName();

    public AnimalLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        Log.e(LOG, "onStartLoading");
        forceLoad();
    }

    @Override
    public Animal loadInBackground() {
        Log.e(LOG, "loadInBackground");
        return AnimalStorage.getAnimal();
    }
}
