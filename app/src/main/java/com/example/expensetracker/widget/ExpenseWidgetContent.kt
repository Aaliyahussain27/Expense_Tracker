package com.example.expensetracker.widget

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceModifier
import androidx.glance.action.clickable
import androidx.glance.appwidget.action.actionStartActivity
import androidx.glance.appwidget.cornerRadius
import androidx.glance.background
import androidx.glance.layout.*
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import com.example.expensetracker.MainActivity

@SuppressLint("RestrictedApi")
@Composable
fun ExpenseWidgetContent(
    context: Context,
    todayExpense: Double,
    monthExpense: Double
) {
    Column(
        modifier = GlanceModifier
            .fillMaxSize()
            .background(ColorProvider(Color(0xFF0F1C24)))
            .cornerRadius(24.dp)
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        WalletCard(
            title = "Today's Expense",
            amount = todayExpense,
            stripColor = Color(0xFF1F7388),
            context = context
        )

        Spacer(modifier = GlanceModifier.height(12.dp))

        WalletCard(
            title = "Monthly Expense",
            amount = monthExpense,
            stripColor = Color(0xFF18426E),
            context = context
        )
    }
}

@SuppressLint("RestrictedApi")
@Composable
private fun WalletCard(
    title: String,
    amount: Double,
    stripColor: Color,
    context: Context
) {
    Box(
        modifier = GlanceModifier
            .fillMaxWidth()
            .height(130.dp)
            .background(ColorProvider(Color(0xFF1C2B34)))
            .cornerRadius(20.dp)
            .clickable(
                actionStartActivity(
                    Intent(context, MainActivity::class.java)
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                )
            )
    ) {
        Column {

            // 🔵 Top wallet strip
            Box(
                modifier = GlanceModifier
                    .fillMaxWidth()
                    .height(44.dp)
                    .background(ColorProvider(stripColor))
                    .cornerRadius(20.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = title,
                    style = TextStyle(
                        color = ColorProvider(Color.White),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = GlanceModifier.padding(start = 16.dp)
                )
            }

            Spacer(modifier = GlanceModifier.height(12.dp))

            Column(modifier = GlanceModifier.padding(start = 16.dp)) {
                Text(
                    text = "-₹${"%.0f".format(amount)}",
                    style = TextStyle(
                        color = ColorProvider(Color.White),
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
    }
}
