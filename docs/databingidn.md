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
