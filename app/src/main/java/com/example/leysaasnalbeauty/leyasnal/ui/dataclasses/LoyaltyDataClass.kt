package com.example.leysaasnalbeauty.leyasnal.ui.dataclasses

import com.example.leysaasnalbeauty.leyasnal.data.loyalty.LoyaltyEntity

data class LoyaltyDataClass(
    val id:Int = 0,
    val points:Int,
    val clientId:Int
)

fun LoyaltyDataClass.toLoyaltyEntity(): LoyaltyEntity = LoyaltyEntity(
    id = id,
    points = points,
    clientId = clientId
)