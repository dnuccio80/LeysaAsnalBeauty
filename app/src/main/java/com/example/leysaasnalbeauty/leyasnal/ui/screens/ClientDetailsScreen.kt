//package com.example.leysaasnalbeauty.leyasnal.ui.screens
//
//import androidx.compose.foundation.BorderStroke
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.PaddingValues
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.foundation.verticalScroll
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
//import androidx.compose.material3.Card
//import androidx.compose.material3.CardDefaults
//import androidx.compose.material3.HorizontalDivider
//import androidx.compose.material3.Icon
//import androidx.compose.material3.TextField
//import androidx.compose.material3.TextFieldDefaults
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.saveable.rememberSaveable
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.focus.FocusRequester
//import androidx.compose.ui.focus.focusRequester
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.res.stringResource
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.window.Dialog
//import com.example.leysaasnalbeauty.R
//import com.example.leysaasnalbeauty.leyasnal.ui.AppViewModel
//import com.example.leysaasnalbeauty.leyasnal.ui.components.AcceptDeclineButtons
//import com.example.leysaasnalbeauty.leyasnal.ui.components.EditIcon
//import com.example.leysaasnalbeauty.leyasnal.ui.components.FirstTitleText
//import com.example.leysaasnalbeauty.leyasnal.ui.components.MainTextField
//import com.example.leysaasnalbeauty.leyasnal.ui.components.SecondTitleText
//import com.example.leysaasnalbeauty.leyasnal.ui.components.ThirdTitleText
//import com.example.leysaasnalbeauty.leyasnal.ui.dataclasses.ClientDataClass
//import com.example.leysaasnalbeauty.ui.theme.AccentColor
//import com.example.leysaasnalbeauty.ui.theme.PositiveColor
//import com.example.leysaasnalbeauty.ui.theme.SecondaryBackgroundColor
//
//@Composable
//fun ClientDetailsScreen(
//    innerPadding: PaddingValues,
//    viewModel: AppViewModel,
//    clientId: Int,
//    onBackButtonClick:() -> Unit
//) {
//
//    val focusRequester = remember { FocusRequester() }
//
//    var showEditNameDialog by rememberSaveable { mutableStateOf(false) }
//    var showEditPhoneNumberDialog by rememberSaveable { mutableStateOf(false) }
//
//    var clientName by rememberSaveable { mutableStateOf(client.name) }
//    var clientPhoneNumber by rememberSaveable { mutableStateOf(client.phone) }
//    var clientDetails by rememberSaveable { mutableStateOf(client.details) }
//
//    var editDetailsMode by rememberSaveable { mutableStateOf(false) }
//
//    LaunchedEffect(editDetailsMode) {
//        if(editDetailsMode) focusRequester.requestFocus()
//    }
//
//    Box(
//        Modifier
//            .fillMaxSize()
//            .verticalScroll(rememberScrollState())
//            .background(Color.Black)
//            .padding(innerPadding)
//    ) {
//        Column(
//            Modifier
//                .fillMaxWidth()
//                .padding(vertical = 16.dp),
//            verticalArrangement = Arrangement.spacedBy(16.dp)
//        ) {
//            Icon(
//                Icons.AutoMirrored.Default.KeyboardArrowLeft,
//                contentDescription = "Back Arrow",
//                tint = Color.White,
//                modifier = Modifier
//                    .size(32.dp)
//                    .clickable {
//                        onBackButtonClick()
//                    }
//            )
//            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
//                Row(
//                    verticalAlignment = Alignment.CenterVertically,
//                    horizontalArrangement = Arrangement.spacedBy(8.dp)
//                ) {
//                    FirstTitleText(clientName)
//                    EditIcon(24.dp) {
//                        showEditNameDialog = true
//                    }
//                }
//            }
//            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
//                Row(
//                    verticalAlignment = Alignment.CenterVertically,
//                    horizontalArrangement = Arrangement.spacedBy(8.dp)
//                ) {
//                    ThirdTitleText("${stringResource(R.string.phone)} $clientPhoneNumber")
//                    EditIcon(20.dp) { showEditPhoneNumberDialog = true }
//                }
//            }
//            Card(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(8.dp),
//                colors = CardDefaults.cardColors(
//                    containerColor = if(editDetailsMode) PositiveColor else SecondaryBackgroundColor
//                ),
//                shape = RoundedCornerShape(16.dp)
//            ) {
//                Column(
//                    Modifier
//                        .fillMaxWidth()
//                        .padding(16.dp),
//                    verticalArrangement = Arrangement.spacedBy(16.dp)
//                ) {
//                    Column(
//                        Modifier.fillMaxWidth(),
//                        verticalArrangement = Arrangement.spacedBy(8.dp)
//                    ) {
//                        Row(
//                            Modifier.fillMaxWidth(),
//                            verticalAlignment = Alignment.CenterVertically
//                        ) {
//                            Box(modifier = Modifier.weight(1f)) {
//                                SecondTitleText(stringResource(R.string.details))
//                            }
//                            EditIcon(24.dp, if(editDetailsMode) R.drawable.ic_check else R.drawable.ic_edit ) {
//                                editDetailsMode = !editDetailsMode
//                            }
//                        }
//                        HorizontalDivider(
//                            Modifier.fillMaxWidth(),
//                            thickness = 3.dp,
//                            color = Color.White
//                        )
//                    }
//                    TextField(
//                        value = clientDetails,
//                        onValueChange = {
//                            clientDetails = it
//                        },
//                        modifier = Modifier.focusRequester(focusRequester),
//                        enabled = editDetailsMode,
//                        colors = TextFieldDefaults.colors(
//                            focusedContainerColor = Color.Transparent,
//                            unfocusedContainerColor = Color.Transparent,
//                            focusedTextColor = Color.White,
//                            unfocusedTextColor = Color.White,
//                            focusedIndicatorColor = Color.Transparent,
//                            unfocusedIndicatorColor = Color.Transparent,
//                            disabledIndicatorColor = Color.Transparent,
//                            disabledContainerColor = Color.Transparent,
//                            disabledTextColor = Color.White
//                        )
//                    )
//                }
//            }
//        }
//    }
//
//    // Edit Name Dialog
//    EditClientDetailsDialog(
//        showEditNameDialog,
//        clientName,
//        title = stringResource(R.string.modify_name),
//        label = stringResource(R.string.name),
//        icon = R.drawable.ic_name,
//        onDismiss = {
//            showEditNameDialog = false
//        }, onConfirm = {
//            clientName = it
//            showEditNameDialog = false
//            viewModel.updateClient(client.copy(name = clientName))
//        }
//    )
//
//    // Edit Phone Number Dialog
//    EditClientDetailsDialog(
//        show = showEditPhoneNumberDialog,
//        value = clientPhoneNumber,
//        title = stringResource(R.string.modify_phone_number),
//        label = stringResource(R.string.phone),
//        isNumeric = true,
//        isPhone = true,
//        icon = R.drawable.ic_phone,
//        onDismiss = { showEditPhoneNumberDialog = false },
//        onConfirm = {
//            clientPhoneNumber = it
//            showEditPhoneNumberDialog = false
//            viewModel.updateClient(client.copy(phone = clientPhoneNumber))
//        }
//    )
//}
//
//@Composable
//fun EditClientDetailsDialog(
//    show: Boolean,
//    value: String,
//    title:String,
//    label: String,
//    icon: Int,
//    isNumeric: Boolean = false,
//    isPhone: Boolean = false,
//    onDismiss: () -> Unit,
//    onConfirm: (String) -> Unit
//) {
//
//    if (!show) return
//
//    var clientValue by rememberSaveable { mutableStateOf(value) }
//
//    Dialog(
//        onDismissRequest = { onDismiss() },
//    ) {
//        Card(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(horizontal = 32.dp),
//            colors = CardDefaults.cardColors(
//                containerColor = AccentColor
//            ),
//            border = BorderStroke(1.dp, color = Color.White)
//        ) {
//            Column(
//                Modifier
//                    .fillMaxWidth()
//                    .padding(vertical = 32.dp, horizontal = 16.dp),
//                verticalArrangement = Arrangement.spacedBy(24.dp)
//            ) {
//                Column(Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(8.dp)) {
//                    SecondTitleText(title)
//                    HorizontalDivider(
//                        Modifier.fillMaxWidth(),
//                        thickness = 2.dp,
//                        color = Color.White
//                    )
//                }
//                MainTextField(
//                    value = clientValue,
//                    isNumeric = isNumeric,
//                    isPhone = isPhone,
//                    onValueChange = { clientValue = it },
//                    label = label,
//                    icon = icon
//                )
//                AcceptDeclineButtons(
//                    acceptText = stringResource(R.string.modify),
//                    declineText = stringResource(R.string.cancel),
//                    onAccept = {
//                        if (clientValue.isNotEmpty()) {
//                            onConfirm(clientValue)
//                        }
//                    },
//                    onDecline = { onDismiss() }
//                )
//            }
//        }
//    }
//}