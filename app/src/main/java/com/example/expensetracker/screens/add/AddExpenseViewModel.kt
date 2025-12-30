package com.example.expensetracker.screens.add

import android.content.Context
import androidx.glance.appwidget.updateAll
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensetracker.widget.ExpenseWidget
import kotlinx.coroutines.launch

class AddExpenseViewModel(
    private val repository: ExpenseRepository,
    private val context: Context
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

            // Update widget directly
            ExpenseWidget().updateAll(context)
        }
    }
}

private fun dateStringToMillis(date: String): Long {
    val formatter = java.text.SimpleDateFormat("dd/MM/yyyy", java.util.Locale.getDefault())
    return formatter.parse(date)?.time ?: System.currentTimeMillis()
}