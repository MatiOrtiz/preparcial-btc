package com.example.preparcialayd.view

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.preparcialayd.presentation.Presenter
import com.example.preparcialayd.R
import com.example.preparcialayd.injector.CriptoInjector

class CriptoView : AppCompatActivity() {

    //TODO: Mover COIN_TYPES a repository
    private val COIN_TYPES = listOf("USD", "EUR", "CAD", "JPY", "RUB", "GBP", "KRW", "PLN")

    private lateinit var presenter: Presenter
    private lateinit var spinner: Spinner
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initModule()
        observePresenter()
        initProperties()
        initListeners()

    }

    private fun initModule(){
        CriptoInjector.init(this)
        presenter = CriptoInjector.presenter
    }

    private fun observePresenter() {
        presenter.viewObserver.subscribe { result ->
            onPrice(result.first, result.second)
        }
    }

    private fun initProperties() {
        spinner = findViewById<Spinner>(R.id.spinnerMonedas)
        textView = findViewById<TextView>(R.id.textPrecio)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, COIN_TYPES)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
    }

    private fun initListeners() {
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val monedaSeleccionada = COIN_TYPES[position]
                presenter.fetchPrice(monedaSeleccionada)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    fun onPrice(symbol: String, price: Int) {
        val mensaje = "$symbol – $price"
        runOnUiThread {
            textView.text = mensaje
        }
    }
}