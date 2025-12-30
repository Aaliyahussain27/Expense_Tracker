package com.example.expensetracker.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// -------------------- Colors --------------------
val Background = Color(0xFF0F1C24)
val CardBackground = Color(0xFF1C2B34)
val AccentCyan = Color(0xFF1EB1FC)
val TextGray = Color(0xFF8A9BA8)
val TextWhite = Color.White
val GradientBlue1 = Color(0xFF1EB1FC)
val GradientBlue2 = Color(0xFF0A84FF)

// -------------------- Home Screen --------------------
@Composable
fun HomeScreen(onNavigate: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 60.dp) // space for button
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            WelcomeSection()
            Spacer(modifier = Modifier.height(24.dp))
            ExpenseSummarySection()
            Spacer(modifier = Modifier.height(24.dp))
            RecentTransactionsSection()
        }

        Button(
            onClick = onNavigate,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF1193D4)
            ),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
        ) {
            Text("Add Expense")
        }
    }
}

// -------------------- Welcome Section --------------------
@Composable
fun WelcomeSection() {
    Column {
        Text(
            text = "WELCOME BACK",
            color = TextGray,
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "Jennifer",
            color = TextWhite,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Cursive
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Budget Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.Transparent),
            shape = RoundedCornerShape(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(GradientBlue1, GradientBlue2)
                        )
                    )
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Total Budget",
                            color = TextWhite,
                            fontSize = 14.sp
                        )
                        Text(
                            text = "₹10,000.00",
                            color = TextWhite,
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Button(
                        onClick = { /* TODO */ },
                        colors = ButtonDefaults.buttonColors(containerColor = TextWhite),
                        shape = RoundedCornerShape(8.dp),
                        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = null,
                            tint = GradientBlue2,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Add Money",
                            color = GradientBlue2,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

// -------------------- Expense Section --------------------
@Composable
fun ExpenseSummarySection() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        ExpenseCard(
            title = "Today's Expense",
            amount = "-₹740",
            icon = Icons.Default.DateRange,
            modifier = Modifier.weight(1f)
        )
        ExpenseCard(
            title = "Monthly Expense",
            amount = "-₹5,540",
            icon = Icons.Default.DateRange,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun ExpenseCard(
    title: String,
    amount: String,
    icon: ImageVector,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = CardBackground),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = AccentCyan,
                modifier = Modifier.size(24.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = title,
                color = TextGray,
                fontSize = 12.sp
            )
            Text(
                text = amount,
                color = TextWhite,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

// -------------------- Transactions Section --------------------
@Composable
fun RecentTransactionsSection() {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Recent Transactions",
                color = TextWhite,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxHeight()
        ) {
            items(getDummyTransactions()) { transaction ->
                TransactionItem(transaction)
            }
        }
    }
}

@Composable
fun TransactionItem(transaction: Transaction) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = CardBackground),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF1C2932)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = transaction.icon,
                    contentDescription = null,
                    tint = TextWhite,
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = transaction.title,
                    color = TextWhite,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = transaction.date,
                    color = TextGray,
                    fontSize = 12.sp
                )
            }

            Text(
                text = transaction.amount,
                color = TextWhite,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

// -------------------- Dummy Data --------------------
data class Transaction(
    val title: String,
    val date: String,
    val amount: String,
    val icon: ImageVector
)

fun getDummyTransactions(): List<Transaction> {
    return listOf(
        Transaction("Lunch", "Friday 8:09am", "-₹740", Icons.Default.Face),
        Transaction("Uber", "Wednesday 8:09am", "-₹340", Icons.Default.Place),
        Transaction("Shopping", "Monday 8:09am", "-₹700", Icons.Default.ShoppingCart),
        Transaction("Phone Bill", "Monday 8:09am", "-₹299", Icons.Default.Phone),
        Transaction("Health", "Sunday 8:09am", "-₹550", Icons.Default.Favorite)
    )
}