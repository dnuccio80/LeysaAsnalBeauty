package com.example.leysaasnalbeauty.leyasnal.data.balance.expenses

import com.example.leysaasnalbeauty.leyasnal.ui.dataclasses.ExpenseDataClass
import com.example.leysaasnalbeauty.leyasnal.ui.dataclasses.toExpenseEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ExpenseRepository @Inject constructor(private val expenseDao: ExpenseDao) {

    fun getAllExpenses(): Flow<List<ExpenseDataClass>> = expenseDao.getAllExpenses().map {
        it.map { expenseEntity -> expenseEntity.toExpenseDataClass() }
    }

    fun deleteAllExpensesData() = expenseDao.deleteAllExpensesData()

    suspend fun addExpense(expense: ExpenseDataClass) = expenseDao.addExpense(expense.toExpenseEntity())

    suspend fun deleteExpense(expense: ExpenseDataClass) = expenseDao.deleteExpense(expense.toExpenseEntity())

    suspend fun updateExpense(expense: ExpenseDataClass) = expenseDao.updateExpense(expense.toExpenseEntity())

}