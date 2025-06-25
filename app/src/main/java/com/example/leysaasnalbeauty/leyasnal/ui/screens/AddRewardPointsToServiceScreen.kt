package com.example.leysaasnalbeauty.leyasnal.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentComposer
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.leysaasnalbeauty.R
import com.example.leysaasnalbeauty.leyasnal.ui.AppViewModel
import com.example.leysaasnalbeauty.leyasnal.ui.components.AcceptDeclineButtons
import com.example.leysaasnalbeauty.leyasnal.ui.components.AlertDialogItem
import com.example.leysaasnalbeauty.leyasnal.ui.components.BackButtonItem
import com.example.leysaasnalbeauty.leyasnal.ui.components.ButtonTextItem
import com.example.leysaasnalbeauty.leyasnal.ui.components.MainTextField
import com.example.leysaasnalbeauty.leyasnal.ui.components.RewardPointsWithTextFieldComponent
import com.example.leysaasnalbeauty.leyasnal.ui.components.SecondTitleText
import com.example.leysaasnalbeauty.ui.theme.NegativeColor
import kotlin.math.truncate

@Composable
fun AddRewardPointsToServiceScreen(
    innerPadding: PaddingValues,
    viewModel: AppViewModel,
    onBackClick: () -> Unit
) {

    var service by rememberSaveable { mutableStateOf("") }
    var points by rememberSaveable { mutableStateOf("") }

    val context = LocalContext.current

    RewardPointsWithTextFieldComponent(
        innerPadding,
        service,
        points,
        forEdit = false,
        onBackClick,
        onAcceptClick = {
            viewModel.addServicePointsLoyalty(service = service, points = points)
            onBackClick()
            Toast.makeText(context, "Recompensa por servicio agregada", Toast.LENGTH_SHORT).show()
        },
        onServiceChange = { service = it },
        onPointsChange = { points = it },
        onDeleteItem = {  }
    )

}

