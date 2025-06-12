package com.example.preparcialayd.model.local

import android.content.SharedPreferences

interface LocalData {
    fun getData(coin: String):Double?
}

class LocalDataImpl(private val sharedPreferences: SharedPreferences) : LocalData {
    override fun getData(coin:String): Double? {
        val savedValue = sharedPreferences.getString(coin, null)
        var result : Double? = null

        if (savedValue != null) {
            result = savedValue.toDouble()
        }

        return result
    }
}