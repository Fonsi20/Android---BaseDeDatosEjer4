package com.example.basededatosejer4;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class BorrarActividad extends AppCompatActivity {

    private String BDname;
    private int BDversion;
    private SQLiteDatabase BDusuarios;
    private Button btnFin, btnIns;
    private EditText edCodigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar);

        btnFin = findViewById(R.id.btnAcFin);
        btnIns = findViewById(R.id.btnAcInsert);
        edCodigo = findViewById(R.id.edCodigo);

        BDname = "BDusuarios";
        BDversion = 1;

        BDHelper bdhelper = new BDHelper(this, BDname, null, BDversion);

        BDusuarios = bdhelper.getWritableDatabase();

        btnIns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (edCodigo.equals("")) {
                    edCodigo.setHint("Introduce un codigo");
                } else {
                    BDusuarios.execSQL("Delete from tUsuarios where codigo = " + edCodigo.getText().toString() + ";");
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
