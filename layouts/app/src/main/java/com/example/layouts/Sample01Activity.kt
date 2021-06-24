package com.example.layouts

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.layouts.databinding.ActivityMainBinding
import com.example.layouts.databinding.ActivitySample01Binding

class Sample01Activity : AppCompatActivity() {
    // binding インスタンス
    private lateinit var binding: ActivitySample01Binding

    private var mArg01: String = ""
    private var mArg02: Int = 0

    private var mIsError: Boolean = false

    // クラスに直接所属するSingleton(主に固定の変数などをセット)
    companion object {
        // constにするとstatic引数からの取得になる(つけない場合はget関数)
        private const val TAG = "Sample01Activity"

        // intent keys
        private const val INTENT_KEY_ARG01 = "arg01"
        private const val INTENT_KEY_ARG02 = "arg02"

        // 引数などを含めると複雑になるので 自前でIntent生成を行わせる
        //
        // INFO: パラメータにオブジェクトを含めることはできない(プリミティブ変数くらい)
        // 本格的にオブジェクトなどを渡したいときは、 Singleton クラスや ViewModel などを利用するほうがよい
        //
        fun createIntent(context: Context, arg01: String, arg02: Int):Intent {
            return Intent(context, Sample01Activity::class.java).apply {
                // activity 間でパラメータを渡すときは Extraにセットする
                putExtra(Sample01Activity.INTENT_KEY_ARG01, arg01)
                putExtra(Sample01Activity.INTENT_KEY_ARG02, arg02)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySample01Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // パラメター受け取り(同じキーで受け取る)
        val arg01 = intent.getStringExtra(Sample01Activity.INTENT_KEY_ARG01)
        val arg02 = intent.getIntExtra(Sample01Activity.INTENT_KEY_ARG02, -1)
        if (arg01 == null || arg02 == -1) {
            showToast("extra parameters missing")
            return
        } else {
            showToast("extra parameters getted!!")
        }

        mArg01 = arg01
        mArg02 = arg02

        // 値のセット
        binding.Sample01TextView.text = mArg01
        binding.Sample02TextView.text = mArg02.toString() // コンバートはたいてい toXXX()
    }

    private fun showToast(message: String) {
        // 簡易アラート表示
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}