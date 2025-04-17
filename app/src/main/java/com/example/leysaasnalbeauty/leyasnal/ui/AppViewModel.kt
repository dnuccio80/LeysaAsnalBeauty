package com.example.leysaasnalbeauty.leyasnal.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.leysaasnalbeauty.leyasnal.domain.AddNewClientUseCase
import com.example.leysaasnalbeauty.leyasnal.domain.DeleteClientUseCase
import com.example.leysaasnalbeauty.leyasnal.domain.GetAllClientsUseCase
import com.example.leysaasnalbeauty.leyasnal.domain.UpdateClientUseCase
import com.example.leysaasnalbeauty.leyasnal.ui.dataclasses.ClientDataClass
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    getAllClientsUseCase: GetAllClientsUseCase,
    private val addNewClientUseCase: AddNewClientUseCase,
    private val deleteClientUseCase: DeleteClientUseCase,
    private val updateClientUseCase: UpdateClientUseCase
) : ViewModel() {

    private val _clients = getAllClientsUseCase().stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList()
    )
    val clients = _clients

    fun addNewClient(client:ClientDataClass) {
        viewModelScope.launch(Dispatchers.IO) {
            addNewClientUseCase(client)
        }
    }

}