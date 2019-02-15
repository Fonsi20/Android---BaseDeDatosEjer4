package com.example.basededatosejer4;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InsertarActivity extends AppCompatActivity {

    private String BDname;
    private int BDversion;
    private SQLiteDatabase BDusuarios;
    private Button btnFin, btnIns;
    private EditText edNombre, edCodigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar);

        btnFin = findViewById(R.id.btnAcFin);
        btnIns = findViewById(R.id.btnAcInsert);
        edNombre = findViewById(R.id.edNombre);
        edCodigo = findViewById(R.id.edCodigo);

        BDname = "BDusuarios";
        BDversion = 1;

        BDHelper bdhelper = new BDHelper(this, BDname, null, BDversion);

        BDusuarios = bdhelper.getWritableDatabase();

        btnIns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (edNombre.getText().toString().equals("")) {
                    edNombre.setHint("Introduce un nombre");
                } else if (edCodigo.getText().toString().equals("")) {
                    edCodigo.setHint("Introduce un codigo");
                } else {
                    BDusuarios.execSQL("Insert into tUsuario (codigo,nombre) values ("+Integer.parseInt(edCodigo.getText().toString())+",'"+edNombre.getText().toString()+"')");
                }
            }
        });

        btnFin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BDusuarios.close();
                finish();
            }
        });

    }
}
