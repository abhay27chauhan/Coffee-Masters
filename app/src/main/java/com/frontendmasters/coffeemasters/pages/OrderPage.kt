package com.frontendmasters.coffeemasters.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.frontendmasters.coffeemasters.DataManager
import com.frontendmasters.coffeemasters.Product
import com.frontendmasters.coffeemasters.ui.theme.Alternative1
import com.frontendmasters.coffeemasters.ui.theme.CardBackground

@Composable
fun OrderPage(dataManager: DataManager) {
    LazyColumn {
        if(dataManager.cart.count() == 0){
            item {
                Card(
                    elevation = 2.dp,
                    modifier = Modifier
                        .padding(12.dp)
                        .fillMaxWidth()
                ) {
                    Text(text = "Your cart is empty",
                    style = MaterialTheme.typography.h4)
                }
            }
        }
        items(dataManager.cart) {
            Card(
                elevation = 2.dp,
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .background(CardBackground)
                    .padding(12.dp)
            ) {
                CartItem(it.product, it.quantity, onRemove = {
                    dataManager.cartRemove(it)
                })
            }
        }
    }
}


@Composable
fun CartItem(product: Product, quantity: Int, onRemove: (Product)->Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Column {
                Text(product.name, fontWeight = FontWeight.Bold)
                Text("$${product.price.format(2)} ea")
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxHeight()
            ) {
                Text("quantity: $quantity", textAlign = TextAlign.Center, fontWeight = FontWeight.Bold)
            }
            Button(
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Alternative1,
                    contentColor = Color.White
                ),
                onClick = {
                    onRemove(product)
                },
            ) {
                Text("Remove")
            }
        }
    }
}