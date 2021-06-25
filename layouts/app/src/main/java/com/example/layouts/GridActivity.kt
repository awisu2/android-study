package com.example.layouts

import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.graphics.drawable.GradientDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.ContextThemeWrapper
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.constraintlayout.helper.widget.Flow
import androidx.core.view.children
import androidx.core.widget.TextViewCompat
import androidx.recyclerview.widget.OrientationHelper
import com.example.layouts.databinding.ActivityGridBinding

class GridActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGridBinding
    private var isGap: Boolean = true
    private var addCount: Int = 0

    companion object {
        private const val TAG = "GridActivity"

        fun createIntent(context: Context): Intent {
            return Intent(context, GridActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGridBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // gapのtoggle
        binding.gridToggleGapButton.setOnClickListener{
            // TODO: gapパラメータを取得し、現在値を参照してのtoggle
            val gap = if (isGap) 0 else 10

            // px指定なので変換してセット
            binding.gridLayoutFlow.setVerticalGap(gap.dpToPx)
            binding.gridLayoutFlow.setHorizontalGap(gap.dpToPx)

            isGap = !isGap
        }

        // add
        binding.gridAddButton.setOnClickListener{
            // constructorであればstyleのセットが可能
            val text = TextView(ContextThemeWrapper(this, R.style.myText)).apply {
                id = View.generateViewId()
                text = "add Text ${addCount}"
                background = getDrawable(R.drawable.round_square)
            }

            addCount += 1

            // constraintLayoutに追加し、flowにも追加(layout画面での Referenced Viewに追加される)
            binding.gridLayout.addView(text)
            binding.gridLayoutFlow.addView(text)
        }

        // delete
        binding.gridDeleteButton.setOnClickListener{
            val view = getLastChildWithoutFlow()
            if (view == null) return@setOnClickListener

            binding.gridLayoutFlow.removeView(view)
            binding.gridLayout.removeView(view)
        }

        // wrapmodeの変更
        binding.gridFlowNoneButton.setOnClickListener {
            binding.gridLayoutFlow.setWrapMode(Flow.WRAP_NONE)
        }

        binding.gridFlowAlignedButton.setOnClickListener {
            binding.gridLayoutFlow.setWrapMode(Flow.WRAP_ALIGNED)
        }

        binding.gridFlowChainButton.setOnClickListener {
            binding.gridLayoutFlow.setWrapMode(Flow.WRAP_CHAIN)
        }
    }

    // 最後のviewを取得(Flow以外)
    private fun getLastChildWithoutFlow(): View? {
        val count = binding.gridLayout.children.count()
        Log.d(TAG, "--- ${count}")
        if (count <= 1) return null

        return binding.gridLayout.children.filter {
            child -> child !is Flow
        }.last()
    }
}

