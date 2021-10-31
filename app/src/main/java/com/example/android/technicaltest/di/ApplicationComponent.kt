package com.example.android.technicaltest.di

import android.app.Application
import com.example.android.technicaltest.MainActivity
import com.example.android.technicaltest.ShowDataActivity
import dagger.Component

@Component(modules = [DataModule::class])
interface AppComponent {
    fun inject(activity: MainActivity)
    fun inject(activity: ShowDataActivity)
}

class MyApplication : Application() {
    val appComponent: AppComponent = DaggerAppComponent.create()
}