package com.example.leysaasnalbeauty.leyasnal.di

import android.app.Application
import androidx.room.Room
import com.example.leysaasnalbeauty.leyasnal.data.AppDataBase
import com.example.leysaasnalbeauty.leyasnal.data.annotations.AnnotationsDao
import com.example.leysaasnalbeauty.leyasnal.data.balance.earnings.EarningDao
import com.example.leysaasnalbeauty.leyasnal.data.balance.expenses.ExpenseDao
import com.example.leysaasnalbeauty.leyasnal.data.clients.ClientDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun privateAppDataBase(app: Application): AppDataBase {
        return Room.databaseBuilder(
            app,
            AppDataBase::class.java,
            "app_db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideClientDao(appDataBase: AppDataBase): ClientDao {
        return appDataBase.clientDao
    }

    @Provides
    @Singleton
    fun provideEarningDao(appDataBase: AppDataBase): EarningDao {
        return appDataBase.earningDao
    }
    @Provides
    @Singleton
    fun provideExpenseDao(appDataBase: AppDataBase): ExpenseDao {
        return appDataBase.expenseDao
    }

    @Provides
    @Singleton
    fun provideAnnotationsDao(appDataBase: AppDataBase): AnnotationsDao {
        return appDataBase.annotationsDao
    }

}