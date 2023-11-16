package com.desarrollojlcp.gps_topography.db;

import android.provider.BaseColumns;

public class Utilidades {

    //Constantes campos tabla puntos
    public static final String NOMBRE_BD = "bd_gpst.db";
    public static final int VERSION_BD = 15;
    //Tabla de poligonos




    public final static String SELECCIONAR_TODOS_POLIGONOS = "SELECT * FROM "+Utilidades.PoligonoEntry.TABLA_POLIGONOS;

    public final static String SELECCIONAR_POLIGONO (int idPol){
        String cad = "SELECT * FROM "+ PoligonoEntry.TABLA_POLIGONOS+ " WHERE "+PoligonoEntry.CAMPO_ID_POLIGONO+" = " +String.valueOf(idPol);
        return cad;
    }

    public final static String SELECCIONAR_ESTACIONES_DE_POLIGONO (int idPol){
        String cad = "SELECT * FROM "+ EstacionEntry.TABLA_ESTACIONES+ " WHERE "+EstacionEntry.CAMPO_ID_POL+" = " +String.valueOf(idPol);
        return cad;
    }

    public final static String SELECCIONAR_RADIACIONES_ESTACION (int idEst){
        String cad = "SELECT * FROM "+ RadiacionEntry.TABLA_RADIACIONES+ " WHERE "+RadiacionEntry.CAMPO_ID_ESTR+" = " +String.valueOf(idEst);
        return cad;
    }

    public final static String BORRAR_POLIGONO (int idPol){
        String cad = "DELETE FROM "+ PoligonoEntry.TABLA_POLIGONOS+ " WHERE "+ PoligonoEntry.CAMPO_ID_POLIGONO+" = " +String.valueOf(idPol);
        return cad;
    }

    public final static String BORRAR_ESTACIONES_DE_POLIGONO (int idPol){
        String cad = "DELETE FROM "+ EstacionEntry.TABLA_ESTACIONES+ " WHERE "+ EstacionEntry.CAMPO_ID_POL+" = " +String.valueOf(idPol);
        return cad;
    }

    public final static String POLIGONO_EXPORTAR (int idPol){
        String cad = "SELECT *"+
                " FROM "+ PoligonoEntry.TABLA_POLIGONOS +
                " WHERE "+ PoligonoEntry.CAMPO_ID_POLIGONO+" = " + String.valueOf(idPol);
        return cad;
    }

    public final static String ESTACIONES_EXPORTAR (int idPol){
        String cad = "SELECT *"+
                " FROM "+ EstacionEntry.TABLA_ESTACIONES +
                " WHERE "+ EstacionEntry.CAMPO_ID_POL+" = " + String.valueOf(idPol);
        return cad;
    }

    public final static String RADIACIONES_EXPORTAR (int idPol){
        String cad = "SELECT *"+
                " FROM "+ RadiacionEntry.TABLA_RADIACIONES +
                " WHERE "+ RadiacionEntry.CAMPO_ID_POLR+" = " + String.valueOf(idPol);
        return cad;
    }

    public static class PoligonoEntry implements BaseColumns {
        public static final String TABLA_POLIGONOS = "tabla_poligonos";
        public static final String CAMPO_ID_POLIGONO = "_id";
        public static final String CAMPO_FECHA = "fecha";
        public static final String CAMPO_TIPO_POL = "tipo_pol";
        public static final String CAMPO_PROYECTO = "proyecto";
        public static final String CAMPO_CLIENTE = "cliente";
        public static final String CAMPO_UBICACION = "ubicacion";
        public static final String CAMPO_RESPONSABLE = "responsable";
        public static final String CAMPO_TIPO_MEDICION = "tipo_medicion";

    }


    public final static String CREAR_TABLA_POLIGONOS = "CREATE TABLE " + Utilidades.PoligonoEntry.TABLA_POLIGONOS
            + "(" + Utilidades.PoligonoEntry.CAMPO_ID_POLIGONO + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + Utilidades.PoligonoEntry.CAMPO_FECHA + " TEXT, "
            + Utilidades.PoligonoEntry.CAMPO_TIPO_POL + " INTEGER, "
            + Utilidades.PoligonoEntry.CAMPO_PROYECTO + " TEXT, "
            + Utilidades.PoligonoEntry.CAMPO_CLIENTE + " TEXT, "
            + Utilidades.PoligonoEntry.CAMPO_UBICACION + " TEXT, "
            + Utilidades.PoligonoEntry.CAMPO_RESPONSABLE + " TEXT,"
            + PoligonoEntry.CAMPO_TIPO_MEDICION + " TEXT)";

    //Tabla de estaciones
    public static class EstacionEntry implements BaseColumns {
        public static final String TABLA_ESTACIONES = "tabla_estaciones";           //1
        public static final String CAMPO_ID_POL = "id_poligono";                    //2
        public static final String CAMPO_ID_EST = "_id";                            //3
        public static final String CAMPO_NOMBRE_EST = "idEst";                      //4
        public static final String CAMPO_DIST = "distancia";
        public static final String CAMPO_AZIMUT = "azimut";
        public static final String CAMPO_ULTIMO_PUNTO = "ultimo_punto";
        public static final String CAMPO_PARTE_POL_FINAL = "parte_pol_final";
        public static final String CAMPO_TIPO_MEDICION = "tipo_medicion";
        public static final String CAMPO_LAT = "latitud";
        public static final String CAMPO_LON = "longitud";
        public static final String CAMPO_OBSERVACIONES = "observaciones";
        public static final String CAMPO_ALT = "altitud";
        public static final String CAMPO_YT = "yt";
        public static final String CAMPO_XT = "xt";
        public static final String CAMPO_ZT = "zt";



    }


    public final static String CREAR_TABLA_ESTACIONES = "CREATE TABLE "+ Utilidades.EstacionEntry.TABLA_ESTACIONES
            +" ( "+Utilidades.EstacionEntry.CAMPO_ID_POL+" INTEGER, "
            +Utilidades.EstacionEntry.CAMPO_ID_EST+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +Utilidades.EstacionEntry.CAMPO_NOMBRE_EST+" TEXT, "
            +Utilidades.EstacionEntry.CAMPO_DIST+" REAL, "
            +Utilidades.EstacionEntry.CAMPO_AZIMUT+" REAL, "
            +Utilidades.EstacionEntry.CAMPO_ULTIMO_PUNTO+" TEXT, "
            +Utilidades.EstacionEntry.CAMPO_PARTE_POL_FINAL+" TEXT, "
            +Utilidades.EstacionEntry.CAMPO_TIPO_MEDICION+" TEXT, "
            +Utilidades.EstacionEntry.CAMPO_LAT+" REAL, "//8
            +Utilidades.EstacionEntry.CAMPO_LON+" REAL, "//9
            +Utilidades.EstacionEntry.CAMPO_OBSERVACIONES+" TEXT, "//10
            +Utilidades.EstacionEntry.CAMPO_ALT+" REAL, "//11
            +Utilidades.EstacionEntry.CAMPO_YT+" REAL, "//12
            +Utilidades.EstacionEntry.CAMPO_XT+" REAL, "//13
            +Utilidades.EstacionEntry.CAMPO_ZT+" REAL, "//14
            +"FOREIGN KEY( "+EstacionEntry.CAMPO_ID_POL+" ) REFERENCES "+ PoligonoEntry.TABLA_POLIGONOS+"("+PoligonoEntry.CAMPO_ID_POLIGONO+" ) )";



    //Tabla de radiaciones

    public static class RadiacionEntry implements BaseColumns {
        public static final String TABLA_RADIACIONES = "tabla_radiaciones";
        public static final String CAMPO_ID_POLR = "id_poligono";
        public static final String CAMPO_ID_ESTR = "id_estacion";
        public static final String CAMPO_ID_RAD = "_id";
        public static final String CAMPO_NOMBRE_RAD = "idEst";
        public static final String CAMPO_DISTR = "distancia";
        public static final String CAMPO_AZIMUTR = "val_dec_az";
        public static final String CAMPO_ULTIMO_PUNTOR = "ultimo_punto";
        public static final String CAMPO_POL_FINALR = "parte_pol_final";
        public static final String CAMPO_OBSERVACIONES = "observaciones";
        public static final String CAMPO_YT = "yt";
        public static final String CAMPO_XT = "xt";
        public static final String CAMPO_ZT = "zt";

    }


    public final static String CREAR_TABLA_RADIACIONES = "CREATE TABLE "+ Utilidades.RadiacionEntry.TABLA_RADIACIONES
            +" ( "+Utilidades.RadiacionEntry.CAMPO_ID_POLR+" INTEGER, "
            +Utilidades.RadiacionEntry.CAMPO_ID_ESTR+" INTEGER, "
            +Utilidades.RadiacionEntry.CAMPO_ID_RAD+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +Utilidades.RadiacionEntry.CAMPO_NOMBRE_RAD+" TEXT, "
            +Utilidades.RadiacionEntry.CAMPO_DISTR+" REAL, "
            +Utilidades.RadiacionEntry.CAMPO_AZIMUTR+" REAL, "
            +Utilidades.RadiacionEntry.CAMPO_ULTIMO_PUNTOR+" INTEGER, "
            +Utilidades.RadiacionEntry.CAMPO_POL_FINALR+" TEXT, "
            +Utilidades.RadiacionEntry.CAMPO_OBSERVACIONES+" TEXT, "
            +Utilidades.RadiacionEntry.CAMPO_YT+" REAL, "
            +Utilidades.RadiacionEntry.CAMPO_XT+" REAL, "
            +Utilidades.RadiacionEntry.CAMPO_ZT+" REAL, "

            +"FOREIGN KEY( "+RadiacionEntry.CAMPO_ID_ESTR+" ) REFERENCES "+ EstacionEntry.TABLA_ESTACIONES+"("+EstacionEntry.CAMPO_ID_EST +" ) )";


    //Actualizar las tablas

    public static final String ACTUALIZAR_TABLA_POLIGONOS_1 = "ALTER TABLE " + PoligonoEntry.TABLA_POLIGONOS + " ADD COLUMN " + PoligonoEntry.CAMPO_TIPO_MEDICION + " TEXT;";
    public static final String ACTUALIZAR_TABLA_ESTACIONES_1 = "ALTER TABLE " + EstacionEntry.TABLA_ESTACIONES + " ADD COLUMN " + EstacionEntry.CAMPO_OBSERVACIONES + " TEXT;";
    public static final String ACTUALIZAR_TABLA_RADIACIONES_1 = "ALTER TABLE " + RadiacionEntry.TABLA_RADIACIONES + " ADD COLUMN " + RadiacionEntry.CAMPO_OBSERVACIONES + " TEXT;";

    public static final String ACTUALIZAR_TABLA_ESTACIONES_2 = "ALTER TABLE " + EstacionEntry.TABLA_ESTACIONES + " ADD COLUMN " + EstacionEntry.CAMPO_ALT + " REAL;";

    //Tercera actualizacion de tablas 20211228, para agregar coordenadas totales a las estaciones y radiaciones
    public static final String ACTUALIZAR_TABLA_ESTACIONES_3 = "ALTER TABLE " + EstacionEntry.TABLA_ESTACIONES + " ADD COLUMN " + EstacionEntry.CAMPO_YT + " REAL;";
    public static final String ACTUALIZAR_TABLA_ESTACIONES_4 = "ALTER TABLE " + EstacionEntry.TABLA_ESTACIONES + " ADD COLUMN " + EstacionEntry.CAMPO_XT + " REAL;";
    public static final String ACTUALIZAR_TABLA_ESTACIONES_5 = "ALTER TABLE " + EstacionEntry.TABLA_ESTACIONES + " ADD COLUMN " + EstacionEntry.CAMPO_ZT + " REAL;";

    public static final String ACTUALIZAR_TABLA_RADIACIONES_3 = "ALTER TABLE " + RadiacionEntry.TABLA_RADIACIONES + " ADD COLUMN " + RadiacionEntry.CAMPO_YT + " REAL;";
    public static final String ACTUALIZAR_TABLA_RADIACIONES_4 = "ALTER TABLE " + RadiacionEntry.TABLA_RADIACIONES + " ADD COLUMN " + RadiacionEntry.CAMPO_XT + " REAL;";
    public static final String ACTUALIZAR_TABLA_RADIACIONES_5 = "ALTER TABLE " + RadiacionEntry.TABLA_RADIACIONES + " ADD COLUMN " + RadiacionEntry.CAMPO_ZT + " REAL;";




}
