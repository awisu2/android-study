package com.example.layouts

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.children
import com.example.layouts.databinding.ActivityLinerLayoutBinding
import org.w3c.dom.Text

class LinerLayoutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLinerLayoutBinding

    companion object {
        private const val TAG = "LinerLayoutActivity"

        fun createIntent(context: Context): Intent {
            return Intent(context, LinerLayoutActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLinerLayoutBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        Log.d(TAG, "--- onCreate! ")

        binding.linerLayout.children.iterator().forEach {
            // TextViewにキャスト
            if (it !is TextView) {
                return@forEach
            }
            val text = it as TextView

            // もう一つのcast
            // as に ? をつけることにより変更不可能だった場合はnull返却
//            val text = it as? TextView
//            if (text == null) {
//                return@forEach
//            }

            text.isClickable = true
            text.setOnClickListener{
//                    TODO: what is this
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    text.setTextAppearance(R.style.style03)
                }
            }
        }
    }
}