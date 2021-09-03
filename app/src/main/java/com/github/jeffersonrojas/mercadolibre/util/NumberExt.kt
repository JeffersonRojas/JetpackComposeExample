package com.github.jeffersonrojas.mercadolibre.util

import java.text.NumberFormat
import java.util.*

fun Number.toCurrency(): String {
    val format = NumberFormat.getCurrencyInstance(Locale("es", "CO"))
    format.maximumFractionDigits = 0
    return format.format(toDouble())
}