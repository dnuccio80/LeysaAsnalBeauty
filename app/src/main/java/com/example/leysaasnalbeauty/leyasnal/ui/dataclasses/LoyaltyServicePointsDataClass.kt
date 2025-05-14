package com.example.leysaasnalbeauty.leyasnal.ui.dataclasses

import com.example.leysaasnalbeauty.leyasnal.data.loyalty.LoyaltyServicePointsEntity

data class LoyaltyServicePointsDataClass(
    val id: Int = 0,
    val service: String,
    val points: Int
)

fun LoyaltyServicePointsDataClass.toLoyaltyServicePointsEntity() = LoyaltyServicePointsEntity(
    id = id,
    service = service,
    points = points
)