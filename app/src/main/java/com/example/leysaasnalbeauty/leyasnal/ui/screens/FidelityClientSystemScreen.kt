package com.example.leysaasnalbeauty.leyasnal.ui.screens

import android.util.Log
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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
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
import com.example.leysaasnalbeauty.leyasnal.ui.components.BodyText
import com.example.leysaasnalbeauty.leyasnal.ui.components.ButtonTextItem
import com.example.leysaasnalbeauty.leyasnal.ui.components.SecondTitleText
import com.example.leysaasnalbeauty.leyasnal.ui.components.ThirdTitleText
import com.example.leysaasnalbeauty.ui.theme.DarkAccentColor
import com.example.leysaasnalbeauty.ui.theme.DarkGrayColor

@Composable
fun FidelityClientSystemScreen(
    innerPadding: PaddingValues,
    viewModel: AppViewModel,
    onBackClick: () -> Unit
) {

    val loyalties by viewModel.loyalties.collectAsState()

    val sectionList: List<String> = listOf(
        "Sistema de Puntos",
        "Tabla de Clientas",
    )

    var sectionSelected by rememberSaveable { mutableStateOf(sectionList[0]) }

    Box(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Color.Black)
            .padding(innerPadding)
    ) {
        Column(Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(16.dp)) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Spacer(Modifier.size(0.dp))
                Icon(
                    Icons.AutoMirrored.Default.ArrowBack,
                    contentDescription = "icon back",
                    tint = Color.White,
                    modifier = Modifier.clickable {
                        onBackClick()
                    }
                )
                Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    SecondTitleText("${stringResource(R.string.rewards_club)} \uD83C\uDF1F")
                }
                Column(Modifier.fillMaxWidth()) {
                    Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                        Box(
                            modifier = Modifier
                                .background(if (sectionSelected == sectionList[0]) DarkAccentColor else DarkGrayColor)
                                .padding(8.dp)
                                .clickable {
                                    sectionSelected = sectionList[0]
                                }
                        ) {
                            ThirdTitleText(sectionList.first())
                        }
                        Box(
                            modifier = Modifier
                                .background(if (sectionSelected == sectionList[1]) DarkAccentColor else DarkGrayColor)
                                .padding(8.dp)
                                .clickable {
                                    sectionSelected = sectionList[1]
                                }
                        ) {
                            ThirdTitleText(sectionList.last(), color = Color.White)
                        }
                    }
                    HorizontalDivider(Modifier.fillMaxWidth(), thickness = 2.dp, DarkAccentColor)
                }

                when (sectionSelected) {
                    sectionList[0] -> {
                        RewardPointsCardItem()
                        RewardExchangeCardItem()
                    }

                    sectionList[1] -> {
                        ClientTable()
                        Row(
                            Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {
                            ButtonTextItem(
                                text = stringResource(R.string.add_client_points),
                                buttonColor = DarkAccentColor
                            ) { }
                            ButtonTextItem(
                                text = stringResource(R.string.change_points),
                                buttonColor = DarkAccentColor
                            ) { }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun RewardPointsCardItem() {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = DarkGrayColor
        )
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Column(Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Row(Modifier.fillMaxWidth()) {
                    SecondTitleText("Puntos de recompensa")
                    Spacer(Modifier.weight(1f))
                    Icon(
                        Icons.Default.Edit,
                        contentDescription = "edit icon",
                        tint = Color.White,
                        modifier = Modifier.clickable { }
                    )
                }
                HorizontalDivider(Modifier.fillMaxWidth(), thickness = 1.dp, color = Color.White)
            }
            RewardItem("Maquillaje Premium", "50")
            RewardItem("Clase de automaquillaje", "50")
            RewardItem("Maquillaje Básico", "30")
            RewardItem("Capping", "10")
            RewardItem("Perfilado de Cejas", "8")
            RewardItem("Laminado de Cejas", "8")
            RewardItem("Polygel", "8")
            RewardItem("Soft Gel", "8")
            RewardItem("Lifting de Pesteñas", "8")
            RewardItem("Esmaltado Semipermanente", "7")
        }
    }
}


@Composable
fun RewardExchangeCardItem() {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = DarkGrayColor
        )
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Column(Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Row(Modifier.fillMaxWidth()) {
                    SecondTitleText("Canje de puntos")
                    Spacer(Modifier.weight(1f))
                    Icon(
                        Icons.Default.Edit,
                        contentDescription = "edit icon",
                        tint = Color.White,
                        modifier = Modifier.clickable { }
                    )
                }
                HorizontalDivider(Modifier.fillMaxWidth(), thickness = 1.dp, color = Color.White)
            }
            RewardItem("2x1 maquillaje/automaquillaje", "500")
            RewardItem("15% en maquillaje/automaquillaje", "250")
            RewardItem("10% en maquillaje/automaquillaje", "180")
            RewardItem("15% servicio de Capping", "80")
            RewardItem("10% servicio de Capping", "55")
            RewardItem("5% servicio de Capping", "30")
            RewardItem("15% descuento en Lifting de Pesteñas", "80")
            RewardItem("10% descuento en Lifting de Pesteñas", "50")
            RewardItem("5% descuento en Lifting de Pesteñas", "30")
            RewardItem("15% descuento en Perfilado de Cejas", "65")
            RewardItem("10% descuento en Perfilado de Cejas", "45")
            RewardItem("5% descuento en Perfilado de Cejas", "25")
            RewardItem("15% descuento en Laminado de Cejas", "65")
            RewardItem("10% descuento en Laminado de Cejas", "45")
            RewardItem("5% descuento en Laminado de Cejas", "25")
            RewardItem("15% descuento en Esmaltado Semipermanente", "50")
            RewardItem("10% descuento en Esmaltado Semipermanente", "35")
            RewardItem("5% descuento en Esmaltado Semipermanente", "21")
        }
    }
}

@Composable
fun ClientTable() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(350.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = DarkGrayColor
        )
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            RewardItem("Leysa Asnal", "250")
            RewardItem("Martina Gutierrez", "100")
            RewardItem("Damian Nuccio", "80")
            RewardItem("Damian Nuccio", "80")
            RewardItem("Damian Nuccio", "80")
            RewardItem("Damian Nuccio", "80")
            RewardItem("Damian Nuccio", "80")
            RewardItem("Damian Nuccio", "80")
            RewardItem("Damian Nuccio", "80")
            RewardItem("Damian Nuccio", "80")
            RewardItem("Damian Nuccio", "80")
            RewardItem("Damian Nuccio", "80")
            RewardItem("Damian Nuccio", "80")
            RewardItem("Damian Nuccio", "80")
            RewardItem("Damian Nuccio", "80")
        }
    }
}


@Composable
fun RewardItem(title: String, points: String) {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        BodyText(title)
        Spacer(modifier = Modifier.weight(1f))
        BodyText("$points pts")
    }
}
