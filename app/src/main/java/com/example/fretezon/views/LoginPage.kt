package com.example.fretezon.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.AsyncImage
import com.example.fretezon.R
import com.example.fretezon.ui.theme.Black
import com.example.fretezon.ui.theme.DarkBlue
import com.example.fretezon.ui.theme.FretezonTheme
import com.example.fretezon.ui.theme.InclusiveSans
import com.example.fretezon.ui.theme.Inter
import com.example.fretezon.ui.theme.Orange
import com.mapbox.maps.extension.style.expressions.dsl.generated.color
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.ui.text.font.FontFamily
import androidx.navigation.NavController
import com.example.fretezon.ui.theme.InterExtraLight

@Composable
fun LoginPage(navController: NavController){
    TemplateLoginPage(navController)
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TemplateLoginPage(navController: NavController){
    var checked by remember { mutableStateOf(false) }
    ConstraintLayout(modifier = Modifier .fillMaxSize()) {
        val (backImg, backColor, contentLogin, logo, voltarbt) = createRefs()

        //background
        Image(
            painter = painterResource(R.drawable.loginback) ,
            contentDescription = "n",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .constrainAs(backImg){
                    top.linkTo(parent.top)
                    width = Dimension.matchParent
                    height = Dimension.value(400.dp)
                },
        )
       Box(modifier = Modifier
           .background(color = DarkBlue.copy(0.8f))
           .constrainAs(backColor){
               top.linkTo(parent.top)
               width = Dimension.matchParent
               height = Dimension.value(400.dp)
           },)

        //Itens header
        TextButton(
            modifier = Modifier .constrainAs(voltarbt){
                top.linkTo(parent.top, 20.dp)
                start.linkTo(parent.start, 10.dp)
            },
            onClick = {
                navController.navigate("firstPage")
            }
        ) {
            Icon(Icons.Default.KeyboardArrowLeft, contentDescription = "arrowBack", tint = Color.White, modifier = Modifier.height(35.dp) )
        }
        Row (modifier = Modifier
            .constrainAs(logo){
                bottom.linkTo(contentLogin.top, 20.dp)
                start.linkTo(parent.start, 20.dp)
            }){
            Text("FRETE", color = Color.White, fontSize = 34.sp, fontFamily = InclusiveSans)
            Text("ZON", color = Orange, fontSize = 34.sp, fontFamily = InclusiveSans)
        }

        //Content
        Box(modifier = Modifier
            .clip(RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
            .constrainAs(contentLogin){
                bottom.linkTo(parent.bottom)
                width = Dimension.matchParent
                height = Dimension.value(500.dp)
            }
            .background(color = Color.White)
        ){

            Column(verticalArrangement = Arrangement.spacedBy(30.dp),
                modifier = Modifier .padding(vertical = 40.dp)) {
                var email by remember { mutableStateOf("") }
                var password by remember { mutableStateOf("") }
                var passwordVisible by remember { mutableStateOf(false) }

                TextField(
                    value = email,
                    modifier = Modifier .fillMaxWidth() .padding(horizontal = 20.dp),
                    onValueChange = { email = it },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent
                    ),
                    label = { Text("Email", color = Black.copy(0.5f)) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                )
                
                TextField(
                    value = password,
                    modifier = Modifier .fillMaxWidth() .padding(horizontal = 20.dp),
                    onValueChange = { password = it },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent
                    ),
                    label = { Text("Senha", color = Black.copy(0.5f)) },
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                            trailingIcon = {
                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(
                                imageVector = if (passwordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility    ,
                                contentDescription = if (passwordVisible) "Ocultar senha" else "Mostrar senha"
                            )
                        }
                    }
                )
                Row(modifier = Modifier.padding(horizontal = 10.dp)) {
                    Checkbox(
                        modifier = Modifier
                            .clip(shape = RoundedCornerShape(16.dp)),
                        checked = checked,
                        onCheckedChange = { checked = it }
                    )
                    Text(text = "Manter-me conectado",
                        fontFamily = InterExtraLight,
                        fontSize = 14.sp,
                        modifier = Modifier .padding(vertical = 15.dp))
                }
                Button(onClick = {
                    validateLogin(navController, email, password)

                },
                    modifier = Modifier.fillMaxWidth()
                        .height(60.dp)
                        .padding(top = 12.dp, start = 12.dp, end = 12.dp),
                    colors = ButtonColors(
                        containerColor = Orange,
                        contentColor = Color.White,
                        disabledContentColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent)
                ){
                    Text(text = "ENTRAR")
                }
                TextButton(onClick = {},
                    modifier = Modifier
                    .fillMaxWidth(),) {
                    Row {
                        TextButton(onClick = {navController.navigate("registerUser")}) {
                        Text(text = "Não tem cadastro? ",
                            modifier = Modifier,
                            fontFamily = Inter,
                            fontWeight = FontWeight.ExtraLight,
                            color = Color.Black)
                            Text("Cadastre-se", fontWeight = FontWeight.Bold, color = Color.Black, )
                        }

                    }
                }
            }
        }
    }
}
fun validateLogin(navController: NavController, user: String, password: String){
    if(user == "admin" && password == "admin123"){
        navController.navigate("home")
    }
}

@Preview(showBackground = true)
@Composable
private fun PagePreview() {
    FretezonTheme {
//        LoginPage()
    }
}