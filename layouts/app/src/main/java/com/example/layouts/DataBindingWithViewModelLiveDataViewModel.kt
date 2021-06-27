package com.example.layouts

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DataBindingWithViewModelLiveDataViewModel: ViewModel() {

    val text: MutableLiveData<String> = MutableLiveData("@={} binding. please change me.")

    fun changeText() {
        Log.d("ViewModel", "changeText()")
        this.text.value = if (this.text.value != "a") "a" else "b"
    }
}