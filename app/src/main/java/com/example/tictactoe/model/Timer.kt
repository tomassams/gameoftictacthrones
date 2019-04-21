package com.example.tictactoe.model

import android.os.SystemClock
import android.widget.Chronometer

/**
 * represents a timer / chronometer with some basic additional functionality
 *
 * a simple chronometer View will in such cases calculate and count the time it was stopped, once started.
 * we don't want this, so we keep track of the time it has been stopped and deduct it.
 */
class Timer (cm: Chronometer){

    private val timer: Chronometer = cm
    private var stoppedTime: Long = 0

    fun start() {
        timer.base = SystemClock.elapsedRealtime() + stoppedTime
        timer.start()
    }

    fun stop() {
        stoppedTime = timer.base - SystemClock.elapsedRealtime()
        timer.stop()
    }

    fun reset() {
        timer.base = SystemClock.elapsedRealtime()
        stoppedTime = 0
    }
}