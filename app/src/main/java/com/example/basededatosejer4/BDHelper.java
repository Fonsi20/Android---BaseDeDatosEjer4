package com.example.basededatosejer4;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BDHelper extends SQLiteOpenHelper {

    static final String tabla1 = "tUsuario";
    static final String columna1 = "nombre";
    static final String columna2 = "codigo";
    String SQLCrear = "CREATE TABLE " + tabla1 + "(" + columna2 + " INTEGER PRIMARY KEY, " + columna1 + " VARCHAR(20))";
    String SQLDelete = "DROP TABLE IF EXISTS " + tabla1;

    public BDHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQLCrear);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQLDelete);
    }
}
