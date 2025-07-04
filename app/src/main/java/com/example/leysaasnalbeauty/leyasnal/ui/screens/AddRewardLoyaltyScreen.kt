package com.example.leysaasnalbeauty.leyasnal.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.example.leysaasnalbeauty.leyasnal.ui.AppViewModel
import com.example.leysaasnalbeauty.leyasnal.ui.components.RewardLoyaltyComponent

@Composable
fun AddRewardLoyaltyScreen(
    innerPadding: PaddingValues,
    viewModel: AppViewModel,
    onAcceptClick: () -> Unit,
    onBackClick: () -> Unit
) {

    var rewardName by rememberSaveable { mutableStateOf("") }
    var rewardPoints by rememberSaveable { mutableStateOf("") }

    val context = LocalContext.current


    RewardLoyaltyComponent(
        innerPadding = innerPadding,
        viewModel = viewModel,
        title = "Agregar Canje de Puntos",
        rewardName = rewardName,
        rewardPoints = rewardPoints,
        isForEdit = false,
        onAcceptClick = {
            onAcceptClick()
            Toast.makeText(context, "Canje agregado", Toast.LENGTH_SHORT).show()
            viewModel.addRewardLoyalty(
                reward = rewardName,
                points = rewardPoints.toInt()
            )
        },
        onBackClick = { onBackClick() },
        onDeleteItem = { },
        onRewardNameChange = {
            if (rewardName.length < 30) {
                rewardName = it
            }
        },
        onPointsChange = { rewardPoints = it }
    )
}

