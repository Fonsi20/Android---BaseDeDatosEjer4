package com.example.basededatosejer4;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class MuestraVariosActivity extends AppCompatActivity {

    private String BDname;
    private int BDversion;
    private SQLiteDatabase BDusuarios;
    private Button btnFin;
    private ListView lvUsers;

    ArrayList<String> listaPersonas;
    ArrayList<Usuario> personasList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muestra_varios);

        btnFin = findViewById(R.id.btnAcFin);
        lvUsers = findViewById(R.id.lv);

        BDname = "BDusuarios";
        BDversion = 1;
        BDHelper bdhelper = new BDHelper(this, BDname, null, BDversion);
        BDusuarios = bdhelper.getWritableDatabase();

        consultarListaUsuarios();

        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter
                (this, android.R.layout.simple_spinner_item, listaPersonas);
        lvUsers.setAdapter(adaptador);

        btnFin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BDusuarios.close();
                finish();
            }
        });
    }

    private void consultarListaUsuarios() {

        Usuario persona = null;
        personasList = new ArrayList<Usuario>();
        //select * from usuarios
        Cursor cursor = BDusuarios.rawQuery("SELECT * FROM tUsuario", null);

        while (cursor.moveToNext()) {
            persona = new Usuario();
            persona.setCodigo(cursor.getInt(0));
            persona.setNombre(cursor.getString(1));

            Log.i("id", persona.getCodigo().toString());
            Log.i("Nombre", persona.getNombre());

            personasList.add(persona);
        }
        obtenerLista();
    }

    private void obtenerLista() {
        listaPersonas = new ArrayList<String>();

        for (int i = 0; i < personasList.size(); i++) {
            listaPersonas.add(personasList.get(i).getCodigo() + " - " + personasList.get(i).getNombre());
        }
    }
}
