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

    @Query("SELECT * FROM ClientEntity WHERE id = :id")
    fun getClientDetails(id:Int): Flow<ClientEntity>

    @Insert
    suspend fun insertClient(client: ClientEntity)

    @Delete
    suspend fun deleteClient(client: ClientEntity)

    @Update
    suspend fun updateClient(client: ClientEntity)


}