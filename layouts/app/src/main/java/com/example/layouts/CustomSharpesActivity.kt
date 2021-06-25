package com.example.layouts

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.layouts.databinding.ActivityCustomSharpesBinding

class CustomSharpesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCustomSharpesBinding

    companion object {
        private const val TAG = "CustomSharpesActivity"

        fun createIntent(context: Context): Intent {
            return Intent(context, CustomSharpesActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCustomSharpesBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}