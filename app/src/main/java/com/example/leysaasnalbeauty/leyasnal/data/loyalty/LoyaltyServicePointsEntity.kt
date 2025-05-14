package com.example.leysaasnalbeauty.leyasnal.data.loyalty

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.leysaasnalbeauty.leyasnal.ui.dataclasses.LoyaltyServicePointsDataClass

@Entity
data class LoyaltyServicePointsEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    val service:String,
    val points:Int
)

fun LoyaltyServicePointsEntity.toLoyaltyServicePointsDataClass() = LoyaltyServicePointsDataClass(
    id = id,
    service = service,
    points = points
)

