package com.github.jeffersonrojas.mercadolibre.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.github.jeffersonrojas.mercadolibre.di.NetworkModule
import com.github.jeffersonrojas.mercadolibre.network.model.Product

class SearchRepository(
    private val query: String,
    private val pageSize: Int
) : PagingSource<Int, Product>() {

    override fun getRefreshKey(state: PagingState<Int, Product>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product> {
        return try {
            val offset = params.key ?: 0
            val searchResponse = NetworkModule.provideApi().searchProducts(query, offset = offset, limit = pageSize)
            LoadResult.Page(
                data = searchResponse.results,
                prevKey = if (offset == 0) null else offset - pageSize,
                nextKey = if (searchResponse.results.isEmpty()) null else searchResponse.paging.offset.toInt() + pageSize
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }
}
