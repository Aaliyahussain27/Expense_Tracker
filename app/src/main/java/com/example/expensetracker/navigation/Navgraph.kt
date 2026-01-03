package com.example.expensetracker.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.expensetracker.screens.add.AddExpenseScreen
import com.example.expensetracker.screens.home.HomeScreen
import com.example.expensetracker.screens.home.HomeViewModel

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home") {

        composable("home") {
            val homeViewModel: HomeViewModel = viewModel()

            HomeScreen(
                viewModel = homeViewModel,
                onNavigate = {
                navController.navigate("add_expense")
            })
        }

        composable("add_expense") {
            AddExpenseScreen(onCancel = { navController.popBackStack() }
            )
        }
    }
}
