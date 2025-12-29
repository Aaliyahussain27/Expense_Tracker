package com.example.expensetracker.screens.home

// ═══════════════════════════════════════════════════════════════
// IMPORTS - Ye saare libraries hume UI banane ke liye chahiye
// ═══════════════════════════════════════════════════════════════

// Layout aur background ke liye
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

// Shapes aur styling ke liye
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape

// Icons ke liye - Har icon ko individually import karna padta hai
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.ShoppingCart

// Material Design 3 components
import androidx.compose.material3.*

// State management ke liye
import androidx.compose.runtime.Composable

// UI arrangement ke liye
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip

// Colors aur gradients ke liye
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

// Text styling ke liye
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Apne custom colors import kar rahe hain (Figma wale)
import com.example.expensetracker.ui.theme.*

@Composable
fun HomeScreen() {
    // Column = Vertical arrangement (upar se neeche)
    Column(
        modifier = Modifier
            .fillMaxSize()              // Puri screen ka size le lo
            .background(AppBackground)  // Dark background color (Figma: #101C22)
            .padding(16.dp)             // Charo taraf se 16dp space
    ) {
        // Welcome Section - User ka naam aur budget card
        WelcomeSection()

        // Vertical space - 24dp
        Spacer(modifier = Modifier.height(24.dp))

        // Expense Summary - Today's aur Monthly cards
        ExpenseSummarySection()

        // Vertical space - 24dp
        Spacer(modifier = Modifier.height(24.dp))

        // Recent Transactions - Scrollable list
        RecentTransactionsSection()
    }
}

// ═══════════════════════════════════════════════════════════════
// WELCOME SECTION
// ═══════════════════════════════════════════════════════════════
@Composable
fun WelcomeSection() {
    Column {
        // "WELCOME BACK" - Small gray text
        Text(
            text = "WELCOME BACK",
            color = TextGray,           // Light gray color
            fontSize = 12.sp,           // Chhota font size
            fontWeight = FontWeight.Normal
        )

        // 4dp vertical space
        Spacer(modifier = Modifier.height(4.dp))

        // User name - Large stylish text
        Text(
            text = "Jennifer",
            color = TextWhite,          // White color
            fontSize = 32.sp,           // Bada font size
            fontWeight = FontWeight.Bold,
            fontFamily = androidx.compose.ui.text.font.FontFamily.Cursive  // Stylish font
        )

        // 16dp vertical space
        Spacer(modifier = Modifier.height(16.dp))

        // ──────────────────────────────────────────────
        // BUDGET CARD WITH GRADIENT BACKGROUND
        // ──────────────────────────────────────────────
        Card(
            modifier = Modifier.fillMaxWidth(),  // Puri width le lo
            colors = CardDefaults.cardColors(
                containerColor = Color.Transparent  // Background transparent (gradient dikhne ke liye)
            ),
            shape = RoundedCornerShape(12.dp)  // Rounded corners
        ) {
            // Box ke andar gradient background lagaya
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        // Horizontal gradient - left se right
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                GradientBlue1,  // Start color: #1193D4 (light blue)
                                GradientBlue2   // End color: #094C6E (dark blue)
                            )
                        )
                    )
                    .padding(16.dp)  // Card ke andar padding
            ) {
                // Row = Horizontal arrangement (left se right)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,  // Space between items
                    verticalAlignment = Alignment.CenterVertically     // Vertically center
                ) {
                    // ──── LEFT SIDE: Budget amount ────
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

                    // ──── RIGHT SIDE: Add Money button ────
                    Button(
                        onClick = {
                            // TODO: Session 4 mein add money logic dalenge
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = AccentCyan  // Bright cyan color: #14FFEC
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        // Button ke andar icon aur text
                        Icon(
                            imageVector = Icons.Default.Add,  // Plus icon
                            contentDescription = "Add Money",
                            tint = GradientBlue2  // Dark blue color
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Add Money",
                            color = GradientBlue2,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}
// ═══════════════════════════════════════════════════════════════
// EXPENSE SUMMARY SECTION
// ═══════════════════════════════════════════════════════════════

@Composable
fun ExpenseSummarySection() {
    // Row = Horizontal arrangement
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween  // Cards ke beech space
    ) {
        // ──── Card 1: Today's Expense ────
        ExpenseCard(
            title = "Today's Expense",
            amount = "-₹740",                        // Negative amount (expense)
            icon = Icons.Default.DateRange,      // Calendar icon
            modifier = Modifier.weight(1f)           // 50% width le lo
        )

        // 16dp horizontal space between cards
        Spacer(modifier = Modifier.width(16.dp))

        // ──── Card 2: Monthly Expense ────
        ExpenseCard(
            title = "Monthly Expense",
            amount = "-₹5,540",                      // Negative amount
            icon = Icons.Default.DateRange,          // Date range icon
            modifier = Modifier.weight(1f)           // 50% width le lo
        )
    }
}
// ═══════════════════════════════════════════════════════════════
// REUSABLE EXPENSE CARD COMPONENT
// ═══════════════════════════════════════════════════════════════

/**
 * ExpenseCard - Single expense card component
 * Ye reusable hai, matlab ek hi code se multiple cards bana sakte hain
 *
 * @param title - Card ka title (e.g., "Today's Expense")
 * @param amount - Expense amount (e.g., "-₹740")
 * @param icon - Icon to display
 * @param modifier - Styling aur layout ke liye
 */
@Composable
fun ExpenseCard(
    title: String,
    amount: String,
    icon: ImageVector,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = CardBackground  // Dark card color: #1C2932
        ),
        shape = RoundedCornerShape(12.dp)    // Rounded corners
    ) {
        Column(
            modifier = Modifier.padding(16.dp)  // Card ke andar padding
        ) {
            // ──── Top: Icon ────
            Icon(
                imageVector = icon,
                contentDescription = null,       // Accessibility ke liye (optional)
                tint = AccentCyan,               // Cyan color
                modifier = Modifier.size(24.dp)  // Icon ka size
            )

            // 8dp vertical space
            Spacer(modifier = Modifier.height(8.dp))

            // ──── Middle: Title ────
            Text(
                text = title,
                color = TextGray,      // Gray color
                fontSize = 12.sp       // Small font
            )

            // ──── Bottom: Amount ────
            Text(
                text = amount,
                color = TextWhite,     // White color
                fontSize = 24.sp,      // Large font
                fontWeight = FontWeight.Bold
            )
        }
    }
}

// ═══════════════════════════════════════════════════════════════
// RECENT TRANSACTIONS SECTION
// ═══════════════════════════════════════════════════════════════
@Composable
fun RecentTransactionsSection() {
    Column {
        // ──── Header Row ────
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,  // Space between title aur button
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Left: Section title
            Text(
                text = "Recent Transactions",
                color = TextWhite,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            // Right: View All button
            TextButton(onClick = {
                // TODO: Session 4 mein all transactions screen pe navigate karenge
            }) {
                Text(
                    text = "View All",
                    color = AccentCyan  // Cyan color
                )
            }
        }

        // 12dp vertical space
        Spacer(modifier = Modifier.height(12.dp))

        // ──── Transaction List (Scrollable) ────
        // LazyColumn = Efficient list (sirf visible items render hote hain)
        // Jaise WhatsApp mein chat list
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)  // Har item ke beech 12dp space
        ) {
            // items() = List ko loop karta hai
            items(getDummyTransactions()) { transaction ->
                TransactionItem(transaction = transaction)
            }
        }
    }
}
// ═══════════════════════════════════════════════════════════════
// SINGLE TRANSACTION ITEM
// ═══════════════════════════════════════════════════════════════

/**
 * TransactionItem - Ek single transaction card
 * Dikhata hai:
 * - Category icon (circular background mein)
 * - Title aur date
 * - Amount (right side pe)
 */
@Composable
fun TransactionItem(transaction: Transaction) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = CardBackground  // Dark card: #1C2932
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),  // Card ke andar padding
            verticalAlignment = Alignment.CenterVertically  // Vertically center
        ) {
            // ──── Left: Category Icon (Circular background) ────
            Box(
                modifier = Modifier
                    .size(48.dp)                   // 48x48 dp square
                    .clip(CircleShape)             // Circle shape
                    .background(AppBackground),    // Background color
                contentAlignment = Alignment.Center  // Icon ko center mein
            ) {
                Icon(
                    imageVector = transaction.icon,
                    contentDescription = null,
                    tint = TextWhite,
                    modifier = Modifier.size(24.dp)
                )
            }

            // 12dp horizontal space
            Spacer(modifier = Modifier.width(12.dp))

            // ──── Middle: Transaction details (Title + Date) ────
            Column(
                modifier = Modifier.weight(1f)  // Remaining space le lo
            ) {
                // Transaction title
                Text(
                    text = transaction.title,
                    color = TextWhite,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
                // Transaction date/time
                Text(
                    text = transaction.date,
                    color = TextGray,
                    fontSize = 12.sp
                )
            }

            // ──── Right: Amount ────
            Text(
                text = transaction.amount,
                color = TextWhite,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
// ═══════════════════════════════════════════════════════════════
// DATA CLASS - Transaction
// ═══════════════════════════════════════════════════════════════
data class Transaction(
    val title: String,
    val date: String,
    val amount: String,
    val icon: ImageVector
)

// ═══════════════════════════════════════════════════════════════
// DUMMY DATA FUNCTION (TEMPORARY)
// ═══════════════════════════════════════════════════════════════

fun getDummyTransactions(): List<Transaction> {
    return listOf(
        // Transaction(title, date, amount, icon)
        Transaction("Lunch", "Friday 8:09am", "-₹740", Icons.Default.Face),
        Transaction("Uber", "Wednesday 8:09am", "-₹340", Icons.Default.Place),
        Transaction("Shopping", "Monday 8:09am", "-₹700", Icons.Default.ShoppingCart),
        Transaction("Phone Bill", "Monday 8:09am", "-₹299", Icons.Default.Phone),
        Transaction("Health", "Sunday 8:09am", "-₹550", Icons.Default.Favorite)
    )
}