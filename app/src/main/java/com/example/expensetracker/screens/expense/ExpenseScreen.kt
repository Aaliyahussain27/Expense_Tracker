package com.example.expensetracker.screens.expense

import android.app.DatePickerDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.flow.collect
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddExpenseScreen(viewModel: ExpenseViewModel, onCancel: () -> Unit) {

    // -------------------- State Variables --------------------
    var amount by remember { mutableStateOf("") }
    var selectedCategoryId by remember { mutableStateOf<Int?>(null) }
    var description by remember { mutableStateOf("") }
    var selectedDate by remember { mutableStateOf("Select Date") }

    val categories by viewModel.categories.collectAsState() // get categories from DB

    val background = Color(0xFF0F1C24)
    val cardBg = Color(0xFF1C2B34)
    val accent = Color(0xFF1EB1FC)
    val textGray = Color(0xFF8A9BA8)
    val textWhite = Color.White

    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    // -------------------- Date Picker --------------------
    val datePickerDialog = DatePickerDialog(
        context,
        { _, year, month, dayOfMonth ->
            selectedDate = "$dayOfMonth/${month + 1}/$year"
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    // -------------------- UI --------------------
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(background)
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        // Top Bar
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "Cancel",
                color = accent,
                fontSize = 16.sp,
                modifier = Modifier.clickable { onCancel() }
            )
            Text(
                "New Expense",
                color = textWhite,
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.width(48.dp))
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Amount Input
        Text(
            "ENTER AMOUNT",
            color = textGray,
            fontSize = 18.sp,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "₹",
                color = textGray.copy(alpha = 0.8f),
                fontSize = 48.sp,
                fontWeight = FontWeight.Light
            )

            TextField(
                value = amount,
                onValueChange = { amount = it },
                textStyle = LocalTextStyle.current.copy(
                    fontSize = 48.sp,
                    fontWeight = FontWeight.Normal,
                    color = textWhite,
                    textAlign = TextAlign.Center
                ),
                placeholder = {
                    Text(
                        "0.00",
                        fontSize = 48.sp,
                        color = textGray.copy(alpha = 0.6f),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = accent
                ),
                modifier = Modifier.width(200.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // -------------------- Category Selection --------------------
        Text("Category", color = textWhite, fontSize = 18.sp, fontWeight = FontWeight.Medium)
        Spacer(modifier = Modifier.height(12.dp))

        if (categories.isEmpty()) {
            Text(
                "No categories found! Add some first.",
                color = Color.Red,
                fontSize = 14.sp
            )
        } else {
            Column {
                categories.chunked(3).forEach { row ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        row.forEach { category ->
                            CategoryItem(
                                title = category.name,
                                icon = mapCategoryToIcon(category.name),
                                selected = selectedCategoryId == category.id,
                                onClick = { selectedCategoryId = category.id },
                                cardBg = cardBg,
                                accent = accent,
                                textGray = textGray
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // -------------------- Description --------------------
        Text("Description", color = textWhite, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = description,
            onValueChange = { description = it },
            placeholder = { Text("What is this for?", color = textGray) },
            leadingIcon = { Icon(Icons.Default.Edit, null, tint = textGray) },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = cardBg,
                unfocusedContainerColor = cardBg,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedTextColor = textWhite,
                unfocusedTextColor = textWhite
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // -------------------- Date Selection --------------------
        Text("Date", color = textWhite, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(cardBg, RoundedCornerShape(12.dp))
                .clickable { datePickerDialog.show() }
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Default.DateRange, null, tint = textGray)
            Spacer(modifier = Modifier.width(12.dp))
            Text(selectedDate, color = textWhite)
        }

        Spacer(modifier = Modifier.weight(1f))

        // -------------------- Save Button --------------------
        Button(
            onClick = {
                if (amount.isNotBlank() && selectedCategoryId != null && selectedDate != "Select Date") {
                    viewModel.saveExpense(
                        amount = amount.toDouble(),
                        categoryId = selectedCategoryId!!,
                        description = description,
                        date = selectedDate
                    )
                    onCancel()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp),
            colors = ButtonDefaults.buttonColors(containerColor = accent),
            shape = RoundedCornerShape(12.dp)
        ) {
            Icon(Icons.Default.Check, null)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Save Expense", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        }
    }
}

// -------------------- Category Item --------------------
@Composable
fun CategoryItem(
    title: String,
    icon: ImageVector,
    selected: Boolean,
    onClick: () -> Unit,
    cardBg: Color,
    accent: Color,
    textGray: Color
) {
    Column(
        modifier = Modifier
            .size(100.dp)
            .background(cardBg, RoundedCornerShape(16.dp))
            .border(
                width = if (selected) 2.dp else 0.dp,
                color = if (selected) accent else Color.Transparent,
                shape = RoundedCornerShape(16.dp)
            )
            .clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(icon, null, tint = if (selected) accent else textGray)
        Spacer(modifier = Modifier.height(6.dp))
        Text(title, color = if (selected) accent else textGray, fontSize = 12.sp)
    }
}

// -------------------- Helper Function to Map Icons --------------------
fun mapCategoryToIcon(categoryName: String): ImageVector {
    return when (categoryName.lowercase()) {
        "food" -> Icons.Default.Face
        "transport" -> Icons.Default.Place
        "shopping" -> Icons.Default.ShoppingCart
        "bills" -> Icons.AutoMirrored.Filled.List
        "fun" -> Icons.Default.Face
        "health" -> Icons.Default.Favorite
        else -> Icons.Default.AttachMoney
    }
}
