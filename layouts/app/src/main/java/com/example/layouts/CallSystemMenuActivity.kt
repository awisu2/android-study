package com.example.layouts

import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.pm.LabeledIntent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.layouts.databinding.ActivityCallSystemMenuBinding


class CallSystemMenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCallSystemMenuBinding

    companion object {
        private const val TAG = "CallSystemMenuActivity"

        fun createIntent(context: Context): Intent {
            return Intent(context, CallSystemMenuActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCallSystemMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.activity = this
    }

    // [他のアプリへの単純なデータの送信 Android デベロッパー Android Developers](https://developer.android.com/training/sharing/send?hl=ja)
    fun callUsableAppList() {
        val sendIntent = Intent()
        sendIntent.action = Intent.ACTION_SEND

        // shareするデータをセット
        sendIntent.putExtra(Intent.EXTRA_TEXT, "share this text")
        sendIntent.type = "text/plain"

        // シェアするデータが何なのかを説明する枠を表示できる
        // が、うまくうごかない
//        sendIntent.putExtra(Intent.EXTRA_TITLE, "extra title")
//        sendIntent.data = Uri.parse("https://developer.android.com/images/training/sharing/sharing_content_preview.png?hl=ja")
//        sendIntent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION

        val shareIntent =Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }

    // TODO: うまく動作しない
//
//    fun callShare() {
////        callShare01()
//        callShare02()
//
//
//    }
//
//    private fun callShare01() {
//        val tweetIntent = Intent(Intent.ACTION_SEND)
//        tweetIntent.putExtra(Intent.EXTRA_TEXT, "This is a Test.")
//        tweetIntent.type = "text/plain"
//
//        // インテントの一覧取得
//        val resolvedInfoList =
//            packageManager.queryIntentActivities(tweetIntent, PackageManager.MATCH_DEFAULT_ONLY)
//
//        if (resolvedInfoList.isEmpty()) {
//            Toast.makeText(this, "シェア可能なアプリが見つかりませんでした", Toast.LENGTH_SHORT).show()
//            return
//        }
//
//        // 必要なものを抽出
//        var intents: ArrayList<LabeledIntent> = ArrayList()
//        for (resolveInfo in resolvedInfoList) {
//            Log.d(TAG, "packageName: ${resolveInfo.activityInfo.packageName}")
//            intents.add(createShareLabeledIntent(resolveInfo))
//        }
//
//        val chooser = Intent.createChooser(intents.removeAt(intents.size - 1), "share")
//        chooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, intents.toArray())
//        startActivity(chooser)
//    }
//
//    private fun createLabeledIntent(intent: Intent, resolveInfo: ResolveInfo): LabeledIntent {
//        return LabeledIntent(intent, resolveInfo.activityInfo.packageName, resolveInfo.loadLabel(packageManager), resolveInfo.icon)
//    }
//
//    private fun createShareIntent(activityInfo: ActivityInfo): Intent {
//        val intent = Intent()
//
//        intent.setClassName(activityInfo.packageName, activityInfo.name)
//
//        intent.action = Intent.ACTION_SEND
//
//        return intent
//    }
//
//    private fun createShareLabeledIntent(resolveInfo: ResolveInfo): LabeledIntent {
//        val intent = createShareIntent(resolveInfo.activityInfo)
//        return createLabeledIntent(intent, resolveInfo)
//    }
//
//    private fun callShare02() {
//    }

}