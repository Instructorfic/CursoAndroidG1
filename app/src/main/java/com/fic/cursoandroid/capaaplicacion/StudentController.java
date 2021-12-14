package com.fic.cursoandroid.capaaplicacion;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.fic.cursoandroid.capadatos.DataAccess;
import com.fic.cursoandroid.capadatos.Student;
import com.fic.cursoandroid.utils.Constants;

import java.util.ArrayList;

public class StudentController {
    public DataAccess dataAccess;

    public StudentController(Context context){
        dataAccess = new DataAccess(context);
    }

    public long saveStudent(Student student){
        SQLiteDatabase db = dataAccess.getWritableDatabase();
        ContentValues values = new ContentValues();
        return db.insert(Constants.STUDENT_TABLE_NAME,null,prepareStudentObject(student));
    }

    public int updateStudent(Student student){
        SQLiteDatabase db = dataAccess.getWritableDatabase();
        String filter = "id=?";
        String[] args = {String.valueOf(student.getId())};
        return db.update(Constants.STUDENT_TABLE_NAME,prepareStudentObject(student),filter,args);
    }

    public int deleteStudent(Student student){
        SQLiteDatabase db = dataAccess.getWritableDatabase();
        String[] args = {String.valueOf(student.getId())};
        return db.delete(Constants.STUDENT_TABLE_NAME,"id=?",args);

    }

    public ArrayList<Student> getStudents(){
        ArrayList<Student> studentsList = new ArrayList<>();

        SQLiteDatabase db = dataAccess.getReadableDatabase();

        String[] columns = {"id","name","paternal_surname","maternal_surname","email","phone_number","grade","student_group"};

        Cursor cursor = db.query(Constants.STUDENT_TABLE_NAME,columns,null,null,null,null,null);

        if(cursor == null){
            return studentsList;
        }

        if(cursor.moveToFirst() == false){
            return studentsList;
        }

        do {
            long studentId = cursor.getLong(0);
            String name = cursor.getString(1);
            String paternalSurname = cursor.getString(2);
            String maternalSurname = cursor.getString(3);
            String email = cursor.getString(4);
            String phoneNumber = cursor.getString(5);
            String grade = cursor.getString(6);
            String group = cursor.getString(7);

            Student student = new Student(studentId,name,paternalSurname,maternalSurname,email,phoneNumber,grade,group);
            studentsList.add(student);

        }while(cursor.moveToNext());

        cursor.close();

        return studentsList;
    }

    public ContentValues prepareStudentObject(Student student){
        ContentValues values = new ContentValues();

        if(student.getId() > 0){
            values.put("id",student.getId());
        }
        values.put("name",student.getName());
        values.put("paternal_surname", student.getPaternalSurname());
        values.put("maternal_surname",student.getMaternalSurname());
        values.put("email",student.getEmail());
        values.put("phone_number",student.getPhoneNumber());
        values.put("grade",student.getGrade());
        values.put("student_group",student.getGroup());
        return values;
    }

}
