package com.example.kotlinlivedata

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel:ViewModel() {

    private lateinit var timer: CountDownTimer

    private val _seconds = MutableLiveData<Int>()

    private val _finished = MutableLiveData<Boolean>()

    var timerValue = MutableLiveData<Long>()

    fun seconds(): LiveData<Int> = _seconds
    fun finished(): LiveData<Boolean> = _finished

    fun startTimer() {
        timer = object: CountDownTimer(timerValue.value!!.toLong(),1000){
            override fun onTick(millisUntilFinished: Long) {
                val timeLeft = millisUntilFinished/1000
                _seconds.value = timeLeft.toInt()
            }

            override fun onFinish() {
                _finished.value = true

            }

        }.start()

    }

    fun stopTimer() {
        timer.cancel()
    }

    fun clearTimer() {
        timerValue.value = 0.toLong()
    }
}