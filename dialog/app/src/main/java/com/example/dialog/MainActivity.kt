package com.example.dialog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.example.dialog.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    companion object {
        val TAG: String = "MainActivity"
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            Log.d(TAG, "click")
            dialog(it)
        }
    }

    private fun dialog(view: View) {
        AlertDialog.Builder(this)
            // 範囲外をクリックしたときにダイアログを消せるか？
            .setCancelable(true)
            .setMessage("メッセージ")
            .setPositiveButton("OK", { dialog, which ->
                showSnackbar(view, "clicked yes")
            })
            .setOnCancelListener {
                showSnackbar(view, "canceled")
            }
            .show()
        // alertdialogで処理は停止しないので、別途イベントをキャッチする必要
        showSnackbar(view, "no wait daialog end")
    }

    private fun showSnackbar(view: View, message: String) {
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()
    }
}