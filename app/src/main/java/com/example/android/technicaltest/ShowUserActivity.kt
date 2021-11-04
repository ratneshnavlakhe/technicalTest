package com.example.android.technicaltest

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.example.android.technicaltest.di.MyApplication
import com.example.android.technicaltest.model.User
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_show_user.*
import javax.inject.Inject

class ShowUserActivity : AppCompatActivity() {

    @Inject
    lateinit var usecase: DataUsecase

    private lateinit var selectedUser: String

    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as MyApplication).appComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_user)

        selectedUser = intent.getStringExtra(USER_KEY).toString()

        getUser(selectedUser)
    }

    @SuppressLint("CheckResult")
    private fun getUser(selectedUser: String) {
        usecase.getUser(selectedUser)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnEvent { _, _ -> hideLoader() }
            .doOnSubscribe {
                showLoader()
                hideLayout()
            }
            .subscribe({
                showUserData(it)
            }, {
                showError()
            })
    }

    private fun showError() {
        Log.e("ShowDataActivity", "no user available with this user id")
    }

    private fun hideLayout() {
        userDataContent.visibility = View.INVISIBLE
    }

    private fun showLoader() {
        progressBar.visibility = ProgressBar.VISIBLE
    }

    private fun hideLoader() {
        progressBar.visibility = ProgressBar.INVISIBLE
    }

    private fun showUserData(user: User) {
        userDataContent.visibility = View.VISIBLE

        name.text = getString(
            R.string.name,
            user.firstName,
            user.lastName
        )
        genderValue.text = user.gender
        emailValue.text = user.email
        dateOfBirthValue.text = user.dateOfBirth
        phoneValue.text = user.phone
        emailValue.text = user.email
        Picasso
            .with(this)
            .load(user.picture)
            .resize(170, 170)
            .centerCrop()
            .into(profilePic)
    }


    companion object {
        private val USER_KEY = "USER_ID"
    }


}