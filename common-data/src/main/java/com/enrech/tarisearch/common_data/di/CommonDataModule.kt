package com.enrech.tarisearch.common_data.di

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ProcessLifecycleOwner
import androidx.room.Room
import com.enrech.tarisearch.common_data.ApplicationLifeCycle
import com.enrech.tarisearch.common_data.provider.DispatcherProviderImpl
import com.enrech.tarisearch.common_data.provider.HttpClientProvider
import com.enrech.tarisearch.common_data.provider.ResourceProviderImpl
import com.enrech.tarisearch.common_data.provider.UrlProviderImpl
import com.enrech.tarisearch.common_data.repository.MarkersRepositoryImpl
import com.enrech.tarisearch.common_data.room.Constant
import com.enrech.tarisearch.common_data.room.MarkersDatabase
import com.enrech.tarisearch.common_domain.provider.DispatcherProvider
import com.enrech.tarisearch.common_domain.provider.ResourceProvider
import com.enrech.tarisearch.common_domain.provider.UrlProvider
import com.enrech.tarisearch.common_domain.repository.MarkersRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CommonDataModule {
    @Binds
    @Singleton
    abstract fun bindDispatcherProvider(impl: DispatcherProviderImpl): DispatcherProvider

    @Binds
    @Singleton
    abstract fun bindUrlProvider(impl: UrlProviderImpl): UrlProvider

    @Binds
    @Singleton
    abstract fun bindMarkersRepository(impl: MarkersRepositoryImpl): MarkersRepository

    @Binds
    @Singleton
    abstract fun bindResourceProvider(impl: ResourceProviderImpl): ResourceProvider

    companion object {
        @Provides
        @ApplicationLifeCycle
        fun provideAppLifeCycleOwner(): LifecycleOwner = ProcessLifecycleOwner.get()

        @Provides
        @Singleton
        fun provideHttpClient(): HttpClient = HttpClientProvider.getClient()

        @Singleton
        @Provides
        fun provideMarkerDatabase(@ApplicationContext context: Context): MarkersDatabase =
            Room
                .databaseBuilder(context, MarkersDatabase::class.java, Constant.dbName)
                .build()
    }
}