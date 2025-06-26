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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
    val clientDetailsLoyalty by viewModel.clientDetailsLoyaltyPoints.collectAsState()
    val rewardsLoyalty by viewModel.rewardsLoyalty.collectAsState()

    if (client == null || client?.id != clientId) return
    if(clientDetailsLoyalty == null) return

    var showConfirmDialog by rememberSaveable { mutableStateOf(false) }

    val filteredLoyaltyList = rewardsLoyalty.filter {
        it.points <= clientDetailsLoyalty!!.points
    }

    Box(
        Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(innerPadding)
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            BackButtonItem(onBackButtonClicked)
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.TopCenter) {
                SecondTitleText(stringResource(R.string.change_points).uppercase())
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
                    ThirdTitleText("${stringResource(R.string.total_points)}: ${clientDetailsLoyalty!!.points}")
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
                    SecondTitleText(stringResource(R.string.available_changes), color = PositiveColor)
                    if(filteredLoyaltyList.isEmpty()) {
                        SecondTitleText("No hay ningún canjeable con sus puntos")
                    } else {
                        filteredLoyaltyList.forEach {
                            ChangeRewardRowItem(it.reward, it.points.toString()) {
                                showConfirmDialog = true
                            }
                        }
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
        Spacer(Modifier.weight(3f))
        BodyText("$points pts")
        Spacer(Modifier.padding(start = 12.dp))
        ButtonTextItem(stringResource(R.string.change), DarkAccentColor, horizontalPadding = 4.dp, verticalPadding = 2.dp) {
            onClick()
        }
    }
}

