package com.desarrollojlcp.gps_topography.map

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.desarrollojlcp.gps_topography.db.ConexionSQLiteHelper
import com.desarrollojlcp.gps_topography.db.Utilidades
import com.desarrollojlcp.gps_topography.ui.theme.GPSTopographyTheme
import com.google.android.libraries.places.api.Places
import java.io.IOException
import java.util.Properties


class MainActivity : ComponentActivity() {
    private val viewModel: MapViewModel by viewModels()

    var helper: ConexionSQLiteHelper = ConexionSQLiteHelper(this, Utilidades.NOMBRE_BD, null, Utilidades.VERSION_BD)


    companion object {
        const val PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1234
        const val PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 12345

    }
    private val locationPermissionGranted = mutableStateOf(false)

    private fun getProperty(key: String): String? {

        // Obtener el contexto de la actividad
        val context: Context = this

        // Obtener el archivo local.properties
        val properties = Properties()
        try {
            properties.load(context.assets.open("local.properties"))
        } catch (e: IOException) {
            Log.e("MainActivity", "Error al leer el archivo local.properties", e)
            return null
        }

        // Obtener el valor de la propiedad
        return properties.getProperty(key)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getProperty("MAPS_API_KEY")?.let { Places.initialize(applicationContext, it) }

        helper = ConexionSQLiteHelper(this, Utilidades.NOMBRE_BD, null, Utilidades.VERSION_BD)


        installSplashScreen()
        setContent {
            GPSTopographyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    getLocationPermission()
                    if (locationPermissionGranted.value) {
                        ColumnaPrincipal()
                    }else{
                        TextoPermisos()
                    }
                }
            }
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    locationPermissionGranted.value = true
                }
            }
            PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permiso para escribir en almacenamiento concedido.
                }
            }
        }
    }

    private fun getLocationPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            locationPermissionGranted.value = true
            // Permiso para escribir en almacenamiento concedido.
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ),
                PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION
            )
        }
    }


}

@Composable
fun TextoPermisos(){
    Text(text="Sin Permisos")
}















