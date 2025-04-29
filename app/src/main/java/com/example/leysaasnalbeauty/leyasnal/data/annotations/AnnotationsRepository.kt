package com.example.leysaasnalbeauty.leyasnal.data.annotations

import com.example.leysaasnalbeauty.leyasnal.data.clients.toClientDataClass
import com.example.leysaasnalbeauty.leyasnal.ui.dataclasses.AnnotationsDataClass
import com.example.leysaasnalbeauty.leyasnal.ui.dataclasses.toAnnotationsEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AnnotationsRepository @Inject constructor(private val annotationsDao: AnnotationsDao) {

    fun getAllAnnotations(): Flow<List<AnnotationsDataClass>> = annotationsDao.getAllAnnotations().map {
        it.map { list ->
            list.toAnnotationsDataClass()
        }
    }

    fun getAnnotationDetail(annotationId:Int) = annotationsDao.getAnnotationDetails(annotationId).map {
        it?.toAnnotationsDataClass()
    }

    suspend fun addAnnotation(annotation: AnnotationsDataClass) = annotationsDao.addAnnotation(annotation.toAnnotationsEntity())

    suspend fun deleteAnnotation(annotation: AnnotationsDataClass) = annotationsDao.deleteAnnotation(annotation.toAnnotationsEntity())

    suspend fun updateAnnotation(annotation: AnnotationsDataClass) = annotationsDao.updateAnnotation(annotation.toAnnotationsEntity())

}