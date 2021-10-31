package com.example.android.technicaltest

import com.example.android.technicaltest.model.DataListResponse
import com.example.android.technicaltest.model.User
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

interface DataRepo {
    fun getDataList(): Single<DataListResponse>
    fun getUser(userId: String): Single<User>
}

class DataRepoImpl(
    private val endpoint: DataEndpoint,
) : DataRepo {
    override fun getDataList(): Single<DataListResponse> {
        return endpoint.getDataList()
            .subscribeOn(Schedulers.io())
    }

    override fun getUser(userId: String): Single<User> {
        return endpoint.getUser(userId)
            .subscribeOn(Schedulers.io())
    }
}