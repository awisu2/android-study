package com.example.layouts

import android.content.res.Resources

// 画面サイズを考慮し、dpをpxに変換
val Int.dpToPx: Int get() = (this * Resources.getSystem().displayMetrics.density).toInt()
