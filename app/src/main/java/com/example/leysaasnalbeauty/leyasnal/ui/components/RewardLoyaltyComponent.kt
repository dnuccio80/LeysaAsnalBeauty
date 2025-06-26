package com.example.leysaasnalbeauty.leyasnal.ui.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import com.example.leysaasnalbeauty.ui.theme.NegativeColor

@Composable
fun RewardLoyaltyComponent(
    innerPadding: PaddingValues,
    viewModel: AppViewModel,
    rewardName: String,
    rewardPoints: String,
    title: String,
    isForEdit: Boolean,
    onAcceptClick: () -> Unit,
    onBackClick: () -> Unit,
    onDeleteItem: () -> Unit,
    onRewardNameChange: (String) -> Unit,
    onPointsChange: (String) -> Unit,
) {


    var showDialog by rememberSaveable { mutableStateOf(false) }

    Box(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Color.Black)
            .padding(innerPadding)
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            BackButtonItem { onBackClick() }
            Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                SecondTitleText(title)
            }
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 48.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                MainTextField(
                    value = rewardName,
                    isNumeric = false,
                    onValueChange = {
                        onRewardNameChange(it)
                    },
                    label = stringResource(R.string.reward),
                    icon = R.drawable.ic_service
                )
                MainTextField(
                    value = rewardPoints,
                    isNumeric = true,
                    onValueChange = {
                        onPointsChange(it)
                    },
                    label = stringResource(R.string.points),
                    icon = R.drawable.ic_star
                )
            }
            AcceptDeclineButtons(
                onAccept = {
                    if (rewardName.isNotEmpty() && rewardPoints.isNotEmpty() && rewardPoints.toInt() > 0) {
                        onAcceptClick()
                    }
                },
                onDecline = { onBackClick() }
            )
            if (!isForEdit) return

            Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                ButtonTextItem(
                    text = stringResource(R.string.delete),
                    buttonColor = NegativeColor,
                ) {
                    showDialog = true
                }
            }

            AlertDialogItem(
                show = showDialog,
                text = stringResource(R.string.delete_reward_change_message),
                onDismiss = { showDialog = false }
            ) {
                onDeleteItem()
                showDialog = false
                onBackClick()
            }
        }
    }
}