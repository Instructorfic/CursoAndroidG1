package com.fic.cursoandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    final String webPageUrl = "http://info.uas.edu.mx";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constraint_layout);

        Button btnLogin = (Button) findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showActivity = new Intent(getApplicationContext(),MainActivity2.class);
                //showActivity.setData(Uri.parse(webPageUrl));
                String message = "Hola Actividad 2";
                int error = 1;

                //Crear paquete de información
                Bundle bundle = new Bundle();
                bundle.putString("message",message);
                bundle.putInt("error",error);

                showActivity.putExtras(bundle);

                //showActivity.putExtra("message",message);
                startActivity(showActivity);
            }
        });
    }
}