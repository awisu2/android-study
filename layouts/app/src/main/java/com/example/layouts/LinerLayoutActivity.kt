package com.example.layouts

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.children
import androidx.core.widget.TextViewCompat
import com.example.layouts.databinding.ActivityLinerLayoutBinding
import org.w3c.dom.Text

// INFO:
// LinerLayoutサンプル
//
// ## LinerLayout内の子viewに等間隔の隙間を空けたい
//
// 残念ながら一括指定する方法はない
// 擬似的であれば、カスタムスタイルに以下の方法でセットすることにより統一可能
//
// 隙間を開ける方法
// 1. viewにlayout_marginをセットする
// 2. viewにlayout_paddingをセットする
// 3. spaceタグを追加する
//
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

        Log.d(TAG, "--- onCreate! ")

        binding.linerLayout.children.iterator().forEach {
            // TextViewにキャスト
            if (it !is TextView) {
                return@forEach
            }
            val text = it as TextView

            // もう一つのcast
            // as に ? をつけることにより変更不可能だった場合はnull返却
//            val text = it as? TextView
//            if (text == null) {
//                return@forEach
//            }

            text.isClickable = true
            text.setOnClickListener{
                // クリック時にスタイルを適用(テキスト関係だけが反映される)
                TextViewCompat.setTextAppearance(text, R.style.myActiveText)
            }
        }
    }
}