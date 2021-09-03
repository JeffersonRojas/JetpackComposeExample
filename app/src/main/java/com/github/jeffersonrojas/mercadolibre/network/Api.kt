package com.github.jeffersonrojas.mercadolibre.network

import com.github.jeffersonrojas.mercadolibre.network.model.ProductDetail
import com.github.jeffersonrojas.mercadolibre.network.response.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("sites/MCO/search")
    suspend fun searchProducts(
        @Query("q") query: String,
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = 50,
    ) : SearchResponse

    @GET("items/{productId}")
    suspend fun getProductDetail(
        @Path("productId") productId: String,
    ) : ProductDetail
}