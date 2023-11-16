package com.desarrollojlcp.gps_topography.map

import android.app.Application
import android.content.SharedPreferences
import android.os.Environment
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.desarrollojlcp.gps_topography.map.model.AutocompleteResult
import com.desarrollojlcp.gps_topography.map.model.Estacion
import com.desarrollojlcp.gps_topography.map.model.reports.GenerarArchivos
import com.desarrollojlcp.gps_topography.map.model.Poligono
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
import com.google.android.libraries.places.api.net.PlacesClient
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class MapViewModel (application: Application) : AndroidViewModel(application) {
    private val _markers = MutableLiveData<List<MarkerOptions>>()
    val markers: LiveData<List<MarkerOptions>> = _markers

    private val _area = MutableLiveData<Double>()
    val area: LiveData<Double> = _area

    private val _perimetro = MutableLiveData<Double>()
    val perimetro: LiveData<Double> = _perimetro

    private var fechaNombreReporte: String? = null

    val ITEM_SKU_SUBSCRIBE = "pro_sub1"
    val PREF_FILE = "preferenciaSusGPSTpro"
    val PREF_FILE_USOS = "preferenciaUSOSGPSTpro"

    val SUBSCRIBE_KEY = "subscribe"
    val USOS_KEY = "usando"

    private val valorSuscripcion = false
    private var usos = 0

    private fun getSubscribeValueFromPref(): Boolean {
        return getPreferenceObject().getBoolean(SUBSCRIBE_KEY, false)
    }

    private fun getUsosValueFromPref(): Int {
        return getPreferenceObjectU().getInt(USOS_KEY, 0)
    }

    val applicationContext = application


    protected fun getPreferenceObject(): SharedPreferences {
        return applicationContext.getSharedPreferences(PREF_FILE, 0)
    }

    protected fun getPreferenceObjectU(): SharedPreferences {
        return applicationContext.getSharedPreferences(PREF_FILE_USOS, 0)
    }

    private fun saveSubscribeValueToPref(value: Boolean) {
        getPreferenceEditObject().putBoolean(SUBSCRIBE_KEY, value).commit()
    }


    private fun getPreferenceEditObject(): SharedPreferences.Editor {
        val pref: SharedPreferences = applicationContext.getSharedPreferences(PREF_FILE, 0)
        return pref.edit()
    }

    private fun saveUsosValueToPref(value: Int) {
        getPreferenceEditObjectUsos().putInt(USOS_KEY, value).commit()
    }

    private fun getPreferenceEditObjectUsos(): SharedPreferences.Editor {
        val pref: SharedPreferences =
            applicationContext.getSharedPreferences(PREF_FILE_USOS, 0)
        return pref.edit()
    }


    var poligono: Poligono = Poligono()

    fun calcular(){
        if ((poligono.estaciones.size == 0) || poligono.estaciones.size == 1)  {
            _area.value = 0.0
            _perimetro.value = 0.0
        } else if (poligono.estaciones.size == 2){
            _area.value = 0.0
            poligono.calcular()
            _perimetro.value = poligono.perimetro
        } else{
            poligono.calcular()
            _area.value = poligono.area
            _perimetro.value = poligono.perimetro
        }

    }


    fun onMapClicked(latLng: LatLng) {
        val estacion = Estacion(latLng.latitude, latLng.longitude)
        estacion.idEstacion = (_markers.value?.size ?: 0)
        estacion.idEst = "Marker #" + (_markers.value?.size ?: 0)
        estacion.observaciones = latLng.toString()
        // Agrega un nuevo marcador en el punto tocado por el usuario
        val newMarker = MarkerOptions()
            .position(latLng)
            .title(estacion.idEst)
            .snippet(estacion.observaciones)
        val updatedMarkers = (_markers.value ?: emptyList()) + newMarker
        _markers.value = updatedMarkers
        poligono.ingresarEstacion(estacion)
    }

    fun onMarkerMovido(index: Int, latLng: LatLng) {
        // Actualiza la lista de marcadores con la nueva posición
        val currentMarkers = _markers.value ?: emptyList()
        if (index in 0 until currentMarkers.size) {
            val updatedMarkers = currentMarkers.toMutableList()
            val oldMarker = currentMarkers[index]
            val newMarker = MarkerOptions()
                .position(latLng)
                .title(oldMarker.title)
                .snippet(latLng.toString())
            // Copia otras propiedades necesarias aquí
            updatedMarkers[index] = newMarker
            _markers.value = updatedMarkers

            val estacion = Estacion(latLng.latitude, latLng.longitude)
            estacion.idEstacion = index
            estacion.idEst = oldMarker.title
            estacion.observaciones = latLng.toString()
            poligono.modificarEstacion(index,estacion)

        }
    }

    private val _deviceLatLng = MutableLiveData<LatLng>()
    val deviceLatLng: LiveData<LatLng> = _deviceLatLng

    fun cambiarUbicacion(punto: LatLng) {
        _deviceLatLng.value = punto
    }

    fun borrarUltimoMarcador() {
        val currentMarkers = _markers.value.orEmpty() // Obtiene la lista actual de marcadores o una lista vacía si es nula
        if (currentMarkers.isNotEmpty()) {
            val newMarkers = currentMarkers.dropLast(1) // Elimina el último elemento
            _markers.value = newMarkers // Actualiza la lista de marcadores
            poligono.borrarUltimaEstacion()
            calcular()
        }
    }

    fun setFecha() {
        val fecha1: String = SimpleDateFormat("HH:mm:ss dd/MM/yyyy", Locale.US).format(Date())
        val ano = fecha1.substring(17, 19)
        val mes = fecha1.substring(12, 14)
        val dia = fecha1.substring(9, 11)
        val hor = fecha1.substring(0, 2)
        val min = fecha1.substring(3, 5)
        fechaNombreReporte = ano + mes + dia + hor + min
        poligono.setFecha(fecha1)
        fechaNombreReporte!!.replace(" ", "")
    }

    fun generarArchivos() {
        setFecha()
        val tarjetaSD: String = Environment.getExternalStorageDirectory().toString()
        val nomCarpetaGPST = "GPS_Topography_Projects"
        poligono.rutaCarpetaActual = tarjetaSD + File.separator + nomCarpetaGPST
        poligono.rutaCarpetaActual2 = (poligono.rutaCarpetaActual
                + File.separator + fechaNombreReporte)
        val nombreArchivoCSV: String = fechaNombreReporte + ".txt"
        val nombreArchivoDXF: String = fechaNombreReporte + ".dxf"
        poligono.nombreArchivoImagen = fechaNombreReporte + ".jpg"
        val nombreArchivoPDF: String = fechaNombreReporte + ".pdf"
        fechaNombreReporte?.replace(" ", "")
        poligono.nombreArchivoCSV = nombreArchivoCSV
        poligono.nombreArchivoGuardado = fechaNombreReporte
        poligono.nombreArchivoDXF = nombreArchivoDXF
        poligono.rutaArchivoCSV =
            poligono.rutaCarpetaActual2 + File.separator + poligono.nombreArchivoCSV
        poligono.rutaArchivoDXF =
            poligono.rutaCarpetaActual2 + File.separator + poligono.nombreArchivoDXF


        val carpetaProyectoActual = File(poligono.rutaCarpetaActual)
        if (!carpetaProyectoActual.exists()) {
            carpetaProyectoActual.mkdirs()
        }



        usos = getUsosValueFromPref()
        if (valorSuscripcion) {
            GenerarArchivos.generar1(poligono, applicationContext)


        } else {
            if (usos < 6) {
                GenerarArchivos.generar1(poligono, applicationContext)
                usos = usos + 1
                saveUsosValueToPref(usos)
                val usosRestantes: Int = 5 - getUsosValueFromPref()
                Toast.makeText(
                    applicationContext,
                    "PDF and DXF files generated, your remaining uses of free trial: $usosRestantes",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                GenerarArchivos.generar2(poligono, applicationContext)
                Toast.makeText(
                    applicationContext,
                    "No free trial, only TXT file was generated",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }







}