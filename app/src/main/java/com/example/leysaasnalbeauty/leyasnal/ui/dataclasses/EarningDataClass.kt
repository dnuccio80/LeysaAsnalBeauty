package com.example.leysaasnalbeauty.leyasnal.ui.dataclasses

import com.example.leysaasnalbeauty.leyasnal.data.balance.earnings.EarningEntity

data class EarningDataClass(
    val id: Int = 0,
    val description: String,
    val amount: Int
)

fun EarningDataClass.toEarningEntity(): EarningEntity = EarningEntity(
    id = id,
    description = description,
    amount = amount
)

fun EarningDataClass.toTransactionsDataClass(): TransactionsDataClass = TransactionsDataClass(
    id = id,
    description = description,
    amount = amount
)