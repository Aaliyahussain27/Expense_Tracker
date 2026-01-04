package com.example.expensetracker.screens.expense

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.glance.appwidget.updateAll
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensetracker.widget.ExpenseWidget
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class ExpenseViewModel(
    application: Application,
    private val repository: ExpenseRepository
) : ViewModel() {
    private val context = application.applicationContext
    val categories: StateFlow<List<CategoryEntity>> = repository.categories
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    fun saveExpense(
        amount: Double,
        categoryId: Int,
        description: String,
        date: String
    ) {
        val expense = ExpenseEntity(
            amount = amount,
            categoryId = categoryId,
            description = description,
            date = dateStringToMillis(date)
        )

        viewModelScope.launch {
            repository.addExpense(expense)

            // Update widget
            ExpenseWidget().updateAll(context)
        }
        Log.d("DATE_DEBUG", "Stored date = ${Date(expense.date)}")
    }
}

private fun dateStringToMillis(date: String): Long {
    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    val parsedDate = formatter.parse(date) ?: return System.currentTimeMillis()

    val cal = Calendar.getInstance()
    cal.time = parsedDate
    cal.set(Calendar.HOUR_OF_DAY, 0)
    cal.set(Calendar.MINUTE, 0)
    cal.set(Calendar.SECOND, 0)
    cal.set(Calendar.MILLISECOND, 0)

    return cal.timeInMillis
}

