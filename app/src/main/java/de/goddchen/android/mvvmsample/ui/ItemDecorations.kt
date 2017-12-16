package de.goddchen.android.mvvmsample.ui

import android.content.Context
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager

class ItemDecorations {

    companion object {
        @JvmStatic
        fun listDivider(context: Context) =
                DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
    }

}