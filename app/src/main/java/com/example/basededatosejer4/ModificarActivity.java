package com.example.basededatosejer4;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class ModificarActivity extends AppCompatActivity {

    private String BDname;
    private int BDversion;
    private SQLiteDatabase BDusuarios;
    private Button btnFin, btnMod;
    private EditText edCodigo, edNombre;
    private Spinner spUsers;

    ArrayList<String> listaPersonas;
    ArrayList<Usuario> personasList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);

        btnFin = findViewById(R.id.btnAcFin);
        btnMod = findViewById(R.id.btnAcModificar);
        edCodigo = findViewById(R.id.edCodigo);
        edNombre = findViewById(R.id.edNombre);
        spUsers = findViewById(R.id.UsuarioSpinner);

        BDname = "BDusuarios";
        BDversion = 1;
        BDHelper bdhelper = new BDHelper(this, BDname, null, BDversion);
        BDusuarios = bdhelper.getWritableDatabase();

        consultarListaUsuarios();

        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter
                (this, android.R.layout.simple_spinner_item, listaPersonas);
        spUsers.setAdapter(adaptador);

        btnMod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (edNombre.getText().toString().equals("")) {
                    edNombre.setHint("Introduce un nombre");
                } else if (edCodigo.getText().toString().equals("")) {
                    edCodigo.setHint("Introduce un codigo");
                } else {
                    String text = spUsers.getSelectedItem().toString();
                    String[] codigo = text.split("(?=\\s)");
                    Log.i("spinner", edNombre.getText().toString());
                    BDusuarios.execSQL("UPDATE tUsuario SET nombre='"+edNombre.getText().toString()+"', codigo="+Integer.parseInt(edCodigo.getText().toString())+" where codigo="+codigo[0]+"");
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
        listaPersonas.add("Seleccione");

        for (int i = 0; i < personasList.size(); i++) {
            listaPersonas.add(personasList.get(i).getCodigo() + " - " + personasList.get(i).getNombre());
        }

    }

}