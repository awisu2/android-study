package com.example.layouts

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.layouts.databinding.ActivityFragmentBinding
import com.example.layouts.databinding.FragmentFragmentBinding

class FragmentFragment(private val text: String = "") : Fragment() {
    private var _binding: FragmentFragmentBinding? = null
    private val binding get() = _binding!!

    companion object {
        private const val TAG = "FragmentFragment"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate")

        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreateView")

        // Inflate the layout for this fragment
        // INFO: テンプレートのデフォルトview生成
//        inflater.inflate(R.layout.fragment_fragment, container, false)

        // Fragmentの場合、onCreateViewでbinding設定
        _binding = FragmentFragmentBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.text = if (text == "") "fragment" else text
        return view
    }
}