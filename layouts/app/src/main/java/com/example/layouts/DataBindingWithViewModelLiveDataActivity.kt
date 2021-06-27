package com.example.layouts

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.layouts.databinding.ActivityDataBindingWithViewModelLiveDataBinding
import com.example.layouts.databinding.ActivityLinerLayoutBinding

// DaatBindingと、ViewModel + LiveDataでの実装
//
// - viewModelに実装を記述することで
//   - layoutの操作の記述をviewModelにまとめる事ができる
//   - viewModelは複数記載できるため(もちろん煩雑になるが)特定の処理を分散させることができる
// - databingindにより
//   - idをベースとしたlayoutへの反映処理をロジックに載せずにすむ
// - 双方向バインディングを設定することで
//   - LiveData の observeを記載せずにすむ
//
class DataBindingWithViewModelLiveDataActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDataBindingWithViewModelLiveDataBinding
    private val viewModel: DataBindingWithViewModelLiveDataViewModel by viewModels()

    companion object {
        private const val TAG = "DataBindingWithViewModelLiveDataActivity"

        fun createIntent(context: Context): Intent {
            return Intent(context, DataBindingWithViewModelLiveDataActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDataBindingWithViewModelLiveDataBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // lifecycleOwnerをセットすることで、双方向のバインディングになる
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }
}