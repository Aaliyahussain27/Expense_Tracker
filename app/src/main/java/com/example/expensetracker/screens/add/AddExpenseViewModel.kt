package com.example.expensetracker.screens.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensetracker.screens.add.ExpenseEntity
import com.example.expensetracker.screens.add.ExpenseRepository
import kotlinx.coroutines.launch

class AddExpenseViewModel(
    private val repository: ExpenseRepository
) : ViewModel() {

    fun saveExpense(
        amount: String,
        category: String,
        description: String,
        date: String
    ) {
        if (amount.isBlank()) return

        val expense = ExpenseEntity(
            amount = amount.toDouble(),
            category = category,
            description = description,
            date = dateStringToMillis(date)
        )

        viewModelScope.launch {
            repository.addExpense(expense)
        }
    }
}

private fun dateStringToMillis(date: String): Long {
    val formatter = java.text.SimpleDateFormat("dd/MM/yyyy", java.util.Locale.getDefault())
    return formatter.parse(date)?.time ?: System.currentTimeMillis()
}

