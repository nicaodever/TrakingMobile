import android.annotation.SuppressLint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.fretezon.ui.theme.FretezonTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.example.fretezon.ui.theme.DarkBlue
import com.example.fretezon.ui.theme.Gray
import com.example.fretezon.ui.theme.Orange
import com.example.fretezon.ui.theme.TextGray
import com.mapbox.maps.MapView
import com.mapbox.maps.extension.compose.MapEffect
import com.mapbox.maps.extension.compose.MapboxMap
import com.mapbox.maps.extension.compose.animation.viewport.rememberMapViewportState
import com.mapbox.maps.plugin.PuckBearing
import com.mapbox.maps.plugin.locationcomponent.createDefault2DPuck
import com.mapbox.maps.plugin.locationcomponent.location
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(navController: () -> NavHostController) {
    MainScafold()
}
@Composable
fun mapView(){
    val mapViewportState = rememberMapViewportState()
    MapboxMap(
        Modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(20.dp),
        mapViewportState = mapViewportState,
    ) {
        MapEffect(Unit) { mapView ->
            mapView.location.updateSettings {
                locationPuck = createDefault2DPuck(withBearing = true)
                enabled = true
                puckBearing = PuckBearing.COURSE
                puckBearingEnabled = true
            }
            mapViewportState.transitionToFollowPuckState()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextNotion(text: String){
    Text(text = "$text", modifier = Modifier
        .padding(horizontal = 20.dp),
        color = Orange
    )
}
@Composable
fun addButton(){
    LargeFloatingActionButton(onClick = {} , shape = CircleShape,
        modifier = Modifier
            .width(80.dp)
            .height(80.dp)
            .padding(horizontal = 12.dp, vertical = 30.dp)
            .zIndex(1f)) {
        Icon(Icons.Default.Add, contentDescription = "add")
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Input(){

    var text by remember { mutableStateOf(TextFieldValue("")) }
    Row(modifier = Modifier .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center) {
        OutlinedTextField(
            colors = TextFieldDefaults.textFieldColors( containerColor = Color.White),
            placeholder = {
                Text("Local de Entrega")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
                .border(
                    width = 1.dp,
                    shape = RectangleShape,
                    color = Gray
                ),
            value = text,
            onValueChange = { newText ->
                text = newText
            }
        )
    }

}


@Composable
fun historicLoc(){
    Box(modifier = Modifier .padding(horizontal = 12.dp)){
        Row {
            Icon(Icons.Default.DateRange, contentDescription = "Times")
            Text("Lorem Impsum", modifier = Modifier .padding(horizontal = 10.dp))
        }
    }
}
@Composable
fun SecondText(){
    Text("selecione o destino da sua encomenda", modifier = Modifier
        .padding(horizontal = 12.dp, vertical = 20.dp),
        fontWeight = FontWeight.Bold,
        color = TextGray
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScafold(){
    Scaffold(
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = DarkBlue),
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
    ) {
        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ) {
            val (menuBurguer, firtinput, map, notices, circle, menu) = createRefs()

            //menuBurguer
            TextButton (modifier = Modifier
                .background(color = Color.Transparent)
                .constrainAs(menuBurguer){
                    top.linkTo(parent.top, 30.dp)
                }, onClick = {}) {
                Icon(Icons.Default.Menu, contentDescription = "menuBurguer")
            }
            Canvas(modifier = Modifier
                .constrainAs(circle){
                    top.linkTo(parent.top, 40.dp)
                    end.linkTo(parent.end, 60.dp)
                }) {
                scale(scaleX = 5.5f, scaleY = 5.5f) {
                    drawCircle(DarkBlue, radius = 20.dp.toPx())
                }
            }
            //first Input configs
            Column (modifier = Modifier
                .constrainAs(firtinput){
                    top.linkTo(menuBurguer.bottom)
                }){
                Input()
                historicLoc()
                mapView()
            }
        }
        }
    }


@Preview(showBackground = true)
@Composable
private fun PagePreview() {
    FretezonTheme {
   MainScafold()
    }
}
