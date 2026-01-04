package com.example.expensetracker.widget

import android.content.Context
import com.example.expensetracker.data.database.ExpenseDatabase
import com.example.expensetracker.screens.home.DateUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object WidgetDataProvider {

    suspend fun getTodayTotal(context: Context): Double {
        val db = ExpenseDatabase.getDatabase(context)
        val (start, end) = DateUtils.todayRange()
        return db.expenseDao()
            .getTodayTotalOnce(start, end) ?: 0.0
    }

    suspend fun getMonthlyTotal(context: Context): Double {
        val db = ExpenseDatabase.getDatabase(context)
        val (start, end) = DateUtils.monthRange()
        return db.expenseDao()
            .getMonthlyTotalOnce(start, end) ?: 0.0
    }
}
