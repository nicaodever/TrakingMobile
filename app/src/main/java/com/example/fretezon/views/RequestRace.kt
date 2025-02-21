import android.annotation.SuppressLint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavHostController
import com.example.fretezon.R
import com.example.fretezon.ui.theme.DarkBlue


@Composable
fun RequestRace(navController: () -> NavHostController){
  Layout()
}
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Layout(){

        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ) {
            val (menuBurguer, card, title, notices, circle, menu) = createRefs()

            //menuBurguer
            TextButton (modifier = Modifier
                .background(color = Color.Transparent)
                .constrainAs(menuBurguer){
                    top.linkTo(parent.top, 30.dp)
                }, onClick = {}) {
                Icon(Icons.Default.KeyboardArrowLeft, contentDescription = "menuBurguer")
            }
            Text(text = "Solicitação de Frete",
                modifier = Modifier
                    .constrainAs(title){
                        top.linkTo(parent.top, 45.dp)
                        start.linkTo(menuBurguer.end, 12.dp)
                    })
            Canvas(modifier = Modifier
                .constrainAs(circle){
                    top.linkTo(parent.top, 40.dp)
                    end.linkTo(parent.end, 50.dp)
                }) {
                scale(scaleX = 5f, scaleY = 5f) {
                    drawCircle(DarkBlue, radius = 20.dp.toPx())
                }
            }
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                ),
                modifier = Modifier
                    .padding(25.dp)
                    .constrainAs(card){
                        top.linkTo(menuBurguer.bottom, 10.dp)
                        width = Dimension.matchParent
                        height = Dimension.value(200.dp)
                    }
            ) {
                Image(
                    painter = painterResource(R.drawable.backgroun1),
                    contentDescription = "Imagem Background",
                    modifier = Modifier .width(120.dp),
                )
                Text(
                    text = "Filled",
                    modifier = Modifier
                        .padding(16.dp),
                    textAlign = TextAlign.Center,
                )

            }
        }

}