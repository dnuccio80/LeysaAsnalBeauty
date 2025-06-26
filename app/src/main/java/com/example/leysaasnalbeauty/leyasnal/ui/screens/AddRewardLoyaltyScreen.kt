package com.example.leysaasnalbeauty.leyasnal.ui.screens

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
import com.example.leysaasnalbeauty.leyasnal.ui.components.AcceptDeclineButtons
import com.example.leysaasnalbeauty.leyasnal.ui.components.BackButtonItem
import com.example.leysaasnalbeauty.leyasnal.ui.components.MainTextField
import com.example.leysaasnalbeauty.leyasnal.ui.components.SecondTitleText

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
                SecondTitleText("Agregar Canje de Puntos")
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
                        if(rewardName.length < 30){
                            rewardName = it
                        }
//                        onServiceChange(it)
                    },
                    label = stringResource(R.string.reward),
                    icon = R.drawable.ic_service
                )
                MainTextField(
                    value = rewardPoints,
                    isNumeric = true,
                    onValueChange = {
//                        onPointsChange(it)
                        rewardPoints = it
                    },
                    label = stringResource(R.string.points),
                    icon = R.drawable.ic_star
                )
            }
            AcceptDeclineButtons(
                onAccept = {
                    if (rewardName.isNotEmpty() && rewardPoints.isNotEmpty() && rewardPoints.toInt() > 0) {
                        onAcceptClick()
                        Toast.makeText(context, "Canje agregado", Toast.LENGTH_SHORT).show()
                        viewModel.addRewardLoyalty(reward = rewardName, points = rewardPoints.toInt())
                    }
                },
                onDecline = { onBackClick() }
            )
        }
    }
}