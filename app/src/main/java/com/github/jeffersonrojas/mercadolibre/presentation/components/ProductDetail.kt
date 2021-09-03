package com.github.jeffersonrojas.mercadolibre.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.github.jeffersonrojas.mercadolibre.R
import com.github.jeffersonrojas.mercadolibre.network.model.ProductDetail
import com.github.jeffersonrojas.mercadolibre.presentation.viewmodel.MainViewModel
import com.github.jeffersonrojas.mercadolibre.util.toCurrency


@OptIn(ExperimentalCoilApi::class)
@Composable
fun ProductDetail(productId: String, viewModel: MainViewModel, onBack : () -> Unit) {

    val productDetail = produceState<Pair<ProductDetail?, Exception?>?>(initialValue = null) {
        value = viewModel.getProductDetail(productId)
    }.value?.first

    Column(Modifier.padding(8.dp)) {
        Row {
            IconButton(onClick = { onBack() }) {
                Icon(
                    Icons.Default.ArrowBack,
                    contentDescription = "",
                    modifier = Modifier
                        .padding(15.dp)
                        .size(48.dp)
                )
            }
            Text(
                text = productDetail?.title ?: placeholderProduct.title,
                style = MaterialTheme.typography.h6,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier,
            )
        }
        Spacer(modifier = Modifier.size(8.dp))
        val thumbnailPainter = rememberImagePainter(
            data = productDetail?.pictures?.first()?.secureUrl
        )
        Image(
            painter = thumbnailPainter,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.3f)
                .clip(shape = RoundedCornerShape(12.dp)),
            contentScale = ContentScale.FillHeight,
        )

        Spacer(modifier = Modifier.size(4.dp))
        Text(
            text = productDetail?.price?.toCurrency() ?: placeholderProduct.price.toCurrency(),
            style = MaterialTheme.typography.h6,
            modifier = Modifier,
        )
        Spacer(modifier = Modifier.size(4.dp))
        val (quantity, amount) = productDetail?.installments ?: placeholderProduct.installments
        Text(
            text = stringResource(
                R.string.product_item_installments,
                quantity.toInt(),
                amount.toCurrency()
            ),
            style = MaterialTheme.typography.caption,
            modifier = Modifier,
        )
        Text(
            text = stringResource(
                R.string.product_detail_available_qty,
                productDetail?.availableQuantity?.toInt() ?: 0,
                productDetail?.soldQuantity?.toInt() ?: 0
            ),
            style = MaterialTheme.typography.body1,
            modifier = Modifier,
        )
    }
}