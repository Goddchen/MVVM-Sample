package de.goddchen.android.mvvmsample.mvvm.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import de.goddchen.android.mvvmsample.caching.AndroidCacheProvider
import de.goddchen.android.mvvmsample.data.chapters.ChaptersDataServiceApi

class ChaptersViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ChaptersViewModel(ChaptersDataServiceApi(), AndroidCacheProvider()) as T
    }
}