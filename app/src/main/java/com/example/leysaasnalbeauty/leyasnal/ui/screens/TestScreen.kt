package com.example.leysaasnalbeauty.leyasnal.ui.screens

import android.app.TimePickerDialog
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import android.app.DatePickerDialog
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.leysaasnalbeauty.leyasnal.ui.components.BodyText
import com.example.leysaasnalbeauty.leyasnal.ui.components.MainTextField
import com.itextpdf.layout.element.Text
import java.time.LocalDateTime
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TestScreen(innerPadding: PaddingValues) {

    val context = LocalContext.current
    var clientName by remember { mutableStateOf("") }
    var selectedDateTime by remember { mutableStateOf<LocalDateTime?>(null) }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .background(Color.Red)
    ) {
        MainTextField(
            value = clientName,
            isNumeric = false,
            onValueChange = { clientName = it }
        )
        MainTextField(
            value = clientName,
            isNumeric = false,
            onValueChange = { clientName = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            val calendar = Calendar.getInstance()
            DatePickerDialog(
                context,
                { _, year, month, day ->
                    TimePickerDialog(
                        context,
                        { _, hour, minute ->
                            selectedDateTime = LocalDateTime.of(year, month + 1, day, hour, minute)
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
        }) {
            BodyText("Seleccionar fecha y hora")
        }

        Spacer(modifier = Modifier.height(16.dp))

        selectedDateTime?.let {
            Text("Selected: $it")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                Log.i("Damian", "Cita Agendada para $clientName, $selectedDateTime")
                selectedDateTime?.let { dateTime ->
//                    viewModel.addAppointment(clientName, dateTime)
                    clientName = ""
                    selectedDateTime = null
                }
            },
            enabled = clientName.isNotBlank() && selectedDateTime != null
        ) {
            BodyText("Agendar Fecha")
        }
    }


}

