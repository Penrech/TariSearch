package com.enrech.tarisearch.api_data.di

import com.enrech.tarisearch.api_data.repository.PlaceQueryRepositoryImpl
import com.enrech.tarisearch.api_domain.repository.PlaceQueryRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ApiDataModule {
    
    @Binds
    @Singleton
    abstract fun bindsPlaceQueryRepository(impl: PlaceQueryRepositoryImpl): PlaceQueryRepository
}