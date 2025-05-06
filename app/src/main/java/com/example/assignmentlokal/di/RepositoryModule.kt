package com.example.assignmentlokal.di

import com.example.assignmentlokal.repository.JobRepository
import com.example.assignmentlokal.repository.JobRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideBlogRepo(repo: JobRepositoryImpl): JobRepository
}