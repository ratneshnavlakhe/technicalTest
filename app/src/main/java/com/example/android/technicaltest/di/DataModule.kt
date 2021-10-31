package com.example.android.technicaltest.di

import com.example.android.technicaltest.*
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class DataModule {

    @Provides
    fun providesDataUsecase(
        repo: DataRepo
    ): DataUsecase = DataUsecaseImpl(repo)

    @Provides
    fun providesDataRepo(endpoint: DataEndpoint): DataRepo {
        return DataRepoImpl(endpoint)
    }

    @Provides
    fun providesDataEndpoint(dependency: NetworkDependency): DataEndpoint {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(dependency.getBaseUrl())
            .build()
            .create(DataEndpoint::class.java)
    }

    @Provides
    fun networkDependency(): NetworkDependency {
        return object : NetworkDependency {
            override fun getBaseUrl(): String {
                return "https://dummyapi.io/"
            }
        }
    }


}