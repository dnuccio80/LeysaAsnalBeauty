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

    fun getAllAppointments(): Flow<List<AppointmentDataClass>> = appointmentDao.getAllAppointments().map {
        it.map { list ->
            list.toAppointmentDataClass()
        }
    }

    fun getAppointmentDetails(id:Int) = appointmentDao.getAppointmentDetails(id).map {
        it?.toAppointmentDataClass()
    }

    suspend fun getFutureAppointments(now: LocalDateTime): List<AppointmentWithClient> = appointmentDao.getFutureAppointments(now)

    suspend fun addAppointment(appointment: AppointmentDataClass) {
        appointmentDao.addAppointment(appointment.toAppointmentEntity())
    }

    suspend fun deleteAppointment(appointment: AppointmentDataClass) {
        appointmentDao.deleteAppointment(appointment.toAppointmentEntity())
    }

    suspend fun updateAppointment(appointment: AppointmentDataClass) {
        appointmentDao.updateAppointment(appointment.toAppointmentEntity())
    }

}