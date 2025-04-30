package com.example.leysaasnalbeauty.leyasnal.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.leysaasnalbeauty.R
import com.example.leysaasnalbeauty.leyasnal.ui.components.BodyText
import com.example.leysaasnalbeauty.leyasnal.ui.components.ButtonTextItem
import com.example.leysaasnalbeauty.leyasnal.ui.components.FirstTitleText
import com.example.leysaasnalbeauty.leyasnal.ui.components.MainTextField
import com.example.leysaasnalbeauty.leyasnal.ui.dataclasses.ClientDataClass
import com.example.leysaasnalbeauty.ui.theme.AccentColor
import java.time.Instant
import java.time.ZoneId

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddClientScreen(
    innerPadding: PaddingValues,
    onAddClient: (ClientDataClass) -> Unit,
    onCancel: () -> Unit
) {

    var name by rememberSaveable { mutableStateOf("") }
    var phoneNumber by rememberSaveable { mutableStateOf("") }
    var details by rememberSaveable { mutableStateOf("") }

    var isButtonEnabled by rememberSaveable { mutableStateOf(false) }

    val datePickerState = rememberDatePickerState()
    var showDatePickerDialog by rememberSaveable { mutableStateOf(false) }

    val date = datePickerState.selectedDateMillis
    var dateValue by rememberSaveable { mutableStateOf("") }

    date?.let {
        val instant = Instant.ofEpochMilli(it).atZone(ZoneId.of("UTC")).toLocalDate()
        dateValue = "${instant.dayOfMonth}/${instant.monthValue}/${instant.year}"
    }

    LaunchedEffect(name, phoneNumber, details, dateValue) {
        isButtonEnabled = name.isNotEmpty() && phoneNumber.isNotEmpty() && details.isNotEmpty() && dateValue.isNotEmpty()
    }

    Box(
        Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(innerPadding)
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Spacer(Modifier.size(0.dp))
            Column(Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                FirstTitleText(stringResource(R.string.add_client))
                HorizontalDivider(Modifier.fillMaxWidth(), thickness = 2.dp, color = Color.White)
            }
            Spacer(Modifier.size(24.dp))
            MainTextField(
                value = name,
                onValueChange = { newName ->
                    name = newName
                },
                isNumeric = false,
                label = stringResource(R.string.name),
                icon = R.drawable.ic_name
            )
            MainTextField(
                value = phoneNumber,
                onValueChange = { newPhoneNumber ->
                    phoneNumber = newPhoneNumber
                },
                isNumeric = true,
                isPhone = true,
                label = stringResource(R.string.phone_number),
                icon = R.drawable.ic_phone
            )
            MainTextField(
                value = details,
                onValueChange = { newDetails ->
                    details = newDetails
                },
                singleLine = false,
                maxLines = 10,
                isNumeric = false,
                label = stringResource(R.string.details),
                icon = R.drawable.ic_info,
            )
            DatePickerTextField(dateValue = dateValue) {
                showDatePickerDialog = true
            }
            Spacer(Modifier.size(32.dp))
            Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    ButtonTextItem(
                        text = stringResource(R.string.cancel),
                        enabled = true,
                        buttonColor = AccentColor
                    ) {
                        onCancel()
                    }
                    ButtonTextItem(
                        text = stringResource(R.string.add_client),
                        enabled = isButtonEnabled,
                        buttonColor = AccentColor
                    ) {
                        onAddClient(
                            ClientDataClass(
                                name = name,
                                phone = phoneNumber,
                                details = details
                            )
                        )
                    }
                }
            }
            DatePickerDialogItem(
                showDatePickerDialog,
                onDismiss = { showDatePickerDialog = false },
                onConfirm = { showDatePickerDialog = false },
                datePickerState
            )
        }
    }
}

@Composable
fun DatePickerTextField(dateValue: String, onClick: () -> Unit) {
    TextField(
        value = dateValue,
        onValueChange = {},
        placeholder = {
            BodyText(stringResource(R.string.birthday_date))
        },
        leadingIcon = {
            Icon(
                painterResource(R.drawable.ic_birthday),
                contentDescription = "bd icon",
                tint = Color.White
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedIndicatorColor = Color.White,
            unfocusedIndicatorColor = Color.White,
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            disabledTextColor = Color.White,
            disabledPlaceholderColor = Color.White,
            disabledLeadingIconColor = Color.White,
            disabledContainerColor = Color.Transparent,
            disabledIndicatorColor = Color.White
        ),
        enabled = false,
        trailingIcon = {
            Icon(
                Icons.Default.KeyboardArrowDown,
                contentDescription = "arrow down",
                tint = Color.White
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerDialogItem(
    show: Boolean,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    datePickerState: DatePickerState
) {
    if (show) {
        DatePickerDialog(
            onDismissRequest = { onDismiss() },
            confirmButton = {
                ButtonTextItem(
                    text = stringResource(R.string.confirm),
                    buttonColor = AccentColor
                ) {
                    onConfirm()
                }
            },
            dismissButton = {
                ButtonTextItem(
                    text = stringResource(R.string.cancel),
                    buttonColor = AccentColor
                ) {
                    onDismiss()
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
}