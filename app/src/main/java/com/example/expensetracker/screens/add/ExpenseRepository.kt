package com.example.expensetracker.screens.add

import com.example.expensetracker.screens.add.ExpenseDao
import com.example.expensetracker.screens.add.ExpenseEntity

class ExpenseRepository(
    private val dao: ExpenseDao
) {

    suspend fun addExpense(expense: ExpenseEntity) {
        dao.insertExpense(expense)
    }

    fun getAllExpenses() = dao.getAllExpenses()

    fun getTodayTotal(today: String) = dao.getTodayTotal(today)

    fun getMonthlyTotal() = dao.getMonthlyTotal()
}
