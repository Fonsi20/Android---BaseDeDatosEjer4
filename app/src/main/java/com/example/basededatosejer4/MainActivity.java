package com.example.basededatosejer4;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnInser, btnBor, btnModi, btnMosUno, btnMosVar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnBor = findViewById(R.id.btnborrar);
        btnInser = findViewById(R.id.btninsertar);
        btnModi = findViewById(R.id.btnmodificar);
        btnMosUno = findViewById(R.id.btnmostaruno);
        btnMosVar = findViewById(R.id.btnmostrarvarios);


        btnInser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, InsertarActivity.class);
                startActivity(i);
            }
        });


        btnBor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, BorrarActividad.class);
                startActivity(i);
            }
        });

        btnModi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ModificarActivity.class);
                startActivity(i);
            }
        });


        btnMosUno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        btnMosVar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}
