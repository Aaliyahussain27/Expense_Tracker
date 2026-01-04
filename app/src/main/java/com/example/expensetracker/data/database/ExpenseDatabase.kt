package com.example.expensetracker.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.expensetracker.screens.expense.CategoryDao
import com.example.expensetracker.screens.expense.CategoryEntity
import com.example.expensetracker.screens.expense.ExpenseDao
import com.example.expensetracker.screens.expense.ExpenseEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [ExpenseEntity::class, CategoryEntity::class], version = 1)
abstract class ExpenseDatabase : RoomDatabase() {

    abstract fun expenseDao(): ExpenseDao
    abstract fun categoryDao(): CategoryDao

    companion object {
        @Volatile
        private var INSTANCE: ExpenseDatabase? = null

        fun getDatabase(context: Context): ExpenseDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ExpenseDatabase::class.java,
                    "expense_db"
                )
                    .addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            // Prepopulate categories inside coroutine
                            CoroutineScope(Dispatchers.IO).launch {
                                getDatabase(context).categoryDao().insertAll(
                                    listOf(
                                        CategoryEntity(1, "Food"),
                                        CategoryEntity(2, "Transport"),
                                        CategoryEntity(3, "Shopping"),
                                        CategoryEntity(4, "Bills"),
                                        CategoryEntity(5, "Fun"),
                                        CategoryEntity(6, "Health")
                                    )
                                )
                            }
                        }
                    })
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}

