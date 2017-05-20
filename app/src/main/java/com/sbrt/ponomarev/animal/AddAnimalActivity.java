package com.sbrt.ponomarev.animal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * @author QuickNick.
 */

public class AddAnimalActivity extends AppCompatActivity {

    private AnimalsStorage mAnimalsStorage;

    private EditText mSpeciesEditText;
    private EditText mNameEditText;
    private EditText mWeightEditText;
    private EditText mHeightEditText;
    private Button mAddButton;
    private EditText[] mEditTexts;

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, AddAnimalActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AnimalsStorageProvider provider = (AnimalsStorageProvider) getApplication();
        mAnimalsStorage = provider.getAnimalsStorage();

        setContentView(R.layout.activity_add_animal);
        mSpeciesEditText = (EditText) findViewById(R.id.add_animal_species);
        mNameEditText = (EditText) findViewById(R.id.add_animal_name);
        mWeightEditText = (EditText) findViewById(R.id.add_animal_weight);
        mHeightEditText = (EditText) findViewById(R.id.add_animal_height);
        mAddButton = (Button) findViewById(R.id.add_animal);
        mEditTexts = new EditText[]{mSpeciesEditText, mWeightEditText, mHeightEditText, mNameEditText};
        for (EditText editText : mEditTexts) {
            editText.addTextChangedListener(new TextWatcherImpl());
        }

        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAnimal();
            }
        });
    }

    private void createAnimal() {
        String species = mSpeciesEditText.getText().toString();
        String name = mNameEditText.getText().toString();
        float weight = Float.valueOf(mWeightEditText.getText().toString());
        float height = Float.valueOf(mHeightEditText.getText().toString());
        Animal animal = new Animal(name, species, weight, height);
        mAnimalsStorage.addAnimal(animal);
        finish();
    }

    private class TextWatcherImpl implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            boolean buttonEnabled = true;
            for (EditText editText : mEditTexts) {
                if (TextUtils.isEmpty(editText.getText())) {
                    buttonEnabled = false;
                    break;
                }
            }
            mAddButton.setEnabled(buttonEnabled);
        }
    }
}
