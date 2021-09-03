package com.github.jeffersonrojas.mercadolibre.util

import android.net.Uri

fun String.toHttpsUrl() = Uri.parse(this).buildUpon().scheme("https").build().toString()