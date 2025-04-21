package com.example.leysaasnalbeauty.leyasnal.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import com.example.leysaasnalbeauty.ui.theme.AccentColor

@Composable
fun EditClientDetailsDialog(
    show: Boolean,
    value: String,
    title: String,
    label: String,
    icon: Int,
    isNumeric: Boolean = false,
    isPhone: Boolean = false,
    onDismiss: () -> Unit,
    onConfirm: (String) -> Unit
) {

    if (!show) return

    var clientValue by rememberSaveable { mutableStateOf(value) }

    Dialog(
        onDismissRequest = { onDismiss() },
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            colors = CardDefaults.cardColors(
                containerColor = AccentColor
            ),
            border = BorderStroke(1.dp, color = Color.White)
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 32.dp, horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                Column(Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    SecondTitleText(title)
                    HorizontalDivider(
                        Modifier.fillMaxWidth(),
                        thickness = 2.dp,
                        color = Color.White
                    )
                }
                MainTextField(
                    value = clientValue,
                    isNumeric = isNumeric,
                    isPhone = isPhone,
                    onValueChange = { clientValue = it },
                    label = label,
                    icon = icon
                )
                AcceptDeclineButtons(
                    acceptText = stringResource(R.string.modify),
                    declineText = stringResource(R.string.cancel),
                    onAccept = {
                        if (clientValue.isNotEmpty()) {
                            onConfirm(clientValue)
                        }
                    },
                    onDecline = { onDismiss() }
                )
            }
        }
    }
}