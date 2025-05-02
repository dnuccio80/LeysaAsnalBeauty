package com.example.leysaasnalbeauty.leyasnal.ui.screens

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.leysaasnalbeauty.R
import com.example.leysaasnalbeauty.leyasnal.ui.AppViewModel
import com.example.leysaasnalbeauty.leyasnal.ui.components.AcceptDeclineButtons
import com.example.leysaasnalbeauty.leyasnal.ui.components.BodyText
import com.example.leysaasnalbeauty.leyasnal.ui.components.ButtonTextItem
import com.example.leysaasnalbeauty.leyasnal.ui.components.FirstTitleText
import com.example.leysaasnalbeauty.leyasnal.ui.components.ThirdTitleText
import com.example.leysaasnalbeauty.ui.theme.DarkAccentColor
import java.time.LocalDateTime
import java.util.Calendar

@Composable
fun SelectDateTImeForAppointmentScreen(
    innerPadding: PaddingValues,
    viewModel: AppViewModel,
    clientId: Int
) {

    viewModel.setClientId(clientId)

    val client by viewModel.clientDetails.collectAsState()
    val context = LocalContext.current
    var selectedDateTime by remember { mutableStateOf<LocalDateTime?>(null) }

    if (client == null) return

    Box(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Color.Black)
            .padding(innerPadding)
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Column(Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                FirstTitleText("${stringResource(R.string.appointment_for)} ${client!!.name}")
                HorizontalDivider(Modifier.fillMaxWidth())
            }
            Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Image(
                    painterResource(R.drawable.image_date),
                    contentDescription = "date image",
                    modifier = Modifier.size(150.dp)
                )
            }
            Column(Modifier.fillMaxWidth().padding(horizontal = 32.dp), horizontalAlignment = Alignment.Start, verticalArrangement = Arrangement.spacedBy(8.dp)) {
                if(selectedDateTime == null){
                    ThirdTitleText("${stringResource(R.string.client)}: ${client!!.name}")
                    ThirdTitleText("${stringResource(R.string.date)}: no definido")
                    ThirdTitleText("${stringResource(R.string.appointment_hour)}: no definido")
                } else {
                    val hour = if(selectedDateTime!!.hour < 10) "0${selectedDateTime!!.hour}" else selectedDateTime!!.hour
                    val minute = if (selectedDateTime!!.minute < 10) "0${selectedDateTime!!.minute}" else selectedDateTime!!.minute

                    ThirdTitleText("${stringResource(R.string.client)}: ${client!!.name}")
                    ThirdTitleText("${stringResource(R.string.date)}: ${selectedDateTime!!.dayOfMonth}/${selectedDateTime!!.monthValue}/${selectedDateTime!!.year}")
                    ThirdTitleText("${stringResource(R.string.appointment_hour)}: $hour:$minute")
                }
            }
            Box(Modifier
                .fillMaxWidth(), contentAlignment = Alignment.Center) {

            }

            Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                ButtonTextItem(
                    text = stringResource(R.string.select_date_time),
                    buttonColor = DarkAccentColor
                ) {
                    val calendar = Calendar.getInstance()
                    DatePickerDialog(
                        context,
                        { _, year, month, day ->
                            TimePickerDialog(
                                context,
                                { _, hour, minute ->
                                    selectedDateTime =
                                        LocalDateTime.of(year, month + 1, day, hour, minute)
                                },
                                calendar.get(Calendar.HOUR_OF_DAY),
                                calendar.get(Calendar.MINUTE),
                                true
                            ).show()
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                    ).show()
                }
            }

            AcceptDeclineButtons(
                onAccept = { },
                acceptEnabled = false,
                onDecline = { }
            )
//            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceAround) {
//                ButtonTextItem(
//                    text = stringResource(R.string.back),
//                    buttonColor = DarkAccentColor
//                ) {
//
//                }
//            }

        }
    }
}