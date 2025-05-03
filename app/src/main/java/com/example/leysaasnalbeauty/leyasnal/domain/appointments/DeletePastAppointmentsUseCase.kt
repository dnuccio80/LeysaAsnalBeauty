package com.example.leysaasnalbeauty.leyasnal.domain.appointments

import com.example.leysaasnalbeauty.leyasnal.data.appoointments.AppointmentRepository
import java.time.LocalDateTime
import javax.inject.Inject

class DeletePastAppointmentsUseCase @Inject constructor(private val appointmentRepository: AppointmentRepository) {
    suspend operator fun invoke(now:LocalDateTime) = appointmentRepository.deletePastAppointments(now)
}