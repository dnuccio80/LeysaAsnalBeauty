package com.example.leysaasnalbeauty.leyasnal.data.appoointments

import androidx.room.Embedded
import androidx.room.Relation
import com.example.leysaasnalbeauty.leyasnal.data.clients.ClientEntity

data class AppointmentWithClient(
    @Embedded val appointment: AppointmentEntity,
    @Relation(
        parentColumn = "clientId",
        entityColumn = "id"
    )
    val client: ClientEntity
)
