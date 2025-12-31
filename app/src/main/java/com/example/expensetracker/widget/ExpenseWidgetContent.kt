package com.example.expensetracker.widget

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
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
import com.example.expensetracker.R
import java.text.NumberFormat
import java.util.Locale
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
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        WalletCard(
            title = "Today's Expense",
            amount = todayExpense,
            gradientDrawable = R.drawable.gradient_expense,
            context = context
        )

        Spacer(modifier = GlanceModifier.height(16.dp))

        WalletCard(
            title = "This Month's Expense",
            amount = monthExpense,
            gradientDrawable = R.drawable.gradient_expense,
            context = context
        )
    }
}

@SuppressLint("RestrictedApi")
@Composable
private fun WalletCard(
    title: String,
    amount: Double,
    gradientDrawable: Int,
    context: Context
) {
    val formatter = NumberFormat.getNumberInstance(Locale("en", "IN"))

    // Outer Box: stroke background
    Box(
        modifier = GlanceModifier
            .fillMaxWidth()
            .background(ImageProvider(R.drawable.widget_card_stroke))
            .padding(2.dp)
    ) {
        // Inner Box: main card content
        Box(
            modifier = GlanceModifier
                .fillMaxWidth()
                .cornerRadius(18.dp) // slightly smaller than outer stroke corners
                .background(ColorProvider(Color(0xFF1C2B34)))
                .clickable(
                    actionStartActivity(
                        Intent(context, MainActivity::class.java)
                            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    )
                )
        ) {
            Column(
                modifier = GlanceModifier.fillMaxWidth()
            ) {
                // Top colored strip with gradient
                Box(
                    modifier = GlanceModifier
                        .fillMaxWidth()
                        .height(42.dp)
                        .background(ImageProvider(gradientDrawable)),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Row(
                        modifier = GlanceModifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = title,
                            style = TextStyle(
                                color = ColorProvider(Color.White),
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                }

                // Amount section
                Box(
                    modifier = GlanceModifier
                        .fillMaxWidth()
                        .background(ImageProvider(R.drawable.gradient_amount))
                        .padding(start = 16.dp, top = 16.dp, bottom = 16.dp, end = 16.dp)
                ) {
                    Text(
                        text = "₹${formatter.format(amount)}",
                        style = TextStyle(
                            color = ColorProvider(Color.White),
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }
        }
    }
}
