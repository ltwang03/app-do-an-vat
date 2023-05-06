package edu.huflit.app_do_an_vat.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBName = "Userdata.db";

    public DBHelper(@Nullable Context context) {
        super(context, "Userdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        CreateDatabase(MyDB);
    }

    public void CreateDatabase(SQLiteDatabase MyDB) {
        String createTableCustomer = ("CREATE TABLE customer" +
                "(customer_username TEXT primary key," +
                " customer_password TEXT, " +
                "customer_name TEXT, "+
                "customer_phone_number TEXT)");
        String createTableFood = ("CREATE TABLE food" +
                "(food_id INTEGER primary key autoincrement," +
                "food_name TEXT," +
                "food_type TEXT," +
                "food_price INTEGER," +
                " food_describe TEXT) ");
        MyDB.execSQL(createTableCustomer);
        MyDB.execSQL(createTableFood);
    }
    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists customer");
        MyDB.execSQL("drop Table if exists food");

    }

    public boolean insertCustomerData(String customer_username, String customer_password, String customer_name,String customer_phone_number) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("customer_username",customer_username);
        contentValues.put("customer_password",customer_password);
        contentValues.put("customer_name",customer_name);
        contentValues.put("customer_phone_number",customer_phone_number);
        long result = MyDB.insert("customer" , null,contentValues);
        if(result == -1) return false;
        else
            return true;


    }
    public boolean updateCustomerData(String customer_username, String customer_password, String customer_name,String customer_phone_number) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("customer_username",customer_username);
        contentValues.put("customer_password",customer_password);
        contentValues.put("customer_name",customer_name);
        contentValues.put("customer_phone_number",customer_phone_number);
        Cursor cursor  = MyDB.rawQuery("Select * from customer where customer_username = ?", new String[] {customer_username});
        if(cursor.getCount() > 0 ) {
            long result = MyDB.update("customer" , contentValues , "customer_username=?", new String[] {customer_username});
            if(result == -1) return false;
            else
                return true;
        }else return false;

    }
    public Boolean deleteOrderingData(String customer_username ) {
        SQLiteDatabase MyDB = this.getWritableDatabase();


        Cursor cursor  = MyDB.rawQuery("Select * from customer where customer_username = ?", new String[] {customer_username});
        if(cursor.getCount() > 0 ) {
            long result = MyDB.delete("customer"  , "customer_username=?", new String[] {customer_username});
            if(result == -1) return false;
            else
                return true;
        }else return false;

    }
    public boolean insertFoodData(String product_id, String product_name, int product_price,String product_describe) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("product_id",product_id);
        contentValues.put("product_name",product_name);
        contentValues.put("product_price",product_price);
        contentValues.put("product_describe",product_describe);
        long result = MyDB.insert("food" , null,contentValues);
        if(result == -1) return false;
        else
            return true;


    }
    public boolean updateFoodData(String product_id, String product_name, int product_price,String product_describe) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("product_id",product_id);
        contentValues.put("product_name",product_name);
        contentValues.put("product_price",product_price);
        contentValues.put("product_describe",product_describe);
        Cursor cursor  = MyDB.rawQuery("Select * from product_id where product_id = ?", new String[] {product_id});
        if(cursor.getCount() > 0 ) {
            long result = MyDB.update("customer" , contentValues , "food_id=?", new String[] {product_id});
            if(result == -1) return false;
            else
                return true;
        }else return false;

    }
    public Boolean deleteFoodData(int food_id ) {
        SQLiteDatabase MyDB = this.getWritableDatabase();


        Cursor cursor  = MyDB.rawQuery("Select * from food where food_id = ?", new String[] {String.valueOf(food_id)});
        if(cursor.getCount() > 0 ) {
            long result = MyDB.delete("food"  , "food_id=?", new String[] {String.valueOf(food_id)});
            if(result == -1) return false;
            else
                return true;
        }else return false;

    }
    public Boolean checkUsernameandpassword( String username ,String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from customer where customer_username = ? and customer_password = ? " , new String[] {  username,password});
        if(cursor.getCount() > 0)
            return true;
        else
            return false;
    }
    public Boolean checkUsername(String customer_username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from customer where customer_username = ?" , new String[] {customer_username});
        if(cursor.getCount() > 0)
            return true;
        else
            return false;
    }


}

