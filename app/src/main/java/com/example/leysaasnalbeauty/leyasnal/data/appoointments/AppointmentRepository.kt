package com.example.leysaasnalbeauty.leyasnal.data.appoointments

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.leysaasnalbeauty.leyasnal.data.annotations.toAnnotationsDataClass
import com.example.leysaasnalbeauty.leyasnal.ui.dataclasses.AppointmentDataClass
import com.example.leysaasnalbeauty.leyasnal.ui.dataclasses.toAppointmentEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDateTime
import javax.inject.Inject

class AppointmentRepository @Inject constructor(private val appointmentDao: AppointmentDao){

    fun getAllAppointments(): Flow<List<AppointmentWithClient>> = appointmentDao.getAllAppointments()

    fun getAppointmentDetails(id:Int):Flow<AppointmentWithClient> = appointmentDao.getAppointmentDetails(id)

    suspend fun getFutureAppointments(now: LocalDateTime): List<AppointmentWithClient> = appointmentDao.getFutureAppointments(now)

    suspend fun deletePastAppointments(now:LocalDateTime) = appointmentDao.deletePastAppointments(now)

    suspend fun addAppointment(appointment: AppointmentDataClass) {
        appointmentDao.addAppointment(appointment.toAppointmentEntity())
    }

    suspend fun deleteAppointment(id:Int) {
        appointmentDao.deleteAppointment(id)
    }

    suspend fun updateAppointment(appointment: AppointmentDataClass) {
        appointmentDao.updateAppointment(appointment.toAppointmentEntity())
    }

}