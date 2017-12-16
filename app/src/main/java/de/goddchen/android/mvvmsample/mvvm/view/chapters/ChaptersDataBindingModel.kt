package de.goddchen.android.mvvmsample.mvvm.view.chapters

import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.databinding.ObservableList
import de.goddchen.android.mvvmsample.mvvm.model.Chapter
import me.tatarka.bindingcollectionadapter2.OnItemBind

class ChaptersDataBindingModel(val itemBinding: OnItemBind<Chapter>) {

    val chapters: ObservableList<Chapter> = ObservableArrayList()

    val isLoading: ObservableBoolean = ObservableBoolean(false)

    val filter: ObservableField<String> = ObservableField()

}