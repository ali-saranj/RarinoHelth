package com.raino.helth.data.di

import com.raino.helth.data.network.ApiService
import com.raino.helth.data.network.ApiServiceImpl
import com.raino.helth.viewmodel.MainViewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
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
        single {
            HttpClient {
                install(ContentNegotiation) {
                    json(Json {
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true
                    })
                }

                install(Logging) {
                    logger = Logger.DEFAULT
                    level = LogLevel.ALL
                }
            }
        }
        single<ApiService> { ApiServiceImpl(client = get()) }
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