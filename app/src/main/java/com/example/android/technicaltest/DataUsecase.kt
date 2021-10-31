package com.example.android.technicaltest

import com.example.android.technicaltest.model.DataEntity
import com.example.android.technicaltest.model.User
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

interface DataUsecase {
    fun getDataList(): Single<List<DataEntity>>
    fun getUser(userId: String): Single<User>
}

class DataUsecaseImpl(
    private val repo: DataRepo
) : DataUsecase {
    override fun getDataList(): Single<List<DataEntity>> {
        return repo.getDataList()
            .subscribeOn(Schedulers.io())
            .map { it.data }
    }

    override fun getUser(userId: String): Single<User> {
        return repo.getUser(userId)
            .subscribeOn(Schedulers.io())
    }

}