package com.example.leysaasnalbeauty.leyasnal.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.example.leysaasnalbeauty.leyasnal.ui.AppViewModel
import com.example.leysaasnalbeauty.leyasnal.ui.components.RewardLoyaltyComponent

@Composable
fun EditRewardLoyaltyScreen(
    rewardId: Int,
    innerPadding: PaddingValues,
    viewModel: AppViewModel,
    onAcceptClick: () -> Unit,
    onBackClick: () -> Unit,
) {

    LaunchedEffect(Unit) {
        viewModel.setRewardId(rewardId)
    }

    val rewardDetails by viewModel.rewardLoyaltyDetails.collectAsState()

    if (rewardDetails == null || rewardDetails?.id != rewardId) return

    var rewardName by rememberSaveable { mutableStateOf(rewardDetails!!.reward) }
    var rewardPoints by rememberSaveable { mutableStateOf(rewardDetails!!.points.toString()) }
    val context = LocalContext.current


    RewardLoyaltyComponent(
        innerPadding = innerPadding,
        viewModel = viewModel,
        title = "Editar Canje de Puntos",
        rewardName = rewardName,
        rewardPoints = rewardPoints,
        isForEdit = true,
        onAcceptClick = {
            onAcceptClick()
            Toast.makeText(context, "Canje Modificado", Toast.LENGTH_SHORT).show()
            viewModel.updateRewardLoyalty(
                id = rewardId,
                reward = rewardName,
                points = rewardPoints.toInt()
            )
        },
        onBackClick = { onBackClick() },
        onDeleteItem = {
            viewModel.deleteRewardLoyaltyById(rewardId)
            Toast.makeText(context, "Canje Eliminado", Toast.LENGTH_SHORT).show()
        },
        onRewardNameChange = {
            if (rewardName.length < 30) {
                rewardName = it
            }
        },
        onPointsChange = { rewardPoints = it }
    )
}
