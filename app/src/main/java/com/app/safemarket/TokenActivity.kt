package com.app.safemarket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.app.safemarket.model.Marker
import com.app.safemarket.repository.Repository

class TokenActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    lateinit var market: TextView
    lateinit var currentPeople: TextView
    lateinit var percent: TextView

    lateinit var up: ImageButton
    lateinit var down: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_token)

        market = findViewById(R.id.market_name)
        currentPeople = findViewById(R.id.current_people)
        percent = findViewById(R.id.percent)
        up = findViewById(R.id.arrUp)
        down = findViewById(R.id.arrDown)

        var d_id: String = ""
        var d_name: String = ""
        var d_curr: Int = 0
        var d_max: Int = 0

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getMarker()
        viewModel.myResponse.observe(this, Observer { response ->
            d_id = response._id
            d_name = response.name
            d_curr = response.currentPeople
            d_max = response.maxCapacity

            market.text = d_name
            currentPeople.text = "Personas: ${d_curr} / ${d_max}"
            val current = Math.round(d_curr.toDouble() * 100 / d_max.toDouble() * 10.0) / 10.0
            percent.text = "Aforo al: ${current}%"
        })

        up.setOnClickListener {
            viewModel.updateMarker(Marker(d_id, d_name, d_curr+1, d_max))
            viewModel.myResponse2.observe(this, Observer { response ->
                Log.d("response", response.body().toString())
                d_curr = response.body()?.currentPeople ?: 0 + 1
                currentPeople.text = "Personas: ${d_curr} / ${d_max}"
                val current = Math.round(d_curr.toDouble() * 100 / d_max.toDouble() * 10.0) / 10.0
                percent.text = "Aforo al: ${current}%"
            })
        }

        down.setOnClickListener {
            viewModel.updateMarker(Marker(d_id, d_name, d_curr-1, d_max))
            viewModel.myResponse2.observe(this, Observer { response ->
                Log.d("response", response.body().toString())
                d_curr = response.body()?.currentPeople ?: d_curr
                currentPeople.text = "Personas: ${d_curr} / ${d_max}"
                val current = Math.round(d_curr.toDouble() * 100 / d_max.toDouble() * 10.0) / 10.0
                percent.text = "Aforo al: ${current}%"
            })
        }
    }
}