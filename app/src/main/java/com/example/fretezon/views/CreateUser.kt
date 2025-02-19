import android.annotation.SuppressLint
import android.util.Log
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.fretezon.models.User
import com.example.fretezon.ui.theme.Black
import com.example.fretezon.ui.theme.DarkBlue
import com.example.fretezon.ui.theme.FretezonTheme
import com.example.fretezon.ui.theme.Gray
import com.example.fretezon.ui.theme.InterSb
import com.example.fretezon.ui.theme.Orange
import com.mapbox.maps.extension.style.expressions.dsl.generated.color
import com.mapbox.maps.extension.style.expressions.dsl.generated.mod
import java.util.regex.Pattern


@Composable
fun CreateUser() {
    MainTemplate()
}


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
private fun MainTemplate(){
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val (header, inputArea, registerButton) = createRefs()

        val label = mutableListOf("name","email"," phone"," password")
        val navController = rememberNavController()
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(bottomStart = 30.dp))
                .constrainAs(header){
                top.linkTo(parent.top)
                    height = Dimension.value(150.dp)
            },
        ){
            Row (modifier = Modifier
                .height(160.dp)
                .fillMaxWidth()
                .background(color = DarkBlue),
                verticalAlignment = Alignment.CenterVertically) {
                TextButton(
                    onClick = {
                        navController.navigate("home")
                    }
                ) {
                    Icon(Icons.Default.KeyboardArrowLeft, contentDescription = "arrowBack", tint = Color.White)

                }
                Column(modifier = Modifier .padding(horizontal = 20.dp)) {
                    Title(title = "Cadastre os")
                    Title(title = "seus dados")
                }

            }
        }

       Column(modifier = Modifier
           .constrainAs(inputArea){
               top.linkTo(header.bottom)
           }) {
           RegistrationForm()
       }

    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationForm() {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var errors by remember { mutableStateOf(mapOf<String, String>()) }

    fun validate(): Boolean {
        val newErrors = mutableMapOf<String, String>()
        if (name.isBlank()) newErrors["name"] = "Nome não pode estar vazio"
        if (!isValidEmail(email)) newErrors["email"] = "E-mail inválido"
        if (password.length < 6) newErrors["password"] = "A senha deve ter pelo menos 6 caracteres"
        if (confirmPassword != password) newErrors["confirmPassword"] = "As senhas não coincidem"
        errors = newErrors
        return newErrors.isEmpty()
    }

    Column(modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)) {
        TextField(
            value = name,
            modifier = Modifier .fillMaxWidth(),
            onValueChange = { name = it },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent
            ),
            label = { Text("Nome", color = Black.copy(0.5f)) },
            visualTransformation = PasswordVisualTransformation(),
            isError = errors.containsKey("name"),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        errors["name"]?.let { Text(it, color = MaterialTheme.colorScheme.error) }

        TextField(
            value = email,
            modifier = Modifier .fillMaxWidth(),
            onValueChange = { email = it },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent
            ),
            label = { Text("E-mail", color = Black.copy(0.5f)) },
            visualTransformation = PasswordVisualTransformation(),
            isError = errors.containsKey("email"),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        errors["email"]?.let { Text(it, color = MaterialTheme.colorScheme.error) }

        TextField(
                value = password,
                modifier = Modifier .fillMaxWidth(),
                onValueChange = { password = it },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent
                ),
                label = { Text("Senha", color = Black.copy(0.5f)) },
                visualTransformation = PasswordVisualTransformation(),
                isError = errors.containsKey("password"),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )

        errors["password"]?.let { Text(it, color = MaterialTheme.colorScheme.error) }


        TextField(
            value =confirmPassword,
            modifier = Modifier .fillMaxWidth(),
            onValueChange = { confirmPassword = it },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent
            ),
            label = { Text("Confirmar Senha", color = Black.copy(0.5f)) },
            visualTransformation = PasswordVisualTransformation(),
            isError = errors.containsKey("confirmPassword"),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                    )
        errors["confirmPassword"]?.let { Text(it, color = MaterialTheme.colorScheme.error) }

        Button(
            onClick = {
                if(validate()){
                    User(
                        name = name,
                        email = email,
                        senha = password,
                    )
                }

            },
            modifier = Modifier.fillMaxWidth().padding(top = 12.dp),
            colors = ButtonColors(
                containerColor = Orange,
                contentColor = Color.White,
                disabledContentColor = Color.Transparent,
                disabledContainerColor = Color.Transparent
            )
        ) {
            //Inter Bold
            Text("CADASTRAR", fontSize = 18.sp)
        }
    }
}

fun isValidEmail(email: String): Boolean {
    val emailPattern = Pattern.compile("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")
    return emailPattern.matcher(email).matches()
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextField() {
    var password by rememberSaveable { mutableStateOf("") }

    TextField(
        value = password,
        modifier = Modifier .fillMaxWidth(),
        onValueChange = { password = it },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.Transparent // Define o fundo como transparente
        ),
        label = { Text("Enter password") },
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
    )
}
@Composable
fun Title(title: String){
   Text(
       text = "$title",
       fontSize = 24.sp,
       fontFamily = InterSb,
       fontWeight = FontWeight.SemiBold,
       color = Color.White
   )
}

@Preview(showBackground = true)
@Composable
private fun PagePreview() {
    FretezonTheme {
        MainTemplate()
    }
}