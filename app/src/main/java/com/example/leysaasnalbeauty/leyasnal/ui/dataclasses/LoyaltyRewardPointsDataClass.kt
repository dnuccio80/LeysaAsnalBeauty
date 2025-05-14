package com.example.leysaasnalbeauty.leyasnal.ui.dataclasses

import com.example.leysaasnalbeauty.leyasnal.data.loyalty.LoyaltyRewardPointsEntity

data class LoyaltyRewardPointsDataClass(
    val id: Int = 0,
    val reward:String,
    val points: Int
)

fun LoyaltyRewardPointsDataClass.toLoyaltyRewardPointsEntity() = LoyaltyRewardPointsEntity(
    id = id,
    reward = reward,
    points = points
)
