package com.sbrt.ponomarev.animal.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.sbrt.ponomarev.animal.Animal;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by user15 on 08.06.2017.
 */

public class SQLiteAnimalDao extends SQLiteOpenHelper implements AnimalsDAO {

    public static final String TABLE_NAME = "animals";
    private static final String NAME = "animals.db";
    private static final int CURRENT_VERSION = 1;

    public SQLiteAnimalDao(Context context) {
        super(context, NAME, null, CURRENT_VERSION);
    }

    public SQLiteAnimalDao(Context context, String name, int version) {
        super(context, name, null, version);
    }

    private static Animal createAnimal(Cursor cursor) {
        return new Animal(
                getLong(cursor, AnimalsContract.Animal._ID),
                getString(cursor, AnimalsContract.Animal.NAME),
                getString(cursor, AnimalsContract.Animal.SPECIES),
                getInt(cursor, AnimalsContract.Animal.WEIGHT),
                getInt(cursor, AnimalsContract.Animal.HEIGHT)
        );
    }

    private static long getLong(Cursor cursor, String columnName) {
        return cursor.getLong(cursor.getColumnIndex(columnName));
    }

    private static String getString(Cursor cursor, String columnName) {
        return cursor.getString(cursor.getColumnIndex(columnName));
    }

    private static int getInt(Cursor cursor, String columnName) {
        return cursor.getInt(cursor.getColumnIndex(columnName));
    }

    private static ContentValues createValuesFromAnimal(Animal animal) {
        ContentValues values = new ContentValues();
        values.put(AnimalsContract.Animal.NAME, animal.getName());
        values.put(AnimalsContract.Animal.SPECIES, animal.getSpecies());
        values.put(AnimalsContract.Animal.WEIGHT, animal.getWeight());
        values.put(AnimalsContract.Animal.HEIGHT, animal.getHeight());
        return values;
    }

    @Override
    public List<Animal> getAnimals() {
        List<Animal> animals = null;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
            animals = new ArrayList<>();
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                animals.add(createAnimal(cursor));
                cursor.moveToNext();
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }

        return animals;
    }

    @Override
    public Animal getAnimal(long id) {
        throw new UnsupportedOperationException("|Not yet implemented");
    }

    @Override
    public long insertAnimal(Animal animal) {
        long id = -1;
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            id = db.insert(TABLE_NAME, null, createValuesFromAnimal(animal));
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
            db.close();
        }
        return id;
    }

    @Override
    public int updateAnimal(Animal animal) {
        throw new UnsupportedOperationException("|Not yet implemented");
    }

    @Override
    public int deleteAnimal(Animal animal) {
        throw new UnsupportedOperationException("|Not yet implemented");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = new StringBuilder()
                .append("CREATE TABLE ").append(TABLE_NAME).append("(")
                .append(AnimalsContract.Animal._ID).append(" INTEGER PRIMARY KEY AUTOINCREMENT, ")
                .append(AnimalsContract.Animal.NAME).append(" TEXT NOT NULL, ")
                .append(AnimalsContract.Animal.SPECIES).append(" TEXT NOT NULL, ")
                .append(AnimalsContract.Animal.WEIGHT).append(" INTEGER NOT NULL, ")
                .append(AnimalsContract.Animal.HEIGHT).append(" INTEGER NOT NULL")
                .append(");")
                .toString();
        Log.e(TAG, "onCreate: " + sql);
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
