package com.example.leysaasnalbeauty.leyasnal.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.leysaasnalbeauty.R
import com.example.leysaasnalbeauty.ui.theme.AccentColor

@Composable
fun AddBalanceDialog(show: Boolean, onDismiss: () -> Unit, onConfirm: (String) -> Unit) {

    if (!show) return

    var value by rememberSaveable { mutableStateOf("") }

    val list = listOf(
        stringResource(R.string.positive_balance),
        stringResource(R.string.negative_balance),
    )

    Dialog(
        onDismissRequest = { onDismiss() },
    ) {
        Box(
            modifier = Modifier
                .width(250.dp)
                .border(1.dp, color = Color.White)
                .background(AccentColor)
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Column(Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    SecondTitleText(stringResource(R.string.add_balance))
                    HorizontalDivider(
                        Modifier.fillMaxWidth(),
                        thickness = 2.dp,
                        color = Color.White
                    )
                }
                MainTextField(
                    value = value,
                    isNumeric = true,
                    isPhone = false,
                    onValueChange = { value = it },
                    label = stringResource(R.string.amount),
                    icon = R.drawable.ic_dollar_sign
                )
                RadioButtonsGroup(list)
                AcceptDeclineButtons(
                    onAccept = {
                        if(value.isNotEmpty()){
                            onConfirm(value)
                        }
                    },
                    onDecline = {
                        onDismiss()
                    }
                )
            }
        }
    }
}

@Composable
fun RadioButtonsGroup(list: List<String>) {

    var selected by rememberSaveable { mutableStateOf(list[0]) }

    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        list.forEach { listItem ->
            RadioButtonItem(text = listItem, selected) {newSelected -> selected = newSelected }

        }
    }
}

@Composable
fun RadioButtonItem(text: String, selected: String, onClick: (String) -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.clickable {
        onClick(text)
    }, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
        RadioButton(
            selected = selected == text,
            onClick = {
                onClick(text)
            },
            colors = RadioButtonDefaults.colors(
                selectedColor = Color.White,
                unselectedColor = Color.White
            )
        )
        BodyText(text)
    }
}