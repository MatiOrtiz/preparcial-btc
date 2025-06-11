package com.example.preparcialayd.injector

import android.content.Context
import android.content.SharedPreferences
import com.example.preparcialayd.model.DataRepo
import com.example.preparcialayd.model.DataRepoImpl
import com.example.preparcialayd.model.LocalData
import com.example.preparcialayd.presentation.Presenter
import com.example.preparcialayd.presentation.PresenterImpl

object CriptoInjector {
    lateinit var presenter: Presenter
    lateinit var dataRepo: DataRepo
    lateinit var sharedPreferences: SharedPreferences

    fun init(context: Context) {
        dataRepo = DataRepoImpl(context)
        presenter = PresenterImpl(dataRepo)
        val sharedPreferences = context.getSharedPreferences("MY_SHARED_PREFERENCES", Context.MODE_PRIVATE)
        val localData : LocalData = LocalDataImpl(sharedPreferences)
        val externalData : ExternalDate = ExternalDataImpl(sharedPreferences)
        dataRepo = DataRepoImpl(localData, externalData)
    }
}