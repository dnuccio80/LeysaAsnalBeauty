package com.example.leysaasnalbeauty.leyasnal.data.clients

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.leysaasnalbeauty.leyasnal.ui.dataclasses.ClientDataClass
import java.time.LocalDate

@Entity
data class ClientEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val phone: String,
    val birthday: LocalDate,
    val details: String
)

fun ClientEntity.toClientDataClass() : ClientDataClass = ClientDataClass(
    id = id,
    name = name,
    phone = phone,
    birthday = birthday,
    details = details
)