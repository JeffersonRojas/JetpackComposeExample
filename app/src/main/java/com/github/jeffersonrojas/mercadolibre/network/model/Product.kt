package com.github.jeffersonrojas.mercadolibre.network.model

data class Product(
    val id: String,
    val siteId: String,
    val title: String,
    val price: Number,
    val currencyId: String,
    val availableQuantity: Number,
    val soldQuantity: Number,
    val condition: String,
    val permalink: String,
    val thumbnail: String,
    val installments: Installment,
)