package com.example.layouts

import android.widget.TextView
import androidx.databinding.BindingAdapter

// layoutでの要素を拡張する
//
//　DataBindingActivity.kt でサンプル
//
object BindingAdapter {
    // helloTextをTextViewに追加
    @BindingAdapter("helloText")
    // javaからでもstatic扱いに(お作法)
    @JvmStatic
    // 関数名は何でも良い
    fun helloText(
        view: TextView,
        text: String
    ) {
        // viewが拾えているので、textにhelloを追加してセット
        view.text = "hello ${text}"
    }
}