package com.example.expensetracker.screens.home

import kotlinx.coroutines.flow.Flow

class HomeRepository(private val dao: HomeDao) {

    fun getAllTransactions(): Flow<List<HomeEntity>> = dao.getAllTransactions()

    fun getTodayTotal(today: String): Flow<Double?> = dao.getTodayTotal(today)

    fun getMonthlyTotal(): Flow<Double?> = dao.getMonthlyTotal()

    suspend fun addTransaction(transaction: HomeEntity) = dao.insertTransaction(transaction)
}
