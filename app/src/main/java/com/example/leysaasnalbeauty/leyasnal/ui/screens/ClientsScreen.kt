package com.example.leysaasnalbeauty.leyasnal.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
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
import com.example.leysaasnalbeauty.leyasnal.ui.AppViewModel
import com.example.leysaasnalbeauty.leyasnal.ui.components.BodyText
import com.example.leysaasnalbeauty.leyasnal.ui.components.ClientItem
import com.example.leysaasnalbeauty.leyasnal.ui.components.MainTextField
import com.example.leysaasnalbeauty.leyasnal.ui.components.SecondTitleText
import com.example.leysaasnalbeauty.leyasnal.ui.components.ThirdTitleText
import com.example.leysaasnalbeauty.leyasnal.ui.dataclasses.ClientDataClass
import com.example.leysaasnalbeauty.ui.theme.DarkAccentColor
import com.example.leysaasnalbeauty.ui.theme.DarkBackground
import com.example.leysaasnalbeauty.ui.theme.SecondaryBackgroundColor

@Composable
fun ClientsScreen(innerPadding: PaddingValues, viewModel: AppViewModel) {

    val clientList by viewModel.clients.collectAsState()
    var querySearch by rememberSaveable { mutableStateOf("") }

    Box(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Color.Black)
            .padding(innerPadding)
    ) {
        if (clientList.isEmpty()) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                BodyText(stringResource(R.string.empty_client_list))
            }
        } else {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp, horizontal = 8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                MainTextField(
                    value = querySearch,
                    isNumeric = false,
                    isPhone = false,
                    onValueChange = { querySearch = it },
                    label = stringResource(R.string.search_client),
                    icon = R.drawable.ic_search
                )

                Card(
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = SecondaryBackgroundColor
                    ),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Column(
                        Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        verticalArrangement = Arrangement.spacedBy(2.dp)
                    ) {
                        clientList.forEachIndexed { index, client ->
                            ClientItem(client)

                            if (index < clientList.lastIndex) {
                                HorizontalDivider(
                                    Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 8.dp),
                                    thickness = 2.dp,
                                    color = Color.Gray
                                )
                            }
                        }
                    }
                }
            }

        }
    }
}

