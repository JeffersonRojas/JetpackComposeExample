package com.github.jeffersonrojas.mercadolibre.repository

import com.github.jeffersonrojas.mercadolibre.di.NetworkModule
import com.github.jeffersonrojas.mercadolibre.network.Api
import com.github.jeffersonrojas.mercadolibre.network.model.ProductDetail

class ProductDetailRepository(
    private val api: Api = NetworkModule.provideApi()
) {

    suspend fun getProductDetail(productId: String): Pair<ProductDetail?, Exception?> {
        return try {
            Pair(api.getProductDetail(productId), null)
        } catch(e: Exception) {
             Pair(null, e)
        }
    }
}