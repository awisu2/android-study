# ContextLifecycle

Android でよく出てくる Application, Activity, Context, fragment, viewmodel の相互関係およびアクセス方法をまとめる

- Fragment: Activity の用に画面表示を行うが、Activity 内のパーツとして挙動する
  - その性質上 Activity が前提となっている挙動がある
    - viewModel を共有できる、Acitivity と同時のライフサイクルを持つなど
- supportFragmentManager: FragmentActivity が持つメソッドで、主に fragment を追加、更新するために利用される
- AppCompatActivity: Activity の拡張。時代に合わせて必要機能がたされたのだと思っている
  - 追加機能分は Activity にはもちろん備わっていないので注意
  - [AppCompatActivity  \|  Android デベロッパー  \|  Android Developers](https://developer.android.com/reference/androidx/appcompat/app/AppCompatActivity)
  - FragmentActivity を Extend しており、supportFragmentManager もそちら経由で利用できるみたい
- Context: Application や Activity でも保持されるデータ部分？みたいなものっぽい
  - [Context  \|  Android デベロッパー  \|  Android Developers](https://developer.android.com/reference/android/content/Context.html)
  - [Android のライフサイクルからアプリ設計を見直してみる \- Qiita](https://qiita.com/teradonburi/items/f2aac85e53f8fa5c79bc)
  - 継承順位としてはほぼ最上位の abstract class で、ほぼすべての表示系オブジェクトの親ではなかろうか？
  - ApplicationContext: `applicationContext` でアクセスできる アプリが生きている限り永続
  - ActivityContext: その名の通り、Activity 毎に保持される Context
    - Intent の作成の際など、 Activity の this で代替される
