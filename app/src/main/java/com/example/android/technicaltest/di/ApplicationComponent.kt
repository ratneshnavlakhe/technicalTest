package com.example.android.technicaltest.di

import android.app.Application
import android.content.Context
import com.example.android.technicaltest.ShowUserActivity
import com.example.android.technicaltest.UserListActivity
import dagger.BindsInstance
import dagger.Component

@Component(modules = [DataModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(activity: UserListActivity)
    fun inject(activity: ShowUserActivity)
}

open class MyApplication : Application() {
    val appComponent: AppComponent by lazy {
        initializeComponent()
    }

    open fun initializeComponent() = DaggerAppComponent.factory().create(applicationContext)
}