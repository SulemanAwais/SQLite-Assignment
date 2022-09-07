package com.example.sqlitecrudapp;

import android.content.ContentValues;
import android.content.Context;
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
        "( StudentID Integer PRIMARY KEY AUTOINCREMENT, STUDENT_NAME Text,STUDENT_ROLLNUM Text ,ENROLLED_STUDENT BOOL)" ;
        sqLiteDatabase.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists StudentDetails");
    }
    
}
