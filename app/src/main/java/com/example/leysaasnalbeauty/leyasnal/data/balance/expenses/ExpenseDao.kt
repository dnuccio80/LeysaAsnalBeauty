package com.example.leysaasnalbeauty.leyasnal.data.balance.expenses

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.leysaasnalbeauty.leyasnal.data.balance.earnings.EarningEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao {

    @Query("SELECT * FROM ExpenseEntity")
    fun getAllExpenses(): Flow<List<ExpenseEntity>>

    @Query("DELETE FROM ExpenseEntity")
    fun deleteAllExpensesData()

    @Insert
    suspend fun addExpense(expense: ExpenseEntity)

    @Delete
    suspend fun deleteExpense(expense: ExpenseEntity)

    @Update
    suspend fun updateExpense(expense: ExpenseEntity)

}