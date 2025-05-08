package com.example.leysaasnalbeauty.leyasnal.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.leysaasnalbeauty.R
import com.example.leysaasnalbeauty.leyasnal.ui.AppViewModel
import com.example.leysaasnalbeauty.leyasnal.ui.helpers.sendWppMessage
import com.example.leysaasnalbeauty.ui.theme.DarkGrayColor
import com.example.leysaasnalbeauty.ui.theme.DarkAccentColor
import com.example.leysaasnalbeauty.ui.theme.NegativeColor

@Composable
fun NotifyClientDialog(
    show: Boolean,
    clientId: Int,
    viewModel: AppViewModel,
    onDismiss: () -> Unit
) {

    if (!show) return

    val radioButtonList = listOf(
        stringResource(R.string.appointment_message),
        stringResource(R.string.welcome_message),
        stringResource(R.string.after_appointment_message),
        stringResource(R.string.customized_message)
    )

    viewModel.setClientId(clientId)

    val context = LocalContext.current
    var selectedRadioButton by rememberSaveable { mutableStateOf(radioButtonList[0]) }
    var isCustomizedMessage by rememberSaveable { mutableStateOf(false) }
    var customMessage by rememberSaveable { mutableStateOf("") }
    var appointmentHour by rememberSaveable { mutableStateOf("") }
    var appointmentDate by rememberSaveable { mutableStateOf("") }
    val client by viewModel.clientDetails.collectAsState()

    if (selectedRadioButton == context.getString(R.string.customized_message)) {
        isCustomizedMessage = true
    } else {
        isCustomizedMessage = false
    }

    if (client == null) return

    val firstName = client!!.name.split(" ").first()

    Dialog(
        onDismissRequest = { onDismiss() },
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .border(1.dp, color = Color.White)
                .background(DarkGrayColor)
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Column(Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    SecondTitleText(client!!.name)
                    HorizontalDivider(
                        Modifier.fillMaxWidth(),
                        thickness = 2.dp,
                        color = Color.White
                    )
                }
                RadioButtonsGroup(
                    list = radioButtonList,
                    selected = selectedRadioButton,
                    onSelectedChanged = {
                        selectedRadioButton = it
                    }
                )
                when (selectedRadioButton) {
                    context.getString(R.string.appointment_message) -> {
                        MainTextField(
                            value = appointmentDate,
                            isNumeric = false,
                            isPhone = false,
                            onValueChange = { appointmentDate = it },
                            label = stringResource(R.string.date),
                            maxLines = 1,
                            singleLine = true,
                            icon = R.drawable.ic_date
                        )
                        MainTextField(
                            value = appointmentHour,
                            isNumeric = false,
                            isPhone = false,
                            onValueChange = { appointmentHour = it },
                            label = stringResource(R.string.appointment_hour),
                            maxLines = 1,
                            singleLine = true,
                            icon = R.drawable.ic_time
                        )
                        AcceptDeclineButtons(
                            acceptText = stringResource(R.string.send),
                            declineText = stringResource(R.string.cancel),
                            acceptButtonColor = DarkAccentColor,
                            acceptEnabled = appointmentHour.isNotEmpty() && appointmentDate.isNotEmpty(),
                            declineButtonColor = NegativeColor,
                            onAccept = {
                                sendWppMessage(
                                    context,
                                    client!!.phone,
                                    message = "${context.getString(R.string.hello)} $firstName \uD83E\uDD17\n${
                                        context.getString(R.string.date_hour_wpp_message_1)
                                    } $appointmentDate ${context.getString(R.string.date_hour_wpp_message_2)} ${appointmentHour}hs, ${
                                        context.getString(
                                            R.string.date_hour_wpp_message_3
                                        )
                                    }"
                                )
                            },
                            onDecline = { onDismiss() }
                        )
                    }

                    context.getString(R.string.customized_message) -> {
                        MainTextField(
                            value = customMessage,
                            isNumeric = false,
                            isPhone = false,
                            onValueChange = { customMessage = it },
                            label = stringResource(R.string.message),
                            maxLines = 5,
                            singleLine = false,
                            icon = R.drawable.ic_chat
                        )
                        AcceptDeclineButtons(
                            acceptText = stringResource(R.string.send),
                            declineText = stringResource(R.string.cancel),
                            acceptEnabled = customMessage.isNotEmpty(),
                            acceptButtonColor = DarkAccentColor,
                            declineButtonColor = NegativeColor,
                            onAccept = {
                                sendWppMessage(
                                    context,
                                    client!!.phone,
                                    message = customMessage
                                )
                            },
                            onDecline = { onDismiss() }
                        )
                    }

                    context.getString(R.string.after_appointment_message) -> {

                        customMessage = ""

                        MainTextField(
                            value = customMessage,
                            isNumeric = false,
                            isPhone = false,
                            enabled = false,
                            onValueChange = { customMessage = it },
                            label = stringResource(R.string.message),
                            maxLines = 5,
                            singleLine = false,
                            icon = R.drawable.ic_chat
                        )

                        AcceptDeclineButtons(
                            acceptText = stringResource(R.string.send),
                            declineText = stringResource(R.string.cancel),
                            acceptButtonColor = DarkAccentColor,
                            declineButtonColor = NegativeColor,
                            onAccept = {
                                sendWppMessage(context, client!!.phone, message = "${context.getString(
                                    R.string.hello)} $firstName \uD83E\uDD17\n${
                                    context.getString(R.string.after_appointment_wpp_message)
                                }")
                            },
                            onDecline = {
                                onDismiss()
                            }
                        )
                    }

                    context.getString(R.string.welcome_message) -> {

                        customMessage = ""

                        MainTextField(
                            value = customMessage,
                            isNumeric = false,
                            isPhone = false,
                            enabled = false,
                            onValueChange = { customMessage = it },
                            label = stringResource(R.string.message),
                            maxLines = 5,
                            singleLine = false,
                            icon = R.drawable.ic_chat
                        )
                        AcceptDeclineButtons(
                            acceptText = stringResource(R.string.send),
                            declineText = stringResource(R.string.cancel),
                            acceptButtonColor = DarkAccentColor,
                            declineButtonColor = NegativeColor,
                            onAccept = {
                                sendWppMessage(
                                    context,
                                    client!!.phone,
                                    message = "${context.getString(R.string.hello)} $firstName \uD83E\uDD17\n${
                                        context.getString(R.string.new_client_message_auto)
                                    }"
                                )
                            },
                            onDecline = { onDismiss() }
                        )
                    }
                }

            }
        }
    }
}

