package com.example.android.technicaltest.di

import android.content.Context
import com.example.android.technicaltest.*
import com.example.android.technicaltest.network.NetworkFactory
import com.google.gson.Gson
import com.google.gson.GsonBuilder
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
        context: Context,
        gson: Gson
    ): UserEndpoint {
        return networkFactory.getRetrofit(dependency, context, gson)
            .create(UserEndpoint::class.java)
    }

    @Provides
    fun providesGson() = GsonBuilder().setLenient().create()

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