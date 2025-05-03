package com.example.leysaasnalbeauty.leyasnal.domain.appointments

import com.example.leysaasnalbeauty.leyasnal.data.appoointments.AppointmentRepository
import com.example.leysaasnalbeauty.leyasnal.data.appoointments.AppointmentWithClient
import com.example.leysaasnalbeauty.leyasnal.ui.dataclasses.AppointmentDataClass
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllAppointmentsUseCase @Inject constructor(private val appointmentRepository: AppointmentRepository) {
    operator fun invoke(): Flow<List<AppointmentWithClient>> = appointmentRepository.getAllAppointments()
}