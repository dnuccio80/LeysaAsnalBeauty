package com.example.leysaasnalbeauty.leyasnal.data.loyalty

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.leysaasnalbeauty.leyasnal.data.clients.ClientEntity
import com.example.leysaasnalbeauty.leyasnal.ui.dataclasses.LoyaltyDataClass

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
data class LoyaltyEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val points: Int,
    val clientId:Int
)

fun LoyaltyEntity.toLoyaltyDataClass(): LoyaltyDataClass = LoyaltyDataClass(
    id = id,
    points = points,
    clientId = clientId
)
