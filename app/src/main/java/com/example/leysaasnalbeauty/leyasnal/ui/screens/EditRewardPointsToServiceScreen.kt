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
import com.example.leysaasnalbeauty.leyasnal.ui.components.RewardPointsWithTextFieldComponent

@Composable
fun EditRewardPointsToServiceScreen(
    innerPadding: PaddingValues,
    viewModel: AppViewModel,
    onBackClick: () -> Unit,
    loyaltyId:Int
) {

    LaunchedEffect(Unit) {
        viewModel.setServicePointsLoyaltyId(loyaltyId)
    }

    val servicePointsLoyaltyDetails by viewModel.servicePointsLoyaltyDetails.collectAsState()

    if(servicePointsLoyaltyDetails == null || servicePointsLoyaltyDetails?.id != loyaltyId) return


    var service by rememberSaveable { mutableStateOf(servicePointsLoyaltyDetails!!.service) }
    var points by rememberSaveable { mutableStateOf(servicePointsLoyaltyDetails!!.points.toString()) }

    val context = LocalContext.current

    RewardPointsWithTextFieldComponent(
        innerPadding,
        service,
        points,
        forEdit = true,
        title = "Editar Recompensa de servicio",
        onBackClick,
        onAcceptClick = {
            viewModel.updateServicePointsLoyaltyUseCase(loyaltyId = loyaltyId, service = service, points = points)
            onBackClick()
            Toast.makeText(context, "Recompensa por servicio modificada", Toast.LENGTH_SHORT).show()
        },
        onServiceChange = { service = it },
        onPointsChange = { points = it },
        onDeleteItem = {
            viewModel.deleteServicePointsLoyalty(loyaltyId)
            Toast.makeText(context, "Recompensa eliminada", Toast.LENGTH_SHORT).show()
        }
    )
}