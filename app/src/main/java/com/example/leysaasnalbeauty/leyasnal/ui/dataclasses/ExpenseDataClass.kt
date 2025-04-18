package com.example.leysaasnalbeauty.leyasnal.ui.dataclasses

import com.example.leysaasnalbeauty.leyasnal.data.balance.expenses.ExpenseEntity

data class ExpenseDataClass(
    val id:Int = 0,
    val description: String,
    val amount: Int
)

fun ExpenseDataClass.toExpenseEntity(): ExpenseEntity = ExpenseEntity(
    id = id,
    description = description,
    amount = amount
)

fun ExpenseDataClass.toTransactionDataClass(): TransactionsDataClass = TransactionsDataClass(
    id = id,
    description = description,
    amount = amount
)