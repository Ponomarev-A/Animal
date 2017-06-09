package com.sbrt.ponomarev.animal.db;

import android.content.ContentValues;
import android.database.Cursor;
import com.sbrt.ponomarev.animal.Animal;

public class SQLiteUtils {

    public static Animal createAnimal(Cursor cursor) {
        return new Animal(
                getLong(cursor, AnimalsContract.Animal._ID),
                getString(cursor, AnimalsContract.Animal.NAME),
                getString(cursor, AnimalsContract.Animal.SPECIES),
                getInt(cursor, AnimalsContract.Animal.WEIGHT),
                getInt(cursor, AnimalsContract.Animal.HEIGHT)
        );
    }

    public static long getLong(Cursor cursor, String columnName) {
        return cursor.getLong(cursor.getColumnIndex(columnName));
    }

    public static String getString(Cursor cursor, String columnName) {
        return cursor.getString(cursor.getColumnIndex(columnName));
    }

    public static int getInt(Cursor cursor, String columnName) {
        return cursor.getInt(cursor.getColumnIndex(columnName));
    }

    public static ContentValues createValuesFromAnimal(Animal animal) {
        ContentValues values = new ContentValues();
        values.put(AnimalsContract.Animal.NAME, animal.getName());
        values.put(AnimalsContract.Animal.SPECIES, animal.getSpecies());
        values.put(AnimalsContract.Animal.WEIGHT, animal.getWeight());
        values.put(AnimalsContract.Animal.HEIGHT, animal.getHeight());
        return values;
    }
}