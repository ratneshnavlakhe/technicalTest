package com.example.android.technicaltest

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.technicaltest.di.MyApplication
import com.example.android.technicaltest.model.DataEntity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_user_list.*
import javax.inject.Inject


class UserListActivity : AppCompatActivity() {

    @Inject
    lateinit var usecase: DataUsecase

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)

        dataList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = UserListAdapter(null)
        }

        getDataList()
    }

    private fun showLoader() {
        progressBar.visibility = ProgressBar.VISIBLE
    }

    private fun hideLoader() {
        progressBar.visibility = ProgressBar.INVISIBLE
    }

    private fun showError() {
        Log.e("MainActivity", "no data from data list")
    }

    private fun showList(it: List<DataEntity>) {
        dataList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = UserListAdapter(it)
        }
    }

    @SuppressLint("CheckResult")
    private fun getDataList() {
        usecase.getDataList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnEvent { _, _ -> hideLoader() }
            .doOnSubscribe { showLoader() }
            .subscribe({
                showList(it)
            }, {
                showError()
            })
    }
}