package com.example.battlepets;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PetDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "battlepets.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_PETS = "pets";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_TYPE = "type";
    private static final String COLUMN_STRENGTH = "strength";

    //Constructor using super to call the parent class
    public PetDatabaseHelper (Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTable = "CREATE TABLE " + TABLE_PETS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_NAME + " TEXT," +
                COLUMN_TYPE + " TEXT," +
                COLUMN_STRENGTH + " INTEGER" + ")";
        sqLiteDatabase.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_PETS);
        onCreate(sqLiteDatabase);
    }

    public void addPet(String name, String type){
        int strength = new Random().nextInt(100) + 1; //Establishes a 1 to 100 range
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_TYPE, type);
        values.put(COLUMN_STRENGTH, strength);

        //Database Operations
        db.insert(TABLE_PETS, null, values);
        db.close();
    }

    public List<Pet> getAllPets(){
        List<Pet> pets = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_PETS, null);

        if(cursor.moveToFirst()){
            do{
                pets.add(new Pet(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getInt(3)
                ));
            }while(cursor.moveToNext());
        }

        //House Keeping
        cursor.close();
        db.close();

        return pets;
    }
}
