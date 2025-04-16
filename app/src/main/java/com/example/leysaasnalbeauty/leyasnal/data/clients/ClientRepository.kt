package com.example.leysaasnalbeauty.leyasnal.data.clients

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import com.example.leysaasnalbeauty.leyasnal.ui.dataclasses.ClientDataClass
import com.example.leysaasnalbeauty.leyasnal.ui.dataclasses.toClientEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ClientRepository @Inject constructor(private val clientDao: ClientDao) {

    fun getAllClients(): Flow<List<ClientDataClass>> = clientDao.getAllClients().map {
        it.map { clientEntity -> clientEntity.toClientDataClass() }
    }

    suspend fun insertClient(client: ClientDataClass) = clientDao.insertClient(client.toClientEntity())

    suspend fun deleteClient(client: ClientDataClass) = clientDao.deleteClient(client.toClientEntity())

    suspend fun updateClient(client: ClientDataClass) = clientDao.updateClient(client.toClientEntity())

}