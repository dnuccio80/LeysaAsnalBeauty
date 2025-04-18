package com.example.leysaasnalbeauty.leyasnal.data.balance.expenses

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.leysaasnalbeauty.leyasnal.ui.dataclasses.ExpenseDataClass

@Entity
data class ExpenseEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    val description: String,
    val amount: Int
)

fun ExpenseEntity.toExpenseDataClass(): ExpenseDataClass = ExpenseDataClass(
    id = id,
    description = description,
    amount = amount
)