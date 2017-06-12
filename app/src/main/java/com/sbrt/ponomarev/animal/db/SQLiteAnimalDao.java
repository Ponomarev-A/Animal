package com.sbrt.ponomarev.animal.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.sbrt.ponomarev.animal.bean.Animal;

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
    private static final int DEFAULT_ANIMAL_ID = -1;

    public SQLiteAnimalDao(Context context) {
        super(context, NAME, null, CURRENT_VERSION);
    }

    public SQLiteAnimalDao(Context context, String name, int version) {
        super(context, name, null, version);
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
                animals.add(SQLiteUtils.createAnimal(cursor));
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
        Animal animal = null;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.query(TABLE_NAME,
                    null,
                    AnimalsContract.Animal._ID + "= ?",
                    new String[]{String.valueOf(id)},
                    null,
                    null,
                    null
            );
            cursor.moveToFirst();
            if (cursor.isFirst()) {
                animal = new Animal(SQLiteUtils.createAnimal(cursor));
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
        return animal;
    }

    @Override
    public long insertAnimal(Animal animal) {
        long id = DEFAULT_ANIMAL_ID;
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            id = db.insert(TABLE_NAME, null, SQLiteUtils.createValuesFromAnimal(animal));
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
            db.close();
        }
        return id;
    }

    @Override
    public int updateAnimal(Animal animal) {
        int rowUpdated = 0;
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            rowUpdated = db.update(TABLE_NAME,
                    SQLiteUtils.createValuesFromAnimal(animal),
                    AnimalsContract.Animal._ID + " = ?",
                    new String[]{String.valueOf(animal.getId())}
            );
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
            db.close();
        }
        return rowUpdated;
    }

    @Override
    public int deleteAnimal(long id) {
        int rowDeleted = 0;
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            rowDeleted = db.delete(TABLE_NAME,
                    AnimalsContract.Animal._ID + " = ?",
                    new String[]{String.valueOf(id)}
            );
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
            db.close();
        }
        return rowDeleted;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = new StringBuilder()
                .append("CREATE TABLE ").append(TABLE_NAME).append("(")
                .append(AnimalsContract.Animal._ID).append(" INTEGER PRIMARY KEY AUTOINCREMENT, ")
                .append(AnimalsContract.Animal.NAME).append(" TEXT NOT NULL, ")
                .append(AnimalsContract.Animal.SPECIES).append(" TEXT NOT NULL, ")
                .append(AnimalsContract.Animal.WEIGHT).append(" REAL NOT NULL, ")
                .append(AnimalsContract.Animal.HEIGHT).append(" REAL NOT NULL")
                .append(");")
                .toString();
        Log.e(TAG, "onCreate: " + sql);
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO: implement upgrade logic
    }
}
