package com.example.layouts

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.layouts.databinding.ActivityFragmentBinding
import com.example.layouts.databinding.ActivityGridBinding

// INFO:
// fragmentの追加、導入サンプル
//
// layout.xmlで追加する方法
//
// - <fragment>タグを追加する
// - idをセットしておかないと、エラーになる
// - 注意事項、通常のviewのように必須attributeはセットする
//
// sample:
// <fragment
//  android:id="@+id/fragment_fragment"
//  android:name="com.example.layouts.FragmentFragment" />
//
class FragmentActivity: AppCompatActivity() {
    private lateinit var binding: ActivityFragmentBinding

    companion object {
        private const val TAG = "FragmentActivity"

        fun createIntent(context: Context): Intent {
            return Intent(context, FragmentActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFragmentBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        if (savedInstanceState == null) {
            addFragment()
        }
    }

    // codeでの fragment 追加
    private fun addFragment() {
        // transactionを開始
        val transaction = supportFragmentManager.beginTransaction()

        // fragmentの生成と追加
        val fragment = FragmentFragment(FragmentData("FragmentActivity Create"))
        transaction.add(binding.fragmentLiner.id, fragment)

        // 既存のfragmentを入れ替えたい場合はreplase
//        transaction.replace(fragment.id, anyFragment)

        // fragmentの生成と追加
        transaction.commit()
    }
}

