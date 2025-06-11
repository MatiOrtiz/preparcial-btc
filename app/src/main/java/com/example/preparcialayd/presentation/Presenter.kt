package com.example.preparcialayd.presentation

import android.util.Log
import ayds.observer.MyObservable
import com.example.preparcialayd.model.DataRepo
import ayds.observer.Subject
import kotlin.concurrent.thread
import kotlin.math.roundToInt

interface Presenter {
    val viewObserver: MyObservable<Pair<String,Int>>

    fun fetchPrice(selectedCoin: String)
}

internal class PresenterImpl(private val repository:DataRepo): Presenter {

    override val viewObserver = Subject<Pair<String,Int>>()

    override fun fetchPrice(selectedCoin: String) {
        thread {
            val result = repository.fetchPrice(selectedCoin)
            Log.e("RESULT", result.toString())
            viewObserver.notify(Pair(selectedCoin, result.roundToInt()))
        }
    }
}