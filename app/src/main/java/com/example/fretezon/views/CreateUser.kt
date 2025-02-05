import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.fretezon.ui.theme.DarkBlue
import com.example.fretezon.ui.theme.FretezonTheme
import com.example.fretezon.ui.theme.Gray
import com.example.fretezon.ui.theme.Orange
import com.mapbox.maps.extension.style.expressions.dsl.generated.mod


class User : ViewModel() {
    var name by mutableStateOf("")
    var email by mutableStateOf("")
    var phone by mutableStateOf("")
    var password by mutableStateOf("")
}
@Composable
fun CreateUser(navController: () -> NavHostController) {
    MainTemplate()
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainTemplate(User : User = viewModel()){
      val name = User.name
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val (header, inputArea, registerButton) = createRefs()

        val label = mutableListOf("name","email"," phone"," password")
        Box(
            modifier = Modifier .constrainAs(header){
                top.linkTo(parent.top)
            }
        ){
            Header()
        }
        Column(modifier = Modifier
            .constrainAs(inputArea){
                top.linkTo(header.bottom)
            }){
            TextInput("name")
            TextInput("email")
            TextInput("phone number")
            TextInput("password")
        }
        Row(modifier = Modifier
            .constrainAs(registerButton){
                top.linkTo(inputArea.bottom, 20.dp)
                width = Dimension.matchParent
                height = Dimension.value(50.dp)
            },) {
            RegisterButton()
        }

    }
}


@Composable
fun Title(title: String){
   Text(
       text = "$title",
       fontSize = 24.sp,
       fontWeight = FontWeight.SemiBold,
       color = Color.White
   )
}
@Composable
fun Header(){
 Row (modifier = Modifier
     .height(160.dp)
     .fillMaxWidth()
     .background(color = DarkBlue),
     verticalAlignment = Alignment.CenterVertically) {
     TextButton(
       onClick = {}
     ) {
       Icon(Icons.Default.KeyboardArrowLeft, contentDescription = "arrowBack", tint = Color.White)

     }
     Column(modifier = Modifier .padding(horizontal = 20.dp)) {
         Title(title = "Cadastre os")
         Title(title = "seus dados")
     }

 }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextInput(label: String, meuViewModel: User = viewModel()){
    Row(modifier = Modifier .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center) {
        OutlinedTextField(
            colors = TextFieldDefaults.textFieldColors( containerColor = Color.White),
            placeholder = {
                Text("$label")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
                .border(
                    width = 1.dp,
                    shape = RectangleShape,
                    color = Gray
                ),
            value = meuViewModel.name,
            onValueChange = { newText ->
               meuViewModel.name = newText
            }
        )
    }
}
@Preview(showBackground = true)
@Composable
private fun PagePreview() {
    FretezonTheme {

    }
}