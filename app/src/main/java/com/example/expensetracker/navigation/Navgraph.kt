package com.example.expensetracker.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.expensetracker.screens.add.AddExpenseScreen
import com.example.expensetracker.screens.home.HomeScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home") {

        composable("home") {
            HomeScreen(onNavigate = {
                navController.navigate("add_expense")
            })
        }

        composable("add_expense") {
            AddExpenseScreen(onCancel = { navController.popBackStack() }
            )
        }
    }
}
