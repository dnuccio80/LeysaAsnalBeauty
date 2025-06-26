package com.example.leysaasnalbeauty.leyasnal.ui.screens

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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
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
import com.example.leysaasnalbeauty.leyasnal.data.loyalty.LoyaltyWithClient
import com.example.leysaasnalbeauty.leyasnal.ui.AppViewModel
import com.example.leysaasnalbeauty.leyasnal.ui.components.BackButtonItem
import com.example.leysaasnalbeauty.leyasnal.ui.components.BodyText
import com.example.leysaasnalbeauty.leyasnal.ui.components.ButtonIconItem
import com.example.leysaasnalbeauty.leyasnal.ui.components.ButtonTextItem
import com.example.leysaasnalbeauty.leyasnal.ui.components.SecondTitleText
import com.example.leysaasnalbeauty.leyasnal.ui.components.ThirdTitleText
import com.example.leysaasnalbeauty.leyasnal.ui.dataclasses.LoyaltyRewardPointsDataClass
import com.example.leysaasnalbeauty.leyasnal.ui.dataclasses.LoyaltyServicePointsDataClass
import com.example.leysaasnalbeauty.ui.theme.DarkAccentColor
import com.example.leysaasnalbeauty.ui.theme.DarkGrayColor

@Composable
fun FidelityClientSystemScreen(
    innerPadding: PaddingValues,
    viewModel: AppViewModel,
    onBackClick: () -> Unit,
    onAddPointsButtonClicked: () -> Unit,
    onChangePointsButtonClicked: () -> Unit,
    onAddRewardPointsToService: () -> Unit,
    onServiceClicked: (Int) -> Unit,
    onAddRewardLoyaltyClicked:() -> Unit,
) {

    val clientPointsLoyalties by viewModel.clientPointsLoyalties.collectAsState()
    val servicePointsLoyaltyList by viewModel.servicePointsLoyaltyList.collectAsState()
    val rewardsLoyalty by viewModel.rewardsLoyalty.collectAsState()

    val sectionList: List<String> = listOf(
        "Sistema de Puntaje",
        "Tabla de Puntos",
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
                BackButtonItem {
                    onBackClick()
                }
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
                        RewardPointsCardItem(
                            servicePointsLoyaltyList,
                            onAddRewardPointsToService = {
                                onAddRewardPointsToService()
                            },
                            onServiceClicked = {
                                onServiceClicked(it)
                            })
                        RewardExchangeCardItem(rewardsLoyalty) {
                            onAddRewardLoyaltyClicked()
                        }
                    }

                    sectionList[1] -> {
                        ClientTable(clientPointsLoyalties)
                        Row(
                            Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {
                            ButtonTextItem(
                                text = stringResource(R.string.add_client_points),
                                buttonColor = DarkAccentColor
                            ) {
                                onAddPointsButtonClicked()
                            }
                            ButtonTextItem(
                                text = stringResource(R.string.change_points),
                                buttonColor = DarkAccentColor
                            ) {
                                onChangePointsButtonClicked()
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun RewardPointsCardItem(
    servicePointsLoyaltyList: List<LoyaltyServicePointsDataClass>,
    onAddRewardPointsToService: () -> Unit,
    onServiceClicked: (Int) -> Unit
) {
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
                Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                    SecondTitleText(stringResource(R.string.reward_points))
                    Spacer(Modifier.weight(1f))
                    ButtonIconItem(
                        icon = Icons.Default.Add
                    ) {
                        onAddRewardPointsToService()
                    }
                }
                HorizontalDivider(Modifier.fillMaxWidth(), thickness = 1.dp, color = Color.White)
            }
            if (servicePointsLoyaltyList.isEmpty()) {
                BodyText(stringResource(R.string.no_reward_points_available))
            } else {
                servicePointsLoyaltyList.forEach { service ->
                    RewardRowItem(
                        title = service.service,
                        points = service.points.toString()
                    ) {
                        onServiceClicked(service.id)
                    }
                }
            }
        }
    }
}


@Composable
fun RewardExchangeCardItem(
    rewardsLoyalty: List<LoyaltyRewardPointsDataClass>,
    onAddRewardClicked: () -> Unit
) {
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
                Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                    SecondTitleText("Canje de puntos")
                    Spacer(Modifier.weight(1f))
                    ButtonIconItem(
                        icon = Icons.Default.Add
                    ) { onAddRewardClicked() }
                }
                HorizontalDivider(Modifier.fillMaxWidth(), thickness = 1.dp, color = Color.White)

            }
            if (rewardsLoyalty.isEmpty()) {
                BodyText(stringResource(R.string.no_reward_changeable_available))
            } else {
                rewardsLoyalty.forEach { reward ->
                    RewardRowItem(reward.reward, reward.points.toString()) {}
                }
            }
        }
    }
}

@Composable
fun ClientTable(clientPointsLoyalties: List<LoyaltyWithClient>) {
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
            // ACA LA TABLA DE CLIENT-POINTS
            if (clientPointsLoyalties.isEmpty()) {
                BodyText(stringResource(R.string.no_client_points))
            } else {
                clientPointsLoyalties.forEach { loyaltyClient ->
                    ClientRowItem(
                        loyaltyClient.client.name,
                        loyaltyClient.loyalty.points.toString()
                    )
                }
            }
        }
    }
}

@Composable
fun ClientRowItem(name: String, points: String) {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        BodyText(name)
        Spacer(modifier = Modifier.weight(1f))
        BodyText("$points pts")
    }
}

@Composable
fun RewardRowItem(title: String, points: String, onClick: () -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            }, horizontalArrangement = Arrangement.SpaceBetween
    ) {
        BodyText(title)
        Spacer(modifier = Modifier.weight(1f))
        BodyText("$points pts")
    }
}
