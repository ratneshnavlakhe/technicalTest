package com.example.android.technicaltest

import com.example.android.technicaltest.model.User
import com.example.android.technicaltest.model.UserListResponse
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

interface DataRepo {
    fun getDataList(): Single<UserListResponse>
    fun getUser(userId: String): Single<User>
}

class DataRepoImpl(
    private val endpoint: UserEndpoint,
) : DataRepo {
    override fun getDataList(): Single<UserListResponse> {
        return endpoint.getUserList()
            .subscribeOn(Schedulers.io())
    }

    override fun getUser(userId: String): Single<User> {
        return endpoint.getUser(userId)
            .subscribeOn(Schedulers.io())
    }
}