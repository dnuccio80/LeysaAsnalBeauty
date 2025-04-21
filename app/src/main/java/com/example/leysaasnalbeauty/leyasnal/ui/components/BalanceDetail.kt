package com.example.leysaasnalbeauty.leyasnal.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.leysaasnalbeauty.R
import com.example.leysaasnalbeauty.leyasnal.ui.AppViewModel
import com.example.leysaasnalbeauty.leyasnal.ui.dataclasses.EarningDataClass
import com.example.leysaasnalbeauty.leyasnal.ui.dataclasses.ExpenseDataClass
import com.example.leysaasnalbeauty.ui.theme.AccentColor
import com.example.leysaasnalbeauty.ui.theme.NegativeColor
import com.example.leysaasnalbeauty.ui.theme.NeutralColor
import com.example.leysaasnalbeauty.ui.theme.PositiveColor

@Composable
fun BalanceDetail(
    earnings: List<EarningDataClass>,
    expenses: List<ExpenseDataClass>,
    viewModel: AppViewModel,
    onAddBalanceButtonClick: () -> Unit,
    onDeleteButtonClicked: () -> Unit
) {

    val balance = earnings.sumOf { it.amount } - expenses.sumOf { it.amount }

    val formatedBalance = viewModel.formatPrice(balance)

    val color: Color = when {
        balance == 0 -> NeutralColor
        balance > 0 -> PositiveColor
        else -> NegativeColor
    }

    Row(
        Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    )
    {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.weight(1f)
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    SecondTitleText(stringResource(R.string.balance), color = AccentColor)
                    Card(
                        modifier = Modifier
                            .size(24.dp)
                            .clickable {
                                onAddBalanceButtonClick()
                            },
                        shape = CircleShape,
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White
                        )
                    ) {
                        Icon(
                            Icons.Default.Add,
                            modifier = Modifier.padding(2.dp),
                            contentDescription = "add balance button"
                        )
                    }
                }
                FirstTitleText(formatedBalance, color = color)
            }
        }
        ButtonTextItem(
            text = stringResource(R.string.clear_all_data),
            buttonColor = AccentColor
        ) {
            onDeleteButtonClicked()
        }
    }
}
