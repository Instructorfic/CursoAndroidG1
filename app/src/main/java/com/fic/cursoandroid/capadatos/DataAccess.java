package com.fic.cursoandroid.capadatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.fic.cursoandroid.utils.Constants;

public class DataAccess extends SQLiteOpenHelper {

    public DataAccess(Context context){
        super(context, Constants.DATABASE_NAME,null,Constants.DATABASE_VERSION);

    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(String.format("CREATE TABLE %s (id integer primary key autoincrement, name text, paternal_surname text, maternal_surname text, email text, phone_number text, grade text, student_group text)",Constants.STUDENT_TABLE_NAME));
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
