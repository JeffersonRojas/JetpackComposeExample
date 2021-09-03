package com.github.jeffersonrojas.mercadolibre.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.github.jeffersonrojas.mercadolibre.R
import com.github.jeffersonrojas.mercadolibre.network.model.Installment
import com.github.jeffersonrojas.mercadolibre.network.model.Product
import com.github.jeffersonrojas.mercadolibre.presentation.ui.theme.MercadolibreTheme
import com.github.jeffersonrojas.mercadolibre.util.toCurrency
import com.github.jeffersonrojas.mercadolibre.util.toHttpsUrl
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.shimmer

internal val placeholderProduct = Product(
    id = "MCO620864876",
    siteId = "MCO",
    title = "Microsoft Xbox Series S 512gb Color  Blanco",
    price = 1489900,
    currencyId = "COP",
    availableQuantity = 369,
    soldQuantity = 515,
    condition = "new",
    permalink = "https://www.mercadolibre.com.co/microsoft-xbox-series-s-512gb-color-blanco/p/MCO16650345",
    thumbnail = "https://http2.mlstatic.com/D_995465-MLA45731835097_042021-I.jpg",
    installments = Installment(
        amount = 41386.11,
        currencyId = "COP",
        quantity = 36,
        rate = 0
    )
)

@Preview
@Composable
private fun Preview() {
    MercadolibreTheme {
        Surface(color = MaterialTheme.colors.background, modifier = Modifier.fillMaxSize()) {
            ProductItem(product = placeholderProduct)
        }
    }

}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun ProductItem(product: Product? = placeholderProduct, onClick: (product: Product) -> Unit = {}) {
    val placeHolder = product == null
    Column(
        Modifier
            .fillMaxWidth()
            .clickable {
                if (product != null) {
                    onClick(product)
                }
            }) {
        Row(Modifier.padding(8.dp)) {
            val thumbnailPainter = rememberImagePainter(
                data = product?.thumbnail?.toHttpsUrl()
            )
            Image(
                painter = thumbnailPainter,
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .clip(shape = RoundedCornerShape(12.dp))
                    .placeholder(
                        visible = placeHolder,
                        highlight = PlaceholderHighlight.shimmer(),
                    ),
                contentScale = ContentScale.Crop,
            )
            Column(modifier = Modifier.padding(start = 8.dp)) {
                Text(
                    text = product?.title ?: placeholderProduct.title,
                    style = MaterialTheme.typography.subtitle1,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.placeholder(
                        visible = placeHolder,
                        highlight = PlaceholderHighlight.shimmer(),
                    ),
                )
                Spacer(modifier = Modifier.size(4.dp))
                Text(
                    text = product?.price?.toCurrency() ?: placeholderProduct.price.toCurrency(),
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.placeholder(
                        visible = placeHolder,
                        highlight = PlaceholderHighlight.shimmer(),
                    ),
                )
                Spacer(modifier = Modifier.size(4.dp))
                val (quantity, amount) = product?.installments ?: placeholderProduct.installments
                Text(
                    text = stringResource(
                        R.string.product_item_installments,
                        quantity.toInt(),
                        amount.toCurrency()
                    ),
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.placeholder(
                        visible = placeHolder,
                        highlight = PlaceholderHighlight.shimmer(),
                    ),
                )
            }
        }
        Divider()
    }
}