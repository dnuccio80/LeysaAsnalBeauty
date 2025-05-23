package com.example.leysaasnalbeauty.leyasnal.domain.appointments

import com.example.leysaasnalbeauty.leyasnal.data.appoointments.AppointmentRepository
import com.example.leysaasnalbeauty.leyasnal.ui.dataclasses.AppointmentDataClass
import javax.inject.Inject

class AddAppointmentUseCase @Inject constructor(private val appointmentRepository: AppointmentRepository) {
    suspend operator fun invoke(appointment: AppointmentDataClass) = appointmentRepository.addAppointment(appointment)
}
