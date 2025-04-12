package com.example.leysaasnalbeauty.leyasnal.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.leysaasnalbeauty.R
import com.example.leysaasnalbeauty.leyasnal.ui.components.FirstTitleText
import com.example.leysaasnalbeauty.leyasnal.ui.components.SecondTitleText
import com.example.leysaasnalbeauty.leyasnal.ui.components.SquareCardComponent
import com.example.leysaasnalbeauty.ui.theme.MainColor

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HomeScreen(innerPadding: PaddingValues) {
    Box(
        Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(innerPadding)
    ) {
        Column(Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(16.dp)) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .height(80.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.logo_with_background),
                    contentDescription = "logo",
                    modifier = Modifier
                        .width(150.dp)
                        .fillMaxHeight(),
                    contentScale = ContentScale.Fit
                )
            }
            Column(Modifier.fillMaxWidth().padding(8.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {

                FirstTitleText("Hola Ley")
                Column {
                    SecondTitleText("Balance", color = MainColor)
                    FirstTitleText("$150.000")
                }
                FlowRow(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Absolute.SpaceEvenly,
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    maxItemsInEachRow = Int.MAX_VALUE,

                    ) {
                    SquareCardComponent(R.drawable.ic_money, "Agregar Ingreso")
                    SquareCardComponent(R.drawable.ic_buys, "Agregar Gasto")
                    SquareCardComponent(R.drawable.ic_woman, "Agregar Clienta")
                    SquareCardComponent(R.drawable.ic_fidelity, "Nueva Clienta Fiel")
                    SquareCardComponent(R.drawable.ic_gift_card, "Crear Gift Card")
                    SquareCardComponent(R.drawable.ic_message, "Notificar Clienta")
                }
            }
        }
    }
}