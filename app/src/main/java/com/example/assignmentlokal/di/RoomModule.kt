package com.example.assignmentlokal.di

import android.content.Context
import androidx.room.Room
import com.example.assignmentlokal.local.JobDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Provides
    @Singleton
    fun provideJobDatabase(@ApplicationContext context: Context): JobDatabase {
        return Room.databaseBuilder(
            context,
            JobDatabase::class.java,
            "jobs.db"
        ).build()
    }
}