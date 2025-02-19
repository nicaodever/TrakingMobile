package com.example.fretezon.models

data class Frete(
    val data_entrega: String = "",
    val descricao_carga: String = "",
    val destino: String = "",
    val foto_carga: String = "",
    val horario_retir: String = "",
    val id: Int = 0,
    val local_partida: String = "",
    val sugerir_tarifa: Double = 0.0,
    val tp_veiculo: String = ""
)