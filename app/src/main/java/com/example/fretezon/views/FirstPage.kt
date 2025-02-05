import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import com.example.fretezon.R
import com.example.fretezon.ui.theme.DarkBlue
import com.example.fretezon.ui.theme.FretezonTheme
import com.mapbox.maps.extension.style.expressions.dsl.generated.mod

@Composable
fun FirstPage(navController: () -> NavHostController){
        Image(
            painter = painterResource(R.drawable.background1),
            contentDescription = "Imagem Background",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier .fillMaxSize()
                .zIndex(0f),
        )
    Text(text = "FRETEZON", color = Color.White )

}


@Preview(showBackground = true)
@Composable
private fun PagePreview() {
    FretezonTheme {
    }
}