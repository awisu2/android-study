# data binding

サンプルは: layouts/DataBindingActivity

- Layout ファイル(xml) 内に、変数を導入することでシンプルな連携を可能にする
- gradle に追記必要(後述)
- メリット: コードでわざわざ layout のどこそこにセットということをせずにすむ
  - 複数の view にまたがる場合なども変数にセットするだけで OK
  - いちいち、それぞれの view に id を割り当てなくて良い
  - layout 側で、イベント処理をある程度可能 (過信厳禁、細かいことはできない)
- デメリット: クラスのあり方が、Layout に引っ張られる
  - viewModel が更に重く。。。
- 色々可能ではあるが、以下はできない
  - Int 型の宣言と、text 表示
    　　- 実際には利用できるが計算に利用できるだけ、toString()すら実行できない
  - 文字の直接表示。どこかで宣言しておきそれを利用 (android:text="hello @{name}"とかできない)
    - `String.valueOf(1 + num)` でいけるっぽい
  - 文字の合成
- ざっくり：三項演算子とオブジェクトのメソッドコール以外のことは不可能
- 色々可能なように見えて、layout 用の挙動は code 側で用意してあげないといけない
- BindingAdapter: `app:myAdapter="@{name}"` の myAdapter を自前で作成する機能(コードは後述)

ざっくり使い方

1. レイアウトの xml ファイルのルートを layout に変更
   - プロパティは xmls の宣言だけ既存の root から移動
2. 直下に data タグを用意し連携したい item 郡を記述
3. 元の(小階層に移動した)layout 内でそれらの値を利用する記述
   　　- 例: `android:text="@{name}"`
4. activity 側で binding 及び値セット

## links

- [レイアウトとバインディング式  |  Android デベロッパー  |  Android Developers](https://developer.android.com/topic/libraries/data-binding/expressions?hl=ja)

## gradle

```gradle
dataBinding {
    enabled = true
}
```

## BindingAdapter の使い方

layout の xml 内で、`app:myAdapter="@{name}"` の myAdapter を自前で作成する機能

## links

- 公式: 初っ端から不明な単語が並んでいるので後回し。何の話をしているかわからなかった。
  - [バインディング アダプター  \|  Android デベロッパー  \|  Android Developers](https://developer.android.com/topic/libraries/data-binding/binding-adapters?hl=ja)
- こちらを参考にさせていただいた
  - [DataBinding チュートリアル第 3 回 – BindingAdapter\(バインディングアダプター\)｜ mizutory ｜ note](https://note.com/mizutory/n/nd6d07e198974)
  - 一通り進めることで、これがアダプターなのかと理解できました。ありがとうございます。
  - 記事に不足していた事項としては、ネットアクセスのためのパーミッションが必要でした(`<uses-permission android:name="android.permission.INTERNET" />`)

## サンプル app:helloText を追加する

TextView にセットするとテキストの頭に "hello" がつくだけの、helloText を追加するサンプル  
コード上のプロパティとしては利用できませんでした。(自前で拡張設定を書くのが妥当？)

アノテーション(@~~)を利用するため、gradle に kapt を追加

f: build.gradle

```gradle
plugins {
    ...
    id 'kotlin-kapt'
}
```

f: BindingAdapter.kt

ファイル名は何でも良い。これで TextView に、**helloText** をセットできるようになる

```kt
package com.example.layouts

import android.widget.TextView
import androidx.databinding.BindingAdapter

object BindingAdapter {
    @BindingAdapter("helloText")
    @JvmStatic
    fun loadImage(
        view: TextView,
        text: String
    ) {
        // 指定テキストに、helloを付与するだけ
        view.text = "hello ${text}"
    }
}
```

f: activity_data_binding.xml

```xml
      <TextView
          style="@style/myText"
          app:helloText="@{sampleData.text}"
          />
```

### 一通りやってみてから、公式を見ると少し理解可能になりました

[バインディング アダプター  \|  Android デベロッパー  \|  Android Developers](https://developer.android.com/topic/libraries/data-binding/binding-adapters?hl=ja)
