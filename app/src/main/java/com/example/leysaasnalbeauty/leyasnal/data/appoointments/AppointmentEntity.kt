package com.example.leysaasnalbeauty.leyasnal.data.appoointments

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.leysaasnalbeauty.leyasnal.data.clients.ClientEntity
import com.example.leysaasnalbeauty.leyasnal.ui.dataclasses.AppointmentDataClass
import java.time.LocalDateTime

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
data class AppointmentEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val clientId: Int,
    val serviceType:String,
    val date:LocalDateTime
)

fun AppointmentEntity.toAppointmentDataClass() = AppointmentDataClass(
    id = id,
    clientId = clientId,
    serviceType = serviceType,
    date = date
)