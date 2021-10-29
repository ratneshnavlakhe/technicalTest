package com.example.android.technicaltest

import com.example.android.technicaltest.model.DataListResponse
import io.reactivex.Single
import retrofit2.http.GET

interface DataEndpoint {

    @GET("data/v1/user?limit=100")
    fun getDataList(): Single<DataListResponse>
}