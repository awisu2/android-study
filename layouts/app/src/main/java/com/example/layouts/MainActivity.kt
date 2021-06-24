package com.example.layouts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Intent
    }

    val intent = Intent(this, DisplayMessageActivity::class.java).apply {
        putExtra(EXTRA_MESSAGE, message)
    }
    startActivity(intent)
}