package com.frontendmasters.coffeemasters

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class DataManager(app: Application): AndroidViewModel(app) {
    var menu: List<Category> by mutableStateOf(listOf())
    var cart: List<ItemInCart> by mutableStateOf(listOf())

    init {
        fetchData()
    }

    private fun fetchData() {
        try {
            viewModelScope.launch {
                menu = API.menuService.fetchMenu()
            }
        } catch (ex: Exception) {
            //TODO: create other state variable for error
        }
    }

    fun cartAdd(product: Product) {
        var found = false
        cart.forEach {
            if (product.id==it.product.id) {
                it.quantity++
                found = true
            }
        }
        // IMPORTANT: it's a state, we have to change the whole list, not mutate its contents
        if (!found) {
            cart = listOf(*cart.toTypedArray(), ItemInCart(product, 1))
        }
    }

    fun cartRemove(product: Product) {
        val aux = cart.toMutableList()
        aux.removeAll { it.product.id == product.id }
        // IMPORTANT: it's a state, we have to change the whole list, not mutate its contents
        cart = listOf(*aux.toTypedArray())
    }

    fun cartClear() {
        // IMPORTANT: it's a state, we have to change the whole list, not mutate its contents
        cart = listOf()
    }

    fun cartTotal(): Double {
        var sum = 0.0
        for (item in cart) {
            sum += item.quantity*item.product.price
        }
        return sum
    }
}