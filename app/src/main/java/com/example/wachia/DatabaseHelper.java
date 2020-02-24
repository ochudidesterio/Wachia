package com.example.wachia;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="Client.db";
    public static final String TABLE_NAME="client_table";

    public static final String Id="ID_NUMBER";
    public static final String Phone="PHONE_NUMBER";
    public static final String Email="EMAIL";
    public static final String Description="PRODUCT_DESCRIPTION";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    db.execSQL("create table "+ TABLE_NAME+ "(ID_NUMBER INTEGER PRIMARY KEY, PHONE_NUMBER INTEGER, EMAIL TEXT, PRODUCT_DESCRIPTION TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);

    }
    public boolean insertData(String id,String phone, String email,String description){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Id,id);
        contentValues.put(Phone,phone);
        contentValues.put(Email,email);
        contentValues.put(Description,description);
        long result=db.insert(TABLE_NAME,null,contentValues);
        if(result == -1)
            return false;
        else
            return true;

    }
}
