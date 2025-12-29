package com.example.expensetracker.screens.add

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * ExpenseEntity - Database table ka structure
 * Ye define karta hai ki expense mein kya kya data store hoga
 */
@Entity(tableName = "expenses")
data class ExpenseEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,                    // Auto-generated unique ID

    val amount: Double,                  // Expense amount (₹740)
    val category: String,                // Category name ("Food", "Transport", etc.)
    val description: String,             // Optional description
    val date: Long,                      // Date in milliseconds (timestamp)
    val createdAt: Long = System.currentTimeMillis()  // Kab create hua
)