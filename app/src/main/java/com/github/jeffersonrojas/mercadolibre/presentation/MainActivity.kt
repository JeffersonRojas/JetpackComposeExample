package com.github.jeffersonrojas.mercadolibre.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.jeffersonrojas.mercadolibre.presentation.components.ProductDetail
import com.github.jeffersonrojas.mercadolibre.presentation.components.SearchInput
import com.github.jeffersonrojas.mercadolibre.presentation.components.SearchProductsList
import com.github.jeffersonrojas.mercadolibre.presentation.navigation.Screen
import com.github.jeffersonrojas.mercadolibre.presentation.navigation.itemIdParameterName
import com.github.jeffersonrojas.mercadolibre.presentation.ui.theme.MercadolibreTheme
import com.github.jeffersonrojas.mercadolibre.presentation.viewmodel.MainViewModel


class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MercadolibreTheme {
                val navController = rememberNavController()
                Scaffold { innerPadding ->
                    NavHost(navController, startDestination = Screen.Search.route, Modifier.padding(innerPadding)) {
                        composable(Screen.Search.route) {
                            Column {
                                SearchInput(state = mainViewModel.query)
                                SearchProductsList(productsList = mainViewModel.getProductList()) {
                                    navController.navigate("item/${it.id}")
                                }
                            }
                        }
                        composable(Screen.ProductDetail.route, Screen.ProductDetail.arguments) {
                            val productId = it.arguments?.getString(itemIdParameterName)!!
                            ProductDetail(productId = productId, viewModel = mainViewModel) {
                                navController.popBackStack()
                            }
                        }
                    }
                }
            }
        }
    }
}
