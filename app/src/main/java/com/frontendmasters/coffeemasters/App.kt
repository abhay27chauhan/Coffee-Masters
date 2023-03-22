package com.frontendmasters.coffeemasters

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.frontendmasters.coffeemasters.pages.InfoPage
import com.frontendmasters.coffeemasters.pages.MenuPage
import com.frontendmasters.coffeemasters.pages.OffersPage
import com.frontendmasters.coffeemasters.pages.OrderPage
import com.frontendmasters.coffeemasters.ui.theme.CoffeeMastersTheme


@Composable
fun AppTitle() {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Coffee Masters Logo"
        )
    }
}

@Composable
fun App(dataManager: DataManager){
    AppContent(dataManager)
}

@Composable
fun AppContent(dataManager: DataManager) {
    val selectedRoute = remember {
        mutableStateOf(Routes.MenuPage.route)
    }
    Scaffold(
        topBar = { TopAppBar {
            AppTitle()
        }},
        content = {
            when (selectedRoute.value) {
                Routes.MenuPage.route -> MenuPage(dataManager)
                Routes.OffersPage.route -> OffersPage()
                Routes.OrderPage.route -> OrderPage(dataManager)
                Routes.InfoPage.route -> InfoPage()
            }
        },
        bottomBar = {
            NavBar(
                selectedRoute = selectedRoute.value,
                onChange = {
                    selectedRoute.value = it
                })
        }
    )
}