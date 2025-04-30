package com.example.leysaasnalbeauty.leyasnal.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.leysaasnalbeauty.leyasnal.ui.components.BodyText
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TestScreen(innerPadding: PaddingValues) {

    val datePickerState = rememberDatePickerState()
    var showDatePickerDialog by rememberSaveable { mutableStateOf(false) }

    Box(
        Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(innerPadding)
    ) {
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {

            Button(
                onClick = { showDatePickerDialog = true }
            ) {
                BodyText("Show Date Picker")
            }
            if(showDatePickerDialog) {
                DatePickerDialog(
                    onDismissRequest = { showDatePickerDialog = false },
                    confirmButton = {
                        Button(
                            onClick = {
                                showDatePickerDialog = false
                            }
                        ) {
                            BodyText("Confirmar")
                        }
                    },
                    dismissButton = {
                        Button(
                            onClick = {
                                showDatePickerDialog = false
                            }
                        ) {
                            BodyText("Cerrar")
                        }
                    }
                ) {
                    DatePicker(state = datePickerState)
                }
            }

            val date = datePickerState.selectedDateMillis
            val today = LocalDate.now()

            date?.let{
                val instant = Instant.ofEpochMilli(it).atZone(ZoneId.of("UTC")).toLocalDate()

                if(instant.dayOfMonth == today.dayOfMonth && instant.month == today.month){
                    val age = today.year - instant.year
                    BodyText("Feliz Cumpleaños N°$age!! :)")
                } else if (instant.dayOfYear - today.dayOfYear == 1){
                    BodyText("Mañana es tu cumpleaños !!")
                }
            }

        }

    }
}