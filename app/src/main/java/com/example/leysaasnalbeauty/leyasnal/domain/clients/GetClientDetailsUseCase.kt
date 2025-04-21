package com.example.leysaasnalbeauty.leyasnal.domain.clients

import com.example.leysaasnalbeauty.leyasnal.data.clients.ClientRepository
import javax.inject.Inject

class GetClientDetailsUseCase @Inject constructor(private val clientRepository: ClientRepository) {
    operator fun invoke(id:Int) = clientRepository.getClientDetails(id)
}