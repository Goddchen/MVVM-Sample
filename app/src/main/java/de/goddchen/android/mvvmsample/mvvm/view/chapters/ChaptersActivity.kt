package de.goddchen.android.mvvmsample.mvvm.view.chapters

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.databinding.Observable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import de.goddchen.android.mvvmsample.BR
import de.goddchen.android.mvvmsample.R
import de.goddchen.android.mvvmsample.caching.AndroidCacheProvider
import de.goddchen.android.mvvmsample.data.chapters.ChaptersDataServiceApi
import de.goddchen.android.mvvmsample.databinding.ActivityChaptersBinding
import de.goddchen.android.mvvmsample.mvvm.viewmodel.ChaptersViewModel
import me.tatarka.bindingcollectionadapter2.OnItemBind

class ChaptersActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(ViewModelProviders.of(this).get(ChaptersViewModel::class.java)) {
            val binding: ActivityChaptersBinding =
                    DataBindingUtil.setContentView(this@ChaptersActivity,
                            R.layout.activity_chapters)
            val bindingModel = ChaptersDataBindingModel(OnItemBind { itemBinding, _, _ ->
                itemBinding?.set(BR.chapter, R.layout.item_chapter)
                itemBinding?.bindExtra(BR.model, this)
            })
            binding.model = bindingModel
            //Map LiveData to DataBinding Observables
            filteredChapters.observe(this@ChaptersActivity, Observer {
                bindingModel.chapters.clear()
                bindingModel.chapters.addAll(it ?: emptyList())
            })
            isLoading.observe(this@ChaptersActivity, Observer {
                bindingModel.isLoading.set(it ?: false)
            })
            bindingModel.filter.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(p0: Observable?, p1: Int) {
                    filter = bindingModel.filter.get()
                }
            })
            //Init
            loadChapters(ChaptersDataServiceApi(), AndroidCacheProvider())
        }
    }
}