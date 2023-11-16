package com.desarrollojlcp.gps_topography.db;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.widget.Toast;

import androidx.annotation.Nullable;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;

public class ConexionSQLiteHelper extends SQLiteOpenHelper {


    public ConexionSQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Utilidades.CREAR_TABLA_POLIGONOS);
        db.execSQL(Utilidades.CREAR_TABLA_ESTACIONES);
        db.execSQL(Utilidades.CREAR_TABLA_RADIACIONES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 11) {
            db.execSQL(Utilidades.ACTUALIZAR_TABLA_POLIGONOS_1);
            db.execSQL(Utilidades.ACTUALIZAR_TABLA_ESTACIONES_1);
            db.execSQL(Utilidades.ACTUALIZAR_TABLA_RADIACIONES_1);
        }
        if (oldVersion < 12) {
            db.execSQL(Utilidades.ACTUALIZAR_TABLA_ESTACIONES_2);
        }
        if (oldVersion < 13) {
            db.execSQL(Utilidades.ACTUALIZAR_TABLA_ESTACIONES_3);
            db.execSQL(Utilidades.ACTUALIZAR_TABLA_RADIACIONES_3);

        }
        if (oldVersion < 14) {
            db.execSQL(Utilidades.ACTUALIZAR_TABLA_ESTACIONES_4);
            db.execSQL(Utilidades.ACTUALIZAR_TABLA_ESTACIONES_5);


            db.execSQL(Utilidades.ACTUALIZAR_TABLA_RADIACIONES_4);
            db.execSQL(Utilidades.ACTUALIZAR_TABLA_RADIACIONES_5);

        }
    }



}
