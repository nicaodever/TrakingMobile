package com.example.fretezon.models


data class Frete(
    val tp_veiculo: String = "",
    val local_partida: String = "",
    val destino: String = "",
    val foto_carga: String = "",
    val data_entrega: String = "",
    val horario_retir: String = "",
    val descricao_carga: String = ""

)