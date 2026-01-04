package com.example.expensetracker.screens.expense

import kotlinx.coroutines.flow.Flow

class ExpenseRepository(
    private val expenseDao: ExpenseDao,
    private val categoryDao: CategoryDao
) {
    val expenses = expenseDao.getAllExpenses()
    val categories: Flow<List<CategoryEntity>> =
        categoryDao.getCategories()

    suspend fun addExpense(expense: ExpenseEntity) {
        expenseDao.insertExpense(expense)
    }

    fun getAllExpenses(): Flow<List<ExpenseEntity>> =
        expenseDao.getAllExpenses()

    fun getTodayTotal(startOfDay: Long, endOfDay: Long): Flow<Double?> =
        expenseDao.getTodayTotal(startOfDay, endOfDay)

    fun getMonthlyTotal(startOfMonth: Long, endOfMonth: Long): Flow<Double?> =
        expenseDao.getMonthlyTotal(startOfMonth, endOfMonth)

}
