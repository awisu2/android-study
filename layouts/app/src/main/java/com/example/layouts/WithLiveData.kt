package com.example.layouts

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.layouts.databinding.ActivityWithLiveDataBinding

// INFO:
// [LiveData の概要 Android デベロッパー Android Developers](https://developer.android.com/topic/libraries/architecture/livedata?hl=ja)
//
// - 細かい話は置いといて、LiveData の observer(値の変更監視的なもの)はいい感じに動いているので、Activity、Fragmentと連動させておくと、メモリリークとか安全になりますよ
// - LiveDataのインスタンス管理は、通常ViewModelクラスを用意してそちらで行う
//   - Activityは非常にもろく画面回転だけで一回死ぬインスタンスなので、値保持には向かない
// - gradleに、activity-ktx のimplementationが必要(fragmentは別なので一緒implementationする)
//   - ktx: kotlin用の拡張のことらしいへんなサードパーティではないのでご安全
//
// ```
// f: build.gradle
//    // for LiveData: リンク先で最新のチェック
//    // https://developer.android.com/jetpack/androidx/releases/activity?hl=ja
//    // https://developer.android.com/jetpack/androidx/releases/fragment?hl=ja
//    def activity_version = "1.2.3"
//    def fragment_version = "1.3.4"
//    implementation "androidx.activity:activity-ktx:$activity_version"
//    implementation "androidx.fragment:fragment-ktx:$fragment_version"
// ```
//
class WithLiveData : AppCompatActivity() {
    private lateinit var binding: ActivityWithLiveDataBinding

    // viewModelのインスタンス
    private val viewModel: WithLiveDataViewModel by viewModels()

    companion object {
        private const val TAG = "WithLiveData"

        fun createIntent(context: Context): Intent {
            return Intent(context, WithLiveData::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWithLiveDataBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // messageの observe (onChangeイベント) をセット

        // INFO: Observerインスタンスも宣言するとこうなる
        // val observer = Observer<String> { v ->
        //     Log.d(TAG, "onChange message ${v}")
        // }
        // viewModel.message.observe(this, observer)

        // this で observe に lifecycle を共有している(STATEに合わせてobsrveの扱いを調整してくれる)
        // 回転したときなどでも、observeは発生する
        viewModel.message.observe(this) { message ->
            Log.d(TAG, "onChange message ${message}")
            // LiveData経由で変更しており、ボタンクリックなどユーザ操作に左右されない
            binding.withLiveDataText.text = message
        }

        // observeは複数登録してもすべてが発生する
        viewModel.message.observe(this) {
            Log.d(TAG, "onChange message second observe!")
        }

        // LiveDataの書き換え
        binding.withLiveDataChangeMessageButton.setOnClickListener{
            val message = if (binding.withLiveDataText.text != "foo") "foo" else "bar"
            // ドキュメントだとsetValue({v})だけどsetterになったっぽい
            viewModel.message.value = message
        }

        // LiveDataの拡張テスト(ドキュメント参照)
        setCustomLiveData()
    }

    // LiveDataの拡張テスト(ドキュメント参照)
    private fun setCustomLiveData() {
        viewModel.message2.observe(this) {
            binding.withLiveDataText2.text = it
        }

        binding.withLiveDataChangeMessageButton2.setOnClickListener {
            val message = if (binding.withLiveDataText2.text != "hoge") "hoge" else "fuga"
            viewModel.message2.value = message
        }
    }
}