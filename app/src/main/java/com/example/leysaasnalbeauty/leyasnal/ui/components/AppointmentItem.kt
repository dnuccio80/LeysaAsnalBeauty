package com.example.leysaasnalbeauty.leyasnal.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.example.leysaasnalbeauty.R
import com.example.leysaasnalbeauty.leyasnal.data.appoointments.AppointmentWithClient
import com.example.leysaasnalbeauty.leyasnal.ui.helper.sendWppMessage
import com.example.leysaasnalbeauty.ui.theme.DarkAccentColor

@Composable
fun AppointmentItem(appointment: AppointmentWithClient, showDate:Boolean) {
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
            if(showDate) {
                BodyText("$appointmentDate: ${appointment.client.name} - ${appointment.appointment.serviceType} - $appointmentHour")
            } else {
                BodyText("${appointment.client.name} - ${appointment.appointment.serviceType} - $appointmentHour")
            }
        }
//        ButtonTextItem(
//            text = stringResource(R.string.remember),
//            buttonColor = DarkAccentColor
//        ) {
//            sendWppMessage(
//                context = context,
//                phoneNumber = appointment.client.phone,
//                message = "${context.getString(R.string.hello)} $clientName \uD83E\uDD17\n${
//                    context.getString(R.string.date_hour_wpp_message_1)
//                } $appointmentDate ${context.getString(R.string.date_hour_wpp_message_2)} $appointmentHour hs, ${
//                    context.getString(
//                        R.string.date_hour_wpp_message_3
//                    )
//                }"
//            )
//        }
    }
}