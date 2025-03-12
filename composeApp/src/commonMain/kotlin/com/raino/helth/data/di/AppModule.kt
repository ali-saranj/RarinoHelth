package com.raino.helth.data.di

import com.raino.helth.data.network.ApiService
import com.raino.helth.data.network.ApiServiceImpl
import com.raino.helth.data.network.KtorClient
import com.raino.helth.viewmodel.MainViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

/**
 * Main application module for dependency injection
 */
object AppModule {
    fun provideModule(): Module = module {
        includes(
            networkModule,
            viewModelModule,
            repositoryModule
        )
    }

    /**
     * Network related dependencies
     */
    private val networkModule = module {
        single { KtorClient.create() }
        single<ApiService>{ ApiServiceImpl(get()) }
    }

    /**
     * ViewModels
     */
    private val viewModelModule = module {
        viewModel { MainViewModel(get()) }
    }

    /**
     * Repositories
     */
    private val repositoryModule = module {
        // Example:
        // single<MyRepository> { MyRepositoryImpl(get()) }
    }
} 