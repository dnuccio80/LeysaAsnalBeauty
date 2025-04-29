package com.example.leysaasnalbeauty.leyasnal.data.annotations

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface AnnotationsDao {

    @Query("SELECT * FROM AnnotationsEntity")
    fun getAllAnnotations(): Flow<List<AnnotationsEntity>>

    @Query("SELECT * FROM AnnotationsEntity WHERE id = :id")
    fun getAnnotationDetails(id:Int): Flow<AnnotationsEntity>

    @Insert
    suspend fun addAnnotation(annotation: AnnotationsEntity)

    @Delete
    suspend fun deleteAnnotation(annotation: AnnotationsEntity)

    @Update
    suspend fun updateAnnotation(annotation: AnnotationsEntity)

}