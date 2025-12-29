package com.example.expensetracker.screens.home

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "home_transactions")
data class HomeEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val amount: String,
    val date: String,
    val iconName: String // We'll store icon type as string for mapping in UI
)
