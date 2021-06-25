package com.example.system

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.system.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    // クラスに直接所属するSingleton(主に固定の変数などをセット)
    companion object {
        // constにするとstatic引数からの取得になる(つけない場合はget関数)
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.button.setOnClickListener {
            startActivity(BuildActivity.createIntent(this))
        }

        binding.gotoLifecycleButton.setOnClickListener {
            startActivity(ActivityLifeCycleActivity.createIntent(this))
        }
    }
}