package com.smithkeegan.billingplayground

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.support.annotation.MainThread
import java.util.concurrent.atomic.AtomicBoolean

/**
 * Created by WillowTree on 5/1/18.
 */
class SingleLiveEvent<T> : MutableLiveData<T>() {

    val pending : AtomicBoolean = AtomicBoolean(false)

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<T>){
        super.observe(owner, object : Observer<T> {
            override fun onChanged(t: T?) {
                if (pending.compareAndSet(true, false))
                    observer.onChanged(t)
            }
        })
    }

    override fun setValue(value: T) {
        pending.set(true)
        super.setValue(value)
    }
}