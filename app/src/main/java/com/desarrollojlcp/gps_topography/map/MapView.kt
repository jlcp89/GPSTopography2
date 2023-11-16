package com.desarrollojlcp.gps_topography.map

import android.Manifest
import android.content.ContentValues.TAG
import android.content.pm.PackageManager
import android.location.Location
import android.util.Log
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.desarrollojlcp.gps_topography.R
import com.desarrollojlcp.gps_topography.map.model.reports.GenerarArchivos
import com.desarrollojlcp.gps_topography.ui.theme.AzulNormal
import com.desarrollojlcp.gps_topography.ui.theme.Blanco
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerInfoWindowContent
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.Polygon
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import java.text.DecimalFormat
import androidx.compose.material.*
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material.icons.outlined.List


class MapView (){


}


@Composable
fun ColumnaPrincipal() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
        verticalArrangement = Arrangement.Top
    ) {
        TextoPantalla()
        Spacer(modifier = Modifier.height(16.dp))
        MiMapaGoogle()
    }

    // Agregar un BottomAppBar con botones para guardar y cargar
    BottomAppBar(
        content = {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                // Botón para guardar
                Icon(imageVector = Icons.Outlined.Create, contentDescription = "Guardar")
                // Botón para cargar
                Icon(imageVector = Icons.Outlined.List, contentDescription = "Cargar")
            }
        }
    )

}


@Composable
fun TextoPantalla(){
    val viewModel: MapViewModel = viewModel()
}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MiMapaGoogle() {
    val madisonSquareGarden = LatLng(40.750246, -73.994848)

    val viewModel: MapViewModel = viewModel()
    val markers by viewModel.markers.observeAsState(initial = emptyList())
    val area    by viewModel.area.observeAsState(initial = 0)
    val perimetro    by viewModel.perimetro.observeAsState(initial = 0)
    val deviceLatLng    by viewModel.deviceLatLng.observeAsState(initial = madisonSquareGarden)

    val context = LocalContext.current
    val fusedLocationProviderClient = remember { LocationServices.getFusedLocationProviderClient(context) }
    var lastKnownLocation by remember {
        mutableStateOf<Location?>(null)
    }

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(deviceLatLng, 18f)
    }

    if (lastKnownLocation == null) {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            val locationResult = fusedLocationProviderClient.lastLocation
            locationResult.addOnCompleteListener(context as MainActivity) { task ->
                if (task.isSuccessful) {
                    lastKnownLocation = task.result
                    if (lastKnownLocation != null) {
                        viewModel.cambiarUbicacion(LatLng(lastKnownLocation!!.latitude, lastKnownLocation!!.longitude))
                        cameraPositionState.position = CameraPosition.fromLatLngZoom(LatLng(lastKnownLocation!!.latitude, lastKnownLocation!!.longitude), 18f)
                    }
                } else {
                    Log.d(TAG, "Current location is null. Using defaults.")
                    Log.e(TAG, "Exception: %s", task.exception)
                }
            }
        }
    }


    val puntos = markers.size
    val areaS = formatNumberWithCommas(area.toDouble())
    val perimetroS = formatNumberWithCommas(perimetro.toDouble())





    Text(text = "Tu $deviceLatLng || Puntos: $puntos || Area: $areaS m2 || Perimetro: $perimetroS m" )
    Spacer(modifier = Modifier.height(6.dp))

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopStart
    ) {

        GoogleMap(
            cameraPositionState = cameraPositionState,
            onMapClick = { location ->
                viewModel.onMapClicked(location)
            },
            properties = MapProperties(mapType = MapType.SATELLITE),
        ){
            MarkerInfoWindowContent(
                state = MarkerState(position = deviceLatLng)
            ) { marker ->
                Text(marker.title ?: "You", color = Color.Red)
            }
            markers.forEach { markerOptions ->
                val markerState = rememberMarkerState(position = markerOptions.position)
                Marker(
                    state = markerState,
                    title = markerOptions.title,
                    snippet = markerOptions.snippet,
                    draggable = true
                )
                LaunchedEffect(key1 = markerState.position) {
                    viewModel.onMarkerMovido(markers.indexOf(markerOptions), markerState.position)
                }
            }

            if (markers.size>0){
                var posiciones: List<LatLng> = listOf()
                for (marker in markers){
                    posiciones = posiciones + (marker.position)
                }

                viewModel.calcular()

                Polygon(
                    points = posiciones,
                    fillColor = AzulNormal, // Relleno del polígono (azul semitransparente)
                    strokeColor = Blanco, // Color del borde del polígono (azul sólido)
                    strokeWidth = 5f, // Ancho del borde en píxeles
                    geodesic = true
                )
            }
        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            SmallFloatingActionButton(
                onClick = {
                    // Código para manejar el clic en el primer botón
                    viewModel.borrarUltimoMarcador()
                },
                modifier = Modifier
                    .padding(16.dp)
                    .size(40.dp),
                contentColor = AzulNormal,
                containerColor = Blanco
            ) {
                Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = "Borrar Punto")
            }

            SmallFloatingActionButton(
                onClick = {
                    // Código para manejar el clic en el segundo botón
                    if (markers.size > 2){
                        viewModel.generarArchivos()
                    } else {
                        Toast.makeText(
                            context,
                            R.string.trepuntos,
                            Toast.LENGTH_SHORT
                        ).show()

                    }

                },
                modifier = Modifier
                    .padding(16.dp)
                    .size(40.dp),
                contentColor = AzulNormal,
                containerColor = Blanco
            ) {
                Icon(imageVector = Icons.Outlined.Done, contentDescription = "Generar Archivos")
            }
        }


    }


}





fun formatNumberWithCommas(number: Double): String {
    val decimalFormat = DecimalFormat("#,###.##")
    return decimalFormat.format(number)
}




