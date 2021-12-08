package com.fic.cursoandroid.capadatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataAccess extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "cursoandroid";
    private static final String STUDENT_TABLE_NAME = "students";
    private static final int DATABASE_VERSION = 1;

    public DataAccess(Context context){
        super(context, DATABASE_NAME,null,DATABASE_VERSION);

    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(String.format("CREATE TABLE %s (id integer primary key autoincrement, name text, paternal_surname text, maternal_surname text, email text, phone_number text, grade text, group text)",STUDENT_TABLE_NAME));
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
