package com.example.leysaasnalbeauty.leyasnal.data.loyalty

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.leysaasnalbeauty.leyasnal.ui.dataclasses.LoyaltyRewardPointsDataClass

@Entity
data class LoyaltyRewardPointsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val reward: String,
    val points: Int
)

fun LoyaltyRewardPointsEntity.toLoyaltyRewardPointsDataClass() = LoyaltyRewardPointsDataClass(
    id = id,
    reward = reward,
    points = points
)
