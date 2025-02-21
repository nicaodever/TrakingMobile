import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.mapbox.common.BaseMapboxInitializer.Companion.init
import com.mapbox.search.autocomplete.PlaceAutocomplete
import com.mapbox.search.autocomplete.PlaceAutocompleteSuggestion
import com.mapbox.search.autofill.AddressAutofill
import com.mapbox.search.autofill.AddressAutofillOptions
import com.mapbox.search.ui.adapter.autofill.AddressAutofillUiAdapter
import com.mapbox.search.ui.view.SearchResultsView
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


val placeAutocomplete = PlaceAutocomplete.create()

data class Suggestion(
    val street: String,
    val city: String,
    val state: String
) {
    fun getFullAddress(): String {
        return "$street, $city, $state"
    }
}
val list = mutableListOf<String>()

@Composable
fun SuggestionItem(suggestion: PlaceAutocompleteSuggestion, onSuggestionClick: (PlaceAutocompleteSuggestion) -> Unit) {
    suggestion.name?.let {
        Text(
        text = it,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onSuggestionClick(suggestion) }
    )
    }
}



@Composable
fun AdressPage() {
    val context = LocalContext.current
    var texto by remember { mutableStateOf("") }
    var suggestions by remember { mutableStateOf(emptyList<PlaceAutocompleteSuggestion>()) }

    OutlinedTextField(
        value = texto,
       onValueChange = { texto = it },
   label = { Text("Digite algo") }
    )
    val query = texto
    LaunchedEffect(texto) {
        delay(300)
        if (query.isNotBlank()) { // Evita chamadas com texto vazio
            val response = placeAutocomplete.suggestions(query)
            response.onValue { newSuggestions: List<PlaceAutocompleteSuggestion> ->
                suggestions = newSuggestions
            }.onError { e ->
                // Trate o erro, por exemplo, log
                println("Erro na API: ${e.message}")
                suggestions = emptyList() // Limpa as sugestões em caso de erro
            }
        } else {
            suggestions = emptyList() // Limpa as sugestões se o texto estiver vazio
        }
    }
    if (suggestions.isNotEmpty()) {
        LazyColumn(modifier = Modifier.fillMaxWidth()
            .padding(vertical = 60.dp)){
            items(suggestions) { suggestion ->
                SuggestionItem(suggestion = suggestion) { selectedSuggestion ->
                    // Ação ao clicar em uma sugestão
                    texto = selectedSuggestion.name
                    suggestions = emptyList() // Limpa as sugestões após a seleção
                }
            }
        }
    }
}

