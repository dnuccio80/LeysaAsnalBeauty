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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.leysaasnalbeauty.R
import com.example.leysaasnalbeauty.leyasnal.data.Routes
import com.example.leysaasnalbeauty.leyasnal.ui.AppViewModel
import com.example.leysaasnalbeauty.leyasnal.ui.components.AddBalanceDialog
import com.example.leysaasnalbeauty.leyasnal.ui.components.AlertDialogItem
import com.example.leysaasnalbeauty.leyasnal.ui.components.AmountDialog
import com.example.leysaasnalbeauty.leyasnal.ui.components.BalanceDetail
import com.example.leysaasnalbeauty.leyasnal.ui.components.EntryFlowRow
import com.example.leysaasnalbeauty.leyasnal.ui.components.Greeting
import com.example.leysaasnalbeauty.leyasnal.ui.dataclasses.EarningDataClass
import com.example.leysaasnalbeauty.leyasnal.ui.dataclasses.ExpenseDataClass
import com.example.leysaasnalbeauty.leyasnal.ui.dataclasses.toEarningDataClass
import com.example.leysaasnalbeauty.leyasnal.ui.dataclasses.toExpenseDataClass
import com.example.leysaasnalbeauty.leyasnal.ui.dataclasses.toTransactionDataClass
import com.example.leysaasnalbeauty.leyasnal.ui.dataclasses.toTransactionsDataClass
import com.example.leysaasnalbeauty.leyasnal.ui.components.EditEarningDialog
import com.example.leysaasnalbeauty.leyasnal.ui.components.EditExpenseDialog
import com.example.leysaasnalbeauty.leyasnal.ui.sections.AppointmentsSection
import com.example.leysaasnalbeauty.leyasnal.ui.sections.BirthdaySection
import com.example.leysaasnalbeauty.leyasnal.ui.sections.TransactionsSection

@Composable
fun HomeScreen(
    innerPadding: PaddingValues,
    viewModel: AppViewModel,
    navController: NavHostController
) {

    var showAddEarningDialog by rememberSaveable { mutableStateOf(false) }
    var showAddExpenseDialog by rememberSaveable { mutableStateOf(false) }
    var showCleanEarningsDialog by rememberSaveable { mutableStateOf(false) }
    var showCleanExpensesDialog by rememberSaveable { mutableStateOf(false) }
    var showEditEarningDialog by rememberSaveable { mutableStateOf(false) }
    var showEditExpenseDialog by rememberSaveable { mutableStateOf(false) }
    var showDeleteEarningDialog by rememberSaveable { mutableStateOf(false) }
    var showDeleteExpenseDialog by rememberSaveable { mutableStateOf(false) }
    var showAddBalanceDialog by rememberSaveable { mutableStateOf(false) }
    var showDeleteAllDataDialog by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.loadAppointments()
    }

    val earnings by viewModel.earnings.collectAsState()
    val expenses by viewModel.expenses.collectAsState()
    val todayAppointments by viewModel.todayAppointments.collectAsState()
    val tomorrowAppointments by viewModel.tomorrowAppointments.collectAsState()

    val context = LocalContext.current

    // Earning DataClass Saver
    val earningDataClassSaver = Saver<EarningDataClass, List<Any>>(
        save = { earning ->
            listOf(
                earning.id,
                earning.description,
                earning.amount,
            )
        },
        restore = { list ->
            EarningDataClass(
                id = list[0] as Int,
                description = list[1] as String,
                amount = list[2] as Int,
            )
        }
    )

    // Expenses Data Class Saver
    val expensesDataClassSaver = Saver<ExpenseDataClass, List<Any>>(
        save = { earning ->
            listOf(
                earning.id,
                earning.description,
                earning.amount,
            )
        },
        restore = { list ->
            ExpenseDataClass(
                id = list[0] as Int,
                description = list[1] as String,
                amount = list[2] as Int,
            )
        }
    )


    var selectedEarning by rememberSaveable(stateSaver = earningDataClassSaver) {
        mutableStateOf(
            EarningDataClass(description = "", amount = 0)
        )
    }

    var selectedExpense by rememberSaveable(stateSaver = expensesDataClassSaver) {
        mutableStateOf(
            ExpenseDataClass(description = "", amount = 0)
        )
    }

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
                BalanceDetail(
                    earnings, expenses, viewModel, onAddBalanceButtonClick = {
                        showAddBalanceDialog = true
                    },
                    onDeleteButtonClicked = {
                        showDeleteAllDataDialog = true
                    }
                )
                EntryFlowRow(
                    showEarningDialog = { showAddEarningDialog = true },
                    showExpenseDialog = { showAddExpenseDialog = true },
                    addNewClientClicked = { navController.navigate(Routes.AddClient.route) },
                    addNewGiftCardClicked = { navController.navigate(Routes.GiftCardMaker.route) },
                    notifyClientClicked = { navController.navigate(Routes.NotifyClient.route) },
                    scheduleAppointmentClicked = { navController.navigate(Routes.ScheduleAppointment.route) },
                    fidelityClientClicked = { navController.navigate(Routes.FidelitySystem.route) }
                )
                Spacer(Modifier.size(0.dp))
                AppointmentsSection(
                    stringResource(R.string.today_dates),
                    todayAppointments,
                    onAppointmentClicked = {
                        navController.navigate(Routes.ModifyAppointment.createRoute(it))
                    })
                AppointmentsSection(
                    stringResource(R.string.tomorrow_dates),
                    tomorrowAppointments,
                    onAppointmentClicked = {
                        navController.navigate(Routes.ModifyAppointment.createRoute(it))
                    })
                BirthdaySection(viewModel)
                TransactionsSection(
                    stringResource(R.string.earnings),
                    earnings.map {
                        it.toTransactionsDataClass()
                    },
                    viewModel,
                    icon = painterResource(R.drawable.ic_money),
                    onItemListClicked = {
                        selectedEarning = it.toEarningDataClass()
                        showEditEarningDialog = true
                    },
                    onCleanButtonClicked = {
                        showCleanEarningsDialog = true
                    }
                )
                TransactionsSection(
                    stringResource(R.string.expenses),
                    expenses.map {
                        it.toTransactionDataClass()
                    },
                    viewModel,
                    icon = painterResource(R.drawable.ic_buys),
                    onItemListClicked = {
                        selectedExpense = it.toExpenseDataClass()
                        showEditExpenseDialog = true
                    },
                    onCleanButtonClicked = {
                        showCleanExpensesDialog = true
                    }
                )

                // Earning Dialog
                AmountDialog(
                    show = showAddEarningDialog,
                    text = stringResource(R.string.add_earning),
                    onDismiss = { showAddEarningDialog = false },
                    onConfirm = { amount, description ->
                        viewModel.addEarning(amount, description)
                    }
                )

                // Expense Dialog
                AmountDialog(
                    show = showAddExpenseDialog,
                    text = stringResource(R.string.add_expense),
                    onDismiss = { showAddExpenseDialog = false },
                    onConfirm = { amount, description ->
                        viewModel.addExpense(amount, description)
                    }
                )

                // Alert Clean Earnings Dialog
                AlertDialogItem(
                    show = showCleanEarningsDialog,
                    text = stringResource(R.string.clean_earnings),
                    onDismiss = { showCleanEarningsDialog = false },
                    onConfirm = {
                        showCleanEarningsDialog = false
                        viewModel.deleteAllEarnings()
                    }
                )

                // Alert Clean Expenses Dialog
                AlertDialogItem(
                    show = showCleanExpensesDialog,
                    text = stringResource(R.string.clean_expenses),
                    onDismiss = { showCleanExpensesDialog = false },
                    onConfirm = {
                        showCleanExpensesDialog = false
                        viewModel.deleteAllExpensesData()
                    }
                )

                // Alert Delete Earning Dialog
                AlertDialogItem(
                    show = showDeleteEarningDialog,
                    text = stringResource(R.string.delete_earning_alert),
                    onDismiss = {
                        showDeleteEarningDialog = false
                    },
                    onConfirm = {
                        viewModel.deleteEarning(selectedEarning)
                        showDeleteEarningDialog = false
                        showEditEarningDialog = false
                    }
                )

                // Alert Delete Expense Dialog
                AlertDialogItem(
                    show = showDeleteExpenseDialog,
                    text = stringResource(R.string.delete_expense_alert),
                    onDismiss = {
                        showDeleteExpenseDialog = false
                    },
                    onConfirm = {
                        viewModel.deleteExpense(selectedExpense)
                        showDeleteExpenseDialog = false
                        showEditExpenseDialog = false
                    }
                )

                // Alert Delete All Data Dialog
                AlertDialogItem(
                    show = showDeleteAllDataDialog,
                    text = stringResource(R.string.clear_all_data_alert),
                    onDismiss = { showDeleteAllDataDialog = false },
                    onConfirm = {
                        showDeleteAllDataDialog = false
                        viewModel.clearBalanceData()
                    }
                )

                // Edit Earning
                EditEarningDialog(
                    show = showEditEarningDialog,
                    earning = selectedEarning,
                    onDismiss = {
                        showEditEarningDialog = false
                    },
                    onConfirm = {
                        viewModel.updateEarning(it)
                    },
                    onDelete = {
                        showDeleteEarningDialog = true
                    }
                )

                // Edit Expense
                EditExpenseDialog(
                    show = showEditExpenseDialog,
                    expense = selectedExpense,
                    onDismiss = { showEditExpenseDialog = false },
                    onConfirm = {
                        viewModel.updateExpense(it)
                    },
                    onDelete = {
                        showDeleteExpenseDialog = true
                    }
                )

                AddBalanceDialog(
                    show = showAddBalanceDialog,
                    onDismiss = {
                        showAddBalanceDialog = false
                    },
                    onPositiveBalanceAdded = { amount ->
                        showAddBalanceDialog = false
                        viewModel.addEarning(
                            amount = amount.toInt(),
                            description = context.getString(R.string.positive_balance_added_description)
                        )
                    },
                    onNegativeBalanceAdded = { amount ->
                        showAddBalanceDialog = false
                        viewModel.addExpense(
                            amount = amount.toInt(),
                            description = context.getString(R.string.negative_balance_added_description)
                        )
                    }
                )
            }
        }
    }
}