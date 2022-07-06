package com.pmberjaya.tvadsmanager.util

import androidx.annotation.NonNull
import io.reactivex.Scheduler


/**
 * Allow providing different types of [Scheduler]s.
 */
interface ISchedulerProvider {

    @NonNull
    fun computation(): Scheduler

    @NonNull
    fun ui(): Scheduler
}