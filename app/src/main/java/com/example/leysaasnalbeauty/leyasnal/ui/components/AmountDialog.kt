package com.example.leysaasnalbeauty.leyasnal.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.leysaasnalbeauty.R
import com.example.leysaasnalbeauty.ui.theme.DarkGrayColor

@Composable
fun AmountDialog(show: Boolean, text: String, onDismiss: () -> Unit, onConfirm: (Int, String) -> Unit) {
    if (!show) return

    var amount by rememberSaveable { mutableStateOf("") }
    var description by rememberSaveable { mutableStateOf("") }

    Dialog(
        onDismissRequest = { onDismiss() },
    ) {
        Box(
            modifier = Modifier
                .width(250.dp)
                .border(1.dp, color = Color.White)
                .background(DarkGrayColor)
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Column(Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    SecondTitleText(text)
                    HorizontalDivider(
                        Modifier.fillMaxWidth(),
                        thickness = 3.dp,
                        color = Color.White
                    )
                }
                MainTextField(
                    value = amount,
                    onValueChange = { newAmount ->
                        amount = newAmount
                    },
                    isNumeric = true,
                    label = stringResource(R.string.amount),
                    icon = R.drawable.ic_dollar_sign
                )
                MainTextField(
                    value = description,
                    onValueChange = { newDescription ->
                        description = newDescription
                    },
                    isNumeric = false,
                    label = stringResource(R.string.description),
                    icon = R.drawable.ic_info
                )
                AcceptDeclineButtons(
                    onAccept = {
                        if (amount.isNotEmpty() && description.isNotEmpty()) {
                            onDismiss()
                            onConfirm(amount.toInt(), description)
                        }
                    },
                    onDecline = { onDismiss() }
                )
            }
        }
    }
}