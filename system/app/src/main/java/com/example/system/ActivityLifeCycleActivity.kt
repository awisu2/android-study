package com.example.system

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.system.databinding.ActivityLifeCycleBinding

// 詳細はこちら
// [アクティビティのライフサイクルについて Android デベロッパー Android Developers](https://developer.android.com/guide/components/activities/activity-lifecycle?hl=ja)
class ActivityLifeCycleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLifeCycleBinding

    companion object {
        // constにするとstatic引数からの取得になる(つけない場合はget関数)
        private const val TAG = "ActivityLifeCycle"

        fun createIntent(context: Context): Intent {
            return Intent(context, ActivityLifeCycleActivity::class.java).apply {
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLifeCycleBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        Log.d(TAG, "onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }

}