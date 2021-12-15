package com.fic.cursoandroid.capapresentacion.Student;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fic.cursoandroid.R;
import com.fic.cursoandroid.capaaplicacion.StudentController;
import com.fic.cursoandroid.capadatos.Student;
import com.fic.cursoandroid.utils.Constants;

public class UpdateStudentActivity extends AppCompatActivity {

    private StudentController studentController;
    private Student student;
    private Button btnUpdateStudent, btnCancel;
    private EditText etName, etPaternalSurname, etMaternalSurname, etEmail, etPhoneNumber, etGrade, etGroup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_student);

        Bundle extras = getIntent().getExtras();

        studentController = new StudentController(UpdateStudentActivity.this);

        long studentId = extras.getLong("studentId");
        String name = extras.getString("name");
        String paternalSurname = extras.getString("paternalSurname");
        String maternalSurname = extras.getString("maternalSurname");
        String email = extras.getString("email");
        String phoneNumber = extras.getString("phoneNumber");
        String grade = extras.getString("grade");
        String group = extras.getString("group");
        Log.d("Student ID", String.valueOf(studentId));

        student = new Student(studentId, name, paternalSurname, maternalSurname, email, phoneNumber, grade, group);

        etName = findViewById(R.id.etStudentName);
        etPaternalSurname = findViewById(R.id.etPaternalSurname);
        etMaternalSurname = findViewById(R.id.etMaternalSurname);
        etEmail = findViewById(R.id.etEmail);
        etPhoneNumber = findViewById(R.id.etPhoneNumber);
        etGrade = findViewById(R.id.etGrade);
        etGroup = findViewById(R.id.etGroup);

        btnUpdateStudent = findViewById(R.id.btnSaveStudent);
        btnCancel = findViewById(R.id.btnCancel);

        etName.setText(student.getName());
        etPaternalSurname.setText(student.getPaternalSurname());
        etMaternalSurname.setText(student.getMaternalSurname());
        etEmail.setText(student.getEmail());
        etPhoneNumber.setText(student.getPhoneNumber());
        etGrade.setText(student.getGrade());
        etGroup.setText(student.getGroup());


        btnCancel.setOnClickListener(buttonCancelListener);
        btnUpdateStudent.setOnClickListener(updateStudentListener);
    }

    private View.OnClickListener buttonCancelListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

    private View.OnClickListener updateStudentListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String newName = etName.getText().toString().trim();
            String newPaternalSurname = etPaternalSurname.getText().toString().trim();
            String newMaternalSurname = etMaternalSurname.getText().toString().trim();
            String newEmail = etEmail.getText().toString().trim();
            String newPhoneNumber = etPhoneNumber.getText().toString().trim();
            String newGrade = etGrade.getText().toString().trim();
            String newGroup = etGroup.getText().toString().trim();

            if(validateEditText(etName,etPaternalSurname,etMaternalSurname,etEmail,etPhoneNumber,etGrade,etGroup) == false){
                Toast.makeText(getApplicationContext(),Constants.VALIDATE_EMPTY_TEXT,Toast.LENGTH_LONG).show();
                return;
            }

            Student updatedStudent = new Student(student.getId(), newName, newPaternalSurname, newMaternalSurname, newEmail, newPhoneNumber, newGrade, newGroup);

            int updatedRows = studentController.updateStudent(updatedStudent);

            if (updatedRows != 1) {
                Toast.makeText(getApplicationContext(), Constants.FAILED_DATABASE_INSERT, Toast.LENGTH_LONG).show();
            } else {
                finish();
            }
        }
    };


    protected boolean validateEditText(EditText... editTexts) {
        boolean result = true;
        for (int i = 0; i < editTexts.length; i++) {
            if (editTexts[i].getText().toString().isEmpty()) {
                result = false;
                editTexts[i].setError(Constants.REQUIRED_FIELD);
            }
        }
        return result;
    }
}