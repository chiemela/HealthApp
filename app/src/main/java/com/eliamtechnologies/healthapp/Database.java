package com.eliamtechnologies.healthapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {
    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        // create database "Userdata.db"
        super(context, "Userdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // create table "users"
        String qry1 = "create table users(username TEXT primary key, email, password)";
        db.execSQL(qry1);

        // create table "cart"
        String qry2 = "create table cart(username TEXT, product TEXT, price FLOAT, otype TEXT)";
        db.execSQL(qry2);

        // create table "cart"
        String qry3 = "create table orderplace(username TEXT, fullname TEXT, address TEXT, contactno TEXT, pincode INT, date TEXT, time TEXT, amount FLOAT, otype TEXT)";
        db.execSQL(qry3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // drops table "users"
        // db.execSQL("drop Table if exists users");
    }

    // register a new user by adding user details to the "users" table
    public void register(String username, String email, String password){
        SQLiteDatabase link = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("email", email);
        cv.put("password", password);
        link.insert("users", null, cv);
        link.close();
    }

    // check if "username" and "password" exists in the "users" table
    public int login(String username, String password){
        int result = 0;
        String str[] = new String[2];
        str[0] = username;
        str[1] = password;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("select * from users where username = ? and password = ?", str);
        if(c.moveToFirst()){
            result = 1;
        }
        return result;
    }

    // add item to "cart" table
    public void addCart(String username, String product, float price, String otype){
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("product", product);
        cv.put("price", price);
        cv.put("otype", otype);
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert("cart",null,cv);
        db.close();
    }

    // check if item exists in the "cart" table
    public int checkCart(String username, String product){
        int result = 0;
        String str[] = new String[2];
        str[0] = username;
        str[1] = product;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("select * from cart where username = ? and product = ?", str);
        if(c.moveToFirst()){
            result = 1;
        }
        db.close();
        return result;
    }

    // remove item from "cart" table
    public void removeCart(String username, String product){
        int result = 0;
        String str[] = new String[2];
        str[0] = username;
        str[1] = product;
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("cart", "username = ? and otype = ?", str);
        db.close();
    }

    // this function will get the details of the cart
    public ArrayList getCartData(String username, String otype){
        ArrayList<String> arr = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String str[] = new String[2];
        str[0] = username;
        str[1] = otype;
        Cursor c = db.rawQuery("select * from cart where username = ? and otype = ?", str);
        if(c.moveToFirst()){
            do{
                String product = c.getString(1);
                String price = c.getString(2);
                arr.add(product + "$" + price); // $ is for the separation of the two string because it's a special symbol
            }while (c.moveToNext());
        }
        db.close();
        return arr;
    }

    // insert new order into "orderplace" table
    public void addOrder(String username, String fullname, String address, String contact, int pincode, String date, String time, float price, String otype){
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("fullname", fullname);
        cv.put("address", address);
        cv.put("contactno", contact);
        cv.put("pincode", pincode);
        cv.put("date", date);
        cv.put("time", time);
        cv.put("amount", price);
        cv.put("otype", otype);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("orderplace", null, cv);
        db.close();
    }

    // fetch info from "orderplace" table
    public ArrayList getOderData(String username){
        ArrayList<String> arr = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String str[] = new String[1];
        str[0] = username;
        Cursor c = db.rawQuery("select * from orderplace where username = ?", str);
        if(c.moveToFirst()){
            do{
                arr.add(c.getString(1) +
                        "$" + c.getString(2) +
                        "$" + c.getString(3) +
                        "$" + c.getString(4) +
                        "$" + c.getString(5) +
                        "$" + c.getString(6) +
                        "$" + c.getString(7));
            }while(c.moveToNext());
        }
        db.close();
        return arr;
    }
}
