package com.example.fretezon.views

import MenuBottom
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.fretezon.R
import com.example.fretezon.models.Frete
import com.example.fretezon.models.Post
import com.example.fretezon.ui.theme.DarkBlue
import com.example.fretezon.ui.theme.FretezonTheme
import com.example.fretezon.ui.theme.Gray
import com.example.fretezon.ui.theme.Inter
import com.example.fretezon.ui.theme.Orange
import com.example.fretezon.ui.theme.TextGray
import com.example.fretezon.viewmodel.FreteViewModel

@Composable
fun ListRaces(navController: NavController){
   MainTemplate(navController)
}
@Composable
fun MainTemplate(navController: NavController){
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (menuBurguer, content, menubt, circle, menu) = createRefs()


        Canvas(modifier = Modifier
            .constrainAs(circle){
                top.linkTo(parent.top, 50.dp)
                end.linkTo(parent.end, 60.dp)
            }) {
            scale(scaleX = 5.5f, scaleY = 5.5f) {
                drawCircle(DarkBlue, radius = 20.dp.toPx())
            }
        }
        //Content
       Column(modifier = Modifier
           .constrainAs(content){
               top.linkTo(menuBurguer.bottom)
           }
       ) {
          FreteListScreen()
       }

        // Menu bottom
        TextButton(onClick = {
            navController.navigate("registerFrete")
        },
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

        MenuBottom(navController ,Modifier
            .height(65.dp)
            .constrainAs(menu){
                bottom.linkTo(parent.bottom)
            })
    }
}

@Composable
fun FreteListScreen(viewModel: FreteViewModel = viewModel()) {
    val fretes by viewModel.fretes.collectAsState()


    LaunchedEffect(Unit) {
        viewModel.fetchFrete()// Chama a API ao entrar na tela
    }
    LazyColumn {
        items(fretes) { frete ->
            CardFrete(frete)
        }
    }
}
@Composable
fun CardFrete(frete: Frete){

    var expanded by remember {
        mutableStateOf(false)
    }
   Card(shape = RoundedCornerShape(30.dp),
       colors = CardDefaults.cardColors(
           containerColor = Color.White
       ),
       elevation = CardDefaults.cardElevation(
           defaultElevation = 8.dp
       ),
       modifier = Modifier .padding(horizontal = 10.dp, vertical = 12.dp),
       content = {
           Column(
               modifier = Modifier
                   .fillMaxWidth()
                   .background(DarkBlue)
                   .padding(16.dp)
           ) {
               Row(horizontalArrangement = Arrangement.SpaceBetween,
                   modifier = Modifier
                       .fillMaxWidth()){
                   Text(
                       text = "${frete.local_partida}",
                       fontSize = 16.sp,
                       modifier = Modifier .padding(vertical = 12.dp),
                       style = TextStyle(
                           color = Color.White.copy(0.8f)
                       )
                   )
                   TextButton(onClick = {}) {
                       Icon(Icons.Default.Notifications, contentDescription = "Notification", tint = Color.White)
                   }
               }
               Row(modifier = Modifier
                   .fillMaxWidth(),
                   horizontalArrangement = Arrangement.SpaceBetween){
                   Text("${frete.data_entrega} as ${frete.horario_retir}",
                       style = TextStyle(
                       color = Color.White.copy(0.8f)
                   ))
                   Text("${frete.tp_veiculo}",
                       style = TextStyle(
                           color = Color.White.copy(0.8f)
                       ))

               }
           }

               Column(
                   modifier = Modifier
                       .fillMaxWidth()
                       .animateContentSize (
                           animationSpec = tween(
                               durationMillis = 500,
                               easing = LinearOutSlowInEasing
                           )
                       )

               ) {
                   if(expanded) {
                           Row {
                               Image(
                                   painter = rememberAsyncImagePainter(frete.foto_carga),
                                   contentDescription = "Imagem Do Frete",
                                   contentScale = ContentScale.Crop,
                                   modifier = Modifier
                                       .clip(RoundedCornerShape(16.dp))
                                       .padding(horizontal = 10.dp, vertical = 5.dp)
                                       .width(120.dp)
                                       .height(120.dp)
                               )
                               Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                                   Text(text = "Descrição: ${frete.descricao_carga}",
                                       fontFamily = Inter,
                                       color = TextGray
                                   )
                                   Text(text = "Destino: ${frete.destino}",
                                       fontFamily = Inter,
                                       color = TextGray)
                                   Text(text = "Horario Retirada: ${frete.horario_retir}",
                                       fontFamily = Inter,
                                       color = TextGray)
                               }
                        }
                       Row(modifier = Modifier .fillMaxWidth(),
                           horizontalArrangement = Arrangement.SpaceEvenly){
                           Button(onClick = {},
                               shape = RoundedCornerShape(16.dp),
                               colors = ButtonColors(
                                   containerColor = Orange,
                                   contentColor = Color.Blue,
                                   disabledContainerColor= Color.Gray,
                                   disabledContentColor= Color.Cyan
                               ),
                               modifier = Modifier
                                   .width(160.dp)
                           ) { Text(text = "Aceitar", color = Color.White) }
                           Button(onClick = {},
                               shape = RoundedCornerShape(16.dp),
                               border = BorderStroke(1.dp, Gray),
                               colors = ButtonColors(
                                   containerColor = Color.Transparent,
                                   contentColor = Color.Blue,
                                   disabledContainerColor= Color.Gray,
                                   disabledContentColor= Color.Cyan
                               ),
                               modifier = Modifier
                                   .width(160.dp) ){ Text(text = "Remover", color = Color.Red) }
                       }
                   }
               }

           Row(modifier = Modifier .fillMaxWidth()
               .clickable { expanded = !expanded },
               horizontalArrangement = Arrangement.Center,
               verticalAlignment = Alignment.CenterVertically
           ) {
               Text(text = if(expanded) "Menos Informações" else "Mais Informações",
                   color = Color.Blue.copy(0.5f),
                   fontWeight = FontWeight.Medium,
                   modifier = Modifier .padding(vertical = 16.dp, horizontal = 5.dp)
               )
              if(!expanded) Icon(Icons.Default.KeyboardArrowDown, contentDescription = "vermais", tint = Color.Blue.copy(0.5f))
               else Icon(Icons.Default.KeyboardArrowUp, contentDescription = "vermais", tint = Color.Blue.copy(0.5f))
           }

       }
       )
}

@Preview(showBackground = true)
@Composable
private fun PagePreview() {
    Column (modifier = Modifier
        .fillMaxSize()
        .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
       CardFrete(
           frete = TODO()
       )
    }
}