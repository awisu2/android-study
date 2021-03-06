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
- `@id`, `@+id` の違い: +付きは新規 ID を生成して割り当てる。確実にユニークな番号が割り当てられる
  - 基本 "+" はつけるし勝手につく。 "+" なしの挙動は不明。どこでどう管理されているか不明な id を利用することになる。
- jetpack: google が管理している android のライブラリ群
  - [Android デベロッパー  \|  Android Developers](https://developer.android.com/jetpack?hl=ja)
- `@JvmStatic`とは？
  - java からでも、companion object 内の要素に static としてアクセスできるようにするアノテーション
  - なんなの？
    - kotlin では、companion object 内に要素に static かのようにアクセスすることができるが、実際は instance 化されたメンバ
      - そのため、interface などが実装できるなど制限の緩和になっている
    - java からでも static にアクセスしたい、singleton で問題ない場合に、@JvmStatic を記載する

## layout

- layout の xml でちょいちょい出てくる `android:`, `app:`, `tools:` は名前空間を import していることを表現している
  - ex: `<root xmlns:android="http://schemas.android.com/apk/res/android" xmlns:tools="http://schemas.android.com/tools" >...</root>`
    - これで、android:, tool: が利用できる様になる
- 名前空間
  - "app:": `xmlns:app="http://schemas.android.com/apk/res-auto"`
    - カスタム属性を利用する際に必要な指定らしい(ドキュメント出てこず)
    - binding adapter もこの名前空間に属する。(コードを打っている感覚だと、android:のタグもすべてカバーしている感じ。継承してるのかな)
      - というところから考えると、カスタム属性が不要であればこの名前空間は利用しなくてもよい
  - "tools:": [ツール属性のリファレンス  \|  Android Studio  \|  Android Developers](https://developer.android.com/studio/write/tool-attributes?hl=ja)
- data binding を利用して、xml 内に foreach を記述する方法は今の所なさそう
