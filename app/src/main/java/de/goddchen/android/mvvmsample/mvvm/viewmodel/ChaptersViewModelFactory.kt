package de.goddchen.android.mvvmsample.mvvm.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import de.goddchen.android.mvvmsample.caching.AndroidCacheProvider
import de.goddchen.android.mvvmsample.data.chapters.ChaptersDataServiceApi
import de.goddchen.android.mvvmsample.mvvm.view.Navigator

class ChaptersViewModelFactory(private val navigator: Navigator) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ChaptersViewModel(ChaptersDataServiceApi(), AndroidCacheProvider(), navigator) as T
    }
}