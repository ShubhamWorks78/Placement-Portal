package com.seals.shubham.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by shubham on 12/25/2016.
 */

public class dbController extends SQLiteOpenHelper{

    public dbController(Context context){
        super(context,"LoginData.db",null,2);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String Query = "Create table LoginDb(RegId varchar(11) not null primary key,Password varchar(20) not null,Mobile varchar(10) not null,Gender varchar(6) not null,Branch varchar(5) not null)";
        sqLiteDatabase.execSQL(Query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String query = "Drop table if exists";
        sqLiteDatabase.execSQL(query);
    }

    public void InsertData(String RegId,String Password,String Mobile,String Gender,String Branch){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("RegId",RegId);
        cv.put("Password",Password);
        cv.put("Mobile",Mobile);
        cv.put("Gender",Gender);
        cv.put("Branch",Branch);
        db.insert("LoginDb",null,cv);
    }

    public int checkData(String RegId,String Password){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT RegId FROM LoginDb WHERE RegId = '"+RegId+"' AND Password='"+Password+"'";
        Cursor c = db.rawQuery(query,null);
        if(c.moveToNext()){
            return 1;
        }
        else{
            return 0;
        }
    }

    public String getVal(String regId){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT Mobile FROM LoginDb WHERE RegId = '"+regId+"'";
        Cursor c = db.rawQuery(query,null);
        if(c.moveToNext()){
            return c.getString(0);
        }else{
            return null;
        }
    }

}
