package de.goddchen.android.mvvmsample.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import de.goddchen.android.mvvmsample.api.ApiGenerator
import timber.log.Timber

class TestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ApiGenerator.generateApi().getChapters()
                .subscribe(
                        { Timber.d("Found %d chapters", it.items.size) },
                        Timber::e)
    }
}