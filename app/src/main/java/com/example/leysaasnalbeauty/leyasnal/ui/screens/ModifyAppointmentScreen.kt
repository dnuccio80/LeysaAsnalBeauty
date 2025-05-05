package com.example.leysaasnalbeauty.leyasnal.ui.screens

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.leysaasnalbeauty.R
import com.example.leysaasnalbeauty.leyasnal.ui.AppViewModel
import com.example.leysaasnalbeauty.leyasnal.ui.components.AcceptDeclineButtons
import com.example.leysaasnalbeauty.leyasnal.ui.components.AlertDialogItem
import com.example.leysaasnalbeauty.leyasnal.ui.components.ButtonTextItem
import com.example.leysaasnalbeauty.leyasnal.ui.components.DisableTextField
import com.example.leysaasnalbeauty.leyasnal.ui.components.FirstTitleText
import com.example.leysaasnalbeauty.leyasnal.ui.components.SelectableDropdownMenu
import com.example.leysaasnalbeauty.leyasnal.ui.dataclasses.AppointmentDataClass
import com.example.leysaasnalbeauty.ui.theme.DarkAccentColor
import com.example.leysaasnalbeauty.ui.theme.NegativeColor
import java.time.LocalDateTime
import java.util.Calendar

@Composable
fun ModifyAppointmentScreen(
    innerPadding: PaddingValues,
    viewModel: AppViewModel,
    appointmentId: Int,
    onCancelButtonClicked: () -> Unit
) {
    viewModel.setAppointmentId(appointmentId)
    val context = LocalContext.current
    val appointment by viewModel.appointmentDetails.collectAsState()

    if (appointment == null) return
    if (appointment!!.appointment.id != appointmentId) return

    var selectedDateTime by remember { mutableStateOf<LocalDateTime?>(appointment!!.appointment.date) }
    var serviceSelected by rememberSaveable { mutableStateOf(appointment!!.appointment.serviceType) }
    var showAlertDialog by rememberSaveable { mutableStateOf(false) }

    val serviceTypeList = listOf(
        "Uñas",
        "Perfilado",
        "Lifting",
        "Maquillaje"
    )


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
                FirstTitleText("${stringResource(R.string.appointment_for)} ${appointment!!.client.name}")
                HorizontalDivider(Modifier.fillMaxWidth())
            }
            Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Image(
                    painterResource(R.drawable.image_modify_date),
                    contentDescription = "date image",
                    modifier = Modifier.size(150.dp)
                )
            }
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                if (selectedDateTime == null) {
                    Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        Column {
                            DisableTextField("${stringResource(R.string.client)}: ${appointment!!.client.name}")
                            DisableTextField("${stringResource(R.string.date)}: no definido")
                            DisableTextField("${stringResource(R.string.appointment_hour)}: no definido")

                        }
                    }
                } else {
                    val hour =
                        if (selectedDateTime!!.hour < 10) "0${selectedDateTime!!.hour}" else selectedDateTime!!.hour
                    val minute =
                        if (selectedDateTime!!.minute < 10) "0${selectedDateTime!!.minute}" else selectedDateTime!!.minute
                    Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        Column {
                            DisableTextField("${stringResource(R.string.client)}: ${appointment!!.client.name}")
                            DisableTextField("${stringResource(R.string.date)}: ${selectedDateTime!!.dayOfMonth}/${selectedDateTime!!.monthValue}/${selectedDateTime!!.year}")
                            DisableTextField("${stringResource(R.string.appointment_hour)}: $hour:$minute")
                        }
                    }
                }
            }
            Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                SelectableDropdownMenu(
                    list = serviceTypeList,
                    serviceSelected = serviceSelected,
                    onValueChange = { serviceSelected = it },
                )
            }
            Spacer(Modifier.size(32.dp))
            Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                ButtonTextItem(
                    text = stringResource(R.string.select_date_time),
                    buttonColor = DarkAccentColor
                ) {
                    val calendar = Calendar.getInstance()
                    val datePickerDialog = DatePickerDialog(
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
                    )
                    datePickerDialog.datePicker.minDate = calendar.timeInMillis
                    datePickerDialog.show()
                }
            }
            AcceptDeclineButtons(
                onAccept = {
                    val updatedAppointment = AppointmentDataClass(
                        id = appointment!!.appointment.id,
                        clientId = appointment!!.client.id,
                        serviceType = serviceSelected,
                        date = selectedDateTime!!
                    )
                    viewModel.updateAppointment(
                        appointment = updatedAppointment
                    )
                    Toast.makeText(context, "Cita modificada", Toast.LENGTH_SHORT).show()
                    onCancelButtonClicked()
                },
                acceptEnabled = selectedDateTime != null && serviceSelected.isNotEmpty(),
                onDecline = {
                    onCancelButtonClicked()
                }
            )
            Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                ButtonTextItem(stringResource(R.string.delete), buttonColor = NegativeColor) {
                    showAlertDialog = true
                }
            }
            AlertDialogItem(
                show = showAlertDialog,
                text = stringResource(R.string.delete_date_alert_message),
                onDismiss = { showAlertDialog = false },
                onConfirm = {
                    viewModel.deleteAppointment(appointmentId)
                    onCancelButtonClicked()
                    showAlertDialog = false
                }
            )
        }
    }
}