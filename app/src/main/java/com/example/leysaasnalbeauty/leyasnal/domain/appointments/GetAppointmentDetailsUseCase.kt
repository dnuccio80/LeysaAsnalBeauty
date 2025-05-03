package com.example.leysaasnalbeauty.leyasnal.domain.appointments

import com.example.leysaasnalbeauty.leyasnal.data.appoointments.AppointmentRepository
import javax.inject.Inject

class GetAppointmentDetailsUseCase @Inject constructor(private val appointmentRepository: AppointmentRepository) {
    operator fun invoke(appointmentId: Int) = appointmentRepository.getAppointmentDetails(appointmentId)
}