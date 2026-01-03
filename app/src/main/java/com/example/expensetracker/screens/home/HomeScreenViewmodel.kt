package com.example.expensetracker.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.map
import com.example.expensetracker.screens.home.HomeRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class HomeViewModel(repository: HomeRepository) : ViewModel() {

    val transactions: StateFlow<List<HomeEntity>> =
        repository.getAllTransactions().stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    val todayTotal: StateFlow<Double> =
        repository.getTodayTotal("10/12/2025")
            .map { it ?: 0.0 }
            .stateIn(viewModelScope, SharingStarted.Lazily, 0.0)

    val monthlyTotal: StateFlow<Double> =
        repository.getMonthlyTotal()
            .map { it ?: 0.0 }
            .stateIn(viewModelScope, SharingStarted.Lazily, 0.0)
}
