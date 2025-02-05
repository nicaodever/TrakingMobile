import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fretezon.ui.theme.Orange

@Composable
fun RegisterButton(){
    Button(onClick = {}, modifier = Modifier
        .padding(horizontal = 20.dp)
        .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = Orange
        )
    ) {
        Text(text = "CADASTRAR",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    }
}