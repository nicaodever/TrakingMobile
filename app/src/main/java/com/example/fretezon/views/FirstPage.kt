import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.navigation.NavHostController
import com.example.fretezon.R
import com.example.fretezon.ui.theme.DarkBlue
import com.example.fretezon.ui.theme.FretezonTheme
import com.mapbox.maps.extension.style.expressions.dsl.generated.mod
import kotlinx.coroutines.delay
import okhttp3.internal.wait
import coil.compose.rememberAsyncImagePainter
import com.example.fretezon.ui.theme.Inter
import com.example.fretezon.ui.theme.Orange

@Composable
fun FirstPage(){

}
@SuppressLint("SuspiciousIndentation")
@Composable
fun TemplateFirstPage(){
    ConstraintLayout(
        modifier = Modifier .fillMaxSize()
    ) {
        val (content, div, image) = createRefs()

        Image(painter = painterResource(R.drawable.background1),
            contentDescription = "Background",
            contentScale = ContentScale.Crop,
            modifier = Modifier .constrainAs(image){
                width = Dimension.matchParent
            })
        Box(modifier = Modifier
            .clip(shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
            .background(color = Color.White)
            .constrainAs(div){
                bottom.linkTo(parent.bottom)
                height = Dimension.value(220.dp)
                width = Dimension.matchParent
            }
            ){
            Column{
                Text(
                    text = "Inove na forma de solicitar e negociar seu frete",
                    fontFamily = Inter,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier .padding(20.dp))
                Button(onClick = {},
                    modifier = Modifier.fillMaxWidth()
                        .padding(top = 12.dp, start = 12.dp, end = 12.dp),
                    colors = ButtonColors(
                        containerColor = Orange,
                        contentColor = Color.White,
                        disabledContentColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent)){
                        Text(text = "ENTRAR")
                    }
            }
            }

        }
    }




@Preview(showBackground = true)
@Composable
private fun PagePreview() {
    FretezonTheme {
        TemplateFirstPage()
    }
}