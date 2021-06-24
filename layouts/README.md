# layouts

- 紫のボタンを直したい: `app;backgroundTint` を設定(#AAAAAAで灰色に)

## binding を設定して、ボタンへのアクセスを簡単にする

- [ビュー バインディング  |  Android デベロッパー  |  Android Developers](https://developer.android.com/topic/libraries/view-binding?hl=ja)

f: build.gradle (app)

```gradle
android {
    viewBinding {
        enabled = true
    }
}
```

f: MainActivity.kt

```kt
package com.example.layouts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
// gradle の設定をすることで自動生成される(名称はlayoutファイル名の camel case)
import com.example.layouts.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // binding インスタンス
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // binding インスタンスのセット (クラス化された時点で対象レイアウトは決まっているのでid指定ではなくなっている)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // クリックイベント
        binding.button.setOnClickListener {
            Log.d("MainActivity", "click!")
        }
    }
}
```
