package com.example.layouts

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.layouts.databinding.ActivityCustomSharpesBinding

// drawableに、sharpeリソースを追加することでいくつかの形を設定することができる
//
// - 基本はbackgroundに設定?
//
// ## 角丸四角
//
// - android:spape=rectangleを設定し、radiusを設定する
// - radiusを割合で指定することはできないらしい
//
// ```xml
//     <item>
//        <shape android:shape="rectangle">
//            <corners
//                android:topRightRadius="20dp"
//                android:bottomRightRadius="20dp"
//                android:bottomLeftRadius="20dp"
//                android:topLeftRadius="20dp"
//                />
//            <solid android:color="@color/yellow" />
//        </shape>
//    </item>
// ```
//
// ## 円
//
// - android:spape=ovalを設定し、height = widthを同じにする
//   - oval = 楕円
//   - android:spape には ring があるが、ドーナッツ状になり内側は塗りつぶされない
//
// ```xml
//     <item>
//        <shape
//            android:shape="oval"
//            android:useLevel="false">
//            <solid android:color="@color/yellow" />
//        </shape>
//    </item>
// ```
class CustomSharpesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCustomSharpesBinding

    companion object {
        private const val TAG = "CustomSharpesActivity"

        fun createIntent(context: Context): Intent {
            return Intent(context, CustomSharpesActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCustomSharpesBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}