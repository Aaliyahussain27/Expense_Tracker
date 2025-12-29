package com.example.expensetracker.screens.add

// Background aur layout
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape

// TextField ke liye
import androidx.compose.foundation.text.KeyboardOptions

// Icons - Har icon individually import hota hai
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.ShoppingCart

// Material Design 3 components
import androidx.compose.material3.*

// State management (remember, mutableStateOf)
import androidx.compose.runtime.*

// UI arrangement
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

// Colors aur styling
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Custom colors (Figma wale)
import com.example.expensetracker.ui.theme.*


// ═══════════════════════════════════════════════════════════════
// MAIN ADD EXPENSE SCREEN
// ═══════════════════════════════════════════════════════════════

@OptIn(ExperimentalMaterial3Api::class)  // TextField experimental feature hai
@Composable
fun AddExpenseScreen() {

    // ──────────────────────────────────────────────
    // STATE VARIABLES (Remember + MutableState)
    // ──────────────────────────────────────────────


    var amount by remember { mutableStateOf("") }                    // Amount input (empty string se start)
    var selectedCategory by remember { mutableStateOf("Food") }      // Selected category (default: Food)
    var description by remember { mutableStateOf("") }               // Description input (optional)
    var selectedDate by remember { mutableStateOf("10/12/2025") }    // Selected date (dummy date)


    // ──────────────────────────────────────────────
    // MAIN CONTAINER
    // ──────────────────────────────────────────────
    Column(
        modifier = Modifier
            .fillMaxSize()              // Puri screen
            .background(AppBackground)  // Dark background: #101C22
            .padding(16.dp)             // Charo taraf padding
    ) {
        // Top bar with Cancel button aur title
        TopBar()

        Spacer(modifier = Modifier.height(24.dp))

        // Amount input section (₹0.00)
        AmountInputSection(
            amount = amount,
            onAmountChange = { amount = it }  // Jab user type kare, amount update ho
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Category selection (Food, Transport, etc.)
        CategorySection(
            selectedCategory = selectedCategory,
            onCategorySelect = { selectedCategory = it }  // Jab user click kare, category update ho
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Description input (optional text)
        DescriptionSection(
            description = description,
            onDescriptionChange = { description = it }
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Date picker
        DateSection(
            selectedDate = selectedDate,
            onDateChange = { selectedDate = it }
        )

        // Spacer with weight - Remaining space le lega aur button ko bottom mein push karega
        Spacer(modifier = Modifier.weight(1f))

        // Save button (bottom pe fixed)
        SaveButton(
            onClick = {
                // TODO: Session 4 mein database mein save karenge
                println("Amount: $amount, Category: $selectedCategory, Description: $description")
            }
        )
    }
}

// ═══════════════════════════════════════════════════════════════
// TOP BAR (Cancel button + Title)
// ═══════════════════════════════════════════════════════════════

@Composable
fun TopBar() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,  // Items ko spread karo
        verticalAlignment = Alignment.CenterVertically
    ) {
        // ──── Left: Cancel button ────
        TextButton(onClick = {
            // TODO: Session 4 mein navigation back karenge
        }) {
            Text(
                text = "Cancel",
                color = AccentCyan,  // Bright cyan: #14FFEC
                fontSize = 16.sp
            )
        }

        // ──── Center: Title ────
        Text(
            text = "New Expense",
            color = TextWhite,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        // ──── Right: Empty space (layout balance ke liye) ────
        Spacer(modifier = Modifier.width(60.dp))
    }
}

// ═══════════════════════════════════════════════════════════════
// AMOUNT INPUT SECTION
// ═══════════════════════════════════════════════════════════════

/**
 * @param amount - Current amount value (state variable se)
 * @param onAmountChange - Callback function jab user type kare
 */
@Composable
fun AmountInputSection(
    amount: String,
    onAmountChange: (String) -> Unit  // Higher-order function (function as parameter)
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally  // Center mein rakho
    ) {
        // ──── Label: "ENTER AMOUNT" ────
        Text(
            text = "ENTER AMOUNT",
            color = TextGray,
            fontSize = 12.sp,
            letterSpacing = 1.sp  // Letters ke beech space
        )

        Spacer(modifier = Modifier.height(8.dp))

        // ──── Row: ₹ symbol + TextField ────
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Rupee symbol (₹)
            Text(
                text = "₹",
                color = TextGray,
                fontSize = 48.sp,       // Very large font
                fontWeight = FontWeight.Light
            )

            // ──── TextField for number input ────
            TextField(
                value = amount,              // Current value
                onValueChange = onAmountChange,  // Jab user type kare toh ye function call hoga
                textStyle = LocalTextStyle.current.copy(
                    fontSize = 48.sp,        // Large font size
                    fontWeight = FontWeight.Light,
                    color = TextWhite,       // White text
                    textAlign = TextAlign.Start
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number  // Number keyboard dikhega (0-9)
                ),
                placeholder = {
                    // Placeholder = Empty field mein dikhne wala text
                    Text(
                        text = "0.00",
                        fontSize = 48.sp,
                        color = TextGray
                    )
                },
                colors = TextFieldDefaults.colors(
                    // Transparent background (border nahi chahiye)
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    // No underline indicator
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                modifier = Modifier.width(200.dp)  // Fixed width
            )
        }
    }
}
// ═══════════════════════════════════════════════════════════════
// CATEGORY SELECTION SECTION
// ═══════════════════════════════════════════════════════════════

/**
 * CategorySection - User yahan category select karega
 * @param selectedCategory - Currently selected category
 * @param onCategorySelect - Callback jab user category click kare
 */
@Composable
fun CategorySection(
    selectedCategory: String,
    onCategorySelect: (String) -> Unit
) {
    Column {
        // ──── Section title ────
        Text(
            text = "Category",
            color = TextWhite,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(16.dp))

        // ──── Row 1: Food, Transport, Shopping ────
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly  // Equal space mein distribute karo
        ) {
            CategoryItem(
                title = "Food",
                icon = Icons.Default.Face,
                isSelected = selectedCategory == "Food",      // Agar selected hai toh true
                onClick = { onCategorySelect("Food") }
            )
            CategoryItem(
                title = "Transport",
                icon = Icons.Default.Place,
                isSelected = selectedCategory == "Transport",
                onClick = { onCategorySelect("Transport") }
            )
            CategoryItem(
                title = "Shopping",
                icon = Icons.Default.ShoppingCart,
                isSelected = selectedCategory == "Shopping",
                onClick = { onCategorySelect("Shopping") }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // ──── Row 2: Bills, Fun, Health ────
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            CategoryItem(
                title = "Bills",
                icon = Icons.Default.AccountBox,
                isSelected = selectedCategory == "Bills",
                onClick = { onCategorySelect("Bills") }
            )
            CategoryItem(
                title = "Fun",
                icon = Icons.Default.Face,
                isSelected = selectedCategory == "Fun",
                onClick = { onCategorySelect("Fun") }
            )
            CategoryItem(
                title = "Health",
                icon = Icons.Default.Favorite,
                isSelected = selectedCategory == "Health",
                onClick = { onCategorySelect("Health") }
            )
        }
    }
}
// ═══════════════════════════════════════════════════════════════
// SINGLE CATEGORY ITEM (Reusable Component)
// ═══════════════════════════════════════════════════════════════

/**
 * CategoryItem - Ek single category button
 * @param title - Category name (e.g., "Food")
 * @param icon - Icon to display
 * @param isSelected - Boolean: Is this category currently selected?
 * @param onClick - Callback jab user click kare
 */
@Composable
fun CategoryItem(
    title: String,
    icon: ImageVector,
    isSelected: Boolean,
    onClick: () -> Unit  // Lambda function (no parameters)
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable(onClick = onClick)  // Clickable banao
            .padding(8.dp)
    ) {
        // ──── Icon container with conditional styling ────
        Box(
            modifier = Modifier
                .size(64.dp)  // 64x64 dp square
                .background(
                    // Conditional background color:
                    // Selected: Dark blue (#094C6E)
                    // Not selected: Card background (#1C2932)
                    color = if (isSelected) GradientBlue2 else CardBackground,
                    shape = RoundedCornerShape(12.dp)
                )
                .border(
                    // Conditional border:
                    // Selected: 2dp cyan border
                    // Not selected: No border
                    width = if (isSelected) 2.dp else 0.dp,
                    color = if (isSelected) AccentCyan else Color.Transparent,
                    shape = RoundedCornerShape(12.dp)
                ),
            contentAlignment = Alignment.Center  // Icon ko center mein
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,  // Accessibility
                tint = TextWhite,
                modifier = Modifier.size(32.dp)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // ──── Category name ────
        Text(
            text = title,
            // Conditional text color:
            // Selected: Cyan
            // Not selected: Gray
            color = if (isSelected) AccentCyan else TextGray,
            fontSize = 12.sp
        )
    }
}
// ═══════════════════════════════════════════════════════════════
// DESCRIPTION INPUT SECTION
// ═══════════════════════════════════════════════════════════════

/**
 * DescriptionSection - Optional text input
 * User yahan note likh sakta hai (e.g., "Lunch with friends")
 * @param description - Current description text
 * @param onDescriptionChange - Callback jab user type kare
 */
@Composable
fun DescriptionSection(
    description: String,
    onDescriptionChange: (String) -> Unit
) {
    Column {
        // Section title
        Text(
            text = "Description",
            color = TextWhite,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(12.dp))

        // ──── TextField with icon placeholder ────
        TextField(
            value = description,
            onValueChange = onDescriptionChange,
            placeholder = {
                // Placeholder mein icon + text dono
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = null,
                        tint = TextGray,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "What is this for?",
                        color = TextGray
                    )
                }
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = CardBackground,    // Dark card background
                unfocusedContainerColor = CardBackground,
                focusedIndicatorColor = Color.Transparent,  // No underline
                unfocusedIndicatorColor = Color.Transparent,
                focusedTextColor = TextWhite,
                unfocusedTextColor = TextWhite
            ),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth()
        )
    }
}
// ═══════════════════════════════════════════════════════════════
// DATE SELECTION SECTION
// ═══════════════════════════════════════════════════════════════

/**
 * DateSection - Date picker button
 * @param selectedDate - Currently selected date
 * @param onDateChange - Callback jab date change ho
 */
@Composable
fun DateSection(
    selectedDate: String,
    onDateChange: (String) -> Unit
) {
    Column {
        // Section title
        Text(
            text = "Date",
            color = TextWhite,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(12.dp))

        // ──── Date picker card (clickable) ────
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    // TODO: Session 4 mein date picker dialog open karenge
                },
            colors = CardDefaults.cardColors(
                containerColor = CardBackground  // Dark card: #1C2932
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Calendar icon
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = "Select Date",
                    tint = AccentCyan,  // Cyan color
                    modifier = Modifier.size(24.dp)
                )

                Spacer(modifier = Modifier.width(12.dp))

                // Selected date text
                Text(
                    text = selectedDate,
                    color = TextWhite,
                    fontSize = 16.sp
                )
            }
        }
    }
}


// ═══════════════════════════════════════════════════════════════
// SAVE BUTTON (Bottom)
// ═══════════════════════════════════════════════════════════════

/**
 * SaveButton - Large button at bottom
 * @param onClick - Callback jab button click ho
 */
@Composable
fun SaveButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()      // Puri width
            .height(56.dp),      // Fixed height (large button)
        colors = ButtonDefaults.buttonColors(
            containerColor = AccentCyan  // Bright cyan: #14FFEC
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        // Button ke andar icon + text
        Icon(
            imageVector = Icons.Default.Check,  // Checkmark icon
            contentDescription = "Save",
            tint = GradientBlue2  // Dark blue
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "Save Expense",
            color = GradientBlue2,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    }
}