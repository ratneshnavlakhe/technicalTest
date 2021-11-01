package com.example.android.technicaltest.network

import com.example.android.technicaltest.di.NetworkDependency
import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NetworkFactory {
    fun getRetrofit(dependency: NetworkDependency): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(dependency.getBaseUrl())
            .build()
    }
}