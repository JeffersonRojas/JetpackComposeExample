package com.github.jeffersonrojas.mercadolibre.network.model

data class ProductDetail(
    val id: String,
    val siteId: String,
    val title: String,
    val price: Number,
    val currencyId: String,
    val availableQuantity: Number,
    val soldQuantity: Number,
    val condition: String,
    val installments: Installment,
    val pictures: List<Image>,
    val warranty: String,
) {
    data class Image(
        val id: String,
        val url: String,
        val secureUrl: String,
    )
}

