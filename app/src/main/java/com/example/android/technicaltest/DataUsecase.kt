package com.example.android.technicaltest

import com.example.android.technicaltest.model.DataListEntity
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

interface DataUsecase {
    fun getDataList(): Single<List<DataListEntity>>
}

class DataUsecaseImpl(
    private val repo: DataRepo
) : DataUsecase {
    override fun getDataList(): Single<List<DataListEntity>> {
        return repo.getDataList()
            .subscribeOn(Schedulers.io())
            .map { it.data }
    }

}