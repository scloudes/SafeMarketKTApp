package com.app.safemarket

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.app.safemarket.util.Constants

class MainActivity : AppCompatActivity() {

    private lateinit var textView: TextView
    private lateinit var button: Button
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.token)
        button = findViewById(R.id.button)


        Log.d("Response",Constants.URL)

        button.setOnClickListener {
            Log.d("Response",textView.text.toString())

            Constants.URL = Constants.URL + textView.text.toString()+"/"
            Log.d("Response",Constants.URL)

            startActivity(Intent(this, TokenActivity::class.java));


        }
    }
}