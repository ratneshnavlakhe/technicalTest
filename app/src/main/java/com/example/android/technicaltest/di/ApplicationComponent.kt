package com.example.android.technicaltest.di

import android.app.Application
import com.example.android.technicaltest.MainActivity
import dagger.Component

@Component(modules = [DataModule::class])
interface ApplicationComponent {
    fun inject(activity: MainActivity)
}

class MyApplication: Application() {
    val appComponent = DaggerApplicationComponent.create()
}