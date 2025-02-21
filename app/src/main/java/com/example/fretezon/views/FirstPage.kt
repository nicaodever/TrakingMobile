import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.fretezon.R
import com.example.fretezon.ui.theme.DarkBlue
import com.example.fretezon.ui.theme.FretezonTheme
import com.mapbox.maps.extension.style.expressions.dsl.generated.mod
import kotlinx.coroutines.delay
import okhttp3.internal.wait
import coil.compose.rememberAsyncImagePainter
import com.example.fretezon.ui.theme.InclusiveSans
import com.example.fretezon.ui.theme.Inter
import com.example.fretezon.ui.theme.LinkBlue
import com.example.fretezon.ui.theme.Orange


@Composable
fun FirstPage(navController: NavController){
    TemplateFirstPage(navController)
}
@SuppressLint("SuspiciousIndentation")
@Composable
fun TemplateFirstPage(navController: NavController){

    ConstraintLayout(
        modifier = Modifier .fillMaxSize()
    ) {
        val (message, div, image, backColor, logo) = createRefs()

        Image(painter = painterResource(R.drawable.backgroun1),
            contentDescription = "Background",
            contentScale = ContentScale.Crop,
            modifier = Modifier .constrainAs(image){
                width = Dimension.matchParent
            })
        Box(modifier = Modifier
            .background(color = DarkBlue.copy(0.6f))
            .constrainAs(backColor){
                top.linkTo(parent.top)
                width = Dimension.matchParent
                height = Dimension.matchParent
            },)
        Row (modifier = Modifier
            .constrainAs(logo){
                top.linkTo(parent.top, 40.dp)
                start.linkTo(parent.start, 20.dp)
            }){
            Text("FRETE", color = Color.White, fontSize = 16.sp, fontFamily = InclusiveSans)
            Text("ZON", color = Orange, fontSize = 16.sp, fontFamily = InclusiveSans)
        }
        Column(modifier = Modifier .constrainAs(message){
            top.linkTo(parent.top, 200.dp)
            start.linkTo(parent.start, 20.dp)
        }) {
            Text("Mudança fácil, solicite",
                color = Color.White,
                fontFamily = InclusiveSans,
                fontWeight = FontWeight.Bold, fontSize = 29.sp)

            Text("seu frete com um clique!",
                modifier = Modifier .padding(horizontal = 40.dp),
                color = Color.White,
                fontFamily = InclusiveSans,
                fontWeight = FontWeight.Normal, fontSize = 23.sp)
        }

        Box(modifier = Modifier
            .clip(shape = RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp))
            .background(color = Color.White)
            .constrainAs(div){
                bottom.linkTo(parent.bottom)
                height = Dimension.value(220.dp)
                width = Dimension.matchParent
            }
            ){
            Column{
                Text(
                    text = "Inove na forma de solicitar e negociar seu frete.",
                    fontFamily = Inter,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier .padding(horizontal = 30.dp, vertical = 20.dp))
                Button(onClick = {
                navController.navigate("login")
                },
                    modifier = Modifier.fillMaxWidth()
                        .padding(top = 12.dp, start = 12.dp, end = 12.dp),
                    colors = ButtonColors(
                        containerColor = Orange,
                        contentColor = Color.White,
                        disabledContentColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent)){
                        Text(text = "ENTRAR")
                    }
                Box (modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.TopEnd ) {
                    TextButton(onClick = {navController.navigate("registerUser")}) {
                        Text(text = "Não estou cadastrado ->",
                            fontFamily = Inter,
                            color = LinkBlue,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 14.sp,
                            modifier = Modifier
                                .padding(end = 10.dp, top = 12.dp)

                        )
                    }

                }

            }
            }

        }
    }




@Preview(showBackground = true)
@Composable
private fun PagePreview() {
    FretezonTheme {
    }
}