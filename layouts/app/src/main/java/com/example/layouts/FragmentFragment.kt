package com.example.layouts

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.layouts.databinding.ActivityFragmentBinding
import com.example.layouts.databinding.FragmentFragmentBinding

class FragmentFragment(private var data: FragmentData? = null) : Fragment() {
    private lateinit var binding: FragmentFragmentBinding

    companion object {
        private const val TAG = "FragmentFragment"
    }

    // 不要？少なくともviewをいじるのは危険すぎる
//    override fun onCreate(savedInstanceState: Bundle?) {
//        Log.d(TAG, "onCreate")
//        super.onCreate(savedInstanceState)
//    }

    // ここでは最低限のinfrateのみ
    // viewなどの操作は可能だが、onViewCreatedなどで操作推奨とのこと
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreateView")

        // Inflate the layout for this fragment
        // INFO: テンプレートのデフォルトview生成
//        inflater.inflate(R.layout.fragment_fragment, container, false)

        // Fragmentの場合、onCreateViewでbinding設定
        binding = FragmentFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(TAG, "onViewCreated")
        super.onViewCreated(view, savedInstanceState)

        if (data == null) {
            data = FragmentData("fragment")
        }

        if (data!!.name == "") {
            data!!.name = "fragment"
        }
        binding.text = data!!.name

        // 画面回転時、dataは保たれるのかテスト
        // 残らない。しっかりと何らかの管理をしておく必要あり
        binding.fragmentButton.setOnClickListener {
            Log.d(TAG, "name: ${data?.name}")
            // fragment で Toastするときは activity をセット
            Toast.makeText(activity, data!!.name, Toast.LENGTH_SHORT).show()
        }
    }
}

data class FragmentData(
    var name: String
)