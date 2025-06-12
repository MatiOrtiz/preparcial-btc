package com.example.preparcialayd.model.external

import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.preparcialayd.model.ApiResolver
import com.example.preparcialayd.model.ApiY

interface ExternalData {
    fun getData(coin:String):Double
}

class ExternalDataImpl(private val sharedPreferences: SharedPreferences,
                       private val resolver: ApiResolver) : ExternalData {

    override fun getData(coin:String): Double {
        val result = ApiY(resolver).get(coin)
        sharedPreferences.edit { putString(coin, result.toString()) }

        return result
    }

}