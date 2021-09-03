package com.github.jeffersonrojas.mercadolibre.presentation.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.github.jeffersonrojas.mercadolibre.network.model.Product
import kotlinx.coroutines.flow.Flow

@Composable
fun SearchProductsList(productsList: Flow<PagingData<Product>>, onClick: (product: Product) -> Unit) {
    val productsListItems: LazyPagingItems<Product> = productsList.collectAsLazyPagingItems()

    LazyColumn {
        items(productsListItems) { item ->
            ProductItem(item, onClick)
        }
        productsListItems.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    items(12) {
                        ProductItem(null)
                    }
                }
                loadState.append is LoadState.Loading -> {
                    items(1) {
                        ProductItem(null)
                    }
                }
                loadState.append is LoadState.Error -> {
                    // TODO: Show Error
                }
            }
        }
    }
}