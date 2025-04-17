package com.example.leysaasnalbeauty.leyasnal.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.leysaasnalbeauty.leyasnal.data.balance.UpdateEarningUseCase
import com.example.leysaasnalbeauty.leyasnal.domain.clients.AddNewClientUseCase
import com.example.leysaasnalbeauty.leyasnal.domain.clients.DeleteClientUseCase
import com.example.leysaasnalbeauty.leyasnal.domain.clients.GetAllClientsUseCase
import com.example.leysaasnalbeauty.leyasnal.domain.clients.UpdateClientUseCase
import com.example.leysaasnalbeauty.leyasnal.domain.earnings.AddEarningUseCase
import com.example.leysaasnalbeauty.leyasnal.domain.earnings.DeleteAllEarningsUseCase
import com.example.leysaasnalbeauty.leyasnal.domain.earnings.DeleteEarningUseCase
import com.example.leysaasnalbeauty.leyasnal.domain.earnings.GetAllEarningsUseCase
import com.example.leysaasnalbeauty.leyasnal.ui.dataclasses.ClientDataClass
import com.example.leysaasnalbeauty.leyasnal.ui.dataclasses.EarningDataClass
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
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

    // Funs


    // Clients

    fun addNewClient(client:ClientDataClass) {
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

    // Earnings

    fun deleteAllEarnings() {
        viewModelScope.launch(Dispatchers.IO) {
            deleteAllEarningsUseCase()
        }
    }

    fun addEarning(earning: EarningDataClass) {
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

}