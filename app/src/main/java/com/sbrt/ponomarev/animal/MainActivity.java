package com.sbrt.ponomarev.animal;

import android.os.Bundle;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String LOG = MainActivity.class.getName();

    private static final int LOADER_ID = 10;

    private RecyclerView mAnimalsView;
    private AnimalsAdapter mAnimalAdapter;
    private AnimalsStorage mAnimalsStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAnimalsStorage = ((AnimalsStorageProvider) getApplication()).getAnimalsStorage();

        mAnimalsView = (RecyclerView) findViewById(R.id.list);
        mAnimalAdapter = new AnimalsAdapter();
        mAnimalsView.setAdapter(mAnimalAdapter);

        getSupportLoaderManager().initLoader(LOADER_ID, null, new AnimalLoaderCallbacks());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_animal_menu_item:
                startActivity(AddAnimalActivity.newIntent(this));
                return true;

            case R.id.destroy_loader_menu_item:
                getSupportLoaderManager().destroyLoader(LOADER_ID);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class AnimalLoaderCallbacks implements android.support.v4.app.LoaderManager.LoaderCallbacks<List<Animal>> {
        @Override
        public Loader<List<Animal>> onCreateLoader(int id, Bundle args) {
            Log.e(LOG, "onCreateLoader");
            return new AnimalLoader(MainActivity.this, mAnimalsStorage);
        }

        @Override
        public void onLoadFinished(Loader<List<Animal>> loader, List<Animal> animals) {
            Log.e(LOG, "onLoadFinished");
            mAnimalAdapter.setAnimals(animals);
        }

        @Override
        public void onLoaderReset(Loader<List<Animal>> loader) {
            Log.e(LOG, "onLoaderReset");
        }
    }
}
