package com.example.leysaasnalbeauty.leyasnal.domain.annotations

import com.example.leysaasnalbeauty.leyasnal.data.annotations.AnnotationsRepository
import javax.inject.Inject

class GetAllAnnotationsUseCase @Inject constructor(private val annotationsRepository: AnnotationsRepository) {
    operator fun invoke() = annotationsRepository.getAllAnnotations()
}