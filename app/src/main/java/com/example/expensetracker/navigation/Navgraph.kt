package com.example.expensetracker.navigation

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.expensetracker.data.database.ExpenseDatabase
import com.example.expensetracker.screens.expense.AddExpenseScreen
import com.example.expensetracker.screens.expense.ExpenseRepository
import com.example.expensetracker.screens.expense.ExpenseViewModel
import com.example.expensetracker.screens.expense.ExpenseViewModelFactory
import com.example.expensetracker.screens.home.HomeScreen
import com.example.expensetracker.screens.home.HomeScreenViewModel
import com.example.expensetracker.screens.home.HomeViewModelFactory

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home") {

        composable("home") {
            val context = LocalContext.current
            val db = ExpenseDatabase.getDatabase(context)
            val repository = ExpenseRepository(db.expenseDao(), db.categoryDao())

            val viewModel: HomeScreenViewModel = viewModel(factory = HomeViewModelFactory(repository))
            HomeScreen(
                viewModel = viewModel,
                onNavigate = { navController.navigate("add_expense") }
            )
        }
        composable("add_expense") {
            val context = LocalContext.current
            val application = context.applicationContext as Application

            val db = ExpenseDatabase.getDatabase(context)
            val repository = ExpenseRepository(
                db.expenseDao(),
                db.categoryDao()
            )

            val expenseViewModel: ExpenseViewModel = viewModel(
                factory = ExpenseViewModelFactory(application, repository)
            )
            AddExpenseScreen(
                viewModel = viewModel(),
                onCancel = { navController.popBackStack() }
            )
        }
    }
}
