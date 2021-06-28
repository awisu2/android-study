package com.example.layouts

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import com.example.layouts.databinding.ActivityDataBindingBinding
import com.example.layouts.databinding.ActivityWithLiveDataBinding

// INFO: data binding
// links: https://developer.android.com/topic/libraries/data-binding/expressions?hl=ja
//
// - Layout内に、変数を導入することでシンプルな連携を可能にする
//   - メリット: コードでわざわざlayoutのどこそこにセットということをせずにすむ
//     - 複数のviewにまたがる場合なども変数にセットするだけでOK
//     - いちいち、それぞれのviewにidを割り当てなくて良い
//     - layout側で、イベント処理をある程度可能 (過信厳禁、細かいことはできない)
//   - デメリット: クラスのあり方が、Layoutに引っ張られる
//     - viewModelが更に重く。。。
// - gradleに追記必要
//
// ```gradle
// dataBinding {
//     enabled = true
// }
// ```
//
// 1. レイアウトのxmlファイルのルートを layout に変更
//    - プロパティはxmlsの宣言だけ既存のrootから移動
// 2. dataタグ内に連携したいdata郡を記述
// 3. layout内でそれらの値を利用する
//
// layout.xmlでの挙動について
//
// - 色々可能ではあるが、以下はできない
//   - Int型の宣言と、text表示
//   　　- 実際には利用できるが計算に利用できるだけ、toString()すら実行できない
//   - 文字の直接表示。どこかで宣言しておきそれを利用 (android:text="hello @{name}"とかできない)
//   - 文字の合成
// - ざっくり：三項演算子とオブジェクトのメソッドコール以外のことは不可能
// - 色々可能なように見えて、layout用の挙動はcode側で用意してあげないといけない
//
class DataBindingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDataBindingBinding

    // bindingを何らかの理由でnullにしたい場合
//    private var _binding: ActivityDataBindingBinding? = null
//    private val binding get() = _binding!!

    // viewModelのインスタンス
    private val viewModel: WithLiveDataViewModel by viewModels()

    companion object {
        private const val TAG = "ActivityDataBindingBinding"

        fun createIntent(context: Context): Intent {
            return Intent(context, DataBindingActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // INFO: viewBindingを利用しない場合はこちら
//         val binding: ActivityDataBindingBinding = DataBindingUtil.setContentView(
//                this, R.layout.activity_data_binding)
        binding = ActivityDataBindingBinding.inflate(layoutInflater)

        val view = binding.root
        setContentView(view)

        binding.message = "foo ---- bar ------"
        binding.sampleData = SampleData(100)
    }
}
