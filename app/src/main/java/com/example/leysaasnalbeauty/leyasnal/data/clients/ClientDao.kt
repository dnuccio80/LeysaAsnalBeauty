package com.example.leysaasnalbeauty.leyasnal.data.clients

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ClientDao {

    @Query("SELECT * FROM ClientEntity")
    fun getAllClients(): Flow<List<ClientEntity>>

    @Insert
    suspend fun insertClient(client: ClientEntity)

    @Delete
    suspend fun deleteClient(client: ClientEntity)

    @Update
    suspend fun updateClient(client: ClientEntity)

}