package com.example.leysaasnalbeauty.leyasnal.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.leysaasnalbeauty.R
import com.example.leysaasnalbeauty.leyasnal.ui.AppViewModel
import com.example.leysaasnalbeauty.leyasnal.ui.components.AlertDialogItem
import com.example.leysaasnalbeauty.leyasnal.ui.components.BackButtonItem
import com.example.leysaasnalbeauty.leyasnal.ui.components.BodyText
import com.example.leysaasnalbeauty.leyasnal.ui.components.ButtonTextItem
import com.example.leysaasnalbeauty.leyasnal.ui.components.SecondTitleText
import com.example.leysaasnalbeauty.leyasnal.ui.components.ThirdTitleText
import com.example.leysaasnalbeauty.ui.theme.DarkAccentColor
import com.example.leysaasnalbeauty.ui.theme.DarkGrayColor
import com.example.leysaasnalbeauty.ui.theme.PositiveColor


@Composable
fun ChangeLoyaltyPointsScreen(
    viewModel: AppViewModel,
    innerPadding: PaddingValues,
    clientId: Int,
    onBackButtonClicked: () -> Unit
) {

    LaunchedEffect(Unit) {
        viewModel.setClientId(clientId)
    }

    val client by viewModel.clientDetails.collectAsState()

    if (client == null || client?.id != clientId) return

    var showConfirmDialog by rememberSaveable { mutableStateOf(false) }

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
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.TopCenter) {
                SecondTitleText("Canje de Puntos")
            }
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.TopCenter) {
                ThirdTitleText(client!!.name)
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = DarkGrayColor
                )
            ) {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    SecondTitleText(client!!.name, color = PositiveColor)
                    ThirdTitleText("Total puntos: 130")
                }
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = DarkGrayColor
                )
            ) {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    SecondTitleText("Canje de Puntos", color = PositiveColor)
                    ChangeRewardRowItem("2x1 Maquillaje", 128.toString()) {
                        showConfirmDialog = true
                    }
                    ChangeRewardRowItem("2x1 Maquillaje", 128.toString()) {
                        showConfirmDialog = true
                    }
                    ChangeRewardRowItem("2x1 Maquillaje", 128.toString()) {
                        showConfirmDialog = true
                    }
                    ChangeRewardRowItem("2x1 Maquillaje", 128.toString()) {
                        showConfirmDialog = true
                    }
                }
            }
            AlertDialogItem(
                show = showConfirmDialog,
                text = stringResource(R.string.change_points_alert_dialog),
                onDismiss = { showConfirmDialog = false },
                onConfirm = {
                    showConfirmDialog = false
                }
            )
        }

    }
}

@Composable
fun ChangeRewardRowItem(title: String, points: String, onClick: () -> Unit) {
    Row(
        Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        BodyText(title)
        BodyText("$points pts")
        ButtonTextItem(stringResource(R.string.change), DarkAccentColor) {
            onClick()
        }
    }
}

