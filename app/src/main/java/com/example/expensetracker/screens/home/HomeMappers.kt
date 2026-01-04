package com.example.expensetracker.screens.home

import com.example.expensetracker.screens.expense.ExpenseEntity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun ExpenseEntity.toHomeUiModel(categoryName: String): HomeTransactionUiModel {
    return HomeTransactionUiModel(
        id = id,
        title = categoryName,
        amount = "₹$amount",
        date = SimpleDateFormat("dd MMM", Locale.getDefault())
            .format(Date(date)),
        category = categoryName
    )
}
