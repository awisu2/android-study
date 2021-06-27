# android-study

自分用 android 開発勉強用リポジトリ

小さめの動作サンプルで構成

## 共通事項でのまとめ

- cast: `val text = it as TextView`
  - `as?` にすることで cast できなかった場合は null、つけない場合だとエラー
  - とはいえ、cast する時点で利用したいはずなので、`it is TextView` でチェックして `it as TextView` のほうが健全(?)
- res/values の基本
  - [アプリリソースの概要  \|  Android デベロッパー  \|  Android Developers](https://developer.android.com/guide/topics/resources/providing-resources?hl=ja)
  - ファイル名は何でも良いが、種類ごとに分けといたほうが見やすい
  - ルートタグは　`<resources></resources>`
  - 内部においたタグで、何らかの値を表現する
  - 設置できるタグの種類
    - color: 色
    - dimen: サイズ(dp など)
    - string: 文字列
    - style: スタイル
  - アクセスする際は、`@{種類}/{name}` という形式になる
    - 例: `<dimen name="space">5dp</dimen>` にアクセスする場合は `@dimen/space`
