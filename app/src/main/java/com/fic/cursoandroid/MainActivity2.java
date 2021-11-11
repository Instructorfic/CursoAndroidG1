package com.fic.cursoandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        /*Uri webSiteURL = getIntent().getData();

        Toast.makeText(getApplicationContext(),webSiteURL.toString(),Toast.LENGTH_LONG).show();*/

        String getStringMessage = getIntent().getStringExtra("message");
        Toast.makeText(getApplicationContext(),getStringMessage,Toast.LENGTH_LONG).show();

        Button btnShowWebPage = (Button) findViewById(R.id.btnShowView);
        btnShowWebPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://www.google.com");
                Intent showWebPageIntent = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(showWebPageIntent);
            }
        });


        Button btnDial = (Button) findViewById(R.id.btnDial);
        btnDial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri dialUri = Uri.parse("tel:6677000000");
                Intent dialIntent = new Intent(Intent.ACTION_DIAL,dialUri);
                startActivity(dialIntent);
            }
        });

        Button btnSettings = (Button) findViewById(R.id.btnSettings);
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent settingsIntent = new Intent(Settings.ACTION_SETTINGS);
                startActivity(settingsIntent);
            }
        });
    }
}