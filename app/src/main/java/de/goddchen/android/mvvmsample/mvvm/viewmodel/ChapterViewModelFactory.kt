package de.goddchen.android.mvvmsample.mvvm.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import de.goddchen.android.mvvmsample.mvvm.model.Chapter
import de.goddchen.android.mvvmsample.mvvm.view.chapter.ChapterFormatProviderAndroid

class ChapterViewModelFactory(private val context: Context, private val chapter: Chapter) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ChapterViewModel(ChapterFormatProviderAndroid(context), chapter) as T
    }
}