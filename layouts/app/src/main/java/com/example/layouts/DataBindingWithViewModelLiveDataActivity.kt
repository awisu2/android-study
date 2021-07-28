package com.example.layouts

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.layouts.databinding.ActivityDataBindingWithViewModelLiveDataBinding

// DaatBindingと、ViewModel + LiveDataでの実装
//
// - viewModelに実装を記述することで
//   - layoutの操作の記述をviewModelにまとめる事ができる
//   - viewModelは複数記載できるため(もちろん煩雑になるが)特定の処理を分散させることができる
//   - メモリリークが(viemodel, liveData による lifecycle の範囲において)考慮される
// - databingindにより
//   - idをベースとしたlayoutへの反映処理をロジックに載せずにすむ
//   - `android:text="@{viewModel.text}"` といった変数的な記載が可能になる
// - 双方向バインディングを設定することで(値の変更をlayoutへ、layoutでの変更を値へ)
//   - LiveData の observeを記載せずにすむ
//
// 双方向バインディングの実装の実装
// - lifecycle の設定
//   - `binding.lifecycleOwner = this`
//   - fragmentの場合は `binding.lifecycleOwner = viewLifecycleOwner`
// - layout.xmlへの記述
//   - `android:text="@={viewModel.text}"`
//     - `@=` と=をつけることで双方向になる
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

        viewModel.changeText()

    }
}w