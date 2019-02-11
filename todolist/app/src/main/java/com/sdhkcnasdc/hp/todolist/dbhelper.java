package com.sdhkcnasdc.hp.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class dbhelper extends SQLiteOpenHelper {

    public static String DB_NAM = "database.db";
    public static String col1 = "ID";
    public static String col2 = "TASK";
    public static String col3 = "DESCR";
    public static String tablename = "todolist";

    public dbhelper(Context context) {
        super(context, DB_NAM, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ tablename+"(ID integer primary key autoincrement, TASK text, DESCR text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long insertData(String task, String descr){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(col2,task);
        values.put(col3,descr);
        long result = db.insert(tablename,null,values);
        return result;
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+tablename,null);
        return res;
    }

    public long updateData(String id, String task, String descr){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(col3,descr);
        values.put(col2,task);

        long res = db.update(tablename,values,"ID=?",new String[] {id});
        return res;
    }
    public Integer deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        int res = db.delete(tablename,"ID=?",new String[]{id});
        return res;
    }
}
