package com.example.leysaasnalbeauty.leyasnal.ui.dataclasses

import com.example.leysaasnalbeauty.leyasnal.data.appoointments.AppointmentEntity
import java.time.LocalDateTime

data class AppointmentDataClass(
    val id: Int = 0,
    val clientId: Int,
    val serviceType:String,
    val date: LocalDateTime
)

fun AppointmentDataClass.toAppointmentEntity() = AppointmentEntity(
    id = id,
    clientId = clientId,
    serviceType = serviceType,
    date = date
)

