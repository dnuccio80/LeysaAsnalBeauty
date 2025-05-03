package com.example.leysaasnalbeauty.leyasnal.ui.screens

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import com.example.leysaasnalbeauty.leyasnal.ui.components.BodyText
import com.example.leysaasnalbeauty.leyasnal.ui.components.ButtonTextItem
import com.example.leysaasnalbeauty.leyasnal.ui.components.FirstTitleText
import com.example.leysaasnalbeauty.leyasnal.ui.components.MainTextField
import com.example.leysaasnalbeauty.leyasnal.ui.components.ThirdTitleText
import com.example.leysaasnalbeauty.ui.theme.DarkAccentColor
import java.time.LocalDateTime
import java.util.Calendar

@Composable
fun SelectDateTImeForAppointmentScreen(
    innerPadding: PaddingValues,
    viewModel: AppViewModel,
    clientId: Int,
    onCancelButtonClicked: () -> Unit
) {

    viewModel.setClientId(clientId)

    val client by viewModel.clientDetails.collectAsState()
    val context = LocalContext.current
    var selectedDateTime by remember { mutableStateOf<LocalDateTime?>(null) }
    var serviceSelected by rememberSaveable { mutableStateOf("") }

    val serviceTypeList = listOf(
        "Uñas",
        "Perfilado",
        "Lifting",
        "Maquillaje"
    )

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
                            DisableTextField("${stringResource(R.string.client)}: ${client!!.name}")
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
                            DisableTextField("${stringResource(R.string.client)}: ${client!!.name}")
                            DisableTextField("${stringResource(R.string.date)}: ${selectedDateTime!!.dayOfMonth}/${selectedDateTime!!.monthValue}/${selectedDateTime!!.year}")
                            DisableTextField("${stringResource(R.string.appointment_hour)}: $hour:$minute")
                        }
                    }
                }
            }
            Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                SelectMenu(
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
                onAccept = {
                    viewModel.addAppointment(
                        clientId = clientId,
                        serviceType = serviceSelected,
                        date = selectedDateTime!!
                    )
                    selectedDateTime = null
                    Toast.makeText(context, "Cita agendada", Toast.LENGTH_SHORT).show()
                    onCancelButtonClicked()
                    serviceSelected = ""
                },
                acceptEnabled = selectedDateTime != null && serviceSelected.isNotEmpty(),
                onDecline = {
                    onCancelButtonClicked()
                }
            )
        }
    }
}

@Composable
fun SelectMenu(list: List<String>, serviceSelected: String, onValueChange: (String) -> Unit) {

    var expandMenu by rememberSaveable { mutableStateOf(false) }

    Column(Modifier.padding(horizontal = 32.dp)) {
        TextField(
            value = serviceSelected,
            modifier = Modifier
                .clickable {
                    expandMenu = !expandMenu
                }
                .fillMaxWidth(),
            onValueChange = { onValueChange(it) },
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                disabledTextColor = Color.White,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                disabledIndicatorColor = DarkAccentColor
            ),
            placeholder = {
                BodyText("Elegé el servicio...")
            },
            trailingIcon = {
                Icon(
                    Icons.Default.KeyboardArrowDown,
                    contentDescription = "arrow icon",
                    tint = Color.White
                )
            },
            enabled = false
        )
        DropdownMenu(
            expanded = expandMenu,
            modifier = Modifier.width(250.dp),
            onDismissRequest = { expandMenu = false },
            containerColor = DarkAccentColor
        ) {
            list.forEach {
                DropdownMenuItem(
                    text = { BodyText(it) },
                    onClick = {
                        onValueChange(it)
                        expandMenu = false
                    }
                )
            }
        }
    }
}

@Composable
fun DisableTextField(value: String) {
    TextField(
        value = value,
        modifier = Modifier.fillMaxWidth(),
        onValueChange = { },
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            disabledTextColor = Color.White,
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
            disabledIndicatorColor = DarkAccentColor
        ),
        enabled = false
    )
}