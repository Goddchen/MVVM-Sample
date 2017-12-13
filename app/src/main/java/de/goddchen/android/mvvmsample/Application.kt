package de.goddchen.android.mvvmsample

import com.jakewharton.threetenabp.AndroidThreeTen
import timber.log.Timber

class Application : android.app.Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        AndroidThreeTen.init(this)
    }
}
