package com.fic.cursoandroid.capapresentacion.Student;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.fic.cursoandroid.R;
import com.fic.cursoandroid.capapresentacion.Main.MainActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class StudentsActivity extends AppCompatActivity {

    private FloatingActionButton fabAddStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students);

        fabAddStudent = findViewById(R.id.fabAddStudent);
        fabAddStudent.setOnClickListener(addStudentListener);
    }

    private View.OnClickListener addStudentListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), NewStudentAcitivty.class);
            startActivity(intent);
        }
    };
}