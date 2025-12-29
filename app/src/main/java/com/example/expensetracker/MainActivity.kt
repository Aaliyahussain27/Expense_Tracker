package com.example.expensetracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.expensetracker.screens.home.HomeScreen
 import com.example.expensetracker.screens.add.AddExpenseScreen
import com.example.expensetracker.ui.theme.ExpenseTrackerTheme

/**
 * MainActivity - App ka entry point
 * Yahan se app start hoti hai
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // setContent = Screen set karna
        setContent {
            // Theme wrap - App ka theme apply hota hai
            ExpenseTrackerTheme {
                // Surface = Background container
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {


                    HomeScreen()
//                     AddExpenseScreen()
                }
            }
        }
    }
}