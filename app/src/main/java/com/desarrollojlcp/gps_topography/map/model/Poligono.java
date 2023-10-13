package com.desarrollojlcp.gps_topography.map.model;

import com.google.android.gms.maps.model.LatLng;
import java.io.Serializable;
import java.util.*;


public class Poligono implements Serializable {

    //Declaración de variables de poligono
    //private float xCen, yCen, displayScale;
    private  String fecha = "";                          //Fecha en que se creo el poligono
    private  double area;                         //area del poligono en metros cuadrados
    private  double perimetro = 0;                //perimetro del poligono, en metros
    private  double sumaNortes = 0;
    private  double sumaSures = 0;
    private  double sumaEstes = 0;
    private  double sumaOestes = 0;
    private  double sumaDist = 0;
    private  double sumaYX = 0;
    private  double sumaXY = 0;
    private  double deltaX = 0;                   //Error de cierre en el eje X
    private  double deltaY = 0;                   //Error de cierre en el eje Y
    private  double errC = 0;                     // error de cierre
    private  double errCierreUn = 0;
    private  double errA = 0;                     //Error de cierre admisible
    private  double Cy = 0;
    private  double Cx = 0;
    public Vector<Double> corrN = new Vector<Double>();
    public Vector<Double> corrS = new Vector<Double>();
    public Vector<Double> corrE = new Vector<Double>();
    public Vector<Double> corrO = new Vector<Double>();
    private  int contEs = 0;                     //contador de estaciones ó puntos
    private  String proyecto = "";               //Nombre del proyecto a que corresponde el poligono
    public String cliente = "";                //Nombre del cliente a que corresponde el poligono
    public String ubicacion = "";              //Ubicacion ó dirección del poligono
    public String responsable = "";            //Nombre del Ingeniero/Arquitecto/Topografo responsable
    public  Vector<Estacion> estaciones = new Vector<Estacion>();    //vector para contener todas las estaciones
    private   Vector<Estacion> estacionesPolFinal = new Vector<Estacion>();    //vector para contener todas las estaciones

    private  boolean estFinal = false;
    private  boolean errorAceptable = false;
    public  String nombreArchivoGuardado = "";
    public  String rutaArchivoGuardado = "";
    private  String tipoTerreno = "";
    public  String rutaArchivoCSV= "";
    public  String nombreArchivoCSV = "";
    public  String rutaArchivoDXF= "";
    public   String rutaArchivoImagen= "";
    public   String nombreArchivoImagen= "";

    public  String nombreArchivoDXF = "";
    public  String nombreArchivoPDF = "";

    private  String opMenuPrincipal = "0";
    private  double yto = 0.0;
    private  double xto = 0.0;
    private  double zto = 0.0;
    private  double xtmin, xtmax, ytmin, ytmax;
    public  String rutaCarpetaActual;
    public  String rutaCarpetaActual2;
    private boolean primeraPantalla;

    private LatLng puntos;
    private String errorCumple = "";
    private final int id_bd= 0;

    public  String getFecha() {
        return fecha;
    }

    public  void setArea(double area) {
       this.area = area;
    }

    public  void setPerimetro(double perimetro) {
        this.perimetro = perimetro;
    }

    public  double getSumaNortes() {
        return sumaNortes;
    }

    public  void setSumaNortes(double sumaNortes) {
        this.sumaNortes = sumaNortes;
    }

    public  double getSumaSures() {
        return sumaSures;
    }

    public  void setSumaSures(double sumaSures) {
        this.sumaSures = sumaSures;
    }

    public  double getSumaEstes() {
        return sumaEstes;
    }

    public  void setSumaEstes(double sumaEstes) {
        this.sumaEstes = sumaEstes;
    }

    public  double getSumaOestes() {
        return sumaOestes;
    }

    public  void setSumaOestes(double sumaOestes) {
        this.sumaOestes = sumaOestes;
    }

    public  double getSumaDist() {
        return sumaDist;
    }

    public  void setSumaDist(double sumaDist) {
        this.sumaDist = sumaDist;
    }

    public  double getSumaYX() {
        return sumaYX;
    }

    public  void setSumaYX(double sumaYX) {
        this.sumaYX = sumaYX;
    }

    public  double getSumaXY() {
        return sumaXY;
    }

    public  void setSumaXY(double sumaXY) {
        this.sumaXY = sumaXY;
    }

    public  double getDeltaX() {
        return deltaX;
    }

    public  void setDeltaX(double deltaX) {
        this.deltaX = deltaX;
    }

    public  double getDeltaY() {
        return deltaY;
    }

    public  void setDeltaY(double deltaY) {
        this.deltaY = deltaY;
    }

    public  double getErrC() {
        return errC;
    }

    public  void setErrC(double errC) {
        this.errC = errC;
    }

    public  double getErrCierreUn() {
        return errCierreUn;
    }

    public  void setErrCierreUn(double errCierreUn) {
        this.errCierreUn = errCierreUn;
    }

    public  double getErrA() {
        return errA;
    }

    public  void setErrA(double errA) {
        this.errA = errA;
    }

    public  double getCy() {
        return Cy;
    }

    public  void setCy(double cy) {
        Cy = cy;
    }

    public  double getCx() {
        return Cx;
    }

    public  void setCx(double cx) {
        Cx = cx;
    }

    public  int getContEs() {
        return contEs;
    }

    public  void setContEs(int contEs) {
        this.contEs = contEs;
    }

    public  String getProyecto() {
        return proyecto;
    }

    public  void setProyecto(String proyecto) {
        this.proyecto = proyecto;
    }

    public  boolean isEstFinal() {
        return estFinal;
    }

    public  void setEstFinal(boolean estFinal) {
        this.estFinal = estFinal;
    }

    public  boolean isErrorAceptable() {
        return errorAceptable;
    }

    public  void setErrorAceptable(boolean errorAceptable) {
        this.errorAceptable = errorAceptable;
    }

    public  String getNombreArchivoGuardado() {
        return nombreArchivoGuardado;
    }

    public  void setNombreArchivoGuardado(String nombreArchivoGuardado) {
        this.nombreArchivoGuardado = nombreArchivoGuardado;
    }

    public  String getRutaArchivoGuardado() {
        return rutaArchivoGuardado;
    }

    public  void setRutaArchivoGuardado(String rutaArchivoGuardado) {
        this.rutaArchivoGuardado = rutaArchivoGuardado;
    }

    public  String getTipoTerreno() {
        return tipoTerreno;
    }

    public  void setTipoTerreno(String tipoTerreno) {
        this.tipoTerreno = tipoTerreno;
    }

    public  String getRutaArchivoCSV() {
        return rutaArchivoCSV;
    }

    public  void setRutaArchivoCSV(String rutaArchivoCSV) {
        this.rutaArchivoCSV = rutaArchivoCSV;
    }

    public  String getNombreArchivoCSV() {
        return nombreArchivoCSV;
    }

    public  void setNombreArchivoCSV(String nombreArchivoCSV) {
        this.nombreArchivoCSV = nombreArchivoCSV;
    }

    public  String getRutaArchivoDXF() {
        return rutaArchivoDXF;
    }

    public  void setRutaArchivoDXF(String rutaArchivoDXF) {
        this.rutaArchivoDXF = rutaArchivoDXF;
    }

    public  String getNombreArchivoDXF() {
        return nombreArchivoDXF;
    }

    public  void setNombreArchivoDXF(String nombreArchivoDXF) {
        this.nombreArchivoDXF = nombreArchivoDXF;
    }

    public  String getOpMenuPrincipal() {
        return opMenuPrincipal;
    }

    public  void setOpMenuPrincipal(String opMenuPrincipal) {
        this.opMenuPrincipal = opMenuPrincipal;
    }

    public  double getYto() {
        return yto;
    }

    public  void setYto(double yto) {
        this.yto = yto;
    }

    public  double getXto() {
        return xto;
    }

    public  void setXto(double xto) {
        this.xto = xto;
    }

    public double getZto() {
        return zto;
    }

    public  void setZto(double zto) {
        this.zto = zto;
    }

    public  double getXtmin() {
        return xtmin;
    }

    public  void setXtmin(double xtmin) {
        this.xtmin = xtmin;
    }

    public  double getXtmax() {
        return xtmax;
    }

    public  void setXtmax(double xtmax) {
        this.xtmax = xtmax;
    }

    public  double getYtmin() {
        return ytmin;
    }

    public  void setYtmin(double ytmin) {
        this.ytmin = ytmin;
    }

    public  double getYtmax() {
        return ytmax;
    }

    public  void setYtmax(double ytmax) {
        this.ytmax = ytmax;
    }

    public  String getRutaCarpetaActual() {
        return rutaCarpetaActual;
    }

    public  void setRutaCarpetaActual(String rutaCarpetaActual) {
        this.rutaCarpetaActual = rutaCarpetaActual;
    }

    public  String getRutaCarpetaActual2() {
        return rutaCarpetaActual2;
    }

    public  void setRutaCarpetaActual2(String rutaCarpetaActual2) {
        this.rutaCarpetaActual2 = rutaCarpetaActual2;
    }

    public boolean isPrimeraPantalla() {
        return primeraPantalla;
    }

    public void setPrimeraPantalla(boolean primeraPantalla) {
        this.primeraPantalla = primeraPantalla;
    }

    public LatLng getPuntos() {
        return puntos;
    }

    public void setPuntos(LatLng puntos) {
        this.puntos = puntos;
    }

    public String getErrorCumple() {
        return errorCumple;
    }

    public void setErrorCumple(String errorCumple) {
        this.errorCumple = errorCumple;
    }

    public int getId_bd() {
        return id_bd;
    }

    public int gettPol() {
        return tPol;
    }

    public String getTipoMedicion() {
        return tipoMedicion;
    }

    public void setTipoMedicion(String tipoMedicion) {
        this.tipoMedicion = tipoMedicion;
    }

    public boolean isPoligonoCargado() {
        return poligonoCargado;
    }

    public void setPoligonoCargado(boolean poligonoCargado) {
        this.poligonoCargado = poligonoCargado;
    }

    private final int tPol= 0;
    private String tipoMedicion = "";






    //Constructor de Poligono
    public Poligono() {
        area = 0;
        perimetro = 0;
    }

    public void agregarEstacion(Estacion est ){
        estaciones.addElement(est);
    }

    public void modificarEstacion(Integer index, Estacion est ){
        estaciones.set(index, est);
    }

    public void borrarUltimaEstacion() {
        if (!estaciones.isEmpty()) {
            estaciones.remove(estaciones.size() - 1);
        } else {
            // El vector está vacío, no hay nada que borrar.
            // Puedes mostrar un mensaje de error o realizar alguna otra acción si es necesario.
        }
    }

    public Poligono getPoligono(){
        return this;
    }

    /*//copy constructor
    public Poligono Poligono(Poligono pol){
        this.fecha = pol.fecha;                          //Fecha en que se creo el poligono
        this.area = pol.area;                         //area del poligono en metros cuadrados
        this.perimetro = pol.perimetro;                //perimetro del poligono, en metros
        this.sumaNortes = pol.sumaNortes;
        this.sumaSures = pol.sumaSures;
        this.sumaEstes = pol.sumaEstes;
        this.sumaOestes = pol.sumaOestes;
        this.sumaDist = pol.sumaDist;
        this.sumaYX = pol.sumaYX;
        this.sumaXY = pol.sumaXY;
        this.deltaX = pol.deltaX;                   //Error de cierre en el eje X
        this.deltaY = pol.deltaY;                   //Error de cierre en el eje Y
        this.errC = pol.errC;                     // error de cierre
        this.errCierreUn = pol.errCierreUn;
        this.errA = 0.003;                     //Error de cierre admisible
        this.Cy = pol.Cy;
        this.Cx = pol.Cx;
        this.estaciones = pol.estaciones;
        this.corrN = pol.corrN;
        this.corrS = pol.corrS  ;
        this.corrE = pol.corrE;
        this.corrO = pol.corrO;
        this.contEs = pol.contEs;                     //contador de estaciones ó puntos
        this.proyecto = pol.proyecto;               //Nombre del proyecto a que corresponde el poligono
        this.cliente = pol.cliente;                //Nombre del cliente a que corresponde el poligono
        this.ubicacion = pol.ubicacion;              //Ubicacion ó dirección del poligono
        this.responsable = pol.responsable;            //Nombre del Ingeniero/Arquitecto/Topografo responsable
        this.estFinal = pol.estFinal;
        this.errorAceptable = pol.errorAceptable;
        this.nombreArchivoGuardado = pol.nombreArchivoGuardado;
        this.rutaArchivoGuardado = pol.rutaArchivoGuardado;
        this.tipoTerreno = pol.tipoTerreno;
        this.rutaArchivoCSV= pol.rutaArchivoCSV;
        this.nombreArchivoCSV = pol.nombreArchivoCSV;
        this.opMenuPrincipal = pol.opMenuPrincipal;
        this.yto = pol.yto;
        this.xto = pol.xto;
        this.rutaArchivoDXF= pol.rutaArchivoDXF;
        this.nombreArchivoDXF = pol.nombreArchivoDXF;
        this.xto = pol.xto;
        this.xtmin = pol.xtmin;
        this.xtmax = pol.xtmax;
        this.ytmin = pol.ytmin;
        this.ytmax = pol.ytmax;
        this.rutaCarpetaActual = pol.rutaCarpetaActual;


        this.puntos = pol.puntos;
        return this;
    }*/

    public  boolean poligonoCargado = false;


    public Vector<Estacion> getEstaciones(){
        return estaciones;
    }

    public void setEstaciones(Vector<Estacion> estaciones1){
        estaciones = estaciones1;
    }

    public void ingresarEstacion(Estacion est){
        estaciones.addElement(est);
    }

    public void ingresoDatosProyecto(String fechaI, String proyectoI) {
        fecha = fechaI;
        proyecto = proyectoI;
        /*cliente = clienteI;
        ubicacion = ubicacionI;
        responsable = responsableI;*/
        area = 0;
        perimetro = 0;
    }

    public void calcular() {
        calculoUTM2();
        calculoNyS();
        calculoEyO();
        calculoErrorCierre();
        calculoCorrecciones();
        calculoCoorCompensadas();
        calculoCoorTotales();
        calculoAreaPoligono();
    }

    public Double getArea(){
        return area;
    }
    public Double getPerimetro(){ return perimetro;  }



    public void calculoUTM(){
        Estacion estIniFin = estaciones.elementAt(0);
        Estacion nuevaIni = new Estacion();
        String idEstIni = estIniFin.idEst;
        double lati = estIniFin.lat;
        double longi = estIniFin.lon;
        double elevi = estIniFin.alt;
        nuevaIni.idEst = idEstIni;
        nuevaIni.lat = lati;
        nuevaIni.lon = longi;
        nuevaIni.alt = elevi;
        nuevaIni.xp = 0;
        nuevaIni.yp = 0;
        nuevaIni.xt = 0;
        nuevaIni.yt = 0;
        /*if (tipoMedicion.equals("poligono")){
            this.ingresarEstacion(estIniFin);
        }*/
        estaciones.setElementAt(nuevaIni,0);
        for (int i = 0; i < estaciones.size(); i++) {
            Estacion estacionTemp = estaciones.elementAt(i);
            CoordinateConversion objetoCoversor = new CoordinateConversion();
            String utm = objetoCoversor.latLon2UTM(estacionTemp.lat, estacionTemp.lon);
            estacionTemp.UTM = utm;
            String[] utmArray = utm.split(" ");
            estacionTemp.zone = Integer.parseInt(utmArray[0]);
            estacionTemp.latZone = utmArray[1];
            estacionTemp.easting = Double.parseDouble(utmArray[2]);
            estacionTemp.northing = Double.parseDouble(utmArray[3]);
            if (i >0){
                Estacion estacionTempAnterior = estaciones.elementAt(i-1);
                estacionTemp.xp = estacionTemp.easting - estacionTempAnterior.easting;
                estacionTemp.yp = estacionTemp.northing - estacionTempAnterior.northing;
                estacionTemp.dist = Math.sqrt(Math.pow(estacionTemp.xp,2)+Math.pow(estacionTemp.yp,2));
                if ((estacionTemp.xp >= 0)&&(estacionTemp.yp >= 0)){
                    estacionTemp.valorDecimalGrados = Math.toDegrees(Math.atan(estacionTemp.xp/estacionTemp.yp));
                }else if ((estacionTemp.xp >= 0)&&(estacionTemp.yp < 0)){
                    estacionTemp.valorDecimalGrados = 90 + Math.toDegrees(Math.atan(estacionTemp.yp/estacionTemp.xp));
                }else if ((estacionTemp.xp < 0)&&(estacionTemp.yp < 0)) {
                    estacionTemp.valorDecimalGrados = 180 + Math.toDegrees(Math.atan(estacionTemp.xp/estacionTemp.yp));
                }else if ((estacionTemp.xp < 0)&&(estacionTemp.yp >= 0)){
                    estacionTemp.valorDecimalGrados = 270 + Math.toDegrees(Math.atan(estacionTemp.yp/estacionTemp.xp));
                }
                estacionTemp.grado = (int) estacionTemp.valorDecimalGrados;
                estacionTemp.minuto = (int) ((estacionTemp.valorDecimalGrados - estacionTemp.grado)*60);
                estacionTemp.segundo = (int) ((estacionTemp.valorDecimalGrados - estacionTemp.grado - (estacionTemp.minuto/60))*3600);
            }
            estaciones.setElementAt(estacionTemp,i);
        }

    }

    public void calculoUTM2(){
        for (int i = 0; i < estaciones.size(); i++) {
            Estacion estacionTemp =  estaciones.elementAt(i);
            CoordinateConversion objetoCoversor = new CoordinateConversion();
            String utm = objetoCoversor.latLon2UTM(estacionTemp.lat, estacionTemp.lon);
            estacionTemp.UTM = utm;
            String[] utmArray = utm.split(" ");
            estacionTemp.zone = Integer.parseInt(utmArray[0]);
            estacionTemp.latZone = utmArray[1];
            estacionTemp.easting = Double.parseDouble(utmArray[2]);
            estacionTemp.northing = Double.parseDouble(utmArray[3]);
            if (i == 0){
                estacionTemp.dist = 0.0;
                estacionTemp.grado = 0.0;
                estacionTemp.minuto = 0.0;
                estacionTemp.segundo = 0.0;
                estacionTemp.seno = 0.0;
                estacionTemp.coseno = 0.0;
            }else if (i >0){
                Estacion estacionTempAnterior = estaciones.elementAt(i-1);
                estacionTemp.xp = estacionTemp.easting - estacionTempAnterior.easting;
                estacionTemp.yp = estacionTemp.northing - estacionTempAnterior.northing;
                estacionTemp.dist = Math.sqrt(Math.pow(estacionTemp.xp,2)+Math.pow(estacionTemp.yp,2));
            }
            estaciones.setElementAt(estacionTemp, i);
        }
    }


    public void calculoCoorParciales(){
        for (int i = 0; i < estaciones.size(); i++) {
            Estacion estacionTemp = estaciones.elementAt(i);
            perimetro = perimetro + estacionTemp.dist;
            estacionTemp.xp = estacionTemp.dist * estacionTemp.seno;
            estacionTemp.yp = estacionTemp.dist * estacionTemp.coseno;

            if (estacionTemp.radiaciones.size()>0){
                for (int j = 0; j< estacionTemp.radiaciones.size();j++){
                    Estacion radiacion = estacionTemp.radiaciones.elementAt(j);
                    radiacion.xp = radiacion.dist * radiacion.seno;
                    radiacion.yp = radiacion.dist * radiacion.coseno;
                    estacionTemp.radiaciones.setElementAt(radiacion,j);

                }
            }

            estaciones.setElementAt(estacionTemp, i);
        }
    }


    public void calculoNyS(){
        for (int i = 0; i < estaciones.size(); i++) {
            if (i > 0){
                Estacion estacionTemp = estaciones.elementAt(i);
                if (estacionTemp.yp >= 0) {
                    estacionTemp.Norte = estacionTemp.yp;
                    estacionTemp.Sur = 0;
                }else if (estacionTemp.yp < 0) {
                    estacionTemp.Norte = 0;
                    estacionTemp.Sur = Math.abs(estacionTemp.yp);
                }
                estaciones.setElementAt(estacionTemp,i);

            }
        }
    }

    public void calculoEyO(){
        for (int i = 0; i < estaciones.size(); i++) {
            if (i > 0){
                Estacion estacionTemp = estaciones.elementAt(i);
                if (estacionTemp.xp >= 0) {
                    estacionTemp.Este = estacionTemp.xp;
                    estacionTemp.Oeste = 0;
                }else if (estacionTemp.xp < 0) {
                    estacionTemp.Este = 0;
                    estacionTemp.Oeste = Math.abs(estacionTemp.xp);
                }
                estaciones.setElementAt(estacionTemp,i);
            }

        }
    }

    public void calculoErrorCierre() {
        sumaNortes = 0;
        sumaSures = 0;
        sumaEstes = 0;
        sumaOestes = 0;
        sumaDist = 0;
        for (int i = 0; i < estaciones.size(); i++) {
            Estacion estacionTemp = estaciones.elementAt(i);
            sumaNortes = sumaNortes + estacionTemp.Norte;
            sumaSures = sumaSures + estacionTemp.Sur;
            sumaEstes = sumaEstes + estacionTemp.Este;
            sumaOestes = sumaOestes + estacionTemp.Oeste;
            sumaDist = sumaDist + estacionTemp.dist;
        }
        deltaY = Math.abs(sumaNortes - sumaSures);
        deltaX = Math.abs(sumaEstes - sumaOestes);
        double numTemp = (deltaY * deltaY)+(deltaX * deltaX);
        errC = Math.sqrt(numTemp);
        errCierreUn = errC / sumaDist;
        if ((errCierreUn <= 0.003)){
            errorAceptable = true;
            Cy = deltaY / (sumaNortes + sumaSures);
            Cx = deltaX / (sumaEstes + sumaOestes);
            errorCumple = "Yes";

        }else{
            errorAceptable = false;
            Cy = deltaY / (sumaNortes + sumaSures);
            Cx = deltaX / (sumaEstes + sumaOestes);
            errorCumple = "No";

        }
    }

    public void calculoCorrecciones(){

        if (sumaNortes > sumaSures){
            for (int i = 0; i < estaciones.size(); i++) {
                Estacion estacionTemp = estaciones.elementAt(i);
                corrN.addElement(estacionTemp.Norte * Cy * (-1));
                corrS.addElement(estacionTemp.Sur * Cy);
            }
        } else if (sumaNortes == sumaSures){
            for (int i = 0; i < estaciones.size(); i++) {
                corrN.addElement(0.0);
                corrS.addElement(0.0);
            }
        } else{
            for (int i = 0; i < estaciones.size(); i++) {
                Estacion estacionTemp = estaciones.elementAt(i);
                corrN.addElement(estacionTemp.Norte * Cy );
                corrS.addElement(estacionTemp.Sur * Cy * (-1));
            }
        }
        if (sumaEstes > sumaOestes){
            for (int i = 0; i < estaciones.size(); i++) {
                Estacion estacionTemp = estaciones.elementAt(i);
                corrE.addElement(estacionTemp.Este * Cx * (-1));
                corrO.addElement(estacionTemp.Oeste * Cx);
            }
        }  else if (sumaEstes == sumaOestes){
            for (int i = 0; i < estaciones.size(); i++) {
                corrE.addElement(0.0 );
                corrO.addElement(0.0);
            }
        } else{
            for (int i = 0; i < estaciones.size(); i++) {
                Estacion estacionTemp = estaciones.elementAt(i);
                corrE.addElement(estacionTemp.Este * Cx );
                corrO.addElement(estacionTemp.Oeste * Cx * (-1));
            }
        }
    }

    public void calculoCoorCompensadas() {
        for (int i = 0; i < estaciones.size(); i++) {
            Estacion estacionTemp = estaciones.elementAt(i);
            estacionTemp.nc = estacionTemp.Norte + corrN.elementAt(i);
            estacionTemp.sc = estacionTemp.Sur + corrS.elementAt(i);
            estacionTemp.ec = estacionTemp.Este + corrE.elementAt(i);
            estacionTemp.oc = estacionTemp.Oeste + corrO.elementAt(i);
            estaciones.setElementAt(estacionTemp,i);
        }
        for (int i = 0; i < estaciones.size(); i++) {
            Estacion estacionTemp = estaciones.elementAt(i);
            estacionTemp.ypc = estacionTemp.nc + (estacionTemp.sc * (-1));
            estacionTemp.xpc = estacionTemp.ec + (estacionTemp.oc * (-1));
            estaciones.setElementAt(estacionTemp,i);
        }
    }

    public void calculoCoorTotales(){
        for (int i = 0; i < estaciones.size(); i++) {
            Estacion estacionTemp = estaciones.elementAt(i);
            if (i == 0){
                yto = estacionTemp.yt;
                xto = estacionTemp.xt;
                zto = 0;
                estacionTemp.zt = 0;
                estaciones.setElementAt(estacionTemp,i);
            }
            if (i >= 1) {
                Estacion estacionTempAnterior = estaciones.elementAt(i-1);
                Estacion estInicial = estaciones.elementAt(0);
                if (tipoMedicion.equals("poligono")){
                    estacionTemp.xt =  estacionTempAnterior.xt + estacionTemp.xpc;
                    estacionTemp.yt =  estacionTempAnterior.yt + estacionTemp.ypc;
                } else {
                    estacionTemp.xt =  estacionTempAnterior.xt + estacionTemp.xp;
                    estacionTemp.yt =  estacionTempAnterior.yt + estacionTemp.yp;
                }

                estacionTemp.zt = estacionTemp.alt - estInicial.alt;
                estacionTemp.yi = 0 - estacionTemp.yt;
                estaciones.setElementAt(estacionTemp,i);
            }
        }
    }



    public void calculoCoorTotalesDibujar(double ycen, double xcen){
        for (int i = 0; i < estaciones.size(); i++) {
            Estacion estacionTemp = estaciones.elementAt(i);
            if (i == 0){
                yto = ycen;
                estacionTemp.yt = ycen;
                xto = xcen;
                estacionTemp.xt = xcen;
                if (estacionTemp.yt < ytmin){
                    ytmin = estacionTemp.yt;
                }else if (estacionTemp.yt >= ytmax){
                    ytmax = estacionTemp.yt;
                }
                if (estacionTemp.xt < xtmin){
                    xtmin = estacionTemp.xt;
                }else if (estacionTemp.xt >= xtmax){
                    xtmax = estacionTemp.xt;
                }

                if (estacionTemp.radiaciones.size()>0){
                    for (int j = 0; j< estacionTemp.radiaciones.size();j++){
                        Estacion radiacion = estacionTemp.radiaciones.elementAt(j);
                        radiacion.xt = estacionTemp.xt +  radiacion.xp;
                        radiacion.yt = estacionTemp.yt +  radiacion.yp;
                        estacionTemp.radiaciones.setElementAt(radiacion,j);
                    }
                }

                estaciones.setElementAt(estacionTemp,i);

            }
            if (i >= 1) {

                Estacion estacionTempAnterior =estaciones.elementAt(i-1);
                estacionTemp.xt =  estacionTempAnterior.xt + estacionTemp.xp;
                estacionTemp.yt = estacionTempAnterior.yt + estacionTemp.yp;
                estacionTemp.yi = ycen - estacionTemp.yt;
                if (estacionTemp.yt < ytmin){
                    ytmin = estacionTemp.yt;
                }else if (estacionTemp.yt >= ytmax){
                    ytmax = estacionTemp.yt;
                }
                if (estacionTemp.xt < xtmin){
                    xtmin = estacionTemp.xt;
                }else if (estacionTemp.xt >= xtmax){
                    xtmax = estacionTemp.xt;
                }

                if (estacionTemp.radiaciones.size()>0){
                    for (int j = 0; j< estacionTemp.radiaciones.size();j++){
                        Estacion radiacion = estacionTemp.radiaciones.elementAt(j);
                        radiacion.xt = estacionTemp.xt +  radiacion.xp;
                        radiacion.yt = estacionTemp.yt +  radiacion.yp;
                        estacionTemp.radiaciones.setElementAt(radiacion,j);
                    }
                }

                estaciones.setElementAt(estacionTemp,i);
            }
        }
    }


    public void calculoAreaPoligono(){
        sumaYX = 0;
        sumaXY = 0;
        perimetro = 0;
        for (int i = 0; i < (estaciones.size()-1); i++) {
            Estacion estacionTemp = estaciones.elementAt(i);
            Estacion estacionTempSig = estaciones.elementAt(i + 1);
            estacionTemp.YX = estacionTemp.yt * estacionTempSig.xt;
            estacionTemp.XY = estacionTemp.xt * estacionTempSig.yt;
            sumaYX = sumaYX + estacionTemp.YX;
            sumaXY = sumaXY + estacionTemp.XY;
            estaciones.setElementAt(estacionTemp,i);

            //calculo del perimetro
            double deltaX1 = estacionTempSig.xt - estacionTemp.xt;
            double deltaY1 = estacionTempSig.yt - estacionTemp.yt;
            double distancia = Math.sqrt((Math.pow(deltaX1,2)) + (Math.pow(deltaY1,2)));
            perimetro += distancia;

        }

        area = Math.abs(sumaYX - sumaXY)/2;

        if (estaciones.size()>2){
            Estacion estacionTemp = estaciones.elementAt(estaciones.size() - 1);
            Estacion estacionTempSig = estaciones.elementAt(0);
            double deltaX1 = estacionTempSig.xt - estacionTemp.xt;
            double deltaY1 = estacionTempSig.yt - estacionTemp.yt;
            double distancia = (Math.sqrt((Math.pow(deltaX1,2)) + (Math.pow(deltaY1,2))));
            perimetro += distancia;
        }

    }




    public void limpiarPoligono() {
        area = 0;                         //area del poligono en metros cuadrados
        perimetro = 0;                //perimetro del poligono, en metros
        sumaNortes = 0;
        sumaSures = 0;
        sumaEstes = 0;
        sumaOestes = 0;
        sumaDist = 0;
        sumaYX = 0;
        sumaXY = 0;
        deltaX = 0;                   //Error de cierre en el eje X
        deltaY = 0;                   //Error de cierre en el eje Y
        errC = 0;                     // error de cierre
        errCierreUn = 0;
        errA = 0;                     //Error de cierre admisible
        Cy = 0;
        Cx = 0;
        estaciones.removeAllElements();
        corrN.removeAllElements();
        corrS.removeAllElements();
        corrE.removeAllElements();
        corrO.removeAllElements();
        contEs = 0;                     //contador de estaciones ó puntos

        estFinal = false;
        errorAceptable = false;
        nombreArchivoGuardado = "";
        rutaArchivoGuardado = "";
        tipoTerreno = "";
        rutaArchivoCSV= "";
        nombreArchivoCSV = "";
        opMenuPrincipal = "0";
        yto = 0.0;
        xto = 0.0;
        rutaArchivoDXF= "";
        nombreArchivoDXF = "";
        xto = 0.0;
        xtmin = 0;
        xtmax = 0;
        ytmin = 0;
        ytmax = 0;
        rutaCarpetaActual = "";
        this.tipoMedicion = "poligono";


    }

    public void setFecha(String f){
        fecha = f;
    }

    public Poligono pol(){
        return  this;
    }

    public void limpiarEstIni(){
        estaciones.elementAt(0).dist = 0;
        estaciones.elementAt(0).xp = 0;
        estaciones.elementAt(0).yp = 0;
        estaciones.elementAt(0).xt = 0;
        estaciones.elementAt(0).yt = 0;
    }

    public void calculoCoorParciales2(){
        perimetro = 0;

        for (int i = 0; i < estacionesPolFinal.size(); i++) {
            if (i == 0){
                Estacion estacionTemp = estacionesPolFinal.elementAt(i);
                estacionTemp.xp = 0;
                estacionTemp.yp = 0;
                estacionesPolFinal.setElementAt(estacionTemp, i);
            } else if (i >0){
                Estacion estacionTemp = estacionesPolFinal.elementAt(i);
                perimetro = perimetro + estacionTemp.dist;
                estacionTemp.xp = estacionTemp.dist * estacionTemp.seno;
                estacionTemp.yp = estacionTemp.dist * estacionTemp.coseno;
                estacionesPolFinal.setElementAt(estacionTemp, i);
            }

        }
    }


    public void calculoNyS2(){
        for (int i = 0; i < estacionesPolFinal.size(); i++) {
            if (i > 0){
                Estacion estacionTemp = estacionesPolFinal.elementAt(i);
                if (estacionTemp.yp >= 0) {
                    estacionTemp.Norte = estacionTemp.yp;
                    estacionTemp.Sur = 0;
                }else if (estacionTemp.yp < 0) {
                    estacionTemp.Norte = 0;
                    estacionTemp.Sur = Math.abs(estacionTemp.yp);
                }
                estacionesPolFinal.setElementAt(estacionTemp,i);

            }
        }
    }

    public void calculoEyO2(){
        for (int i = 0; i < estacionesPolFinal.size(); i++) {
            if (i > 0){
                Estacion estacionTemp = estacionesPolFinal.elementAt(i);
                if (estacionTemp.xp >= 0) {
                    estacionTemp.Este = estacionTemp.xp;
                    estacionTemp.Oeste = 0;
                }else if (estacionTemp.xp < 0) {
                    estacionTemp.Este = 0;
                    estacionTemp.Oeste = Math.abs(estacionTemp.xp);
                }
                estacionesPolFinal.setElementAt(estacionTemp,i);
            }

        }
    }

    public void calculoErrorCierre2() {
        sumaNortes = 0;
        sumaSures = 0;
        sumaEstes = 0;
        sumaOestes = 0;
        sumaDist = 0;
        for (int i = 0; i < estacionesPolFinal.size(); i++) {
            Estacion estacionTemp = estacionesPolFinal.elementAt(i);
            sumaNortes = sumaNortes + estacionTemp.Norte;
            sumaSures = sumaSures + estacionTemp.Sur;
            sumaEstes = sumaEstes + estacionTemp.Este;
            sumaOestes = sumaOestes + estacionTemp.Oeste;
            sumaDist = sumaDist + estacionTemp.dist;
        }
        deltaY = Math.abs(sumaNortes - sumaSures);
        deltaX = Math.abs(sumaEstes - sumaOestes);
        double numTemp = (deltaY * deltaY)+(deltaX * deltaX);
        errC = Math.sqrt(numTemp);
        errCierreUn = errC / sumaDist;
        if ((errCierreUn <= 0.003)){
            errorAceptable = true;
            Cy = deltaY / (sumaNortes + sumaSures);
            Cx = deltaX / (sumaEstes + sumaOestes);
            errorCumple = "Yes";

        }else{
            errorAceptable = false;
            Cy = deltaY / (sumaNortes + sumaSures);
            Cx = deltaX / (sumaEstes + sumaOestes);
            errorCumple = "No";

        }
    }

    public void calculoCorrecciones2(){

        if (sumaNortes > sumaSures){
            for (int i = 0; i < estacionesPolFinal.size(); i++) {
                Estacion estacionTemp = estacionesPolFinal.elementAt(i);
                corrN.addElement(estacionTemp.Norte * Cy * (-1));
                corrS.addElement(estacionTemp.Sur * Cy);
            }
        } else if (sumaNortes == sumaSures){
            for (int i = 0; i < estacionesPolFinal.size(); i++) {
                corrN.addElement(0.0);
                corrS.addElement(0.0);
            }
        } else{
            for (int i = 0; i < estacionesPolFinal.size(); i++) {
                Estacion estacionTemp = estacionesPolFinal.elementAt(i);
                corrN.addElement(estacionTemp.Norte * Cy );
                corrS.addElement(estacionTemp.Sur * Cy * (-1));
            }
        }
        if (sumaEstes > sumaOestes){
            for (int i = 0; i < estacionesPolFinal.size(); i++) {
                Estacion estacionTemp = estacionesPolFinal.elementAt(i);
                corrE.addElement(estacionTemp.Este * Cx * (-1));
                corrO.addElement(estacionTemp.Oeste * Cx);
            }
        }  else if (sumaEstes == sumaOestes){
            for (int i = 0; i < estacionesPolFinal.size(); i++) {
                corrE.addElement(0.0 );
                corrO.addElement(0.0);
            }
        } else{
            for (int i = 0; i < estacionesPolFinal.size(); i++) {
                Estacion estacionTemp = estacionesPolFinal.elementAt(i);
                corrE.addElement(estacionTemp.Este * Cx );
                corrO.addElement(estacionTemp.Oeste * Cx * (-1));
            }
        }
    }

    public void calculoCoorCompensadas2() {
        for (int i = 0; i < estacionesPolFinal.size(); i++) {
            Estacion estacionTemp = estacionesPolFinal.elementAt(i);
            estacionTemp.nc = estacionTemp.Norte + corrN.elementAt(i);
            estacionTemp.sc = estacionTemp.Sur + corrS.elementAt(i);
            estacionTemp.ec = estacionTemp.Este + corrE.elementAt(i);
            estacionTemp.oc = estacionTemp.Oeste + corrO.elementAt(i);
            estacionesPolFinal.setElementAt(estacionTemp,i);
        }
        for (int i = 0; i < estacionesPolFinal.size(); i++) {
            Estacion estacionTemp = estacionesPolFinal.elementAt(i);
            estacionTemp.ypc = estacionTemp.nc + (estacionTemp.sc * (-1));
            estacionTemp.xpc = estacionTemp.ec + (estacionTemp.oc * (-1));
            estacionesPolFinal.setElementAt(estacionTemp,i);
        }
    }

    public void calculoCoorTotales2(){
        for (int i = 0; i < estacionesPolFinal.size(); i++) {
            Estacion estacionTemp = estacionesPolFinal.elementAt(i);
            if (i == 0){
                yto = estacionTemp.yt;
                xto = estacionTemp.xt;
                zto = 0;
                estacionTemp.zt = 0;
                estacionesPolFinal.setElementAt(estacionTemp,i);
            }
            if (i >= 1) {
                Estacion estacionTempAnterior = estacionesPolFinal.elementAt(i-1);
                Estacion estInicial = estacionesPolFinal.elementAt(0);
                estacionTemp.xt =  estacionTempAnterior.xt + estacionTemp.xpc;
                estacionTemp.yt =  estacionTempAnterior.yt + estacionTemp.ypc;
                estacionTemp.zt = estacionTemp.alt - estInicial.alt;
                estacionTemp.yi = 0 - estacionTemp.yt;
                estacionesPolFinal.setElementAt(estacionTemp,i);
            }
        }
    }




    public void calculoAreaPoligono2(){
        sumaYX = 0;
        sumaXY = 0;
        for (int i = 0; i < (estacionesPolFinal.size()-1); i++) {
            Estacion estacionTemp = estacionesPolFinal.elementAt(i);
            Estacion estacionTempSig = estacionesPolFinal.elementAt(i + 1);
            estacionTemp.YX = estacionTemp.yt * estacionTempSig.xt;
            estacionTemp.XY = estacionTemp.xt * estacionTempSig.yt;
            sumaYX = sumaYX + estacionTemp.YX;
            sumaXY = sumaXY + estacionTemp.XY;
            estacionesPolFinal.setElementAt(estacionTemp,i);
        }
        area = Math.abs(sumaYX - sumaXY)/2;
    }


    public void calcularPerimetro2(){
        perimetro = 0;
        for (int i = 1; i < estacionesPolFinal.size();i++){
            double deltaX1 = estacionesPolFinal.elementAt(i).xt - estacionesPolFinal.elementAt(i-1).xt;
            double deltaY1 = estacionesPolFinal.elementAt(i).yt - estacionesPolFinal.elementAt(i-1).yt;
            double distancia = Math.sqrt(Math.sqrt((Math.pow(deltaX1,2)) + (Math.pow(deltaY1,2))));
            perimetro += distancia;
        }
        /*double deltaX1 = estacionesPolFinal.elementAt(0).xt - estacionesPolFinal.elementAt(estacionesPolFinal.size()-1).xt;
        double deltaY1 = estacionesPolFinal.elementAt(0).yt - estacionesPolFinal.elementAt(estacionesPolFinal.size()-1).yt;
        double distancia = Math.sqrt(Math.sqrt((Math.pow(deltaX1,2)) + (Math.pow(deltaY1,2))));
        perimetro += distancia;*/

    }

    public Estacion primeraEstacionPolFinal(){
        Estacion e0 = new Estacion();
        boolean primeraEstacionEncontrada = false;
        if (estaciones.size()>0){
            for (int i = 0; i < estaciones.size(); i++){
                Estacion eTemp = estaciones.elementAt(i);
                if (eTemp.partePoligonoFinal){
                    if (primeraEstacionEncontrada){

                    }else {
                        e0 = eTemp;
                        primeraEstacionEncontrada = true;
                    }
                } else {
                    if (eTemp.radiaciones.size()>0){
                        for (int j = 0; j < eTemp.radiaciones.size(); j++){
                            Estacion rTemp = eTemp.radiaciones.elementAt(j);
                            if (rTemp.partePoligonoFinal){
                                if (primeraEstacionEncontrada){

                                }else {
                                    e0 = rTemp;
                                    primeraEstacionEncontrada = true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return  e0;
    }


    public void calculosParaPoligonoFinal(){
        estacionesPolFinal.removeAllElements();
        try {
            if (estaciones.size()>0){
                for (int i = 0; i < estaciones.size(); i++){
                    Estacion e1 = estaciones.elementAt(i);

                    if (e1.partePoligonoFinal){
                        Estacion eTemp2 = (Estacion) e1.clone();
                        estacionesPolFinal.addElement(eTemp2);
                    }

                    if (e1.radiaciones.size()>0){
                        for (int j = 0; j < e1.radiaciones.size(); j++){
                            Estacion r1 = e1.radiaciones.elementAt(j);
                            if (r1.partePoligonoFinal){
                                Estacion eTemp2 = (Estacion) r1.clone();
                                estacionesPolFinal.addElement(eTemp2);
                            }
                        }
                    }
                }
            }
        } catch (CloneNotSupportedException e){e.printStackTrace();
        }
    }


    public void calculosParaPoligonoFinal2(){
        if (estacionesPolFinal.size()>0){
            double dX;
            double dY;
            double angulo;

            try{
                Estacion eTemp = estacionesPolFinal.elementAt(0);
                Estacion eTemp2 = (Estacion) eTemp.clone();
                estacionesPolFinal.addElement(eTemp2);
            } catch (CloneNotSupportedException e){
                e.printStackTrace();
            }

            for (int i = 0; i <estacionesPolFinal.size(); i++){
                if (i == 0){
                    dX = 0;
                    dY = 0;
                    angulo = 0;
                    estacionesPolFinal.elementAt(i).dist = 0;
                    estacionesPolFinal.elementAt(i).valorDecimalGrados = 0;
                    estacionesPolFinal.elementAt(i).grado = 0;
                    estacionesPolFinal.elementAt(i).seno =  0;
                    estacionesPolFinal.elementAt(i).coseno = 0;
                    estacionesPolFinal.elementAt(i).idAnterior = estacionesPolFinal.elementAt(i).idEst;
                    estacionesPolFinal.elementAt(i).idEst = "E-0";

                } else if (i >= 1) {
                    dX = (estacionesPolFinal.elementAt(i).xt - estacionesPolFinal.elementAt(i-1).xt);
                    dY = (estacionesPolFinal.elementAt(i).yt - estacionesPolFinal.elementAt(i-1).yt);
                    double diferencial = dX/dY;
                    double tangenteInversa = Math.atan(diferencial);
                    double anguloVariable = Math.toDegrees(tangenteInversa);
                    angulo = 0;

                    if (dX > 0){
                        if (dY>0){
                            angulo = anguloVariable;
                        } else if (dY==0){
                            angulo = 90;
                        } else if (dY <0){
                            angulo = 180 + anguloVariable;
                        }
                    }else if (dX<0){
                        if (dY>0){
                            angulo = 360 + anguloVariable;
                        } else if (dY==0){
                            angulo = 270;
                        } else if (dY <0){
                            angulo = 180 + anguloVariable;
                        }
                    }else if (dX == 0){
                        if (dY>0){
                            angulo = 0;
                        } else if (dY==0){
                            angulo = 0;
                        } else if (dY <0){
                            angulo = 180 ;
                        }
                    }

                    if (angulo == 360 ){
                        angulo = 0;
                    }

                    estacionesPolFinal.elementAt(i).dist = Math.sqrt(Math.pow(dX,2) + Math.pow(dY,2));
                    estacionesPolFinal.elementAt(i).valorDecimalGrados = angulo;
                    int grados = (int) angulo;
                    //estacionesPolFinal.elementAt(i).grado = grados;
                    estacionesPolFinal.elementAt(i).grado = grados;
                    double minutos = ((angulo - grados)*60);
                    int min = (int) minutos;
                    estacionesPolFinal.elementAt(i).minuto = min;
                    double seg = (minutos - min) * 60;
                    estacionesPolFinal.elementAt(i).segundo = seg;
                    if (estacionesPolFinal.elementAt(i).segundo == 60){
                        estacionesPolFinal.elementAt(i).segundo = 0;
                        estacionesPolFinal.elementAt(i).minuto += 1;
                    }

                    if (estacionesPolFinal.elementAt(i).minuto == 60){
                        estacionesPolFinal.elementAt(i).minuto = 0;
                        estacionesPolFinal.elementAt(i).grado += 1;
                        if (estacionesPolFinal.elementAt(i).grado>360){
                            estacionesPolFinal.elementAt(i).grado = estacionesPolFinal.elementAt(i).grado - 360;
                        }
                    }

                    Double anguloRadianes = Math.toRadians(angulo);

                    estacionesPolFinal.elementAt(i).seno =  Math.sin(anguloRadianes);
                    estacionesPolFinal.elementAt(i).coseno = Math.cos(anguloRadianes);
                    estacionesPolFinal.elementAt(i).idAnterior = estacionesPolFinal.elementAt(i).idEst;

                    if (i == (estacionesPolFinal.size()-1)){
                        estacionesPolFinal.elementAt(i).idEst = "E-0";
                    }else
                        estacionesPolFinal.elementAt(i).idEst = "E-" + i;
                }
            }
        }
    }

    public static double fijarNumero(double numero, int digitos) {
        double resultado;
        resultado = numero * Math.pow(10, digitos);
        resultado = Math.round(resultado);
        resultado = resultado/Math.pow(10, digitos);
        return resultado;
    }

    public static double [] calcularGPSdeUTM (String utm1){
        double[] latLon;
        CoordinateConversion objetoCoversor = new CoordinateConversion();
        latLon = objetoCoversor.utm2LatLon(utm1);
        return latLon;
    }



}