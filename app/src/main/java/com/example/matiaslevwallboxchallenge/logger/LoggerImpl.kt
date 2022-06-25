package com.example.matiaslevwallboxchallenge.logger

import android.util.Log
import com.example.domain.logger.Logger

class LoggerImpl : Logger {
    override fun setLog(screen: String, action: String) {
        Log.d("challengeLogger: $screen", action)
    }
}