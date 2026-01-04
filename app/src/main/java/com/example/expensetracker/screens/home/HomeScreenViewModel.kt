package com.example.expensetracker.screens.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensetracker.screens.expense.ExpenseRepository
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class HomeScreenViewModel(
    private val repository: ExpenseRepository
) : ViewModel() {
    init {
        val range = DateUtils.todayRange()
        Log.d("DATE_DEBUG", "Today start = ${Date(range.first)} (${range.first})")
        Log.d("DATE_DEBUG", "Today end   = ${Date(range.second)} (${range.second})")

        viewModelScope.launch {
            repository.getAllExpenses().collect {
                it.forEach { expense ->
                    Log.d(
                        "EXPENSE_DEBUG",
                        "Expense id=${expense.id}, amount=${expense.amount}, date=${Date(expense.date)} (${expense.date})"
                    )
                }
            }
        }
    }
    init {
        viewModelScope.launch {
            repository.getAllExpenses().collect {
                Log.d("ROOM_TEST", "Expenses from DB: $it")
            }
        }
        viewModelScope.launch {
            repository.categories.collect {
                Log.d("CATEGORY_TEST", it.toString())
            }
        }

    }
    private val todayRange = DateUtils.todayRange()
    private val monthRange = DateUtils.monthRange()
    private val categoryMap: StateFlow<Map<Int, String>> =
        repository.categories
            .map { list ->
                list.associate { it.id to it.name }
            }
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5_000),
                emptyMap()
            )

    val expenses: StateFlow<List<HomeTransactionUiModel>> =
        repository.getAllExpenses()
            .combine(categoryMap) { expenses, categoryMap ->
                expenses.map { expense ->
                    HomeTransactionUiModel(
                        id = expense.id,
                        title = expense.description.ifBlank { "Expense" },
                        amount = "₹${expense.amount}",
                        date = DateUtils.formatDate(expense.date),
                        category = categoryMap[expense.categoryId] ?: "Other"
                    )
                }
            }
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5_000),
                emptyList()
            )


    val todayTotal: StateFlow<Double> =
        repository.getTodayTotal(
            todayRange.first,
            todayRange.second
        )
            .map { it ?: 0.0 }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), 0.0)

    val monthlyTotal: StateFlow<Double> =
        repository.getMonthlyTotal(
            monthRange.first,
            monthRange.second
        )
            .map { it ?: 0.0 }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), 0.0)
}

object DateUtils {

    fun todayRange(): Pair<Long, Long> {
        val cal = Calendar.getInstance()
        cal.timeInMillis = System.currentTimeMillis()

        cal.set(Calendar.HOUR_OF_DAY, 0)
        cal.set(Calendar.MINUTE, 0)
        cal.set(Calendar.SECOND, 0)
        cal.set(Calendar.MILLISECOND, 0)
        val start = cal.timeInMillis

        cal.set(Calendar.HOUR_OF_DAY, 23)
        cal.set(Calendar.MINUTE, 59)
        cal.set(Calendar.SECOND, 59)
        cal.set(Calendar.MILLISECOND, 999)
        val end = cal.timeInMillis

        return start to end
    }


    fun monthRange(): Pair<Long, Long> {
        val cal = Calendar.getInstance()
        cal.set(Calendar.DAY_OF_MONTH, 1)
        cal.set(Calendar.HOUR_OF_DAY, 0)
        val start = cal.timeInMillis

        cal.add(Calendar.MONTH, 1)
        cal.set(Calendar.DAY_OF_MONTH, 1)
        cal.add(Calendar.MILLISECOND, -1)
        val end = cal.timeInMillis

        return start to end
    }

    fun formatDate(millis: Long): String =
        SimpleDateFormat("dd MMM", Locale.getDefault())
            .format(Date(millis))
}
