package com.example.android.technicaltest.di

import android.content.Context
import com.example.android.technicaltest.*
import com.example.android.technicaltest.network.NetworkFactory
import dagger.Module
import dagger.Provides


@Module
class DataModule {

    @Provides
    fun providesDataUsecase(
        repo: DataRepo
    ): DataUsecase = DataUsecaseImpl(repo)

    @Provides
    fun providesDataRepo(endpoint: UserEndpoint): DataRepo {
        return DataRepoImpl(endpoint)
    }

    @Provides
    fun providesDataEndpoint(
        dependency: NetworkDependency,
        networkFactory: NetworkFactory,
        context: Context
    ): UserEndpoint {
        return networkFactory.getRetrofit(dependency, context).create(UserEndpoint::class.java)
    }

    @Provides
    fun providesNetworkFactory(): NetworkFactory = NetworkFactory()

    @Provides
    fun networkDependency(): NetworkDependency {
        return object : NetworkDependency {
            override fun getBaseUrl(): String {
                return "https://dummyapi.io/"
            }
        }
    }
}