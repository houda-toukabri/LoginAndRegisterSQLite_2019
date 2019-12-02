package com.iset.loginandregistersqlite;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    DatabaseHelper(@Nullable Context context) {
        super(context, "Login.db" , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table user(email text primary key, password text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists user");
    }

    //Insertion dans la base de données
    boolean insert(String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password", password);
        long ins = db.insert("user",null,contentValues);
        return ins != -1;
        /*Equivalent à
        if(ins == -1)
            return false;
        return true;
        */
    }
    //Tester l'existence du mail
    boolean checkMail(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery("Select * from user where email=?",new String[]{email});
        return cursor.getCount() <= 0;
        /*Equivalent à
        if(cursor.getCount() > 0)
            return false;
        return true;
        */
    }
    //Vérification de l'email et du mot de passe
    boolean emailPassword(String email, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from user where email=? and password=?",new String[]{email, password});
        return cursor.getCount() > 0;
    }
}
