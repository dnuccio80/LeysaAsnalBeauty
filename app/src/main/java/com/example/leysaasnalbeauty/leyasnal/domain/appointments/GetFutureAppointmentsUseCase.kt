package com.example.leysaasnalbeauty.leyasnal.domain.appointments

import com.example.leysaasnalbeauty.leyasnal.data.appoointments.AppointmentRepository
import com.example.leysaasnalbeauty.leyasnal.data.appoointments.AppointmentWithClient
import com.example.leysaasnalbeauty.leyasnal.ui.dataclasses.AppointmentDataClass
import java.time.LocalDateTime
import javax.inject.Inject

class GetFutureAppointmentsUseCase @Inject constructor(private val appointmentRepository: AppointmentRepository) {
    suspend operator fun invoke(now:LocalDateTime): List<AppointmentWithClient> = appointmentRepository.getFutureAppointments(now)
}