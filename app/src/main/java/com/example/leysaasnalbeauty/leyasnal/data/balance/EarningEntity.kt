package com.example.leysaasnalbeauty.leyasnal.data.balance

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.leysaasnalbeauty.leyasnal.ui.dataclasses.EarningDataClass

@Entity
data class EarningEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    val description: String,
    val amount: Int
)

fun EarningEntity.toEarningDataClass(): EarningDataClass = EarningDataClass(
    id = id,
    description = description,
    amount = amount
)