package com.example.leysaasnalbeauty.leyasnal.data.loyalty

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.leysaasnalbeauty.leyasnal.data.clients.ClientEntity
import com.example.leysaasnalbeauty.leyasnal.ui.dataclasses.LoyaltyClientPointsDataClass

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = ClientEntity::class,
            parentColumns = ["id"],
            childColumns = ["clientId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class LoyaltyClientPointsEntity(
    @PrimaryKey
    val clientId:Int,
    val points: Int,
)

fun LoyaltyClientPointsEntity.toLoyaltyDataClass(): LoyaltyClientPointsDataClass = LoyaltyClientPointsDataClass(
    points = points,
    clientId = clientId
)
