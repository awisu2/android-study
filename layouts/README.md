# layouts

- ボタンの紫を直したい: `app;backgroundTint` を設定(#AAAAAA で灰色に)
- TextView
  - テキストを中央にしたい: `android:gravity = center`
  - TextView 自体を親 View の中央にしたい: `layout_gravity = center`
- style 設定を使い回す: res/values 配下に resource file を作成しその中に style タグを記載。何個でも OK
  - [スタイル リソース  |  Android デベロッパー  |  Android Developers](https://developer.android.com/guide/topics/resources/style-resource?hl=ja)
  - 詳細は後述
- view の cast: `val text = view as TextView`
  - 型が合わない場合はクラッシュ
  - 型チェック: `view is TextView`
  - 変換できない場合は null にする: `val text = view as? TextView`
  - `is` して `as` (`as?` でなく)　するのが良さそう(後で使うのだから)

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

## style 設定を使い回す

[スタイル リソース  |  Android デベロッパー  |  Android Developers](https://developer.android.com/guide/topics/resources/style-resource?hl=ja)

- res/values 配下に resource file を追加し、style タグを記載することで利用可能
  - ファイル名は何でも良い 例: styles.xml button_styles.xml
- name が必須で、指定するときの名前になる
- parent は option で他の style 設定を継承できる
- style の指定方法: {style_name}, @style/{style_name}, [package:]style_property_name
  - @style は style タグのことを表現している
- layout 上では、style プロパティにセットするだけで良い

f: styles.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <style name="style01">
        <item name="android:textColor">#0000FF</item>
    </style>
    <style name="style02">
        <item name="android:textColor">#FF0000</item>
    </style>
    <style name="style03" parent="style01">
        <item name="android:textStyle">bold</item>
    </style>
</resources>
```

f: layout.xml

```xml
        <TextView
            android:id="@+id/textView"
            style="@style/style01"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:clickable="true"
            android:text="TextView" />
```
