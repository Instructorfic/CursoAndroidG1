package com.fic.cursoandroid.capapresentacion.Student;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fic.cursoandroid.R;
import com.fic.cursoandroid.capaaplicacion.StudentController;
import com.fic.cursoandroid.capadatos.Student;
import com.fic.cursoandroid.utils.Constants;

public class NewStudentAcitivty extends AppCompatActivity {

    private Button btnAddStudent, btnCancel;
    private EditText etName, etPaternalSurname, etMaternalSurname, etEmail, etPhoneNumber, etGrade, etGroup;
    private StudentController studentController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_student_acitivty);


        etName = findViewById(R.id.etStudentName);
        etPaternalSurname = findViewById(R.id.etPaternalSurname);
        etMaternalSurname = findViewById(R.id.etMaternalSurname);
        etEmail = findViewById(R.id.etEmail);
        etPhoneNumber = findViewById(R.id.etPhoneNumber);
        etGroup = findViewById(R.id.etGroup);
        etGrade = findViewById(R.id.etGrade);

        studentController = new StudentController(NewStudentAcitivty.this);

        btnAddStudent = findViewById(R.id.btnSaveStudent);
        btnAddStudent.setOnClickListener(saveStudentList);

        btnCancel = findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(cancelButtonListener);
    }

    private View.OnClickListener saveStudentList = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String name = etName.getText().toString();
            String paternalSurname = etPaternalSurname.getText().toString();
            String maternalSurname = etMaternalSurname.getText().toString();
            String email = etEmail.getText().toString();
            String phoneNumber = etPhoneNumber.getText().toString();
            String grade = etGrade.getText().toString();
            String group = etGroup.getText().toString();

            Student student = new Student(0,name,paternalSurname,maternalSurname,email,phoneNumber,grade,group);

            long insertResult = studentController.saveStudent(student);

            if(insertResult == -1){
                Toast.makeText(NewStudentAcitivty.this, Constants.FAILED_DATABASE_INSERT, Toast.LENGTH_SHORT).show();
            }else{
                finish();
            }

        }
    };

    private View.OnClickListener cancelButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };
}