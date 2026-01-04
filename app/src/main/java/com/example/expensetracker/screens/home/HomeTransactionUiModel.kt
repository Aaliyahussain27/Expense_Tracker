package com.example.expensetracker.screens.home

import androidx.compose.ui.graphics.vector.ImageVector

data class HomeTransactionUiModel(
    val id: Int,
    val title: String,
    val amount: String,
    val date: String,
    val category: String
)
