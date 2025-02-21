package com.example.fretezon.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fretezon.models.Frete
import com.example.fretezon.viewmodel.FreteViewModel

@Composable
fun PostFreteScreen(viewModel: FreteViewModel = viewModel()) {
    val fretes by viewModel.fretes.collectAsState()


    LaunchedEffect(Unit) {
//        viewModel.postFrete()
        viewModel.fetchFrete()// Chama a API ao entrar na tela
    }
    LazyColumn {
        items(fretes) { frete ->
            FreteItem(frete)
        }
    }
}
@Composable
fun FreteItem(frete: Frete) {
    Card(modifier = Modifier.padding(8.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "tpVeiculo: ${frete.tp_veiculo}")
            Text(text = "local_partida: ${frete.local_partida}")
        }
    }
}