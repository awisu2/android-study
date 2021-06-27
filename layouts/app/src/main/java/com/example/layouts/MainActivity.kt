package com.example.layouts

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
// gradle の設定をすることで自動生成される(名称はlayoutファイル名の camel case)
import com.example.layouts.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // binding インスタンス
    private lateinit var binding: ActivityMainBinding

    // クラスに直接所属するSingleton(主に固定の変数などをセット)
    companion object {
        // constにするとstatic引数からの取得になる(つけない場合はget関数)
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // binding インスタンスのセット (クラス化された時点で対象レイアウトは決まっているのでid指定ではなくなっている)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // クリックイベント
        // setOnClickListenerを二重登録すると上書きされる
        binding.button.setOnClickListener {
            Log.w(TAG, "not call! when i over write")
        }
        binding.button.setOnClickListener {
            val intent = Sample01Activity.createIntent(this, "a", 123)
            startActivity(intent)
        }

        binding.button2.setOnClickListener {
            startActivity(LinerLayoutActivity.createIntent(this))
        }

        binding.button3.setOnClickListener {
            startActivity(GridActivity.createIntent(this))
        }

        binding.button4.setOnClickListener {
            startActivity(CustomSharpesActivity.createIntent(this))
        }

        binding.button5.setOnClickListener {
            startActivity(WithLiveData.createIntent(this))
        }

        binding.button6.setOnClickListener {
            startActivity(DataBindingActivity.createIntent(this))
        }

        binding.button7.setOnClickListener {
            startActivity(DataBindingWithViewModelLiveDataActivity.createIntent(this))
        }

        binding.button8.setOnClickListener {
            startActivity(FragmentActivity.createIntent(this))
        }
    }
}