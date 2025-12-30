package com.example.expensetracker.widget

import android.content.Context
import androidx.glance.GlanceId
import androidx.glance.GlanceTheme
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.provideContent
import androidx.room.Room
import com.example.expensetracker.screens.add.ExpenseDatabase
import kotlinx.coroutines.flow.first
import java.text.SimpleDateFormat
import java.util.*

class ExpenseWidget : GlanceAppWidget() {

    override suspend fun provideGlance(context: Context, id: GlanceId) {

        val database = Room.databaseBuilder(
            context.applicationContext,
            ExpenseDatabase::class.java,
            "expense_database"
        ).build()

        val dao = database.expenseDao()

        val today = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            .format(Date())

        val todayTotal = dao.getTodayTotal(today).first() ?: 0.0
        val monthTotal = dao.getMonthlyTotal().first() ?: 0.0

        provideContent {
            GlanceTheme {
                ExpenseWidgetContent(
                    context = context,
                    todayExpense = todayTotal,
                    monthExpense = monthTotal
                )
            }
        }
    }
}
