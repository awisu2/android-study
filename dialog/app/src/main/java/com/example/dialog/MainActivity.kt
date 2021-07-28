package com.example.dialog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.dialog.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    companion object {
        val TAG: String = "MainActivity"
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            Log.d(TAG, "click")
        }
    }
}