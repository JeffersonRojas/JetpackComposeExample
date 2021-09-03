package com.github.jeffersonrojas.mercadolibre.network.response

import com.github.jeffersonrojas.mercadolibre.network.model.Product

data class SearchResponse(
    val siteId: String,
    val query: String,
    val paging: Paging,
    val results: List<Product>
)

data class Paging(
    val total: Number,
    val primaryResults: Number,
    val offset: Number,
    val limit: Number,
)