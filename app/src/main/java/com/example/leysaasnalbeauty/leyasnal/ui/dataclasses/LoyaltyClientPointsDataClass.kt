package com.example.leysaasnalbeauty.leyasnal.ui.dataclasses

import com.example.leysaasnalbeauty.leyasnal.data.loyalty.LoyaltyClientPointsEntity

data class LoyaltyClientPointsDataClass(
    val clientId:Int,
    val points:Int,
)

fun LoyaltyClientPointsDataClass.toLoyaltyClientPointsEntity(): LoyaltyClientPointsEntity = LoyaltyClientPointsEntity(
    clientId = clientId,
    points = points
)