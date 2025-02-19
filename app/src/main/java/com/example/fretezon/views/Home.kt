import android.annotation.SuppressLint
import android.view.Menu
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.fretezon.R
import com.example.fretezon.models.Frete
import com.example.fretezon.ui.theme.DarkBlue
import com.example.fretezon.ui.theme.Gray
import com.example.fretezon.ui.theme.Inter
import com.example.fretezon.ui.theme.InterSb
import com.example.fretezon.ui.theme.Orange
import com.example.fretezon.ui.theme.TextGray
import com.mapbox.maps.MapView
import com.mapbox.maps.extension.compose.MapEffect
import com.mapbox.maps.extension.compose.MapboxMap
import com.mapbox.maps.extension.compose.animation.viewport.rememberMapViewportState
import com.mapbox.maps.extension.style.expressions.dsl.generated.color
import com.mapbox.maps.plugin.PuckBearing
import com.mapbox.maps.plugin.locationcomponent.createDefault2DPuck
import com.mapbox.maps.plugin.locationcomponent.location


fun getMockCliente(): Frete {
    return Frete(
        id = 1,
        descricao_carga = "Geladeira Electrolux 190,5 x 78,9 x 74,5 cm",
        local_partida = "R.Sebatia Noroes, Ribeiro Junior, 23",
        data_entrega = "21-02-25",
        horario_retir = "14:00",
        tp_veiculo = "pequeno porte",
        destino = "R.Jesus me deu, Mauazinho, 1203",
        foto_carga = "https://firebasestorage.googleapis.com/v0/b/qualisafe-7f25b.appspot.com/o/anuncios%2Fpexels-artempodrez-5025666.jpg?alt=media&token=29ff706a-be08-4ba2-9cfa-0a7f175f7d56"
    )
}

@Composable
fun MapView(){
    val mapViewportState = rememberMapViewportState {
        // Define a posição inicial da câmera sem animação
        setCameraOptions {
            zoom(12.0) // Define o nível de zoom inicial
            bearing(0.0) // Define a orientação inicial
            pitch(0.0) // Sem inclinação
        }
    }
    MapboxMap(
        Modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(horizontal = 10.dp),
        mapViewportState = mapViewportState,
    ) {
        MapEffect(Unit) { mapView ->
            mapView.location.updateSettings {
                locationPuck = createDefault2DPuck(withBearing = true)
                enabled = true
                puckBearing = PuckBearing.HEADING
                puckBearingEnabled = false
            }
            mapViewportState.transitionToFollowPuckState()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextNotion(text: String){
    Text(text = "$text", modifier = Modifier
        .padding(horizontal = 10.dp),
        color = Orange,
        fontFamily = InterSb,
        fontWeight = FontWeight.SemiBold
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClickableInputBox() {
    var text by remember { mutableStateOf("Local de Entrega") }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 12.dp)
            .border(width = 1.dp, color = Gray, shape = RoundedCornerShape(12.dp))
            .background(Color.White, shape = RoundedCornerShape(12.dp))
            .clickable {  }
            .padding(20.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier .fillMaxWidth()) {
            Text(text = text, color = Color.Gray, modifier = Modifier .padding(vertical = 4.dp))
            Icon(Icons.Default.Search, contentDescription = "search", tint = Gray)
        }

    }
}

@Composable
//location: String, modifier: Modifier = Modifier
fun RecentLocationItem( modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Outlined.Info, // Ícone de relógio (Histórico)
            contentDescription = "Histórico",
            modifier = Modifier.size(24.dp),
            tint = Color.Gray
        )
        Spacer(modifier = Modifier.width(12.dp)) // Espaço entre o ícone e o texto
        Text(
            text = "Sebastia Noroes",
            color = Color.Black,
            fontSize = 16.sp
        )
    }
}

@Composable
fun SecondText(){
    Text("Selecione o destino da sua encomenda", modifier = Modifier
        .padding(horizontal = 10.dp, vertical = 10.dp),
        fontFamily = InterSb,
        fontSize = 12.sp,
        color = TextGray
    )
}
@Composable
fun Carrossel(){
    val frete = getMockCliente()
    val pagerState = rememberPagerState {
        3
    }
    val pagerItems = listOf(
      R.drawable.frete, R.drawable.frete, R.drawable.frete
    )
    Column {
        Text(text = "Ultimas Atualizações",
            fontSize = 16.sp,
            color = TextGray,
            fontFamily = InterSb,
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 10.dp)
            )
        Divider(modifier = Modifier .width(300.dp) .padding(horizontal = 10.dp))
        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 35.dp, vertical = 20.dp),
            pageSize = PageSize.Fill
        ) { page ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ){
                Image(
                    painter = painterResource(pagerItems[page]),
                    contentDescription = null,
                    modifier = Modifier .width(300.dp).height(150.dp)
                        .clip(
                            shape = RoundedCornerShape(20.dp)
                        )
                        .padding(5.dp),
                    contentScale = ContentScale.Crop
                )
            }

        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
//navController: NavController
fun Home(navController: NavController){
        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ) {
            val (menuBurguer, firtinput, menubt, notices, circle, menu) = createRefs()

            //menuBurguer
            TextButton (modifier = Modifier
                .background(color = Color.Transparent)
                .constrainAs(menuBurguer){
                    top.linkTo(parent.top, 40.dp)
                }, onClick = {}) {

            }
            Canvas(modifier = Modifier
                .constrainAs(circle){
                    top.linkTo(parent.top, 50.dp)
                    end.linkTo(parent.end, 60.dp)
                }) {
                scale(scaleX = 5.5f, scaleY = 5.5f) {
                    drawCircle(DarkBlue, radius = 20.dp.toPx())
                }
            }
            //Conteudo interno
            Column (modifier = Modifier
                .constrainAs(firtinput){
                    top.linkTo(menuBurguer.bottom)
                }){
                TextNotion("Faça seu frete já!")
                ClickableInputBox()
                SecondText()
                MapView()
                Carrossel()
            }

            // Menu bottom
            TextButton(onClick = {},
                modifier = Modifier
                    .clip(shape = CircleShape)
                    .background(color = Orange)
                    .zIndex(2f)
                .constrainAs(menubt){
                bottom.linkTo(parent.bottom, 20.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                width = Dimension.value(70.dp)
                height = Dimension.value(70.dp)
            },) {
                Icon(Icons.Default.Add, contentDescription = "btAdd", tint = Color.White)
            }

            MenuBottom(navController,
                Modifier
                    .height(65.dp)
                .constrainAs(menu){
                    bottom.linkTo(parent.bottom)
                },
                )

        }
        }


@Preview(showBackground = true)
@Composable
private fun PagePreview() {
    FretezonTheme {

    }
}
