package com.example.sqlitecrudapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context context) {
        super(context, "studentDB.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTableStatement = "CREATE TABLE STUDENT_TABLE"+
        "( StudentID Integer PRIMARY KEY AUTOINCREMENT, STUDENT_NAME Text,STUDENT_ROLLNUM Text)" ;
        sqLiteDatabase.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists STUDENT_TABLE");
    }
    public Boolean AddStudent(String name, String rollNum){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("STUDENT_NAME",name);
        values.put("STUDENT_ROLLNUM",rollNum);
        long insert =db.insert("STUDENT_TABLE",null,values);
        if (insert==-1)
        {
            return false;
        }
        else {
            return true;
        }
    }
    public Boolean Update(String name, String rollNumber){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("STUDENT_NAME",name);
        values.put("STUDENT_ROLLNUM",rollNumber);
        Cursor c=db.rawQuery("SELECT * from STUDENT_TABLE where STUDENT_ROLLNUM=?",new String[]{rollNumber});
        if (c.getCount()>0){
            long result=db. update("STUDENT_TABLE",values," STUDENT_ROLLNUM=?",new String[]{rollNumber});
            if(result==-1)
            {
                return false;
            }
            else return true;
        }
        else  return  false;
    }
    public  Boolean delete(String rollNumber){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor c=db.rawQuery("SELECT * from STUDENT_TABLE where STUDENT_ROLLNUM=?",new String[]{rollNumber});
        if (c.getCount()>0){
            long result=db.delete("STUDENT_TABLE"," STUDENT_ROLLNUM=?",new String[]{rollNumber});
            if(result==-1)
            {
                return false;
            }
            else return true;
        }
        else  return  false;
    }

    public Cursor getData()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor c=db.rawQuery("SELECT * from STUDENT_TABLE",null);
        return c;
    }

}
