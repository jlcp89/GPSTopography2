package com.desarrollojlcp.gps_topography.map.model.reports;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Environment;
import android.widget.Toast;
import com.desarrollojlcp.gps_topography.R;
import com.desarrollojlcp.gps_topography.map.model.Estacion;
import com.desarrollojlcp.gps_topography.map.model.Poligono;
import com.desarrollojlcp.gps_topography.map.model.reports.Cabezera;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jsevy.adxf.DXFCanvas;
import com.jsevy.adxf.DXFDocument;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Objects;


public class GenerarArchivos {


    private static Poligono poligono = new Poligono();
    private static Context context;
    private static File file;
    public static String encabezado, autor;



    public static void generar1 (Poligono pol, Context con){
        poligono = pol;
        context = con;
        generarTXT();
        generarDXF();
        generarPDF();
    }

    public static void generar2 (Poligono pol, Context con){
        poligono = pol;
        context = con;
        generarTXT();

    }

    public static void generarTXT(){
        int conti = poligono.getEstaciones().size();
        DecimalFormat df = new DecimalFormat("################.###");
        String cadTemp = "";
        Uri uri = Uri.parse(poligono.getRutaCarpetaActual2() + File.separator + poligono.getNombreArchivoCSV());
        file = new File(Objects.requireNonNull(uri.getPath()));
        Objects.requireNonNull(file.getParentFile()).mkdirs();

        FileOutputStream pw = null;
        try {
            pw = new FileOutputStream(file);
            StringBuilder sb = new StringBuilder();
            cadTemp = "#Point,Description,X,Y,Z  -  (m)  -  www.d3sarrollo.com";
            sb.append(cadTemp);

            sb.append("\r\n");
            sb.append("\r\n");
            for (int F = 0; F < (conti - 1); F++) {
                Estacion estacionTemp = poligono.estaciones.elementAt(F);
                sb.append(Integer.toString(F));
                sb.append(",");

                cadTemp = (estacionTemp.getObservaciones());
                sb.append(cadTemp);
                sb.append(",");

                cadTemp = df.format(estacionTemp.getLat());
                sb.append(cadTemp);
                sb.append(",");

                cadTemp = df.format(estacionTemp.getLon());
                sb.append(cadTemp);
                sb.append(",");
                cadTemp = df.format(estacionTemp.getAlt());
                sb.append(cadTemp);
                sb.append("");
                sb.append("\r\n");
            }
            byte[] contentInBytes = sb.toString().getBytes();
            pw.write(contentInBytes);
            pw.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(context,"Error FileNotFound -----  "+ e +"  -----on loading TXT, working on this.",Toast.LENGTH_LONG).show();

        } catch (Exception e1) {
            e1.printStackTrace();
            Toast.makeText(context,"Error-----  "+ e1 +"  -----on loading TXT, working on this.",Toast.LENGTH_LONG).show();
        }
    }

    public static void generarDXF (){
        DXFDocument dxfDocument = new DXFDocument(poligono.nombreArchivoDXF);
        DXFCanvas dxfCanvas = dxfDocument.getCanvas();
        drawDesign(dxfCanvas);
        try
        {
            String stringOutput = dxfDocument.toDXFString();
            System.out.println(stringOutput);
            String tarjetaSD = Environment.getExternalStorageDirectory().toString();
            String nombreCarpetaProyectos = "GPS_Topography_Projects";
            String rutaCarpetaProyectos = tarjetaSD + File.separator + nombreCarpetaProyectos;
            File carpetaProyectosGPST = new File (rutaCarpetaProyectos);
            if (!carpetaProyectosGPST.exists()) {
                carpetaProyectosGPST.mkdir();
            }
            File carpetaProyectoActual = new File (poligono.rutaCarpetaActual2);
            if (!carpetaProyectoActual.exists()){
                carpetaProyectoActual.mkdir();
            }

            FileWriter dxfFileWriter = new FileWriter(poligono.rutaCarpetaActual2+File.separator + poligono.nombreArchivoDXF);
            dxfFileWriter.write(stringOutput);
            dxfFileWriter.flush();
            dxfFileWriter.close();
        }
        catch (IOException e)
        {
            System.out.println("Bah!!!!! " + e.toString());
        }
    }

    private static void drawDesign(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawPaint(paint);
        dibujarEstaciones(canvas);
        dibujarLibreta(canvas);
        dibujarElementos(canvas);
    }

    public static void dibujarEstaciones(Canvas canvas){
        for (int i = 0; i < poligono.estaciones.size(); i++){
            Estacion estTemp = poligono.estaciones.elementAt(i);
            String id = estTemp.idEst ;
            float cX = (float) (estTemp.xt * 1);
            float cY = (float) (estTemp.yt * -1);
            if (!estTemp.ultimoPunto) {
                dibujarNodo(canvas,id,cX,cY,(float)estTemp.xt,(float)estTemp.yt, estTemp.isPartePoligonoFinal());
            }
        }
    }

    private static void dibujarNodo(Canvas canvas, String id, float cX, float cY, float coorX, float coorY, boolean partePoligonoFinal){
        final Paint paint = new Paint();
        paint.setColor(Color.BLUE);

        paint.setStyle(Paint.Style.STROKE);
        int tamano = 1;

        paint.setStrokeWidth(tamano);
        canvas.drawPoint(cX, cY, paint);
        canvas.drawCircle(cX, cY,10 * tamano, paint);
        paint.setStyle(Paint.Style.FILL);
        float division = (10 * tamano) / 2;
        canvas.drawCircle(cX, cY,division, paint);
        paint.setStyle(Paint.Style.FILL);
        DecimalFormat df = new DecimalFormat("###,###.##");
        String cadX = df.format(coorX);
        String cadY = df.format(coorY);

        paint.setTextSize(4f * tamano*12);
        paint.setStrokeWidth(tamano * 8);
        canvas.drawText(id, (float) (cX - (20*tamano*4)), cY+(4*tamano*-6), paint);
        paint.setTextSize(4f * tamano * 4);
        paint.setStrokeWidth(tamano);
        String cadCoor = "(" + cadX + " , " + cadY + ")";
        canvas.drawText(cadCoor, cX - 23, (cY*tamano)+30 , paint);
    }

    private static Double YMin(){
        //encontrar coordenada minima en metros
        double yMin = 0;
        if (poligono.estaciones.size()>0) {
            for (int i = 0; i < poligono.estaciones.size(); i++) {
                Estacion e = poligono.estaciones.elementAt(i);
                if (e.yt < yMin){
                    yMin = e.yt;
                }
                if (e.radiaciones.size() > 0) {
                    for (int j = 0; j < e.radiaciones.size(); j++) {
                        Estacion r = e.radiaciones.elementAt(j);
                        if (r.yt < yMin){
                            yMin = r.yt;
                        }
                    }
                }

            }
        }
        return yMin;
    }

    public static void dibujarLibreta (Canvas canvas){
        final Paint paint = new Paint();
        paint.setStrokeWidth(4);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(40f);
        float x, y, x2,y2, xL1, xL2,xL3, xL4, xL5;
        String texto;
        x = 50;
        y = 50 + ((float)(Math.abs(YMin()) * 1));
        paint.setColor(Color.WHITE);
        x2 = 900;
        y2 = 40;
        xL1 = 125;
        xL2 = 250;
        xL3 = 400;
        xL4 = 600;
        xL5 = 750;

        canvas.drawLine(x, y, x + x2, y, paint);
        canvas.drawLine(x, y+y2, x + x2, y+y2, paint);

        //lineas verticales de la fila
        canvas.drawLine(x, y, x, y+y2, paint);
        canvas.drawLine(x+x2, y, x + x2, y+y2, paint);

        paint.setColor(Color.RED);
        paint.setTextSize(28f);
        texto = String.valueOf(R.string.libreta);
        canvas.drawText(texto, x +300, y+27 , paint);

        //Segunda linea
        //lineas horizontales de la fila
        y += 40;

        paint.setColor(Color.WHITE);

        canvas.drawLine(x, y, x + x2, y, paint);
        canvas.drawLine(x, y+y2, x + x2, y+y2, paint);

        //lineas verticales de la fila
        canvas.drawLine(x, y, x, y+y2, paint);
        canvas.drawLine(x+x2, y, x + x2, y+y2, paint);
        //lineas division
        canvas.drawLine(x+xL1, y, x + xL1, y+y2, paint);
        canvas.drawLine(x+xL2, y, x + xL2, y+y2, paint);
        canvas.drawLine(x+xL3, y, x + xL3, y+y2, paint);
        canvas.drawLine(x+xL4, y, x + xL4, y+y2, paint);
        canvas.drawLine(x+xL5, y, x + xL5, y+y2, paint);

        paint.setColor(Color.RED);
        paint.setTextSize(28f);
        texto = String.valueOf(R.string.est);
        canvas.drawText(texto, x +10, y+27 , paint);
        texto = String.valueOf(R.string.PO);
        canvas.drawText(texto, x +10+xL1, y+27 , paint);

        texto = String.valueOf(R.string.dist);
        canvas.drawText(texto, x +10+xL2, y+27 , paint);
        texto = String.valueOf(R.string.azimut);
        canvas.drawText(texto, x +10+xL3, y+27 , paint);

        texto = String.valueOf(R.string.xt);
        canvas.drawText(texto, x +10+xL4+40, y+27 , paint);
        texto = String.valueOf(R.string.yt);
        canvas.drawText(texto, x +10+xL5+40, y+27 , paint);



        //imprimir la linea de la E-0
        y += 40;

        paint.setColor(Color.WHITE);

        canvas.drawLine(x, y, x + x2, y, paint);
        canvas.drawLine(x, y+y2, x + x2, y+y2, paint);

        //lineas verticales de la fila
        canvas.drawLine(x, y, x, y+y2, paint);
        canvas.drawLine(x+x2, y, x + x2, y+y2, paint);
        //lineas division
        canvas.drawLine(x+xL1, y, x + xL1, y+y2, paint);
        canvas.drawLine(x+xL2, y, x + xL2, y+y2, paint);
        canvas.drawLine(x+xL3, y, x + xL3, y+y2, paint);
        canvas.drawLine(x+xL4, y, x + xL4, y+y2, paint);
        canvas.drawLine(x+xL5, y, x + xL5, y+y2, paint);

        if (poligono.estaciones.size()>0){
            if (poligono.estaciones.elementAt(0).isPartePoligonoFinal()){
                paint.setColor(Color.YELLOW);
            } else {
                paint.setColor(Color.CYAN);
            }
        }
        paint.setTextSize(28f);
        texto = "-";
        canvas.drawText(texto, x +10+30, y+27 , paint);
        texto = "E-0";
        canvas.drawText(texto, x +10+xL1, y+27 , paint);
        texto = "-";
        canvas.drawText(texto, x +10+xL2+80, y+27 , paint);
        canvas.drawText(texto, x +10+xL3+80, y+27 , paint);
        texto = "0";
        canvas.drawText(texto, x +10+xL4, y+27 , paint);
        texto = "0";
        canvas.drawText(texto, x +10+xL5, y+27 , paint);

        if (poligono.estaciones.size()>0){
            for (int i = 0; i< poligono.estaciones.size(); i++){
                Estacion e = poligono.estaciones.elementAt(i);
                if (i == 0){
                    //La linea de la E-0 ya esta dibujada, ahora hay que dibujar sus radiaciones
                    if (e.radiaciones.size()>0){
                        for (int j = 0; j < e.radiaciones.size(); j++){
                            Estacion r = e.radiaciones.elementAt(j);

                            //imprimir cada una de las radiaciones
                            y += 40;

                            paint.setColor(Color.WHITE);

                            canvas.drawLine(x, y, x + x2, y, paint);
                            canvas.drawLine(x, y+y2, x + x2, y+y2, paint);

                            //lineas verticales de la fila
                            canvas.drawLine(x, y, x, y+y2, paint);
                            canvas.drawLine(x+x2, y, x + x2, y+y2, paint);
                            //lineas division
                            canvas.drawLine(x+xL1, y, x + xL1, y+y2, paint);
                            canvas.drawLine(x+xL2, y, x + xL2, y+y2, paint);
                            canvas.drawLine(x+xL3, y, x + xL3, y+y2, paint);
                            canvas.drawLine(x+xL4, y, x + xL4, y+y2, paint);
                            canvas.drawLine(x+xL5, y, x + xL5, y+y2, paint);

                            if (r.isPartePoligonoFinal()){
                                paint.setColor(Color.YELLOW);
                            }else {
                                paint.setColor(Color.CYAN);
                            }

                            paint.setTextSize(22f);
                            texto = e.idEst;
                            canvas.drawText(texto, x +10, y+27 , paint);
                            texto = r.idEst;
                            canvas.drawText(texto, x +10+xL1, y+27 , paint);

                            DecimalFormat df = new DecimalFormat("###,###.##");
                            texto = df.format(r.dist);
                            canvas.drawText(texto, x +10+xL2, y+27 , paint);
                            texto = ((int) r.grado)+"°" + ((int) r.minuto)+"\'"+ df.format(r.segundo)+"\"";
                            canvas.drawText(texto, x +10+xL3, y+27 , paint);
                            texto = df.format(r.xt);
                            canvas.drawText(texto, x +10+xL4, y+27 , paint);
                            texto = df.format(r.yt);
                            canvas.drawText(texto, x +10+xL5, y+27 , paint);
                        }
                    }
                }else if (poligono.estaciones.size()>1) {
                    Estacion e0 = poligono.estaciones.elementAt(i-1);
                    //imprimir cada una de las estaciones
                    y += 40;

                    paint.setColor(Color.WHITE);

                    canvas.drawLine(x, y, x + x2, y, paint);
                    canvas.drawLine(x, y+y2, x + x2, y+y2, paint);

                    //lineas verticales de la fila
                    canvas.drawLine(x, y, x, y+y2, paint);
                    canvas.drawLine(x+x2, y, x + x2, y+y2, paint);
                    //lineas division
                    canvas.drawLine(x+xL1, y, x + xL1, y+y2, paint);
                    canvas.drawLine(x+xL2, y, x + xL2, y+y2, paint);
                    canvas.drawLine(x+xL3, y, x + xL3, y+y2, paint);
                    canvas.drawLine(x+xL4, y, x + xL4, y+y2, paint);
                    canvas.drawLine(x+xL5, y, x + xL5, y+y2, paint);

                    if (e.isPartePoligonoFinal()){
                        paint.setColor(Color.YELLOW);
                    }else {
                        paint.setColor(Color.CYAN);
                    }
                    paint.setTextSize(22f);
                    texto = e0.idEst;
                    canvas.drawText(texto, x +10, y+27 , paint);
                    texto = e.idEst;
                    canvas.drawText(texto, x +10+xL1, y+27 , paint);

                    DecimalFormat df = new DecimalFormat("###,###.##");
                    texto = df.format(e.dist);
                    canvas.drawText(texto, x +10+xL2, y+27 , paint);
                    texto = ((int) e.grado)+"°" + ((int) e.minuto)+"\'"+ df.format(e.segundo)+"\"";
                    canvas.drawText(texto, x +10+xL3, y+27 , paint);
                    texto = df.format(e.xt);
                    canvas.drawText(texto, x +10+xL4, y+27 , paint);
                    texto = df.format(e.yt);
                    canvas.drawText(texto, x +10+xL5, y+27 , paint);

                    //La linea de la E-0 ya esta dibujada, ahora hay que dibujar sus radiaciones
                    if (e.radiaciones.size()>0){
                        for (int j = 0; j < e.radiaciones.size(); j++){
                            Estacion r = e.radiaciones.elementAt(j);

                            //imprimir cada una de las radiaciones
                            y += 40;

                            paint.setColor(Color.WHITE);

                            canvas.drawLine(x, y, x + x2, y, paint);
                            canvas.drawLine(x, y+y2, x + x2, y+y2, paint);

                            //lineas verticales de la fila
                            canvas.drawLine(x, y, x, y+y2, paint);
                            canvas.drawLine(x+x2, y, x + x2, y+y2, paint);
                            //lineas division
                            canvas.drawLine(x+xL1, y, x + xL1, y+y2, paint);
                            canvas.drawLine(x+xL2, y, x + xL2, y+y2, paint);
                            canvas.drawLine(x+xL3, y, x + xL3, y+y2, paint);
                            canvas.drawLine(x+xL4, y, x + xL4, y+y2, paint);
                            canvas.drawLine(x+xL5, y, x + xL5, y+y2, paint);

                            if (r.isPartePoligonoFinal()){
                                paint.setColor(Color.YELLOW);
                            }else {
                                paint.setColor(Color.CYAN);
                            }
                            paint.setTextSize(22f);
                            texto = e.idEst;
                            canvas.drawText(texto, x +10, y+27 , paint);
                            texto = r.idEst;
                            canvas.drawText(texto, x +10+xL1, y+27 , paint);

                            df = new DecimalFormat("###,###.##");
                            texto = df.format(r.dist);
                            canvas.drawText(texto, x +10+xL2, y+27 , paint);
                            texto = ((int) r.grado)+"°" + ((int) r.minuto)+"\'"+ df.format(r.segundo)+"\"";
                            canvas.drawText(texto, x +10+xL3, y+27 , paint);
                            texto = df.format(r.xt);
                            canvas.drawText(texto, x +10+xL4, y+27 , paint);
                            texto = df.format(r.yt);
                            canvas.drawText(texto, x +10+xL5, y+27 , paint);
                        }
                    }
                }
            }
        }
    }

    private static void dibujarElementos(Canvas canvas){
        if (poligono.estaciones.size() > 1){
            for (int i = 0; i < poligono.estaciones.size(); i++){
                if (i != 0){
                    Estacion estacion1 = poligono.estaciones.elementAt(i-1);
                    Estacion estacion2 = poligono.estaciones.elementAt(i);
                    float cXN = (float) (estacion1.xt * 1);
                    float cYN = (float) (estacion1.yt * -1);
                    float cXF = (float) (estacion2.xt * 1);
                    float cYF = (float) (estacion2.yt * -1);
                    if (cYN == 0) {
                        cYN = 0;
                    }
                    if (cYF == 0) {
                        cYF = 0;
                    }
                    dibujarElemento(canvas,cXN,cYN,cXF,cYF);
                }
            }
        }
    }

    private  static void dibujarElemento(Canvas canvas, float cXN, float cYN, float cXF, float cYF){
        final Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        int tamano = 1;
        //Dibujar Elemento
        paint.setStrokeWidth(2*tamano*2);
        canvas.drawLine(cXN, cYN, cXF, cYF, paint);
    }

    static Phrase ESPACIO = new Phrase(" ");

    public  static void generarPDF() {
        Paint paint = new Paint();
        PdfWriter writer = null;
        Uri uri = Uri.parse(poligono.rutaCarpetaActual2 + File.separator + poligono.nombreArchivoPDF);
        file = new File(Objects.requireNonNull(uri.getPath()));
        file.getParentFile().mkdirs();

        Document document = new Document(PageSize.A4);
        autor = String.valueOf(R.string.app_name) + String.valueOf(R.string.resto_autor);

        encabezado = poligono.getProyecto() + " " + poligono.getFecha();
        try {
            writer = PdfWriter.getInstance(document, new FileOutputStream(file));
            Cabezera event = new Cabezera();
            writer.setPageEvent(event);
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(context,"Error PDF ----  "+ e +"  -----, working on this.",Toast.LENGTH_LONG).show();

        }
        document.open();
        float anchoPaginaA4 = PageSize.A4.getRight() - 30;
        paint.setColor(Color.BLACK);
        PdfPTable tablaPro = generarTablaDatosProfesional(anchoPaginaA4);
        PdfPTable tablaDatos = generarTablaDatos(anchoPaginaA4);
        PdfPTable tabla6 = generarTabla6(anchoPaginaA4);
        PdfPTable tabla11 = generarTabla1(anchoPaginaA4);
        PdfPTable tabla2 = generarTabla2(anchoPaginaA4);
        PdfPTable tabla5 = generarTabla5(anchoPaginaA4);
        Paragraph tituloReporte = new Paragraph(String.valueOf(R.string.encabezado));
        tituloReporte.setAlignment(Element.ALIGN_CENTER);
        Paragraph ultimaLinea = new Paragraph(String.valueOf(R.string.ultima_linea));
        ultimaLinea.setAlignment(Element.ALIGN_CENTER);

        try {
            document.add(tituloReporte);
            document.add(ESPACIO);
            document.add(tablaPro);
            document.add(ESPACIO);
            document.add(tablaDatos);
            document.add(ESPACIO);
            //tabla GPS
            document.add(tabla6);
            document.add(ESPACIO);
            document.add(tabla11);
            document.add(ESPACIO);
            document.add(tabla2);
            document.add(ESPACIO);

            PdfPTable tablaError = generarTablaError(anchoPaginaA4);
            PdfPTable tabla3 = generarTabla3(anchoPaginaA4);
            PdfPTable tabla4 = generarTabla4(anchoPaginaA4);
            document.add(tablaError);
            document.add(ESPACIO);
            document.add(tabla3);
            document.add(ESPACIO);
            document.add(tabla4);
            document.add(ESPACIO);

            document.add(tabla5);
            document.add(ESPACIO);
            document.add(ultimaLinea);
        } catch (DocumentException e) {
            Toast.makeText(context,"Error PDF ----  "+ e +"  -----, working on this.",Toast.LENGTH_LONG).show();
        }
        document.close();
    }

    public static PdfPTable tabla1;


    private  static PdfPTable generarTablaDatosProfesional(float anchoPag) {
        tabla1 = new PdfPTable(4);
        float anchoTabla = anchoPag - 40;
        tabla1.setTotalWidth(anchoTabla);
        tabla1.setLockedWidth(true);
        PdfPCell cell;
        String resposable = String.valueOf(R.string.responsable);
        String resp = poligono.responsable;
        String tituloTabla = String.valueOf(R.string.tabla_pro);

        cell = new PdfPCell(new Phrase(tituloTabla));
        cell.setColspan(4);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla1.addCell(cell);

        cell = new PdfPCell(new Phrase(resposable));
        cell.setColspan(1);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla1.addCell(cell);

        cell = new PdfPCell(new Phrase(String.valueOf(R.string.firma_sello)));
        cell.setColspan(3);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla1.addCell(cell);

        cell = new PdfPCell(new Phrase(resp));
        cell.setColspan(1);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_CENTER);
        tabla1.addCell(cell);

        cell = new PdfPCell(new Phrase(" "));
        cell.setFixedHeight(50);
        cell.setColspan(3);
        tabla1.addCell(cell);
        return tabla1;
    }

    private  static PdfPTable generarTablaDatos(float anchoPag) {
        tabla1 = new PdfPTable(5);
        float anchoTabla = anchoPag - 40;
        tabla1.setTotalWidth(anchoTabla);
        tabla1.setLockedWidth(true);
        PdfPCell cell;
        String tituloTabla = String.valueOf(R.string.tabla_datos);
        cell = new PdfPCell(new Phrase(tituloTabla));
        cell.setColspan(5);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla1.addCell(cell);

        cell = new PdfPCell(new Phrase(String.valueOf(R.string.fecha)));
        cell.setColspan(1);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla1.addCell(cell);
        cell = new PdfPCell(new Phrase(poligono.getFecha()));
        cell.setColspan(4);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla1.addCell(cell);

        cell = new PdfPCell(new Phrase( String.valueOf(R.string.proyecto)));
        cell.setColspan(1);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla1.addCell(cell);
        cell = new PdfPCell(new Phrase(poligono.getProyecto()));
        cell.setColspan(4);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla1.addCell(cell);

        cell = new PdfPCell(new Phrase(String.valueOf(R.string.cliente)));
        cell.setColspan(1);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla1.addCell(cell);
        cell = new PdfPCell(new Phrase(poligono.cliente));
        cell.setColspan(4);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla1.addCell(cell);

        cell = new PdfPCell(new Phrase(String.valueOf(R.string.ubicacion)));
        cell.setColspan(1);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla1.addCell(cell);
        cell = new PdfPCell(new Phrase(poligono.ubicacion));
        cell.setColspan(4);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla1.addCell(cell);

        cell = new PdfPCell(new Phrase(String.valueOf(R.string.responsable)));
        cell.setColspan(1);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla1.addCell(cell);
        cell = new PdfPCell(new Phrase(poligono.responsable));
        cell.setColspan(4);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla1.addCell(cell);

        return tabla1;
    }

    private  static  PdfPTable generarTabla1(float anchoPag) {
        tabla1 = new PdfPTable(11);
        float anchoTabla = anchoPag - 40;
        tabla1.setTotalWidth(anchoTabla);
        tabla1.setLockedWidth(true);
        tabla1.setHeaderRows(2);
        PdfPCell cell;
        String tituloTabla = String.valueOf(R.string.tabla1);
        cell = new PdfPCell(new Phrase(tituloTabla));
        cell.setColspan(11);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla1.addCell(cell);

        for (int F = 0; F < (poligono.estaciones.size() + 1); F++) {
            if (F == 0) {
                cell = new PdfPCell(new Phrase(String.valueOf(R.string.est)));
                cell.setColspan(1);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabla1.addCell(cell);

                cell = new PdfPCell(new Phrase(String.valueOf(R.string.PO)));
                cell.setColspan(1);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabla1.addCell(cell);

                cell = new PdfPCell(new Phrase(
                        String.valueOf(R.string.dist) ));
                cell.setColspan(2);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabla1.addCell(cell);

                cell = new PdfPCell(new Phrase(String.valueOf( R.string.gra) ));
                cell.setColspan(1);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabla1.addCell(cell);

                cell = new PdfPCell(new Phrase(String.valueOf(R.string.min)));
                cell.setColspan(1);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabla1.addCell(cell);

                cell = new PdfPCell(new Phrase(String.valueOf(R.string.seg)));
                cell.setColspan(1);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabla1.addCell(cell);

                cell = new PdfPCell(new Phrase("Yp"));
                cell.setColspan(2);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabla1.addCell(cell);

                cell = new PdfPCell(new Phrase("Xp"));
                cell.setColspan(2);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabla1.addCell(cell);

            } else {
                String idEstTempAntes;
                if (F == 1) {
                    idEstTempAntes = "-";
                } else {
                    Estacion estacionTempAntes = poligono.estaciones.elementAt(F - 2);
                    idEstTempAntes = estacionTempAntes.idEst;
                }
                Estacion estacionTemp = poligono.estaciones.elementAt(F - 1);
                DecimalFormat df = new DecimalFormat("###,###.##");

                cell = new PdfPCell(new Phrase(idEstTempAntes));
                cell.setColspan(1);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabla1.addCell(cell);

                cell = new PdfPCell(new Phrase(estacionTemp.idEst));
                cell.setColspan(1);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabla1.addCell(cell);

                String cadTemp = df.format(estacionTemp.dist);
                cell = new PdfPCell(new Phrase(cadTemp));
                cell.setColspan(2);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabla1.addCell(cell);

                cadTemp = df.format(estacionTemp.grado);
                cell = new PdfPCell(new Phrase(cadTemp));
                cell.setColspan(1);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabla1.addCell(cell);

                cadTemp = df.format(estacionTemp.minuto);
                cell = new PdfPCell(new Phrase(cadTemp));
                cell.setColspan(1);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabla1.addCell(cell);

                cadTemp = df.format(estacionTemp.segundo);
                cell = new PdfPCell(new Phrase(cadTemp));
                cell.setColspan(1);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabla1.addCell(cell);

                cadTemp = df.format(estacionTemp.yp);
                cell = new PdfPCell(new Phrase(cadTemp));
                cell.setColspan(2);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabla1.addCell(cell);

                cadTemp = df.format(estacionTemp.xp);
                cell = new PdfPCell(new Phrase(cadTemp));
                cell.setColspan(2);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabla1.addCell(cell);
            }
        }
        return tabla1;
    }

    private  static PdfPTable generarTabla2(float anchoPag) {
        tabla1 = new PdfPTable(10);
        float anchoTabla = anchoPag - 40;
        tabla1.setTotalWidth(anchoTabla);
        tabla1.setLockedWidth(true);
        tabla1.setHeaderRows(2);
        PdfPCell cell;
        String tituloTabla = String.valueOf(R.string.tabla2);
        cell = new PdfPCell(new Phrase(tituloTabla));
        cell.setColspan(10);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla1.addCell(cell);

        for (int F = 0; F < (poligono.estaciones.size() + 1); F++) {
            if (F == 0) {

                cell = new PdfPCell(new Phrase(String.valueOf(R.string.est)));
                cell.setColspan(1);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabla1.addCell(cell);

                cell = new PdfPCell(new Phrase(String.valueOf(R.string.PO)));
                cell.setColspan(1);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabla1.addCell(cell);

                cell = new PdfPCell(new Phrase(String.valueOf(R.string.n)));
                cell.setColspan(2);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabla1.addCell(cell);

                cell = new PdfPCell(new Phrase(String.valueOf(R.string.s)));
                cell.setColspan(2);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabla1.addCell(cell);

                cell = new PdfPCell(new Phrase(String.valueOf(R.string.e)));
                cell.setColspan(2);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabla1.addCell(cell);

                cell = new PdfPCell(new Phrase(String.valueOf(R.string.o)));
                cell.setColspan(2);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabla1.addCell(cell);


            } else {
                String idEstTempAntes;
                if (F == 1) {
                    idEstTempAntes = "-";
                } else {
                    Estacion estacionTempAntes = poligono.estaciones.elementAt(F - 2);
                    idEstTempAntes = estacionTempAntes.idEst;
                }
                Estacion estacionTemp = poligono.estaciones.elementAt(F - 1);
                DecimalFormat df = new DecimalFormat("###,###.##");
                String cadTemp;

                cell = new PdfPCell(new Phrase(idEstTempAntes));
                cell.setColspan(1);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabla1.addCell(cell);

                cell = new PdfPCell(new Phrase(estacionTemp.idEst));
                cell.setColspan(1);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabla1.addCell(cell);

                cadTemp = df.format(estacionTemp.Norte);
                cell = new PdfPCell(new Phrase(cadTemp));
                cell.setColspan(2);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabla1.addCell(cell);

                cadTemp = df.format(estacionTemp.Sur);
                cell = new PdfPCell(new Phrase(cadTemp));
                cell.setColspan(2);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabla1.addCell(cell);

                cadTemp = df.format(estacionTemp.Este);
                cell = new PdfPCell(new Phrase(cadTemp));
                cell.setColspan(2);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabla1.addCell(cell);

                cadTemp = df.format(estacionTemp.Oeste);
                cell = new PdfPCell(new Phrase(cadTemp));
                cell.setColspan(2);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabla1.addCell(cell);
            }
        }
        return tabla1;
    }

    private static  PdfPTable generarTablaError(float anchoPag) {
        tabla1 = new PdfPTable(12);
        float anchoTabla = anchoPag - 40;
        tabla1.setTotalWidth(anchoTabla);
        tabla1.setLockedWidth(true);
        tabla1.setHeaderRows(1);
        PdfPCell cell;
        String tituloTabla = String.valueOf(R.string.tabla_error);

        cell = new PdfPCell(new Phrase(tituloTabla));
        cell.setColspan(12);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla1.addCell(cell);
        DecimalFormat df = new DecimalFormat("#,###,###.####");

        cell = new PdfPCell(new Phrase(String.valueOf(R.string.suma_n)));
        cell.setColspan(3);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla1.addCell(cell);
        cell = new PdfPCell(new Phrase(String.valueOf(R.string.suma_s)));
        cell.setColspan(3);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla1.addCell(cell);
        cell = new PdfPCell(new Phrase(String.valueOf(R.string.suma_e)));
        cell.setColspan(3);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla1.addCell(cell);
        cell = new PdfPCell(new Phrase(String.valueOf(R.string.suma_o)));
        cell.setColspan(3);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla1.addCell(cell);

        String cadTemp = df.format(poligono.getSumaNortes());
        cell = new PdfPCell(new Phrase(cadTemp));
        cell.setColspan(3);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla1.addCell(cell);
        cadTemp = df.format(poligono.getSumaSures());
        cell = new PdfPCell(new Phrase(cadTemp));
        cell.setColspan(3);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla1.addCell(cell);
        cadTemp = df.format(poligono.getSumaEstes());
        cell = new PdfPCell(new Phrase(cadTemp));
        cell.setColspan(3);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla1.addCell(cell);
        cadTemp = df.format(poligono.getSumaOestes());
        cell = new PdfPCell(new Phrase(cadTemp));
        cell.setColspan(3);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla1.addCell(cell);

        cell = new PdfPCell(new Phrase(String.valueOf(R.string.resta_y)));
        cell.setColspan(4);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla1.addCell(cell);
        cell = new PdfPCell(new Phrase(String.valueOf(R.string.resta_x)));
        cell.setColspan(4);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla1.addCell(cell);
        cell = new PdfPCell(new Phrase(String.valueOf(R.string.perimetro)));
        cell.setColspan(4);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla1.addCell(cell);

        cadTemp = df.format(poligono.getDeltaY());
        cell = new PdfPCell(new Phrase(cadTemp));
        cell.setColspan(4);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla1.addCell(cell);
        cadTemp = df.format(poligono.getDeltaX());
        cell = new PdfPCell(new Phrase(cadTemp));
        cell.setColspan(4);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla1.addCell(cell);
        cadTemp = df.format(poligono.getSumaDist());
        cell = new PdfPCell(new Phrase(cadTemp));
        cell.setColspan(4);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla1.addCell(cell);

        cell = new PdfPCell(new Phrase(String.valueOf(R.string.error)));
        cell.setColspan(3);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla1.addCell(cell);
        cell = new PdfPCell(new Phrase(String.valueOf(R.string.error_un)));
        cell.setColspan(3);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla1.addCell(cell);
        cell = new PdfPCell(new Phrase(String.valueOf(R.string.cy)));
        cell.setColspan(3);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla1.addCell(cell);
        cell = new PdfPCell(new Phrase(String.valueOf(R.string.cx)));
        cell.setColspan(3);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla1.addCell(cell);

        cadTemp = df.format(poligono.getErrC());
        cell = new PdfPCell(new Phrase(cadTemp));
        cell.setColspan(3);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla1.addCell(cell);
        cadTemp = df.format(poligono.getErrCierreUn());
        cell = new PdfPCell(new Phrase(cadTemp));
        cell.setColspan(3);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla1.addCell(cell);
        cadTemp = df.format(poligono.getCy());
        cell = new PdfPCell(new Phrase(cadTemp));
        cell.setColspan(3);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla1.addCell(cell);
        cadTemp = df.format(poligono.getCx());
        cell = new PdfPCell(new Phrase(cadTemp));
        cell.setColspan(3);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla1.addCell(cell);

        cell = new PdfPCell(new Phrase(String.valueOf(R.string.error_permisible)));
        cell.setColspan(4);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla1.addCell(cell);
        cell = new PdfPCell(new Phrase(poligono.getErrorCumple()));
        cell.setColspan(8);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla1.addCell(cell);

        return tabla1;
    }

    private static  PdfPTable generarTabla3(float anchoPag) {
        tabla1 = new PdfPTable(10);
        float anchoTabla = anchoPag - 40;
        tabla1.setTotalWidth(anchoTabla);
        tabla1.setLockedWidth(true);
        tabla1.setHeaderRows(2);
        PdfPCell cell;
        String tituloTabla = String.valueOf(R.string.tabla3);
        cell = new PdfPCell(new Phrase(tituloTabla));
        cell.setColspan(10);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla1.addCell(cell);

        for (int F = 0; F < (poligono.estaciones.size() + 1); F++) {
            if (F == 0) {

                cell = new PdfPCell(new Phrase(String.valueOf(R.string.est).toString()));
                cell.setColspan(1);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabla1.addCell(cell);

                cell = new PdfPCell(new Phrase(String.valueOf(R.string.PO).toString()));
                cell.setColspan(1);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabla1.addCell(cell);

                cell = new PdfPCell(new Phrase(String.valueOf(R.string.cn).toString()));
                cell.setColspan(2);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabla1.addCell(cell);

                cell = new PdfPCell(new Phrase(String.valueOf(R.string.cs).toString()));
                cell.setColspan(2);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabla1.addCell(cell);

                cell = new PdfPCell(new Phrase(String.valueOf(R.string.ce).toString()));
                cell.setColspan(2);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabla1.addCell(cell);

                cell = new PdfPCell(new Phrase(String.valueOf(R.string.co).toString()));
                cell.setColspan(2);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabla1.addCell(cell);


            } else {
                String idEstTempAntes;
                if (F == 1) {
                    idEstTempAntes = "-";
                } else {
                    Estacion estacionTempAntes = poligono.estaciones.elementAt(F - 2);
                    idEstTempAntes = estacionTempAntes.idEst;
                }
                Estacion estacionTemp = poligono.estaciones.elementAt(F - 1);
                DecimalFormat df = new DecimalFormat("###,###.####");
                String cadTemp;

                cell = new PdfPCell(new Phrase(idEstTempAntes));
                cell.setColspan(1);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabla1.addCell(cell);

                cell = new PdfPCell(new Phrase(estacionTemp.idEst));
                cell.setColspan(1);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabla1.addCell(cell);


                cadTemp = df.format(poligono.corrN.elementAt(F - 1));
                cell = new PdfPCell(new Phrase(cadTemp));
                cell.setColspan(2);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabla1.addCell(cell);

                cadTemp = df.format(poligono.corrS.elementAt(F - 1));
                cell = new PdfPCell(new Phrase(cadTemp));
                cell.setColspan(2);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabla1.addCell(cell);

                cadTemp = df.format(poligono.corrE.elementAt(F - 1));
                cell = new PdfPCell(new Phrase(cadTemp));
                cell.setColspan(2);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabla1.addCell(cell);

                cadTemp = df.format(poligono.corrO.elementAt(F - 1));
                cell = new PdfPCell(new Phrase(cadTemp));
                cell.setColspan(2);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabla1.addCell(cell);
            }
        }
        return tabla1;
    }

    private  static PdfPTable generarTabla4(float anchoPag) {
        tabla1 = new PdfPTable(10);
        float anchoTabla = anchoPag - 40;
        tabla1.setTotalWidth(anchoTabla);
        tabla1.setLockedWidth(true);
        tabla1.setHeaderRows(2);
        PdfPCell cell;
        String tituloTabla = String.valueOf(R.string.tabla4).toString();
        cell = new PdfPCell(new Phrase(tituloTabla));
        cell.setColspan(10);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla1.addCell(cell);

        for (int F = 0; F < (poligono.estaciones.size() + 1); F++) {
            if (F == 0) {

                cell = new PdfPCell(new Phrase(String.valueOf(R.string.est).toString()));
                cell.setColspan(1);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabla1.addCell(cell);

                cell = new PdfPCell(new Phrase(String.valueOf(R.string.PO).toString()));
                cell.setColspan(1);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabla1.addCell(cell);

                cell = new PdfPCell(new Phrase(String.valueOf(R.string.nc).toString()));
                cell.setColspan(2);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabla1.addCell(cell);

                cell = new PdfPCell(new Phrase(String.valueOf(R.string.sc).toString()));
                cell.setColspan(2);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabla1.addCell(cell);

                cell = new PdfPCell(new Phrase(String.valueOf(R.string.ec).toString()));
                cell.setColspan(2);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabla1.addCell(cell);

                cell = new PdfPCell(new Phrase(String.valueOf(R.string.oc).toString()));
                cell.setColspan(2);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabla1.addCell(cell);


            } else {
                String idEstTempAntes;
                if (F == 1) {
                    idEstTempAntes = "-";
                } else {
                    Estacion estacionTempAntes = poligono.estaciones.elementAt(F - 2);
                    idEstTempAntes = estacionTempAntes.idEst;
                }
                Estacion estacionTemp = poligono.estaciones.elementAt(F - 1);
                DecimalFormat df = new DecimalFormat("###,###.###");
                String cadTemp;

                cell = new PdfPCell(new Phrase(idEstTempAntes));
                cell.setColspan(1);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabla1.addCell(cell);

                cell = new PdfPCell(new Phrase(estacionTemp.idEst));
                cell.setColspan(1);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabla1.addCell(cell);

                cadTemp = df.format(estacionTemp.nc);
                cell = new PdfPCell(new Phrase(cadTemp));
                cell.setColspan(2);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabla1.addCell(cell);

                cadTemp = df.format(estacionTemp.sc);
                cell = new PdfPCell(new Phrase(cadTemp));
                cell.setColspan(2);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabla1.addCell(cell);

                cadTemp = df.format(estacionTemp.ec);
                cell = new PdfPCell(new Phrase(cadTemp));
                cell.setColspan(2);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabla1.addCell(cell);

                cadTemp = df.format(estacionTemp.oc);
                cell = new PdfPCell(new Phrase(cadTemp));
                cell.setColspan(2);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabla1.addCell(cell);
            }
        }
        return tabla1;
    }

    static Font fuente = new Font();


    private static  PdfPTable generarTabla5(float anchoPag) {
        tabla1 = new PdfPTable(14);
        float anchoTabla = anchoPag - 40;
        tabla1.setTotalWidth(anchoTabla);
        tabla1.setLockedWidth(true);
        tabla1.setHeaderRows(2);
        PdfPCell cell;
        String tituloTabla = String.valueOf(R.string.tabla5).toString();
        cell = new PdfPCell(new Phrase(tituloTabla));
        cell.setColspan(14);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_CENTER);
        tabla1.addCell(cell);
        DecimalFormat df = new DecimalFormat("###,###.###");
        String cadTemp;
        for (int F = 0; F < (poligono.estaciones.size() + 1); F++) {
            if (F == 0) {

                cell = new PdfPCell(new Phrase(String.valueOf(R.string.est).toString()));
                cell.setColspan(1);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabla1.addCell(cell);

                cell = new PdfPCell(new Phrase(String.valueOf(R.string.PO).toString()));
                cell.setColspan(1);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabla1.addCell(cell);

                cell = new PdfPCell(new Phrase(String.valueOf(R.string.ypc).toString()));
                cell.setColspan(2);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabla1.addCell(cell);

                cell = new PdfPCell(new Phrase(String.valueOf(R.string.xpc).toString()));
                cell.setColspan(2);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabla1.addCell(cell);

                cell = new PdfPCell(new Phrase(String.valueOf(R.string.yt).toString()));
                cell.setColspan(2);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabla1.addCell(cell);

                cell = new PdfPCell(new Phrase(String.valueOf(R.string.xt).toString()));
                cell.setColspan(2);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabla1.addCell(cell);

                cell = new PdfPCell(new Phrase(String.valueOf(R.string.yx).toString()));
                cell.setColspan(2);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabla1.addCell(cell);

                cell = new PdfPCell(new Phrase(String.valueOf(R.string.xy).toString()));
                cell.setColspan(2);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabla1.addCell(cell);


            } else {
                String idEstTempAntes;
                if (F == 1) {
                    idEstTempAntes = "-";
                } else {
                    Estacion estacionTempAntes = poligono.estaciones.elementAt(F - 2);
                    idEstTempAntes = estacionTempAntes.idEst;
                }
                Estacion estacionTemp = poligono.estaciones.elementAt(F - 1);
                fuente.setSize(9);
                cell = new PdfPCell(new Phrase(idEstTempAntes, fuente));
                cell.setColspan(1);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabla1.addCell(cell);

                cell = new PdfPCell(new Phrase(estacionTemp.idEst, fuente));
                cell.setColspan(1);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabla1.addCell(cell);

                cadTemp = df.format(estacionTemp.ypc);
                cell = new PdfPCell(new Phrase(cadTemp));
                cell.setColspan(2);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabla1.addCell(cell);

                cadTemp = df.format(estacionTemp.xpc);
                cell = new PdfPCell(new Phrase(cadTemp));
                cell.setColspan(2);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabla1.addCell(cell);

                fuente.setSize(8);
                cadTemp = df.format(estacionTemp.yt);
                cell = new PdfPCell(new Phrase(cadTemp, fuente));
                cell.setColspan(2);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabla1.addCell(cell);

                cadTemp = df.format(estacionTemp.xt);
                cell = new PdfPCell(new Phrase(cadTemp, fuente));
                cell.setColspan(2);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabla1.addCell(cell);

                fuente.setSize(5);
                cadTemp = df.format(estacionTemp.YX);
                cell = new PdfPCell(new Phrase(cadTemp,fuente));
                cell.setColspan(2);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabla1.addCell(cell);

                cadTemp = df.format(estacionTemp.XY);
                cell = new PdfPCell(new Phrase(cadTemp,fuente));
                cell.setColspan(2);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabla1.addCell(cell);
            }
        }

        cell = new PdfPCell(new Phrase(String.valueOf(R.string.totales).toString()));
        cell.setColspan(10);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla1.addCell(cell);

        fuente.setSize(7);
        cadTemp = df.format(poligono.getSumaYX());
        cell = new PdfPCell(new Phrase(cadTemp,fuente));
        cell.setColspan(2);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla1.addCell(cell);

        cadTemp = df.format(poligono.getSumaXY());
        cell = new PdfPCell(new Phrase(cadTemp,fuente));
        cell.setColspan(2);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla1.addCell(cell);

        cell = new PdfPCell(new Phrase(String.valueOf(R.string.area).toString()));
        cell.setColspan(10);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla1.addCell(cell);

        DecimalFormat df1 = new DecimalFormat("###,###,###,###,###.###");
        fuente.setSize(10);

        cadTemp = df1.format(poligono.getArea());
        cell = new PdfPCell(new Phrase(cadTemp,fuente));
        cell.setColspan(4);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla1.addCell(cell);

        /////////////
        cell = new PdfPCell(new Phrase(String.valueOf(R.string.perimetro).toString()));
        cell.setColspan(10);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla1.addCell(cell);

        df1 = new DecimalFormat("###,###,###,###,###.###");
        fuente.setSize(10);

        cadTemp = df1.format(poligono.getPerimetro());
        cell = new PdfPCell(new Phrase(cadTemp,fuente));
        cell.setColspan(4);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla1.addCell(cell);

        return tabla1;


    }

    private  static PdfPTable generarTabla6(float anchoPag) {
        tabla1 = new PdfPTable(12);
        float anchoTabla = anchoPag - 40;
        tabla1.setTotalWidth(anchoTabla);
        tabla1.setLockedWidth(true);
        tabla1.setHeaderRows(2);
        PdfPCell cell;
        String tituloTabla = String.valueOf(R.string.tabla6).toString();
        cell = new PdfPCell(new Phrase(tituloTabla));
        cell.setColspan(12);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_CENTER);
        tabla1.addCell(cell);

        for (int F = 0; F < (poligono.estaciones.size()+1); F++) {
            if (F == 0) {

                cell = new PdfPCell(new Phrase(String.valueOf(R.string.est).toString()));
                cell.setColspan(1);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabla1.addCell(cell);

                cell = new PdfPCell(new Phrase("#"));
                cell.setColspan(2);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabla1.addCell(cell);

                cell = new PdfPCell(new Phrase(String.valueOf(R.string.latitud).toString()));
                cell.setColspan(2);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabla1.addCell(cell);

                cell = new PdfPCell(new Phrase(String.valueOf(R.string.longitud).toString()));
                cell.setColspan(2);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabla1.addCell(cell);

                cell = new PdfPCell(new Phrase(String.valueOf(R.string.elevacion).toString()));
                cell.setColspan(2);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabla1.addCell(cell);

                cell = new PdfPCell(new Phrase(String.valueOf(R.string.utm).toString()));
                cell.setColspan(3);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabla1.addCell(cell);

            } else {
                Estacion estacionTemp = poligono.estaciones.elementAt(F - 1);
                DecimalFormat df = new DecimalFormat("#,###.#######");
                String cadTemp;

                cell = new PdfPCell(new Phrase(estacionTemp.idEst));
                cell.setColspan(1);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabla1.addCell(cell);

                cadTemp = estacionTemp.getObservaciones();
                cell = new PdfPCell(new Phrase(cadTemp));
                cell.setColspan(2);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabla1.addCell(cell);

                cadTemp = df.format(estacionTemp.getLat());
                cell = new PdfPCell(new Phrase(cadTemp));
                cell.setColspan(2);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabla1.addCell(cell);

                cadTemp = df.format(estacionTemp.getLon());
                cell = new PdfPCell(new Phrase(cadTemp));
                cell.setColspan(2);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabla1.addCell(cell);

                cadTemp = df.format(estacionTemp.getAlt());
                cell = new PdfPCell(new Phrase(cadTemp));
                cell.setColspan(2);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabla1.addCell(cell);

                cell = new PdfPCell(new Phrase(estacionTemp.UTM));
                cell.setColspan(3);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabla1.addCell(cell);
            }
        }

        return tabla1;
    }
}
