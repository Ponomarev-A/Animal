package com.sbrt.ponomarev.animal.UI;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.sbrt.ponomarev.animal.R;
import com.sbrt.ponomarev.animal.bean.Animal;
import com.sbrt.ponomarev.animal.bean.AnimalsStorage;
import com.sbrt.ponomarev.animal.bean.AnimalsStorageProvider;

/**
 * @author QuickNick.
 */

public class AnimalActivity extends AppCompatActivity {

    private AnimalsStorage mAnimalsStorage;

    private EditText mSpeciesEditText;
    private EditText mNameEditText;
    private EditText mWeightEditText;
    private EditText mHeightEditText;
    private Button mSaveButton;
    private Button mCancelButton;
    private Button mDeleteButton;
    private EditText[] mEditTexts;
    private long id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AnimalsStorageProvider provider = (AnimalsStorageProvider) getApplication();
        mAnimalsStorage = provider.getAnimalsStorage();

        setContentView(R.layout.activity_add_animal);
        initViews();

        id = getIntent().getLongExtra(MainActivity.KEY_ID, -1);
        if (id != -1) {
            updateViews(mAnimalsStorage.getAnimal(id));
        }

        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animal animal = createAnimal();
                if (id == -1) {
                    mAnimalsStorage.addAnimal(animal);
                } else {
                    animal.setId(id);
                    mAnimalsStorage.updateAnimal(animal);
                }
                setResult(RESULT_OK);
                finish();
            }
        });

        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAnimalsStorage.deleteAnimal(id);
                setResult(RESULT_OK);
                finish();
            }
        });

    }

    private void initViews() {
        mSpeciesEditText = (EditText) findViewById(R.id.add_animal_species);
        mNameEditText = (EditText) findViewById(R.id.add_animal_name);
        mWeightEditText = (EditText) findViewById(R.id.add_animal_weight);
        mHeightEditText = (EditText) findViewById(R.id.add_animal_height);
        mSaveButton = (Button) findViewById(R.id.save);
        mCancelButton = (Button) findViewById(R.id.cancel);
        mDeleteButton = (Button) findViewById(R.id.delete);
        mEditTexts = new EditText[]{mSpeciesEditText, mWeightEditText, mHeightEditText, mNameEditText};
        for (EditText editText : mEditTexts) {
            editText.addTextChangedListener(new TextWatcherImpl());
        }
    }

    private void updateViews(Animal animal) {
        mSpeciesEditText.setText(animal.getSpecies());
        mNameEditText.setText(animal.getName());
        mWeightEditText.setText(String.valueOf(animal.getWeight()));
        mHeightEditText.setText(String.valueOf(animal.getHeight()));

    }

    private Animal createAnimal() {
        String species = mSpeciesEditText.getText().toString();
        String name = mNameEditText.getText().toString();
        float weight = Float.valueOf(mWeightEditText.getText().toString());
        float height = Float.valueOf(mHeightEditText.getText().toString());
        return new Animal(name, species, weight, height);
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
            mSaveButton.setEnabled(buttonEnabled);
        }
    }
}
