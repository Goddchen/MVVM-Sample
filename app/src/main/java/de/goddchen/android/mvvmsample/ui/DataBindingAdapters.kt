package de.goddchen.android.mvvmsample.ui

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView

class DataBindingAdapters {

    companion object {
        @JvmStatic
        @BindingAdapter("itemDecoration")
        fun bindItemDecoration(recyclerView: RecyclerView, itemDecoration: RecyclerView.ItemDecoration) {
            recyclerView.addItemDecoration(itemDecoration)
        }
    }

}