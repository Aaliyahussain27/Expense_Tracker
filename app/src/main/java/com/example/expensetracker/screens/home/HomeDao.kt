package com.example.expensetracker.screens.home

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface HomeDao {

    @Insert
    suspend fun insertTransaction(transaction: HomeEntity)

    @Query("SELECT * FROM home_transactions ORDER BY id DESC")
    fun getAllTransactions(): Flow<List<HomeEntity>>

    @Query("SELECT SUM(CAST(REPLACE(amount,'₹','') AS REAL)) FROM home_transactions WHERE date = :today")
    fun getTodayTotal(today: String): Flow<Double?>

    @Query("SELECT SUM(CAST(REPLACE(amount,'₹','') AS REAL)) FROM home_transactions")
    fun getMonthlyTotal(): Flow<Double?>
}
