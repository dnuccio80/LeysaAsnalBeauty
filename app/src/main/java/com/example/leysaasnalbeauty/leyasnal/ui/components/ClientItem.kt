package com.example.leysaasnalbeauty.leyasnal.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.leysaasnalbeauty.leyasnal.ui.dataclasses.ClientDataClass

@Composable
fun ClientItem(client: ClientDataClass) {
    Row(
        Modifier
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Card(
            modifier = Modifier.size(40.dp),
            shape = CircleShape
        ) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                SecondTitleText("L")
            }
        }
        Column(Modifier.fillMaxWidth()) {
            ThirdTitleText(client.name)
        }
    }
}