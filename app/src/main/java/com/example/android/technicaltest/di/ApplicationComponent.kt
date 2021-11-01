package com.example.android.technicaltest.di

import android.app.Application
import com.example.android.technicaltest.ShowUserActivity
import com.example.android.technicaltest.UserListActivity
import dagger.Component

@Component(modules = [DataModule::class])
interface AppComponent {
    fun inject(activity: UserListActivity)
    fun inject(activity: ShowUserActivity)
}

class MyApplication : Application() {
    val appComponent: AppComponent = DaggerAppComponent.create()
}