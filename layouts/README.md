# layouts

- ボタンのあれを直したい
  - 紫ががが: `android:backgroundTint` を設定(#AAAAAA で灰色に)
    - このとき`android:background` は設定してはだめ
    - これについて掘り下げる:
  - 全部大文字になる: `android:textAllCaps` を false で指定
- もっとしっかり紫をなくしたい
  - 原因は、"res/values/themes/themes.xml" で style の parent に "Theme.MaterialComponents.DayNight.DarkActionBar" が割当てられているのが原因
    - このテーマは、background など特定の色を強制設定しており、そのため変更ができない
  - `Theme.AppCompat.DayNight.DarkActionBar` に変更することで、background が無事機能するようになる
    - note: **ただし、この影響がどのくらいの範囲に渡っているのかは不明なので注意**
  - じゃあ `backgroundTint` って何さ？: 「background に割り当てた画像リソースにカラーフィルタをかけて色を変化させる属性」とのこと
    - [Android のボタンスタイルを理解する - Qiita](https://qiita.com/ushi3_jp/items/5a77afe89ab876046eb1)
    - そのため **α のある色をセットすると紫が交じる** 色変更できてるっぽいのは α が無いまたはベタ塗りでの設定のため
      - 実際、drawable での sharpe で色指定をして backgroundTint にセットすると合成した色になる
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
- android の 2 大 layout: ConstraintLayout, FlexBoxLayout
  - ConstraintLayout: 親及び同階層の View に対し相対的な位置を指定することが可能な Layout
    - default では Activity のベースとして利用されている
    - 子要素は垂直、水平の制約をかならず指定する必要がある
    - 詳細は後述
  - FlexBoxLayout: まだ良くわかっていないが, 座標指定不可で内部の view は重なる
    - Fragment のデフォルトルート view に利用されている
    - 単体パーツ用？
- grid 表示の実装は、ConstraintLayout を利用する
  - Android では GridLayout, GridView は Legacy になっている
  - [ConstraintLayout でレスポンシブ UI を作成する  |  Android デベロッパー](https://developer.android.com/training/constraint-layout?hl=ja)
- サイズ単位について
  - dp どの(解像度が違う)端末でも同じ大きさに見えるように調整される単位。基本これが利用される
  - px (pixel): dp と違い、端末ごとの 1px に相当
- サイズを考慮した変換関数
  - dp と px 間の倍率: `Resources.getSystem().displayMetrics.density`
    - Activity 内であれば、 `resources.displayMetrics.density` で OK
  - dp から px に変換する関数を Int に追加
    - `val Int.dpToPx: Int get() = (this * Resources.getSystem().displayMetrics.density).toInt()`
    - サブパッケージからでも import して利用できるので、Util.kt にでも書いておくと便利

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

## ConstraintLayout で可能なこと

[ConstraintLayout でレスポンシブ UI を作成する  |  Android デベロッパー](https://developer.android.com/training/constraint-layout?hl=ja)

- ガイドラインを設定して制約の一つにする
  - 砂時計みたいなアイコンをクリックして Add Horizontal Guideline or Add Vertical Guideline
- バリア：View に関連付けられたガイドライン
  - 砂時計みたいなアイコンをクリックして Add Horizontal Barrier or Add Vertical Barrier
- 制約のサイズ指定時のアイコンの見た目について
  - I : 固定
  - ">>>" : コンテンツ(内部要素)を収めるのに必要なだけ拡大(wrap content 的な?)
  - ギザギザ: 制約に合致する範囲で可能な限り拡大
- チェーン: View 同士を相互に連携させた線形グループ(縦/横のグルーピング)
  - 相互のサイズを考慮した設定が可能
    - Spread: 均等な margin で配置
    - Spread inside: 外枠(layout と)設置する部分の margin は 0 または固定で均等配置
    - Weighted: 隙間がないように拡大しつつ均等配置 (weight の指定が可能)
      - layout_constraintHorizontal_weight, layout_constraintVertical_weight
  - 必ずしも垂直、水平の両方を設定する必要はなく、「水平のチェーンを設定して、垂直方向はずれている」みたいな設定も可能
- アニメーション: ConstraintSets を利用するとのこと

## プログラム上で、TextView に style を割り当てる

[How to Dynamically Change an Android View’s Style | by Elye | Mobile App Development Publication | Medium](https://medium.com/mobile-app-development-publication/dynamically-change-android-views-style-56b18e59b33b)

- `TextView(this, null, 0, R.style.myText)` と、第４引数に割り当てることで設定が可能
  - ただ、第２，３引数に空の値をセットしてしまっている
- SDK API 21 ~ であれば、`TextView(ContextThemeWrapper(this, R.style.myText))` という記述も可能
- テキスト関連だけが対象だが、生成されたインスタンスに上書きもできる
  - `TextViewCompat.setTextAppearance(text, R.style.myText)`
