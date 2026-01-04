package com.example.expensetracker.widget

import android.content.Context
import androidx.glance.GlanceId
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.provideContent

class ExpenseWidget : GlanceAppWidget() {

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        val todayTotal = WidgetDataProvider.getTodayTotal(context)
        val monthTotal = WidgetDataProvider.getMonthlyTotal(context)

        provideContent {
            ExpenseWidgetContent(
                context = context,
                todayExpense = todayTotal,
                monthExpense = monthTotal
            )
        }
    }
}

