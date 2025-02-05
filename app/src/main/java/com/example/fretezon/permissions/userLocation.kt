import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


import com.example.fretezon.ui.theme.DarkBlue
import com.example.fretezon.ui.theme.Orange
import com.mapbox.android.core.permissions.PermissionsListener
import com.mapbox.android.core.permissions.PermissionsManager

class PermissionActivity: ComponentActivity(), PermissionsListener {

    lateinit var permissionsManager: PermissionsManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (PermissionsManager.areLocationPermissionsGranted(this)) {
            setContent {
                Text(text = "Teste")
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
                ScaffoldExample()
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
@Composable
fun ScaffoldExample() {
    var presses by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            TextButton(
                onClick = {},
                modifier = Modifier.padding(horizontal = 15.dp, vertical = 10.dp)
            ) {
                Icon(
                    Icons.Default.Menu, contentDescription = "Menu-burguer",
                    tint = DarkBlue

                )
            }
        },
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Orange),
                horizontalArrangement = Arrangement.SpaceAround,
            ) {
                TextButton(onClick = {}) {
                    Icon(
                        Icons.Default.Home, contentDescription = "Home",
                        tint = Color.White
                    )
                }
                TextButton(onClick = {}, modifier = Modifier) {
                    Icon(
                        Icons.Default.LocationOn, contentDescription = "Home",
                        tint = Color.White
                    )
                }
            }
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
        ) {
            Input()
//            mapView()
        }
    }
}