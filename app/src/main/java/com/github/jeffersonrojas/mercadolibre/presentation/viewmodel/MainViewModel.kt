package com.github.jeffersonrojas.mercadolibre.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.github.jeffersonrojas.mercadolibre.network.model.Product
import com.github.jeffersonrojas.mercadolibre.repository.ProductDetailRepository
import com.github.jeffersonrojas.mercadolibre.repository.SearchRepository
import kotlinx.coroutines.flow.Flow

class MainViewModel : ViewModel() {

    private val pageSize = 50

    private var lastQuery = ""

    var pager = Pager(PagingConfig(pageSize = pageSize)) {
        SearchRepository(query.value.text, pageSize)
    }.flow.cachedIn(viewModelScope)

    val query = mutableStateOf(TextFieldValue(""))

    fun getProductList(): Flow<PagingData<Product>> {
        return if (query.value.text != lastQuery) {
            lastQuery = query.value.text
            pager = Pager(PagingConfig(pageSize = pageSize)) {
                SearchRepository(query.value.text, pageSize)
            }.flow.cachedIn(viewModelScope)
            pager
        } else {
            pager
        }
    }

    suspend fun getProductDetail(productId: String) = ProductDetailRepository().getProductDetail(productId = productId)
}