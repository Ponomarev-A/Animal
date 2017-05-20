package com.sbrt.ponomarev.animal;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import java.util.List;

/**
 * Created by user15 on 18.05.2017.
 */

public class AnimalLoader extends AsyncTaskLoader<List<Animal>> implements OnContentChangeListener {

    private static final String LOG = AnimalLoader.class.getName();
    private List<Animal> mCachedData;
    private AnimalsStorage mAnimalsStorage;

    public AnimalLoader(Context context, AnimalsStorage mAnimalsStorage) {
        super(context);

        this.mAnimalsStorage = mAnimalsStorage;
        this.mAnimalsStorage.addOnContentChangeListener(this);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        Log.e(LOG, "onStartLoading");

        if (mCachedData == null || takeContentChanged()) {
            Log.e(LOG, "onStartLoading: forceLoad");
            forceLoad();
        }
    }

    @Override
    public void deliverResult(List<Animal> data) {
        super.deliverResult(data);

        mCachedData = data;
    }

    @Override
    public List<Animal> loadInBackground() {
        Log.e(LOG, "loadInBackground");
        return mAnimalsStorage.getAnimals();
    }

    @Override
    protected void onReset() {
        super.onReset();
        Log.e(LOG, "onReset");
        mAnimalsStorage.removeOnContentChangeListener(this);
    }

    @Override
    protected void onAbandon() {
        super.onAbandon();
        Log.e(LOG, "onAbandon");
    }


    @Override
    public void abandon() {
        super.abandon();
        Log.e(LOG, "abandon");
    }

    @Override
    public void onAnimalAdded(AnimalsStorage storage, Animal animal) {
        onContentChanged();
    }
}
