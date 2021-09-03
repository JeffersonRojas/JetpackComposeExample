package com.github.jeffersonrojas.mercadolibre.presentation.navigation

import androidx.navigation.NavType
import androidx.navigation.compose.NamedNavArgument
import androidx.navigation.compose.navArgument

const val itemIdParameterName = "itemId"


sealed class Screen(val route: String, val arguments: List<NamedNavArgument> = emptyList()) {
    object Search : Screen("search")
    object ProductDetail : Screen(
        route = "item/{$itemIdParameterName}",
        arguments = listOf(navArgument(itemIdParameterName) { type = NavType.StringType })
    )
}