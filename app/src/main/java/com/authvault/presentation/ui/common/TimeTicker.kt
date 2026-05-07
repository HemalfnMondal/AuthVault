package com.authvault.presentation.ui.common

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

fun tickerFlow(periodMillis: Long = 1000L): Flow<Long> = flow {
    while (true) {
        emit(System.currentTimeMillis())
        delay(periodMillis)
    }
}
