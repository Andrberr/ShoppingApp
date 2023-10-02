package com.example.shopping.di

import com.example.shopping.data.RepositoryImpl
import com.example.shopping.domain.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun getRepository(impl: RepositoryImpl): Repository
}