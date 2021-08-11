package com.example.contextlifecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.contextlifecycle.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        addFragment()

        applicationContext
    }


    private fun addFragment() {
        // transactionを開始
        val transaction = supportFragmentManager.beginTransaction()

        // fragmentの生成と追加
        // ほぼ通常のviewと同じ扱い。ここではLinerLayoutに追加
        val fragment = SampleFragment()
        transaction.add(binding.fragmentLinear.id, fragment)

        // 既存のfragmentを入れ替えたい場合はreplase
//        transaction.replace(fragment.id, anyFragment)

        // fragmentの生成と追加
        transaction.commit()
    }
}