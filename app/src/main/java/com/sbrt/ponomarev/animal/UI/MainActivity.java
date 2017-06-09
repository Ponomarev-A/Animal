package com.sbrt.ponomarev.animal.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.sbrt.ponomarev.animal.R;
import com.sbrt.ponomarev.animal.bean.Animal;
import com.sbrt.ponomarev.animal.bean.AnimalsStorage;
import com.sbrt.ponomarev.animal.bean.AnimalsStorageProvider;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String KEY_ID = "KEY_ID";
    private static final String LOG = MainActivity.class.getName();
    private static final int LOADER_ID = 10;
    private static final int ANIMAL_ACTIVITY_REQUEST_CODE = 10;
    private RecyclerView mAnimalsView;
    private AnimalsAdapter mAnimalAdapter;
    private AnimalsStorage mAnimalsStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAnimalsStorage = ((AnimalsStorageProvider) getApplication()).getAnimalsStorage();

        mAnimalsView = (RecyclerView) findViewById(R.id.list);
        mAnimalAdapter = new AnimalsAdapter(MainActivity.this);
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
                startActivity(new Intent(MainActivity.this, AnimalActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        int position = mAnimalsView.getChildLayoutPosition(v);
        Animal animal = mAnimalAdapter.getAnimal(position);
        Log.e(LOG, "click on item: " + animal);

        Intent intent = new Intent(MainActivity.this, AnimalActivity.class);
        intent.putExtra(KEY_ID, animal.getId());
        startActivityForResult(intent, ANIMAL_ACTIVITY_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ANIMAL_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            mAnimalAdapter.notifyDataSetChanged();
        }
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
