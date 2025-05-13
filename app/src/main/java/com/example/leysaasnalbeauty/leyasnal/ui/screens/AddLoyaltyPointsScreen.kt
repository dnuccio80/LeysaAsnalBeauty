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
import androidx.compose.ui.unit.dp
import com.example.leysaasnalbeauty.R
import com.example.leysaasnalbeauty.leyasnal.ui.AppViewModel
import com.example.leysaasnalbeauty.leyasnal.ui.components.AcceptDeclineButtons
import com.example.leysaasnalbeauty.leyasnal.ui.components.BackButtonItem
import com.example.leysaasnalbeauty.leyasnal.ui.components.ButtonTextItem
import com.example.leysaasnalbeauty.leyasnal.ui.components.DisableTextField
import com.example.leysaasnalbeauty.leyasnal.ui.components.FirstTitleText
import com.example.leysaasnalbeauty.leyasnal.ui.components.SecondTitleText
import com.example.leysaasnalbeauty.leyasnal.ui.components.SelectableDropdownMenu
import com.example.leysaasnalbeauty.leyasnal.ui.dataclasses.LoyaltyServicePointsDataClass
import com.example.leysaasnalbeauty.ui.theme.NegativeColor


@Composable
fun AddLoyaltyPointsScreen(
    viewModel: AppViewModel,
    innerPadding: PaddingValues,
    clientId: Int,
    onBackButtonClicked: () -> Unit,
    onConfirm:() -> Unit
) {

    LaunchedEffect(Unit) {
        viewModel.setClientId(clientId)
    }

    val client by viewModel.clientDetails.collectAsState()

    if (client == null || client?.id != clientId) return

    val context = LocalContext.current
    val serviceTypeData = listOf(
        LoyaltyServicePointsDataClass(service = "Esmaltado Semipermanente", points = 8),
        LoyaltyServicePointsDataClass(service = "Lifting de pestañas", points = 11),
        LoyaltyServicePointsDataClass(service = "Maquillaje", points = 50),
        LoyaltyServicePointsDataClass(service = "Cejas", points = 10),
    )

    val serviceType: List<String> = serviceTypeData.map {
        it.service
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
                SecondTitleText(client!!.name)
            }
            SelectableDropdownMenu(
                list = serviceType,
                serviceSelected = serviceSelected,
                onValueChange = {
                    serviceSelected = it
                    pointsByService = serviceTypeData.find { item ->
                        item.service == it
                    }?.points?:0
                }
            )
            Column(Modifier.fillMaxWidth().padding(horizontal = 32.dp)) {
                DisableTextField(
                    value = "Puntos: $pointsByService"
                )
            }
            Spacer(Modifier.size(16.dp))
            AcceptDeclineButtons(
                onAccept = {
                    Toast.makeText(context, "Puntos agregados", Toast.LENGTH_SHORT).show()
                    onConfirm()
                },
                onDecline = { onBackButtonClicked() }
            )
        }
    }
}





