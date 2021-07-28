package com.example.layouts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

// material desgin
//
// get started: https://material.io/resources/get-started
// getting start: https://material.io/develop/android/docs/getting-started
//
// 1. `implementation 'com.google.android.material:material:<version>'`
//
//
//
// - themaは継承するような命名規則: Theme.MaterialComponents.DayNight.DarkActionBar
//   - Theme.MaterialComponents に DyNight を加え、さらに DarkActionBar の設定を加えている
// - Bridge theme: メインテーマを変更できないとき、個別のstyleにparentとして割り当てられるBridgeが用意されている
//
class MaterialDesignActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_material_design)
    }
}