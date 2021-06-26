package com.example.layouts

import androidx.databinding.ObservableField

class SampleData(
    var num: Int = 0,
) {
    var text = ObservableField<String>("a")
    var text2 = "a"

    fun changeText() {
        text.set(if (text.get() != "a") "a" else "b")
    }

    fun changeText2() {
        text2 = if (text2 != "a") "a" else "b"
    }
}
