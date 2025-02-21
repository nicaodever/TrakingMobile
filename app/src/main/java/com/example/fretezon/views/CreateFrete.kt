package com.example.fretezon.views

import UserLocation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.fretezon.ui.theme.FretezonTheme
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.fretezon.R
import com.example.fretezon.models.Frete
import com.example.fretezon.services.uploadImage
import com.example.fretezon.ui.theme.DarkBlue
import com.example.fretezon.ui.theme.Gray
import com.example.fretezon.ui.theme.InterSb
import com.example.fretezon.ui.theme.Orange
import com.example.fretezon.viewmodel.FreteViewModel
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import com.mapbox.maps.extension.style.expressions.dsl.generated.mod
import java.util.*

@Composable
fun CreateFrete(navController: NavController){
    TemplateCreateFrete(navController)
}
@Composable
fun TemplateCreateFrete(navController: NavController){
    ConstraintLayout(modifier = Modifier
        .fillMaxSize()) {
        val (arrowback, contentImage, circle) = createRefs()
        Canvas(modifier = Modifier
            .constrainAs(circle){
                top.linkTo(parent.top, 40.dp)
                end.linkTo(parent.end, 40.dp)
            }) {
            scale(scaleX = 5f, scaleY = 5.5f) {
                drawCircle(DarkBlue, radius = 20.dp.toPx())
            }
        }
        Row (modifier = Modifier
            .constrainAs(arrowback){
                top.linkTo(parent.top, 40.dp)
                start.linkTo(parent.start, 5.dp)
            }) {
           TextButton(onClick = {
               navController.navigate("home")
           }) {
               Icon(Icons.Default.KeyboardArrowLeft, contentDescription = "menuBurguer")
               }
            Text("Solicitação Frete",
                fontSize = 20.sp,
                fontFamily = InterSb,
                modifier = Modifier.padding(vertical = 12.dp))
        }
        FreightForm(modifier = Modifier
            .constrainAs(contentImage){
                top.linkTo(arrowback.bottom, 20.dp)
            })

    }
}

@Composable
fun FreightForm(modifier: Modifier, viewModel: FreteViewModel = viewModel()) {
    var userLocation = UserLocation()
    val context = LocalContext.current
    val vehicleOptions = listOf("Caminhonete", "Bau pequeno", "Bau Medio", "Bau Grande")

    var tp_veiculo by remember { mutableStateOf(vehicleOptions[0]) }
    var data_entrega by remember { mutableStateOf("") }
    var horario_retirada by remember { mutableStateOf("") }
    var descrica_carga by remember { mutableStateOf("") }
    var foto_carga by remember { mutableStateOf<Uri?>(null) }
    val calendar = Calendar.getInstance()

    // Abrir DatePickerDialog
    val datePickerDialog = DatePickerDialog(
        context,
        { _, year, month, day ->
            data_entrega = "$day/${month + 1}/$year"
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    // Abrir TimePickerDialog
    val timePickerDialog = TimePickerDialog(
        context,
        { _, hour, minute ->
            horario_retirada = String.format("%02d:%02d", hour, minute)
        },
        calendar.get(Calendar.HOUR_OF_DAY),
        calendar.get(Calendar.MINUTE),
        true
    )

    //Image Picker
    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri != null) {
            foto_carga = uri
        }
    }
//Inicio
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        // Preview
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(10.dp))
                .height(200.dp)
                .padding(horizontal = 2.dp)
                .background(Gray)
        ){
            Row (modifier = Modifier .fillMaxWidth()) {
                //picker Image
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp)
                        .height(200.dp)
                        .background(Color.LightGray, shape = RoundedCornerShape(8.dp))
                        .clickable { imagePickerLauncher.launch("image/*") },
                    contentAlignment = Alignment.Center
                ) {
                    if (foto_carga != null) {

                        Image(
                            painter = rememberAsyncImagePainter(foto_carga),
                            contentDescription = "Imagem selecionada",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    } else {
                        Text("imagem do produto(opcional)")
                    }
                }

                //labels content
                Column(modifier = Modifier
                    .weight(0.8f)) {
                    Label("Tipo Veiculo:")
                    DataFrete("$tp_veiculo")
                    Label("Horario Retirada")
                    DataFrete("$horario_retirada")
                    Label("Data:")
                    DataFrete("$data_entrega")
                    Label("descrição da carga:")
                    DataFrete("$descrica_carga")
                }

            }
        }


        Spacer(modifier = Modifier.height(12.dp))
        //label area
    Row(modifier = Modifier.fillMaxWidth()) {
      Column(modifier = Modifier.weight(1f)) {
          Text("Tipo de Veículo", fontSize = 14.sp, color = Color.Gray)
          var expanded by remember { mutableStateOf(false) }
          Box(modifier = Modifier.fillMaxWidth()
              .padding(2.dp)) {
              OutlinedButton(
                  onClick = { expanded = true },
                  modifier = Modifier.fillMaxWidth()
              ) {
                  Text(tp_veiculo)
                  Icon(Icons.Default.ArrowDropDown, contentDescription = "drop")
              }
              DropdownMenu(
                  expanded = expanded,
                  onDismissRequest = { expanded = false }
              ) {
                  vehicleOptions.forEach { vehicle ->
                      DropdownMenuItem(
                          text = { Text(vehicle) },
                          onClick = {
                              tp_veiculo = vehicle
                              expanded = false
                          }
                      )
                  }
              }
          }
      }
     Column(modifier = Modifier .weight(0.7f)) {
         Text("Hora", fontSize = 14.sp, color = Color.Gray)
         OutlinedButton(
             onClick = { timePickerDialog.show() },
             modifier = Modifier.fillMaxWidth()
         ) {
             Text(if (horario_retirada.isNotEmpty()) horario_retirada else "--:--")
         }
     }


  }

        Spacer(modifier = Modifier.height(8.dp))
        Text("Data", fontSize = 14.sp, color = Color.Gray)
        OutlinedButton(
            onClick = { datePickerDialog.show() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (data_entrega.isNotEmpty()) data_entrega else "--/--/----")
        }
        Spacer(modifier = Modifier.height(12.dp))


        Text("Descrição da carga", fontSize = 14.sp, color = Color.Gray)
        OutlinedTextField(
            value = descrica_carga,
            onValueChange = { descrica_carga = it },
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 5.dp),
            placeholder = { Text("ex: Mudança") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {

               val register = Frete(
                    data_entrega = "2025-02-26",
                    horario_retir = horario_retirada,
                    foto_carga = foto_carga.toString(),
                    local_partida = "Sebastiao Noroes",
                    tp_veiculo = tp_veiculo,
                    descricao_carga = descrica_carga,
                    destino = "Cidade Nova"
                )
                Log.i("OBJETO", register.toString())
                viewModel.postFrete(register)

            },
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 5.dp),
            colors = ButtonColors(
                containerColor = Orange,
                contentColor = Color.White,
                disabledContentColor = Color.Transparent,
                disabledContainerColor = Color.Transparent
            )

        ) {
            Text("CADASTRAR")
        }
    }
}
@Composable
fun Label(txt: String){
    Text("$txt",
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp,
        modifier = Modifier
            .padding(vertical = 2.dp)
    )
}
@Composable
fun DataFrete(txt: String){
    Text("$txt",
        modifier = Modifier .padding(vertical = 5.dp),
        fontSize = 12.sp)
}

fun upLoadImagetoFirebase(uri: Uri?, context: Context){
     val storage = FirebaseStorage.getInstance()
    val storageReference = storage.reference
    val imageReference = storageReference.child("images/"+uri!!.lastPathSegment)

    val uploadTask = uri.let { imageReference.putFile(it) }
    uploadTask.addOnSuccessListener {
        Log.i("SUCESS", "imagem selecionado")
    }


}
fun listImage(){
    val storage = Firebase.storage
    val listRef = storage.reference.child("files/uid")

// You'll need to import com.google.firebase.storage.component1 and
// com.google.firebase.storage.component2


}

@Preview(showBackground = true)
@Composable
private fun PagePreview() {
    FretezonTheme{
    }
}