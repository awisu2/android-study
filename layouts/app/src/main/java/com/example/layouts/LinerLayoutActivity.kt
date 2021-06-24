package com.example.layouts

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.layouts.databinding.ActivityLinerLayoutBinding

class LinerLayoutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLinerLayoutBinding

    companion object {
        private const val TAG = "LinerLayoutActivity"

        fun createIntent(context: Context): Intent {
            return Intent(context, LinerLayoutActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLinerLayoutBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}