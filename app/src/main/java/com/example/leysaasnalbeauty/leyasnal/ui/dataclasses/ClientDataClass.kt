package com.example.leysaasnalbeauty.leyasnal.ui.dataclasses

import com.example.leysaasnalbeauty.leyasnal.data.clients.ClientEntity
import java.time.LocalDate

data class ClientDataClass(
    val id: Int = 0,
    val name: String,
    val phone: String,
    val details: String
)

fun ClientDataClass.toClientEntity(): ClientEntity = ClientEntity(
    id = id,
    name = name,
    phone = phone,
    details = details
)
