package com.example.layouts

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*
import kotlin.concurrent.schedule

// INFO:
// ViewModelを宣言するときは単にViewModel()を継承するだけでOK
//
class WithLiveDataViewModel: ViewModel() {
    val message: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    // カスタムLiveDataの挙動を見る
    val message2: CustomLiveData by lazy {
        CustomLiveData(123)
    }
}

// LiveDataのカスタマイズ
// 生成及び終了時に、listenerの停止
class CustomLiveData(private var count: Int = 0): MutableLiveData<String>() {
    private val timer: Timer = Timer()

    companion object {
        private const val TAG = "CustomLiveData"
    }

    override fun onActive() {
        super.onActive()
        Log.d(TAG, "onActive")

        // onActiveで timer をセット
        val self = this
        timer.schedule(0, 1000) {
            self.countUp()
        }
    }

    override fun onInactive() {
        super.onInactive()
        Log.d(TAG, "onInactive")

        // 不要になったら終了
        timer.cancel()
    }

    fun countUp () {
        this.count += 1
        // timer.schedule() は別スレッドで行われるため、それを考慮したpostValueでセット
        this.postValue(this.count.toString())
    }
}
