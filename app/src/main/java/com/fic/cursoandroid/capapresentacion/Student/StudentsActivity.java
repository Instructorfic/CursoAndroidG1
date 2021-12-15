package com.fic.cursoandroid.capapresentacion.Student;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.fic.cursoandroid.R;
import com.fic.cursoandroid.capaaplicacion.StudentController;
import com.fic.cursoandroid.capadatos.DataAccess;
import com.fic.cursoandroid.capadatos.Student;
import com.fic.cursoandroid.capapresentacion.Main.MainActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class StudentsActivity extends AppCompatActivity {

    private FloatingActionButton fabAddStudent;
    private StudentController studentController;
    private RecyclerView recyclerViewStudents;
    private StudentAdapter studentAdapter;
    private DataAccess dataAccess;
    private ArrayList<Student> studentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students);

        dataAccess = new DataAccess(StudentsActivity.this);

        fabAddStudent = findViewById(R.id.fabAddStudent);
        fabAddStudent.setOnClickListener(addStudentListener);

        recyclerViewStudents = findViewById(R.id.rvStudents);
        studentController = new StudentController(StudentsActivity.this);

        studentList = new ArrayList<>();
        studentAdapter = new StudentAdapter(studentList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewStudents.setLayoutManager(layoutManager);
        recyclerViewStudents.setItemAnimator(new DefaultItemAnimator());
        recyclerViewStudents.setAdapter(studentAdapter);

        getStudents();

        recyclerViewStudents.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerViewStudents, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                //click sencillo

                Student selectedStudent = studentList.get(position);

                Intent intent = new Intent(StudentsActivity.this,UpdateStudentActivity.class);
                intent.putExtra("studentId", selectedStudent.getId());
                intent.putExtra("name",selectedStudent.getName());
                intent.putExtra("paternalSurname",selectedStudent.getPaternalSurname());
                intent.putExtra("maternalSurname",selectedStudent.getMaternalSurname());
                intent.putExtra("email",selectedStudent.getEmail());
                intent.putExtra("phoneNumber",selectedStudent.getPhoneNumber());
                intent.putExtra("grade",selectedStudent.getGrade());
                intent.putExtra("group",selectedStudent.getGroup());
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {
                //click prolongado
                Student selectedStudent = studentList.get(position);
                deleteStudent(selectedStudent);
            }
        }));
    }

    private View.OnClickListener addStudentListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), NewStudentAcitivty.class);
            startActivity(intent);
        }
    };

    public void getStudents(){

        if(studentAdapter == null){
            return;
        }

        studentList = studentController.getStudents();
        studentAdapter.setStudentList(studentList);
        studentAdapter.notifyDataSetChanged();
    }

    public void deleteStudent(Student selectedStudent){
        AlertDialog dialog = new AlertDialog.Builder(StudentsActivity.this)
                .setPositiveButton("Sí, Eliminar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        studentController.deleteStudent(selectedStudent);
                        getStudents();
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setTitle("Confirmar información")
                .setMessage("¿Está seguro de elimniar al estudiante "+ selectedStudent.getName() + "?")
                .create();
        dialog.show();
    }

    public void onResume(){
        getStudents();
        super.onResume();
    }
}