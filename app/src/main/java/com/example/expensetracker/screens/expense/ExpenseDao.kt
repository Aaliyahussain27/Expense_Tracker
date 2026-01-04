package com.example.expensetracker.screens.expense

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExpense(expense: ExpenseEntity)

    @Query("SELECT * FROM expenses ORDER BY date DESC")
    fun getAllExpenses(): Flow<List<ExpenseEntity>>

    @Query("""
    SELECT SUM(amount)
    FROM expenses
    WHERE `date` BETWEEN :startOfDay AND :endOfDay
""")
    fun getTodayTotal(startOfDay: Long, endOfDay: Long): Flow<Double?>

    @Query("""
    SELECT SUM(amount)
    FROM expenses
    WHERE `date` BETWEEN :startOfMonth AND :endOfMonth
""")
    fun getMonthlyTotal(startOfMonth: Long, endOfMonth: Long): Flow<Double?>


    @Query("""
    SELECT SUM(amount) FROM expenses 
    WHERE date BETWEEN :start AND :end
""")
    suspend fun getTodayTotalOnce(start: Long, end: Long): Double?

    @Query("""
    SELECT SUM(amount) FROM expenses 
    WHERE date BETWEEN :start AND :end
""")
    suspend fun getMonthlyTotalOnce(start: Long, end: Long): Double?

}
