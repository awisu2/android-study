package com.example.layouts

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.layouts.databinding.ActivityLayoutInflateBinding
import com.example.layouts.databinding.LayoutLayoutInflateBinding

class LayoutInflateActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLayoutInflateBinding

    // viewModelのインスタンス
    private val viewModel: WithLiveDataViewModel by viewModels()

    companion object {
        private const val TAG = "LayoutInflateActivity"

        fun createIntent(context: Context): Intent {
            return Intent(context, LayoutInflateActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLayoutInflateBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.layoutInfolateButton.setOnClickListener {
            // INFO: viewGroupのものしか変更できない
//            inflate(it)
            layoutInflate(binding.layoutInflateLiner, "linerLayout")
        }

        binding.layoutInfolateButton2.setOnClickListener {
            // frameLayoutが対象として適役かと思ったが、中身が残る問題発生
            // INFO: inflate用に使っているlayoutがconstraintLayoutなので、framelayoutとの互換で発生している?
            // - 後述しているが、そもそもActivityやFragmentは空のViewGroupにinflateしている。それが本来的な使い方
            layoutInflate(binding.layoutFrameLayout, "flameLayout")
        }

        binding.layoutInfolateButton3.setOnClickListener {
            layoutInflateWithBinding(binding.layoutConstraintLayout2)
        }
    }

    // INFO: inflate
    // 対象のViewGroupのlayoutを「上書きする」(inflateする)機能
    //
    // - activityや、fragmentで毎回お決まりで利用しているlayout設定方法
    // - inflate対象の view instance があること前提
    // - 空の ViewGroup を置換するのが割と間違えなさそう
    // - 機能も含むときはfragmentのほうが良いかも。 (TODO: inflate vs fragment)
    //   - activityもセットでほしいと考えるとfragmentのほうが優勢か？
    // - 上書きする範囲が不明: 多分xmlで書き換えられる範囲が対象(?)
    //   - setOnClickListerは消えなかった
    //
    private fun layoutInflate(viewGroup: ViewGroup, name: String = "inflateView") {
        // じゃあ、ViewGroupを追加してそれをinflateすればと思ったが、Abstract Classのためインスタンス化不可
        // よく考えるとinfrate元のlayoutは何らかのViewGroup継承なわけで、それのinstanceを利用するのが良いのか
        //
        // val viewGroup = ViewGroup(this)

        // inflate
        val inflateView = layoutInflater.inflate(R.layout.layout_layout_inflate, viewGroup)

        // inflate自体にclickイベント
        inflateView.setOnClickListener{
            Toast.makeText(this, "${name} Click!", Toast.LENGTH_SHORT).show()
        }

        // ViewGroupを置き換えているだけなので、何回inflateしても何も変わらない
        // また、ViewGroup自体が変化しているわけではないので、setOnClickListernerのイベントも残る
        // TODO: もともと内部にあったものがどうなるかは不明(破棄される?)
        layoutInflater.inflate(R.layout.layout_layout_inflate, viewGroup)
        layoutInflater.inflate(R.layout.layout_layout_inflate, viewGroup)

        // 置き換えであることの証明のため、座標をずらしてみる
        layoutInflater.inflate(R.layout.layout_layout_inflate, viewGroup).apply {
            id = View.generateViewId()

            //　動的にmarginを変更
            val marginLayout = layoutParams as? ViewGroup.MarginLayoutParams
            if (marginLayout!= null) {
                marginLayout.topMargin = 50.dpToPx
            }
        }
    }

    // binding式で、inflate
    private fun layoutInflateWithBinding(viewGroup: ViewGroup) {
        Log.d(TAG, "layoutInflateWithBinding")
        // 第３引数 attachToParentはtrueじゃないと表示されないというかどこ行くんだ？
        val binding = LayoutLayoutInflateBinding.inflate(layoutInflater, viewGroup, true)
        val inflateView = binding.root

        // inflate自体にclickイベント
//        inflateView.isClickable = true
        inflateView.setOnClickListener{
            Toast.makeText(this, "Click!", Toast.LENGTH_SHORT).show()
        }

    }
}
