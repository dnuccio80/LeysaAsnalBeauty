package com.example.leysaasnalbeauty.leyasnal.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import com.example.leysaasnalbeauty.leyasnal.ui.components.AcceptDeclineButtons
import com.example.leysaasnalbeauty.leyasnal.ui.components.BodyText
import com.example.leysaasnalbeauty.leyasnal.ui.components.ClientItem
import com.example.leysaasnalbeauty.leyasnal.ui.components.FirstTitleText
import com.example.leysaasnalbeauty.leyasnal.ui.components.MainTextField
import com.example.leysaasnalbeauty.leyasnal.ui.components.RadioButtonsGroup
import com.example.leysaasnalbeauty.leyasnal.ui.components.SecondTitleText
import com.example.leysaasnalbeauty.leyasnal.ui.components.ThirdTitleText
import com.example.leysaasnalbeauty.leyasnal.ui.dataclasses.ClientDataClass
import com.example.leysaasnalbeauty.ui.theme.AccentColor
import com.example.leysaasnalbeauty.ui.theme.NegativeColor
import com.example.leysaasnalbeauty.ui.theme.PositiveColor
import com.example.leysaasnalbeauty.ui.theme.SecondaryBackgroundColor

@Composable
fun NotifyClientScreen(
    innerPadding: PaddingValues,
    viewModel: AppViewModel,
    onCancel: () -> Unit
) {
    val query by viewModel.query.collectAsState()
    val clientList by viewModel.filteredClients.collectAsState()

    var showNotifyDialog by rememberSaveable { mutableStateOf(false) }
    var clientId by rememberSaveable { mutableIntStateOf(0) }

    Box(
        Modifier
            .fillMaxSize()
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
                FirstTitleText(stringResource(R.string.notify_client))
                HorizontalDivider(Modifier.fillMaxWidth())
            }
            Spacer(Modifier.size(0.dp))
            MainTextField(
                value = query,
                isNumeric = false,
                isPhone = false,
                onValueChange = { viewModel.onQueryChanged(it) },
                label = stringResource(R.string.search_client),
                icon = R.drawable.ic_search
            )
            if (clientList.isEmpty()) {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    BodyText(stringResource(R.string.empty_client_list))
                }
            } else {
                Card(
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = SecondaryBackgroundColor
                    ),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Column(
                        Modifier
                            .fillMaxWidth()
                            .verticalScroll(rememberScrollState())
                            .padding(8.dp),
                        verticalArrangement = Arrangement.spacedBy(2.dp)
                    ) {
                        clientList.forEachIndexed { index, client ->
                            ClientItem(client) {
                                clientId = client.id
                                showNotifyDialog = true
                            }

                            if (index < clientList.lastIndex) {
                                HorizontalDivider(
                                    Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 8.dp),
                                    thickness = 2.dp,
                                    color = Color.Gray
                                )
                            }
                        }
                    }
                }
            }
        }
    }
    NotifyClientDialog(
        show = showNotifyDialog,
        clientId = clientId,
        viewModel,
        onDismiss = {
            showNotifyDialog = false
        }
    )
}

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
        stringResource(R.string.customized_message)
    )

    val context = LocalContext.current
    var selectedRadioButton by rememberSaveable { mutableStateOf(radioButtonList[0]) }
    var isCustomizedMessage by rememberSaveable { mutableStateOf(false) }
    var customMessage by rememberSaveable { mutableStateOf("") }


    if (selectedRadioButton == context.getString(R.string.customized_message)) {
        isCustomizedMessage = true
    } else {
        isCustomizedMessage = false
    }

    // Search client by id with viewModel
    val client = ClientDataClass(0, "Rosa Meltrozo", "", "")

    Dialog(
        onDismissRequest = { onDismiss() },
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .border(2.dp, color = Color.White)
                .background(AccentColor)
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Column(Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    SecondTitleText(client.name)
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
                MainTextField(
                    value = customMessage,
                    isNumeric = false,
                    isPhone = false,
                    onValueChange = { customMessage = it },
                    label = stringResource(R.string.message),
                    maxLines = 5,
                    enabled = isCustomizedMessage,
                    singleLine = false,
                    icon = R.drawable.ic_chat
                )
                AcceptDeclineButtons(
                    acceptText = stringResource(R.string.send),
                    declineText = stringResource(R.string.cancel),
                    acceptButtonColor = PositiveColor,
                    declineButtonColor = NegativeColor,
                    onAccept = { },
                    onDecline = { onDismiss() }
                )
            }
        }
    }
}

