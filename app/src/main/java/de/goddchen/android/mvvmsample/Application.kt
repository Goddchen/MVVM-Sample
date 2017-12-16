package de.goddchen.android.mvvmsample

import android.arch.persistence.room.Room
import com.jakewharton.threetenabp.AndroidThreeTen
import de.goddchen.android.mvvmsample.persistence.Database
import timber.log.Timber

class Application : android.app.Application() {

    companion object {
        var DATABASE: Database? = null
    }

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        AndroidThreeTen.init(this)
        Application.DATABASE =
                Room.databaseBuilder(this, Database::class.java, "database").build()
    }
}
