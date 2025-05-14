package com.example.leysaasnalbeauty.leyasnal.data.loyalty

import androidx.room.Embedded
import androidx.room.Relation
import com.example.leysaasnalbeauty.leyasnal.data.clients.ClientEntity

data class LoyaltyWithClient (
    @Embedded
    val loyalty:LoyaltyClientPointsEntity,
    @Relation(
        parentColumn = "clientId",
        entityColumn = "id"
    )
    val client: ClientEntity
)