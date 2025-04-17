package com.example.leysaasnalbeauty.leyasnal.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.leysaasnalbeauty.R
import com.example.leysaasnalbeauty.leyasnal.ui.AppViewModel
import com.example.leysaasnalbeauty.leyasnal.ui.components.AlertDialogItem
import com.example.leysaasnalbeauty.leyasnal.ui.components.AmountDialog
import com.example.leysaasnalbeauty.leyasnal.ui.components.BalanceDetail
import com.example.leysaasnalbeauty.leyasnal.ui.components.ClientDialog
import com.example.leysaasnalbeauty.leyasnal.ui.components.EntryFlowRow
import com.example.leysaasnalbeauty.leyasnal.ui.components.Greeting
import com.example.leysaasnalbeauty.leyasnal.ui.sections.TransactionsSection

@Composable
fun HomeScreen(innerPadding: PaddingValues, viewModel: AppViewModel) {

    var showAddEarningDialog by rememberSaveable { mutableStateOf(false) }
    var showAddExpenseDialog by rememberSaveable { mutableStateOf(false) }
    var showAddClientDialog by rememberSaveable { mutableStateOf(false) }
    var showCleanEarningsDialog by rememberSaveable { mutableStateOf(false) }
    var showCleanExpensesDialog by rememberSaveable { mutableStateOf(false) }

    val earnings by viewModel.earnings.collectAsState()

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
                Greeting("Ley")
                BalanceDetail(earnings)
                EntryFlowRow(
                    showEarningDialog = { showAddEarningDialog = true },
                    showExpenseDialog = { showAddExpenseDialog = true },
                    showNewClientDialog = { showAddClientDialog = true }
                )
                Spacer(Modifier.size(0.dp))
                TransactionsSection(stringResource(R.string.earnings)) {
                    showCleanEarningsDialog = true
                }
                TransactionsSection(stringResource(R.string.expenses)) {
                    showCleanExpensesDialog = true
                }

                // Earning Dialog
                AmountDialog(
                    show = showAddEarningDialog,
                    text = stringResource(R.string.add_earning),
                    onDismiss = { showAddEarningDialog = false },
                    onConfirm = {}
                )

                // Expense Dialog
                AmountDialog(
                    show = showAddExpenseDialog,
                    text = stringResource(R.string.add_expense),
                    onDismiss = { showAddExpenseDialog = false },
                    onConfirm = {}
                )

                // Client Dialog
                ClientDialog(
                    show = showAddClientDialog,
                    text = stringResource(R.string.new_client),
                    onDismiss = { showAddClientDialog = false },
                    onConfirm = { client ->
                        viewModel.addNewClient(client)
                    }
                )

                // Alert Clean Earnings Dialog
                AlertDialogItem(
                    show = showCleanEarningsDialog,
                    text = stringResource(R.string.clean_earnings),
                    onDismiss = { showCleanEarningsDialog = false },
                    onConfirm = { showCleanEarningsDialog = false }
                )

                AlertDialogItem(
                    show = showCleanExpensesDialog,
                    text = stringResource(R.string.clean_expenses),
                    onDismiss = { showCleanExpensesDialog = false },
                    onConfirm = { showCleanExpensesDialog = false }
                )
            }
        }
    }
}