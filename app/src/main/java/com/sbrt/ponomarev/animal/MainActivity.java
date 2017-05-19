package com.sbrt.ponomarev.animal;

import android.os.Bundle;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String LOG = MainActivity.class.getName();

    private static final int LOADER_ID = 10;

    private Button mRefreshButton;
    private TextView mNameTextView;
    private TextView mHeightTextView;
    private TextView mWeightTextView;
    private TextView mSpeciesTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNameTextView = (TextView) findViewById(R.id.name);
        mSpeciesTextView = (TextView) findViewById(R.id.species);
        mWeightTextView = (TextView) findViewById(R.id.weight);
        mHeightTextView = (TextView) findViewById(R.id.height);
        mRefreshButton = (Button) findViewById(R.id.refresh);

        mRefreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportLoaderManager().getLoader(LOADER_ID).forceLoad();
            }
        });

        getSupportLoaderManager().initLoader(LOADER_ID, null, new AnimalLoaderCallbacks());

    }

    private class AnimalLoaderCallbacks implements android.support.v4.app.LoaderManager.LoaderCallbacks<Animal> {
        @Override
        public Loader<Animal> onCreateLoader(int id, Bundle args) {
            Log.e(LOG, "onCreateLoader");
            return new AnimalLoader(MainActivity.this);
        }

        @Override
        public void onLoadFinished(Loader<Animal> loader, Animal animal) {
            Log.e(LOG, "onLoadFinished");

            mNameTextView.setText(animal.getName());
            mSpeciesTextView.setText(animal.getSpecies());
            mWeightTextView.setText(String.valueOf(animal.getWeight()));
            mHeightTextView.setText(String.valueOf(animal.getHeight()));
        }

        @Override
        public void onLoaderReset(Loader<Animal> loader) {
            Log.e(LOG, "onLoaderReset");
        }
    }
}
