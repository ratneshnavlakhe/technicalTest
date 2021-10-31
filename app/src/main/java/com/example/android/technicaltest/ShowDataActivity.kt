package com.example.android.technicaltest

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.android.technicaltest.di.MyApplication
import com.example.android.technicaltest.model.User
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_show_data.*
import javax.inject.Inject

class ShowDataActivity : AppCompatActivity() {

    @Inject
    lateinit var usecase: DataUsecase

    private lateinit var selectedUser: String

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_data)

        selectedUser = intent.getStringExtra(USER_KEY).toString()

        getUser(selectedUser)
    }

    @SuppressLint("CheckResult")
    private fun getUser(selectedUser: String) {
        usecase.getUser(selectedUser)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.i("ShowDataActivity", it.toString())
                showUserData(it)
            }, {
                Log.e("ShowDataActivity", "no data from data list")
            })
    }

    private fun showUserData(user: User) {
        userTitle.text = user.title
        firstName.text = user.firstName
        lastName.text = user.lastName
        Picasso.with(this).load(user.picture).into(profilePic)
    }

    companion object {
        private val USER_KEY = "USER_ID"
    }


}