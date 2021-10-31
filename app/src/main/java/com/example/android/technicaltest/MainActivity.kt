package com.example.android.technicaltest

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.technicaltest.di.MyApplication
import com.example.android.technicaltest.model.DataEntity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var usecase: DataUsecase

    private lateinit var linerLayoutManager: LinearLayoutManager
    private lateinit var adapter: DataAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        linerLayoutManager = LinearLayoutManager(this)
        dataList.layoutManager = linerLayoutManager

        getDataList()
    }

    private fun showLoader() {
        // no-op
    }

    private fun hideLoader() {
        // no-op
    }

    private fun showError() {
        // no-op
    }

    private fun showList(it: List<DataEntity>) {
        adapter = DataAdapter(it)
        dataList.adapter = adapter
    }

    @SuppressLint("CheckResult")
    private fun getDataList() {
        usecase.getDataList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnEvent { _, _ -> hideLoader() }
            .doOnSubscribe { showLoader() }
            .subscribe({
                Log.i("MainActivity", it.toString())
                showList(it)
            }, {
                showError()
                Log.e("MainActivity", "no data from data list")
            })
    }
}