package com.example.android.technicaltest

import com.example.android.technicaltest.model.DataListResponse
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

interface DataRepo {
    fun getDataList(): Single<DataListResponse>
}

class DataRepoImpl(
    private val endpoint: DataEndpoint,
) : DataRepo {
    override fun getDataList(): Single<DataListResponse> {
        return endpoint.getDataList()
            .subscribeOn(Schedulers.io())
    }
}