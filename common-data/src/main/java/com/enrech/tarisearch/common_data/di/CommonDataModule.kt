package com.enrech.tarisearch.common_data.di

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ProcessLifecycleOwner
import com.enrech.tarisearch.common_data.ApplicationLifeCycle
import com.enrech.tarisearch.common_data.provider.DispatcherProviderImpl
import com.enrech.tarisearch.common_data.provider.HttpClientProvider
import com.enrech.tarisearch.common_data.provider.UrlProviderImpl
import com.enrech.tarisearch.common_domain.provider.DispatcherProvider
import com.enrech.tarisearch.common_domain.provider.UrlProvider
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

    companion object {
        @Provides
        @ApplicationLifeCycle
        fun provideAppLifeCycleOwner(): LifecycleOwner = ProcessLifecycleOwner.get()

        @Provides
        @Singleton
        fun provideHttpClient(): HttpClient = HttpClientProvider.getClient()
    }
}