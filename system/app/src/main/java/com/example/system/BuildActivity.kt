package com.example.system

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.system.databinding.ActivityBuildBinding
import com.example.system.databinding.ActivityMainBinding

class BuildActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBuildBinding

    companion object {
        // constにするとstatic引数からの取得になる(つけない場合はget関数)
        private const val TAG = "BuildActivity"

        fun createIntent(context: Context): Intent {
            return Intent(context, BuildActivity::class.java).apply {
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBuildBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.buildText01.text = "Build.VERSION_CODES.M = ${Build.VERSION_CODES.M} (M is for Marshmallow!)"
        binding.buildText02.text = "Build.VERSION.SDK_INT = ${Build.VERSION.SDK_INT} (The SDK version of the software currently running on this hardware device.)"


    }
}