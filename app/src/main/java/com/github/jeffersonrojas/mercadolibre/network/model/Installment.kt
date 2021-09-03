package com.github.jeffersonrojas.mercadolibre.network.model

data class Installment(
    val quantity: Number,
    val amount: Number,
    val rate: Number,
    val currencyId: String,
)