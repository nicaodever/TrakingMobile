package com.example.fretezon

import CreateUser
import FirstPage
import Home
import MainTemplate
import RequestRace
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fretezon.ui.theme.DarkBlue
import com.example.fretezon.ui.theme.FretezonTheme
import com.example.fretezon.ui.theme.Gray
import com.example.fretezon.ui.theme.Orange
import com.mapbox.android.core.permissions.PermissionsManager
import com.mapbox.maps.extension.compose.MapboxMap
import com.mapbox.maps.extension.compose.animation.viewport.rememberMapViewportState
import com.mapbox.android.core.permissions.PermissionsListener
import com.mapbox.maps.extension.compose.MapEffect
import com.mapbox.maps.plugin.PuckBearing
import com.mapbox.maps.plugin.locationcomponent.createDefault2DPuck
import com.mapbox.maps.plugin.locationcomponent.location
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.key.Key.Companion.Home
import androidx.compose.ui.text.font.FontWeight
import com.example.fretezon.ui.theme.TextGray


class MainActivity : ComponentActivity(), PermissionsListener {
    lateinit var permissionsManager: PermissionsManager





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (PermissionsManager.areLocationPermissionsGranted(this)) {
            setContent {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "home'") {
                    composable(route = "home") { Home {navController} }
                    composable(route = "register") {CreateUser {navController}}
                    composable(route = "image") {FirstPage{navController}}
                    composable(route = "requestRace") { RequestRace{navController} }
                }
            }
        } else {
            permissionsManager = PermissionsManager(this)
            permissionsManager.requestLocationPermissions(this)

        }
    }
    override fun onExplanationNeeded(permissionsToExplain: List<String>) {
        Toast.makeText(this, "Precisamos da sua localização para exibir o mapa.", Toast.LENGTH_LONG)
            .show()
    }

    override fun onPermissionResult(granted: Boolean) {
        if (granted) {
            setContent {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "FirstPage") {
                    composable(route = "FirstPage") {}
                    composable(route = "saveTask") { }
                }
            }
        } else {
            Toast.makeText(this, "Permissão negada. O mapa não será exibido.", Toast.LENGTH_LONG)
                .show()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        permissionsManager.onRequestPermissionsResult(
            requestCode,
            permissions as Array<String>, grantResults
        )

    }



}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FretezonTheme {

    }

}
