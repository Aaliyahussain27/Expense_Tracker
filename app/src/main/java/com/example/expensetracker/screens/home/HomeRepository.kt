package com.example.expensetracker.screens.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Receipt
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.ShoppingCart
import com.example.expensetracker.screens.expense.ExpenseDao
import com.example.expensetracker.screens.home.DateUtils.formatDate
import kotlinx.coroutines.flow.map

class HomeRepository(
    private val expenseDao: ExpenseDao
) {

    fun getAllTransactions() =
        expenseDao.getAllExpenses()
            .map { list ->
                list.map { expense ->
                    HomeTransactionUiModel(
                        id = expense.id,
                        title = expense.description,
                        date = formatDate(expense.date),
                        amount = "-₹${expense.amount}",
                        category = expense.categoryId.toString()
                    )
                }
            }

    private fun categoryToIcon(categoryId: Int) = when (categoryId) {
        1 -> Icons.Default.Restaurant
        2 -> Icons.Default.DirectionsCar
        3 -> Icons.Default.ShoppingCart
        4 -> Icons.Default.Phone
        else -> Icons.Default.Receipt
    }
}
