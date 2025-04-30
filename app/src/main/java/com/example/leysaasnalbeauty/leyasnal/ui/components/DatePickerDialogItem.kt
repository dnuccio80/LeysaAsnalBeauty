package com.example.leysaasnalbeauty.leyasnal.ui.components

import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.leysaasnalbeauty.R
import com.example.leysaasnalbeauty.ui.theme.AccentColor

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