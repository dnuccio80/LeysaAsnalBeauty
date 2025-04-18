package com.example.leysaasnalbeauty.leyasnal.ui.dataclasses

data class TransactionsDataClass(
    val id: Int,
    val description: String,
    val amount: Int,
)

fun TransactionsDataClass.toEarningDataClass() : EarningDataClass= EarningDataClass(
    id = this.id,
    description = this.description,
    amount = this.amount,
)

fun TransactionsDataClass.toExpenseDataClass() : ExpenseDataClass = ExpenseDataClass(
    id = this.id,
    description = this.description,
    amount = this.amount,
)
