package com.example.gacha_kelompok;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "GachaKelompok.db";

    public DBHelper(Context context) {
        super(context, "GachaKelompok.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table users(id INTEGER primary key AUTOINCREMENT, username TEXT, password TEXT, email TEXT, phone NUMBER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users");
    }

    public Boolean insertData(String username, String password, String email, String phone) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("email", email);
        contentValues.put("phone", phone);
        long result = MyDB.insert("users", null, contentValues);
        if (result == -1) return false;
        else
            return true;
    }

    public boolean addOne(UserModel userModel){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", userModel.getUsername());
        contentValues.put("password", userModel.getPassword());
        contentValues.put("email", userModel.getEmail());
        contentValues.put("phone", userModel.getPhone());
        long result = MyDB.insert("users", null, contentValues);
        if (result == -1) return false;
        else
            return true;
    }

    public boolean updateOne(UserModel userModel){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", userModel.getUsername());
        contentValues.put("password", userModel.getPassword());
        contentValues.put("email", userModel.getEmail());
        contentValues.put("phone", userModel.getPhone());

        long result = MyDB.update("users", contentValues, "id=?", new String[]{String.valueOf(userModel.getId())});
        if (result == -1) return false;
        else
            return true;
    }

    public UserModel getOne(String username, String id) {
        UserModel user = new UserModel(-1, "error", "error", "error", "error");
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor;
        if(id != "-1"){
            cursor = MyDB.rawQuery("Select * from users where id = ?", new String[]{id});
        } else {
            cursor = MyDB.rawQuery("Select * from users where username = ?", new String[]{username});
        }

        if (cursor.moveToFirst()) {

            do {
                int CustomerId = cursor.getInt(0);
                String CustomerName = cursor.getString(1);
                String CustomerPhone = cursor.getString(2);
                String CustomerEmail = cursor.getString(3);
                String CustomerPassword = cursor.getString(4);

                user = new UserModel(CustomerId, CustomerName, CustomerEmail, CustomerPassword, CustomerPhone);

            } while (cursor.moveToNext());


        } else {

        }
            cursor.close();
            MyDB.close();
            return user;
    }

    public Boolean checkusername(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ?", new String[]{username});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkemail(String email) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where email = ?", new String[]{email});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkusernamepassword(String username, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ? and password = ?", new String[]{username, password});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

}