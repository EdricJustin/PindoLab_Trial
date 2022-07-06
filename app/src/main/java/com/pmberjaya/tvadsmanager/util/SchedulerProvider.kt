package com.pmberjaya.tvadsmanager.util

import androidx.annotation.NonNull
import androidx.annotation.Nullable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Provides different types of schedulers.
 */
class SchedulerProvider// Prevent direct instantiation.
private constructor() : ISchedulerProvider {

    @NonNull
    override fun computation(): Scheduler {
        return Schedulers.computation()
    }

    @NonNull
    override fun ui(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

    companion object {

        @Nullable
        private var INSTANCE: SchedulerProvider? = null

        val instance: SchedulerProvider
            get() {
                if (INSTANCE == null) {
                    INSTANCE = SchedulerProvider()
                }
                return INSTANCE as SchedulerProvider
            }
    }
}