package com.example.expensetracker.screens.home

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface HomeDao {

    //transactions
    @Insert
    suspend fun insertTransaction(transaction: HomeEntity)

    @Query("SELECT * FROM home_transactions ORDER BY id DESC")
    fun getAllTransactions(): Flow<List<HomeEntity>>

    @Query("SELECT SUM(CAST(REPLACE(amount,'₹','') AS REAL)) FROM home_transactions WHERE date = :today")
    fun getTodayTotal(today: String): Flow<Double?>

    @Query("SELECT SUM(CAST(REPLACE(amount,'₹','') AS REAL)) FROM home_transactions")
    fun getMonthlyTotal(): Flow<Double?>

    //budget
    @Query("SELECT amount FROM budget WHERE id = 1")
    fun getBudget(): Flow<Int?>

    @Insert
    suspend fun insertBudget(budget: BudgetEntity)

    @Query("UPDATE budget SET amount = :amount WHERE id = 1")
    suspend fun updateBudget(amount: Int)

}
