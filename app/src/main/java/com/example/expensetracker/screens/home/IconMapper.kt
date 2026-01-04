package com.example.expensetracker.screens.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

fun mapCategoryToIcon(category: String): ImageVector {
    return when (category.lowercase()) {
        "food" -> Icons.Default.Restaurant
        "transport" -> Icons.Default.DirectionsCar
        "shopping" -> Icons.Default.ShoppingCart
        "bills" -> Icons.Default.Receipt
        "health" -> Icons.Default.Favorite
        "fun" -> Icons.Default.Movie
        else -> Icons.Default.AttachMoney
    }
}
