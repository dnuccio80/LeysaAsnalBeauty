package com.example.leysaasnalbeauty.leyasnal.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.leysaasnalbeauty.leyasnal.data.appoointments.AppointmentWithClient
import com.example.leysaasnalbeauty.leyasnal.domain.annotations.AddAnnotationUseCase
import com.example.leysaasnalbeauty.leyasnal.domain.annotations.DeleteAnnotationUseCase
import com.example.leysaasnalbeauty.leyasnal.domain.annotations.GetAllAnnotationsUseCase
import com.example.leysaasnalbeauty.leyasnal.domain.annotations.GetAnnotationDetailsUseCase
import com.example.leysaasnalbeauty.leyasnal.domain.annotations.UpdateAnnotationUseCase
import com.example.leysaasnalbeauty.leyasnal.domain.appointments.AddAppointmentUseCase
import com.example.leysaasnalbeauty.leyasnal.domain.appointments.DeleteAppointmentUseCase
import com.example.leysaasnalbeauty.leyasnal.domain.appointments.DeletePastAppointmentsUseCase
import com.example.leysaasnalbeauty.leyasnal.domain.appointments.GetAllAppointmentsUseCase
import com.example.leysaasnalbeauty.leyasnal.domain.appointments.GetAppointmentDetailsUseCase
import com.example.leysaasnalbeauty.leyasnal.domain.appointments.GetFutureAppointmentsUseCase
import com.example.leysaasnalbeauty.leyasnal.domain.appointments.UpdateAppointmentUseCase
import com.example.leysaasnalbeauty.leyasnal.domain.earnings.UpdateEarningUseCase
import com.example.leysaasnalbeauty.leyasnal.domain.clients.AddNewClientUseCase
import com.example.leysaasnalbeauty.leyasnal.domain.clients.DeleteClientUseCase
import com.example.leysaasnalbeauty.leyasnal.domain.clients.GetAllClientsUseCase
import com.example.leysaasnalbeauty.leyasnal.domain.clients.GetClientDetailsUseCase
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
import com.example.leysaasnalbeauty.leyasnal.ui.dataclasses.AnnotationsDataClass
import com.example.leysaasnalbeauty.leyasnal.ui.dataclasses.AppointmentDataClass
import com.example.leysaasnalbeauty.leyasnal.ui.dataclasses.ClientDataClass
import com.example.leysaasnalbeauty.leyasnal.ui.dataclasses.EarningDataClass
import com.example.leysaasnalbeauty.leyasnal.ui.dataclasses.ExpenseDataClass
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(

    // Clients

    getAllClientsUseCase: GetAllClientsUseCase,
    private val addNewClientUseCase: AddNewClientUseCase,
    private val deleteClientUseCase: DeleteClientUseCase,
    private val updateClientUseCase: UpdateClientUseCase,
    private val getClientDetailsUseCase: GetClientDetailsUseCase,

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

    // Annotations
    getAllAnnotationsUseCase: GetAllAnnotationsUseCase,
    private val addAnnotationUseCase: AddAnnotationUseCase,
    private val deleteAnnotationUseCase: DeleteAnnotationUseCase,
    private val updateAnnotationUseCase: UpdateAnnotationUseCase,
    private val getAnnotationDetailsUseCase: GetAnnotationDetailsUseCase,

    // Appointments
    getAllAppointmentsUseCase: GetAllAppointmentsUseCase,
    private val getFutureAppointmentsUseCase: GetFutureAppointmentsUseCase,
    private val addAppointmentUseCase: AddAppointmentUseCase,
    private val deleteAppointmentUseCase: DeleteAppointmentUseCase,
    private val updateAppointmentUseCase: UpdateAppointmentUseCase,
    private val getAppointmentDetailsUseCase: GetAppointmentDetailsUseCase,
    private val deletePastAppointmentsUseCase: DeletePastAppointmentsUseCase,

    ) : ViewModel() {

    // Vals

    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query

    private val _clients = getAllClientsUseCase().map { list ->
        list.sortedBy { it.name }
    }.stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList()
    )
    val clients = _clients

    val filteredClients = combine(_clients, _query) { clients, query ->
        if (query.isEmpty() || query.isBlank()) {
            clients
        } else {
            clients.filter { it.name.contains(query, ignoreCase = true) }
        }
    }.stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList()
    )

    private val clientId = MutableStateFlow(0)

    @OptIn(ExperimentalCoroutinesApi::class)
    private val _clientDetails = clientId.flatMapLatest {
        getClientDetailsUseCase(clientId.value)
    }.stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5000), null
    )

    val clientDetails: StateFlow<ClientDataClass?> = _clientDetails


    private val _earnings = getAllEarningsUseCase().stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList()
    )
    val earnings = _earnings

    private val _expenses = getAllExpensesUseCase().stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList()
    )
    val expenses = _expenses

    private val _annotations = getAllAnnotationsUseCase().stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList()
    )
    val annotations: StateFlow<List<AnnotationsDataClass>> = _annotations

    private val annotationId = MutableStateFlow(0)

    @OptIn(ExperimentalCoroutinesApi::class)
    private val _annotationDetails = annotationId.flatMapLatest {
        getAnnotationDetailsUseCase(annotationId.value)
    }.stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5000), null
    )
    val annotationDetails: StateFlow<AnnotationsDataClass?> = _annotationDetails

    private val _appointments = getAllAppointmentsUseCase().stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList()
    )
    val appointments: StateFlow<List<AppointmentWithClient>> = _appointments

    private val appointmentId = MutableStateFlow(0)

    @OptIn(ExperimentalCoroutinesApi::class)
    private val _appointmentDetails = appointmentId.flatMapLatest {
        getAppointmentDetailsUseCase(appointmentId.value)
    }.stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5000), null
    )
    val appointmentDetails = _appointmentDetails

    private val _futureAppointments = MutableStateFlow<List<AppointmentWithClient>>(emptyList())
    val futureAppointments: StateFlow<List<AppointmentWithClient>> = _futureAppointments

    private val _todayAppointments = MutableStateFlow<List<AppointmentWithClient>>(emptyList())
    val todayAppointments: StateFlow<List<AppointmentWithClient>> = _todayAppointments

    private val _tomorrowAppointments = MutableStateFlow<List<AppointmentWithClient>>(emptyList())
    val tomorrowAppointments: StateFlow<List<AppointmentWithClient>> = _tomorrowAppointments

    // Fun

    fun loadAppointments() {
        viewModelScope.launch {

            deletePastAppointments()

            val allAppointments = getFutureAppointmentsUseCase(LocalDateTime.now())

            val now = LocalDate.now()
            val todayStart = now.atStartOfDay()
            val todayEnd = now.plusDays(1).atStartOfDay()

            val tomorrowStart = now.plusDays(1).atStartOfDay()
            val tomorrowEnd = now.plusDays(2).atStartOfDay()



            _todayAppointments.value = allAppointments.filter {
                it.appointment.date in todayStart..todayEnd
            }

            _tomorrowAppointments.value = allAppointments.filter {
                it.appointment.date in tomorrowStart..tomorrowEnd
            }
        }
    }

    private fun deletePastAppointments() {
        viewModelScope.launch {
            val now = LocalDateTime.now()
            deletePastAppointmentsUseCase(now)
        }
    }


    // Clients

    fun addNewClient(client: ClientDataClass) {
        viewModelScope.launch(Dispatchers.IO) {
            addNewClientUseCase(client)
        }
    }

    fun deleteClient(clientId: Int) {
        val client = clients.value.find { it.id == clientId }
        viewModelScope.launch(Dispatchers.IO) {
            if (client != null) deleteClientUseCase(client)
        }
    }

    fun updateClient(client: ClientDataClass) {
        viewModelScope.launch(Dispatchers.IO) {
            updateClientUseCase(client)
        }
    }

    fun setClientId(id: Int) {
        clientId.value = id
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

    // Annotations

    fun addAnnotation(title: String, description: String) {
        val annotation = AnnotationsDataClass(
            title = title,
            description = description
        )
        viewModelScope.launch(Dispatchers.IO) {
            addAnnotationUseCase(annotation)
        }
    }

    fun deleteAnnotation(annotation: AnnotationsDataClass) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteAnnotationUseCase(annotation)
        }
    }

    fun updateAnnotation(annotation: AnnotationsDataClass) {
        viewModelScope.launch(Dispatchers.IO) {
            updateAnnotationUseCase(annotation)
        }
    }

    fun setAnnotationId(id: Int) {
        annotationId.value = id
    }

    // Appointments

    fun addAppointment(clientId: Int, date: LocalDateTime, serviceType: String) {
        viewModelScope.launch(Dispatchers.IO) {
            addAppointmentUseCase(AppointmentDataClass(clientId = clientId, serviceType = serviceType, date = date))
        }
    }

    fun deleteAppointment(id:Int) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteAppointmentUseCase(id)
        }
    }

    fun updateAppointment(appointment: AppointmentDataClass) {
        viewModelScope.launch(Dispatchers.IO) {
            updateAppointmentUseCase(appointment)
        }
    }

    fun setAppointmentId(id: Int) {
        appointmentId.value = id
    }

    // Generic fun

    fun formatPrice(price: Int): String {
        val format = NumberFormat.getCurrencyInstance(Locale("es", "AR"))
        format.maximumFractionDigits = 0
        return format.format(price)
    }

    fun clearBalanceData() {
        deleteAllExpensesData()
        deleteAllEarnings()
    }

    fun onQueryChanged(newQuery: String) {
        _query.value = newQuery
    }

}