package com.example.leysaasnalbeauty.leyasnal.domain.clients

import com.example.leysaasnalbeauty.leyasnal.data.clients.ClientRepository
import com.example.leysaasnalbeauty.leyasnal.ui.dataclasses.ClientDataClass
import javax.inject.Inject

class DeleteClientUseCase @Inject constructor(private val clientRepository: ClientRepository) {
    suspend operator fun invoke(client:ClientDataClass) = clientRepository.deleteClient(client)
}