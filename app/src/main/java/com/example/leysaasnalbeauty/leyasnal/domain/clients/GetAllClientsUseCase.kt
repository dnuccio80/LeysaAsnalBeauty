package com.example.leysaasnalbeauty.leyasnal.domain.clients

import com.example.leysaasnalbeauty.leyasnal.data.clients.ClientRepository
import javax.inject.Inject

class GetAllClientsUseCase @Inject constructor(private val clientRepository: ClientRepository) {
    operator fun invoke() = clientRepository.getAllClients()
}