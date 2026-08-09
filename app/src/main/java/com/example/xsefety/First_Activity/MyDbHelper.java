package com.example.xsefety.First_Activity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class MyDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="x_sefety";
    private static final String GUARDIAN_TABLE="guardian_table";
    private static final String USER_TABLE="user_table";
    private static final int DATABASE_VERSION=1;


    public MyDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_USER_TABLE_QUERY="CREATE TABLE "+USER_TABLE+"(id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT,phone TEXT,email TEXT,password TEXT,pin TEXT)";
        String CREATE_GUARDIAN_TABLE_QUERY="CREATE TABLE "+GUARDIAN_TABLE+"(id INTEGER PRIMARY KEY AUTOINCREMENT,phone TEXT,UNIQUE(phone) ON CONFLICT REPLACE)";
        db.execSQL(CREATE_USER_TABLE_QUERY);
        db.execSQL(CREATE_GUARDIAN_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+USER_TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+GUARDIAN_TABLE);
        onCreate(db);
    }

    public boolean InsertUserHelper(String name1,String phone1,String email1,String password1,String pin1)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("name",name1);
        contentValues.put("phone",phone1);
        contentValues.put("email",email1);
        contentValues.put("password",password1);
        contentValues.put("pin",pin1);
        long l=sqLiteDatabase.insert(USER_TABLE,null,contentValues);
        sqLiteDatabase.close();
        return l > 0;
    }
    public boolean InsertGuardianHelper(String phone1)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("phone",phone1);
        long l=sqLiteDatabase.insert(GUARDIAN_TABLE,null,contentValues);
        sqLiteDatabase.close();
        return l > 0;
    }

    public ArrayList<UserModel> getUserAllData(String phone1)
    {
        ArrayList<UserModel> al=new ArrayList<>();
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        String query="SELECT * FROM "+USER_TABLE+" WHERE phone='"+phone1+"'";
        Cursor cursor=sqLiteDatabase.rawQuery(query,null);
        if(cursor.moveToFirst())
        {
            String name=cursor.getString(1);
            String phone=cursor.getString(2);
            String email=cursor.getString(3);
            String password=cursor.getString(4);
            String pin=cursor.getString(5);
            UserModel userModel=new UserModel();
            userModel.setName(name);
            userModel.setPhone(phone);
            userModel.setEmail(email);
            userModel.setPassword(password);
            userModel.setPin(pin);
            al.add(userModel);
            return al;
        }
        return null;
    }

    public ArrayList<UserModel_two> GetAllGuardianPhone()
    {
        ArrayList<UserModel_two> al = new ArrayList<UserModel_two>();
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM "+GUARDIAN_TABLE,null);
        if (cursor.moveToFirst())
        {
            do {
                UserModel_two userModel_two=new UserModel_two();
                String phone = cursor.getString(1);
                userModel_two.setGuardian_phone(phone);
                al.add(userModel_two);
            }while (cursor.moveToNext());
            return  al;
        }
        return null;
    }
    public boolean deleteHelper(String phone1)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        int i=sqLiteDatabase.delete(GUARDIAN_TABLE,"phone=?",new String[]{phone1});
        sqLiteDatabase.close();
        return i>0;
    }
}


