package com.example.leysaasnalbeauty.leyasnal.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.leysaasnalbeauty.leyasnal.data.Routes
import com.example.leysaasnalbeauty.leyasnal.domain.earnings.UpdateEarningUseCase
import com.example.leysaasnalbeauty.leyasnal.domain.clients.AddNewClientUseCase
import com.example.leysaasnalbeauty.leyasnal.domain.clients.DeleteClientUseCase
import com.example.leysaasnalbeauty.leyasnal.domain.clients.GetAllClientsUseCase
import com.example.leysaasnalbeauty.leyasnal.domain.clients.UpdateClientUseCase
import com.example.leysaasnalbeauty.leyasnal.domain.earnings.AddEarningUseCase
import com.example.leysaasnalbeauty.leyasnal.domain.earnings.DeleteAllEarningsUseCase
import com.example.leysaasnalbeauty.leyasnal.domain.earnings.DeleteEarningUseCase
import com.example.leysaasnalbeauty.leyasnal.domain.earnings.GetAllEarningsUseCase
import com.example.leysaasnalbeauty.leyasnal.domain.expenses.AddExpenseUseCase
import com.example.leysaasnalbeauty.leyasnal.domain.expenses.DeleteAllExpensesDataUseCase
import com.example.leysaasnalbeauty.leyasnal.domain.expenses.DeleteExpenseUseCase
import com.example.leysaasnalbeauty.leyasnal.domain.expenses.GetAllExpensesUseCase
import com.example.leysaasnalbeauty.leyasnal.domain.expenses.UpdateExpenseUseCase
import com.example.leysaasnalbeauty.leyasnal.ui.dataclasses.ClientDataClass
import com.example.leysaasnalbeauty.leyasnal.ui.dataclasses.EarningDataClass
import com.example.leysaasnalbeauty.leyasnal.ui.dataclasses.ExpenseDataClass
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(

    // Clients

    getAllClientsUseCase: GetAllClientsUseCase,
    private val addNewClientUseCase: AddNewClientUseCase,
    private val deleteClientUseCase: DeleteClientUseCase,
    private val updateClientUseCase: UpdateClientUseCase,

    // Earnings

    getAllEarningsUseCase: GetAllEarningsUseCase,
    private val deleteAllEarningsUseCase: DeleteAllEarningsUseCase,
    private val addEarningUseCase: AddEarningUseCase,
    private val deleteEarningUseCase: DeleteEarningUseCase,
    private val updateEarningUseCase: UpdateEarningUseCase,

    // Expenses

    getAllExpensesUseCase: GetAllExpensesUseCase,
    private val deleteAllExpensesDataUseCase: DeleteAllExpensesDataUseCase,
    private val addExpenseUseCase: AddExpenseUseCase,
    private val deleteExpenseUseCase: DeleteExpenseUseCase,
    private val updateExpenseUseCase: UpdateExpenseUseCase,

    ) : ViewModel() {

    // Vals

    private val _clients = getAllClientsUseCase().stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList()
    )
    val clients = _clients

    private val _earnings = getAllEarningsUseCase().stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList()
    )
    val earnings = _earnings

    private val _expenses = getAllExpensesUseCase().stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList()
    )
    val expenses = _expenses

    // Fun

    // Clients

    fun addNewClient(client: ClientDataClass) {
        viewModelScope.launch(Dispatchers.IO) {
            addNewClientUseCase(client)
        }
    }

    fun deleteClient(client: ClientDataClass) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteClientUseCase(client)
        }
    }

    fun updateClient(client: ClientDataClass) {
        viewModelScope.launch(Dispatchers.IO) {
            updateClientUseCase(client)
        }
    }

    fun getClientById(clientId: Int): ClientDataClass {
        clients.value.forEach {
            if(it.id == clientId) return it
        }
        return ClientDataClass(0,"","","")
    }

    // Earnings

    fun deleteAllEarnings() {
        viewModelScope.launch(Dispatchers.IO) {
            deleteAllEarningsUseCase()
        }
    }

    fun addEarning(amount: Int, description: String) {

        val earning: EarningDataClass = EarningDataClass(
            amount = amount,
            description = description
        )

        viewModelScope.launch(Dispatchers.IO) {
            addEarningUseCase(earning)
        }
    }

    fun deleteEarning(earning: EarningDataClass) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteEarningUseCase(earning)
        }
    }

    fun updateEarning(earning: EarningDataClass) {
        viewModelScope.launch(Dispatchers.IO) {
            updateEarningUseCase(earning)
        }
    }

    // Expenses

    fun deleteAllExpensesData() {
        viewModelScope.launch(Dispatchers.IO) {
            deleteAllExpensesDataUseCase()
        }
    }

    fun addExpense(amount: Int, description: String) {
        val expense = ExpenseDataClass(
            amount = amount,
            description = description
        )
        viewModelScope.launch(Dispatchers.IO) {
            addExpenseUseCase(expense)
        }
    }

    fun deleteExpense(expense: ExpenseDataClass) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteExpenseUseCase(expense)
        }
    }

    fun updateExpense(expense: ExpenseDataClass) {
        viewModelScope.launch(Dispatchers.IO) {
            updateExpenseUseCase(expense)
        }
    }

    // Generic fun

    fun formatPrice(price: Int): String {
        val format = NumberFormat.getCurrencyInstance(Locale("es", "AR"))
        format.maximumFractionDigits = 0
        return format.format(price)
    }

}