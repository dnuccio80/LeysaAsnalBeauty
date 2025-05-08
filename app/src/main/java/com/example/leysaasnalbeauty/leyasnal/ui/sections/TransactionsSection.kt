package com.example.leysaasnalbeauty.leyasnal.ui.sections

import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.leysaasnalbeauty.R
import com.example.leysaasnalbeauty.leyasnal.ui.AppViewModel
import com.example.leysaasnalbeauty.leyasnal.ui.components.ButtonIconItem
import com.example.leysaasnalbeauty.leyasnal.ui.components.ButtonTextItem
import com.example.leysaasnalbeauty.leyasnal.ui.components.SecondTitleText
import com.example.leysaasnalbeauty.leyasnal.ui.components.TransactionDetailsItem
import com.example.leysaasnalbeauty.leyasnal.ui.dataclasses.TransactionsDataClass
import com.example.leysaasnalbeauty.ui.theme.DarkGrayColor
import com.example.leysaasnalbeauty.ui.theme.DarkAccentColor

@Composable
fun TransactionsSection(
    title: String,
    transactions: List<TransactionsDataClass>,
    viewModel: AppViewModel,
    icon: Painter,
    onCleanButtonClicked: () -> Unit,
    onItemListClicked: (TransactionsDataClass) -> Unit
) {

    var isExpanded by rememberSaveable { mutableStateOf(false) }

    val cardHeight by animateDpAsState(
        targetValue = if (isExpanded) 370.dp else 250.dp,
        animationSpec = TweenSpec(durationMillis = 200),
    )

    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(cardHeight)
            .padding(horizontal = 8.dp),
//            .border(4.dp, DarkAccentColor, shape = RoundedCornerShape(16.dp)),
        colors = CardDefaults.cardColors(
            containerColor = DarkGrayColor
        )
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier.weight(1f)) {
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)){
                        SecondTitleText(title)
                        Image(icon, modifier = Modifier.size(28.dp), contentDescription = "")
                    }
                }
                ButtonIconItem(if (isExpanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown) {
                    isExpanded = !isExpanded
                }
                Spacer(Modifier.size(4.dp))
                ButtonTextItem(stringResource(R.string.clean), DarkAccentColor) { onCleanButtonClicked() }
            }
            Column(Modifier.fillMaxWidth()) {
                HorizontalDivider(thickness = 1.dp, color = DarkAccentColor)
                HorizontalDivider(thickness = 1.dp, color = Color.Black)
            }

            Column(
                Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                transactions.forEach {
                    TransactionDetailsItem(it, viewModel) { transaction ->
                        onItemListClicked(transaction)
                    }
                }
            }
        }

    }


}

