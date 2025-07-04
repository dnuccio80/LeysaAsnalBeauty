package com.example.leysaasnalbeauty.leyasnal.ui.screens

import android.widget.Toast
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
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.leysaasnalbeauty.R
import com.example.leysaasnalbeauty.leyasnal.ui.AppViewModel
import com.example.leysaasnalbeauty.leyasnal.ui.components.AcceptDeclineButtons
import com.example.leysaasnalbeauty.leyasnal.ui.components.BackButtonItem
import com.example.leysaasnalbeauty.leyasnal.ui.components.BodyText
import com.example.leysaasnalbeauty.leyasnal.ui.components.ButtonTextItem
import com.example.leysaasnalbeauty.leyasnal.ui.components.DisableTextField
import com.example.leysaasnalbeauty.leyasnal.ui.components.FirstTitleText
import com.example.leysaasnalbeauty.leyasnal.ui.components.SecondTitleText
import com.example.leysaasnalbeauty.leyasnal.ui.components.SelectableDropdownMenu
import com.example.leysaasnalbeauty.leyasnal.ui.components.ThirdTitleText
import com.example.leysaasnalbeauty.leyasnal.ui.dataclasses.LoyaltyClientPointsDataClass
import com.example.leysaasnalbeauty.leyasnal.ui.dataclasses.LoyaltyServicePointsDataClass
import com.example.leysaasnalbeauty.ui.theme.NegativeColor
import com.example.leysaasnalbeauty.ui.theme.PositiveColor


@Composable
fun AddLoyaltyPointsScreen(
    viewModel: AppViewModel,
    innerPadding: PaddingValues,
    clientId: Int,
    onBackButtonClicked: () -> Unit,
    onConfirm: () -> Unit
) {

    LaunchedEffect(Unit) {
        viewModel.setClientId(clientId)
    }

    val client by viewModel.clientDetails.collectAsState()
    val clientDetailsLoyalty by viewModel.clientDetailsLoyaltyPoints.collectAsState()

    if (client == null || client?.id != clientId) return
    if (clientDetailsLoyalty == null) return

    val context = LocalContext.current

    val serviceTypeData: List<LoyaltyServicePointsDataClass> by viewModel.servicePointsLoyaltyList.collectAsState()

    val serviceType: List<String> = serviceTypeData.map {
        it.service
    }

    if (serviceTypeData.isEmpty()) {
        Box(
            Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(innerPadding)
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                BackButtonItem(onBackButtonClicked)
                BodyText(
                    text = "No hay puntos de recompensa agregados, por favor agrega nuevos desde la tabla de puntos de recompensa",
                    textAlignment = TextAlign.Start,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }
        return
    }

    var serviceSelected by rememberSaveable { mutableStateOf(serviceTypeData[0].service) }
    var pointsByService by rememberSaveable { mutableIntStateOf(serviceTypeData[0].points) }

    Box(
        Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(innerPadding)
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                BackButtonItem(onBackButtonClicked)
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 16.dp),
                    contentAlignment = Alignment.TopStart
                ) {
                    SecondTitleText(stringResource(R.string.add_points))
                }

            }
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    SecondTitleText(client!!.name)
                    ThirdTitleText(
                        "${stringResource(R.string.current_points)}: ${clientDetailsLoyalty!!.points}",
                        color = PositiveColor
                    )
                }
            }
            SelectableDropdownMenu(
                list = serviceType,
                serviceSelected = serviceSelected,
                onValueChange = {
                    serviceSelected = it
                    pointsByService = serviceTypeData.find { item ->
                        item.service == it
                    }?.points ?: 0
                }
            )
            Column(Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)) {
                DisableTextField(
                    value = "Puntos: $pointsByService"
                )
            }
            Spacer(Modifier.size(16.dp))
            AcceptDeclineButtons(
                onAccept = {
                    val newLoyaltyPoints = clientDetailsLoyalty!!.points + pointsByService
                    viewModel.upsertClientPointsLoyalty(
                        clientDetailsLoyalty!!.copy(points = newLoyaltyPoints)
                    )
                    Toast.makeText(context, "Puntos agregados", Toast.LENGTH_SHORT).show()
                    onConfirm()
                },
                onDecline = { onBackButtonClicked() }
            )
        }
    }
}





