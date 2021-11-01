package com.example.android.technicaltest.network

import com.example.android.technicaltest.di.NetworkDependency
import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NetworkFactory {
    fun getRetrofit(dependency: NetworkDependency): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(dependency.getBaseUrl())
            .client(getInterceptor())
            .build()
    }

    private fun getInterceptor(): OkHttpClient {
        return OkHttpClient.Builder().apply {
            addInterceptor(
                Interceptor { chain ->
                    val builder = chain.request().newBuilder()
                    builder.header("app-id", "617a643fd02e388d62fef08a")
                    return@Interceptor chain.proceed(builder.build())
                }
            )
        }.build()
    }
}