package com.example.leysaasnalbeauty.leyasnal.data.appoointments

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.leysaasnalbeauty.leyasnal.ui.dataclasses.AppointmentDataClass
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime

@Dao
interface AppointmentDao {

    @Query("SELECT * FROM AppointmentEntity ORDER BY date ASC")
    fun getAllAppointments(): Flow<List<AppointmentWithClient>>

    @Query("SELECT * FROM AppointmentEntity WHERE id = :id")
    fun getAppointmentDetails(id:Int): Flow<AppointmentEntity>

    @Query("DELETE FROM AppointmentEntity WHERE date < :now")
    suspend fun deletePastAppointments(now:LocalDateTime)

    @Transaction
    @Query("SELECT * FROM AppointmentEntity WHERE date >= :now ORDER BY date ASC")
    suspend fun getFutureAppointments(now: LocalDateTime): List<AppointmentWithClient>

    @Insert
    suspend fun addAppointment(appointment: AppointmentEntity)

    @Delete
    suspend fun deleteAppointment(appointment: AppointmentEntity)

    @Update
    suspend fun updateAppointment(appointment: AppointmentEntity)
}