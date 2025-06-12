package com.example.preparcialayd.injector

import android.content.Context
import android.content.SharedPreferences
import com.example.preparcialayd.model.ApiResolverImpl
import com.example.preparcialayd.model.DataRepo
import com.example.preparcialayd.model.DataRepoImpl
import com.example.preparcialayd.model.external.ExternalData
import com.example.preparcialayd.model.external.ExternalDataImpl
import com.example.preparcialayd.model.local.LocalData
import com.example.preparcialayd.model.local.LocalDataImpl
import com.example.preparcialayd.presentation.Presenter
import com.example.preparcialayd.presentation.PresenterImpl

object CriptoInjector {
    lateinit var presenter: Presenter
    lateinit var dataRepo: DataRepo

    fun init(context: Context) {
        val sharedPreferences = context.getSharedPreferences("MY_SHARED_PREFERENCES", Context.MODE_PRIVATE)
        val resolver = ApiResolverImpl()

        val localData : LocalData = LocalDataImpl(sharedPreferences)
        val externalData : ExternalData = ExternalDataImpl(sharedPreferences, resolver)
        dataRepo = DataRepoImpl(localData, externalData)
        presenter = PresenterImpl(dataRepo)
    }
}