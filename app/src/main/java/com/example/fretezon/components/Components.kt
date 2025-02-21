import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.fretezon.ui.theme.DarkBlue
import com.example.fretezon.ui.theme.Orange

@Composable
fun MenuBottom(navController: NavController, modifier: Modifier = Modifier) {
    NavigationBar(
        containerColor = DarkBlue,
        modifier = modifier
    ) {
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = null,
                    tint = Color.White
                )
            },
            label = {
                Text(text = "início", color = Color.White)
            },
            selected = false,
            onClick = {
                navController.navigate("home"){
                    popUpTo(0) // Remove todas as telas anteriores
                    launchSingleTop = true
                }
            }
        )
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = null,
                    tint = Color.White
                )
            },
            label = {
                Text(text = "agendados", color = Color.White)
            },
            selected = false,
            onClick = {
                navController.navigate("listRaces") {
                    popUpTo(0) // Remove todas as telas anteriores
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )
    }
}