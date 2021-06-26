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
        view.text = "hello ${text}"
    }
}