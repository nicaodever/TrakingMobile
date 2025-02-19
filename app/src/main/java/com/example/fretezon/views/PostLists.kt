package com.example.fretezon.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fretezon.models.Frete
import com.example.fretezon.models.Post
import com.example.fretezon.viewmodel.FreteViewModel


@Composable
fun PostListScreen(viewModel: FreteViewModel = viewModel()) {
    val fretes by viewModel.fretes.collectAsState()


    LaunchedEffect(Unit) {
        viewModel.fetchFrete() // Chama a API ao entrar na tela
    }

    LazyColumn {
        items(fretes) { frete ->
            PostItem(frete)
            Text(frete.destino)
        }
    }
}

@Composable
fun PostItem(frete: Frete) {
    Card(modifier = Modifier.padding(8.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "ID: ${frete.id}", fontWeight = FontWeight.Bold)
            Text(text = "tpVeiculo: ${frete.tp_veiculo}")
            Text(text = "local_partida: ${frete.local_partida}")
        }
    }
}
