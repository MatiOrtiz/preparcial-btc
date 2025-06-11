package com.example.preparcialayd.model

import C.ApiY
import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.preparcialayd.injector.CriptoInjector

interface DataRepo {
    fun fetchPrice(coin:String):Double
}

interface LocalData {
    fun getData():Double
}

interface ExternalData {
    fun getData():Double
}

class localDataImpl(private val sharedPreferences:SharedPreferences) : LocalData {
    override fun getData(): Double {
        TODO("Not yet implemented")
    }
}

class ExternalDataImpl(private val sharedPreferences: SharedPreferences) : ExternalData {
    override fun getData(): Double {
        TODO("Not yet implemented")
    }
}


internal class DataRepoImpl(localData: LocalData, externalData: ExternalData): DataRepo {

    val sharedPreferences = CriptoInjector.sharedPreferences

    override fun fetchPrice(coin: String): Double {
        val savedValue = sharedPreferences.getString(coin, null)
        var result : Double

        if(savedValue != null) {
            try {
               result = savedValue.toDouble()
            } catch (e: Exception) {
               result = 0.0
           }
        } else {
            result = ApiY().get(coin)
            sharedPreferences.edit { putString(coin, result.toString()) }
        }
        return result
    }
}