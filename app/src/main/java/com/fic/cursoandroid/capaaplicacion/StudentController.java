package com.fic.cursoandroid.capaaplicacion;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.fic.cursoandroid.capadatos.DataAccess;
import com.fic.cursoandroid.capadatos.Student;

public class StudentController {
    public DataAccess dataAccess;
    final String STUDENT_TABLE_NAME = "students";

    public StudentController(Context context){
        dataAccess = new DataAccess(context);
    }

    public long saveStudent(Student student){
        SQLiteDatabase db = dataAccess.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name",student.getName());
        values.put("paternal_surname", student.getPaternalSurname());
        values.put("maternal_surname",student.getMaternalSurname());
        values.put("email",student.getEmail());
        values.put("phone_number",student.getPhoneNumber());
        values.put("grade",student.getGrade());
        values.put("group",student.getGroup());
        return db.insert(STUDENT_TABLE_NAME,null,values);
    }

    public int updateStudent(Student student){
        SQLiteDatabase db = dataAccess.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name",student.getName());
        values.put("paternal_surname", student.getPaternalSurname());
        values.put("maternal_surname",student.getMaternalSurname());
        values.put("email",student.getEmail());
        values.put("phone_number",student.getPhoneNumber());
        values.put("grade",student.getGrade());
        values.put("group",student.getGroup());
        String filter = "id=?";
        String[] args = {String.valueOf(student.getId())};
        return db.update(STUDENT_TABLE_NAME,values,filter,args);
    }

    public int deleteStudent(Student student){
        SQLiteDatabase db = dataAccess.getWritableDatabase();
        String[] args = {String.valueOf(student.getId())};
        return db.delete(STUDENT_TABLE_NAME,"id=?",args);

    }

}
