package com.example.leysaasnalbeauty.leyasnal.ui.dataclasses

import androidx.room.PrimaryKey
import com.example.leysaasnalbeauty.leyasnal.data.balance.EarningEntity

data class EarningDataClass(
    val id:Int = 0,
    val description: String,
    val amount: Int
)

fun EarningDataClass.toEarningEntity(): EarningEntity = EarningEntity(
    id = id,
    description = description,
    amount = amount
)