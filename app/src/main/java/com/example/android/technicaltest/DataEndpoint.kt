package com.example.android.technicaltest

import com.example.android.technicaltest.model.DataListResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers

interface DataEndpoint {

    @Headers("app-id: 617a643fd02e388d62fef08a")
    @GET("data/v1/user?limit=100")
    fun getDataList(): Single<DataListResponse>
}