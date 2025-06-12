package com.example.preparcialayd.model

import com.example.preparcialayd.model.external.ExternalData
import com.example.preparcialayd.model.local.LocalData

interface DataRepo {
    val coinTypes : List<String>
    fun fetchPrice(coin:String):Double
}

internal class DataRepoImpl(private val localData: LocalData,
                            private val externalData: ExternalData): DataRepo {

    override val coinTypes = listOf("USD", "EUR", "CAD", "JPY", "RUB", "GBP", "KRW", "PLN")

    override fun fetchPrice(coin: String): Double {
        val dbPrice = localData.getData(coin)
        val result : Double

        if(dbPrice!= null) {
            result = dbPrice
        } else {
            result = externalData.getData(coin)
        }
        return result
    }
}