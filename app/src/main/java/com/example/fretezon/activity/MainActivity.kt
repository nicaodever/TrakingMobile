package com.example.fretezon.activity

import CreateUser
import FirstPage
import Home
import RequestRace
import TemplateFirstPage
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fretezon.ui.theme.FretezonTheme
import com.mapbox.android.core.permissions.PermissionsManager
import com.mapbox.android.core.permissions.PermissionsListener
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fretezon.R
import com.example.fretezon.models.Post
import com.example.fretezon.utils.RetrofitInstance
import com.example.fretezon.views.CardFrete
import com.example.fretezon.views.FreteListScreen
import com.example.fretezon.views.ListRaces
import com.example.fretezon.views.MainTemplate
import com.example.fretezon.views.PostItem
import com.example.fretezon.views.PostListScreen

import com.mapbox.common.MapboxOptions
import com.mapbox.search.autocomplete.PlaceAutocomplete
import com.mapbox.search.autocomplete.PlaceAutocompleteSuggestion
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okio.IOException
import retrofit2.HttpException
import retrofit2.http.POST


class MainActivity : ComponentActivity(), PermissionsListener  {
    lateinit var permissionsManager: PermissionsManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
        onPermissionResult(true)
        }
    }
    override fun onExplanationNeeded(permissionsToExplain: List<String>) {
        Toast.makeText(this, "Precisamos da sua localização para exibir o mapa.", Toast.LENGTH_LONG)
            .show()
    }

    override fun onPermissionResult(granted: Boolean) {
        if (granted) {
            setContent {
//                val navController = rememberNavController()
//                NavHost(navController = navController, startDestination = "home") {
//                    composable(route = "listRaces") { ListRaces(navController) }
//                    composable(route = "home") { Home(navController)  }
//                }
TemplateFirstPage()
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
