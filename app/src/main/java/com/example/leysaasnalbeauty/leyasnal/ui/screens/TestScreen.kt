package com.example.leysaasnalbeauty.leyasnal.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.leysaasnalbeauty.R
import com.example.leysaasnalbeauty.leyasnal.data.appoointments.AppointmentWithClient
import com.example.leysaasnalbeauty.leyasnal.ui.AppViewModel
import com.example.leysaasnalbeauty.leyasnal.ui.components.BodyText
import com.example.leysaasnalbeauty.leyasnal.ui.components.ButtonTextItem
import com.example.leysaasnalbeauty.leyasnal.ui.components.ThirdTitleText
import com.example.leysaasnalbeauty.leyasnal.ui.helpers.sendWppMessage
import com.example.leysaasnalbeauty.ui.theme.DarkAccentColor

@Composable
fun TestScreen(innerPadding: PaddingValues, viewModel: AppViewModel) {

    val appointments by viewModel.appointments.collectAsState()

    val todayAppointments by viewModel.todayAppointments.collectAsState()
    val tomorrowAppointments by viewModel.tomorrowAppointments.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadAppointments()
    }

    Box(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Color.Black)
            .padding(innerPadding)
    ) {
        Column(
            Modifier.fillMaxWidth()
        ) {
            ThirdTitleText("Todas las citas:")
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp, horizontal = 8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                appointments.forEach {
                    TestItem(it)
                }
            }
            ThirdTitleText("Citas de hoy:")
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp, horizontal = 8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                if (todayAppointments.isEmpty()) {
                    BodyText("No hay citas para hoy")
                } else {
                    todayAppointments.forEach {
                        TestItem(it)
                    }
                }

            }
            ThirdTitleText("Citas de mañana:")
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp, horizontal = 8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                if (tomorrowAppointments.isEmpty()) {
                    BodyText("No hay citas para mañana")
                } else {
                    tomorrowAppointments.forEach {
                        TestItem(it)
                    }
                }
            }
        }
    }


}

@Composable
fun TestItem(appointment: AppointmentWithClient) {
    val context = LocalContext.current
    val clientName = appointment.client.name.split(" ").first()
    val hour =
        if (appointment.appointment.date.hour < 10) "0${appointment.appointment.date.hour}" else appointment.appointment.date.hour
    val minute =
        if (appointment.appointment.date.minute < 10) "0${appointment.appointment.date.minute}" else appointment.appointment.date.minute
    val appointmentDate =
        "${appointment.appointment.date.dayOfMonth}/${appointment.appointment.date.monthValue}"
    val appointmentHour = "$hour:$minute"
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Box(modifier = Modifier.weight(1f)) {
            BodyText("$appointmentDate: ${appointment.client.name} - ${appointment.appointment.serviceType} - $appointmentHour")
        }
        ButtonTextItem(
            text = stringResource(R.string.remember),
            buttonColor = DarkAccentColor
        ) {
            sendWppMessage(
                context = context,
                phoneNumber = appointment.client.phone,
                message = "${context.getString(R.string.hello)} $clientName \uD83E\uDD17\n${
                    context.getString(R.string.date_hour_wpp_message_1)
                } $appointmentDate ${context.getString(R.string.date_hour_wpp_message_2)} $appointmentHour hs, ${
                    context.getString(
                        R.string.date_hour_wpp_message_3
                    )
                }"
            )
        }
    }
}