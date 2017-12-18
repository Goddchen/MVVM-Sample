package de.goddchen.android.mvvmsample.mvvm.view.chapters

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.databinding.Observable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import de.goddchen.android.mvvmsample.BR
import de.goddchen.android.mvvmsample.R
import de.goddchen.android.mvvmsample.databinding.ActivityChaptersBinding
import de.goddchen.android.mvvmsample.mvvm.view.chapter.ChapterActivity
import de.goddchen.android.mvvmsample.mvvm.viewmodel.ChaptersViewModel
import de.goddchen.android.mvvmsample.mvvm.viewmodel.ChaptersViewModelFactory
import me.tatarka.bindingcollectionadapter2.OnItemBind

class ChaptersActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = getString(R.string.app_name)
        val binding: ActivityChaptersBinding =
                DataBindingUtil.setContentView(this@ChaptersActivity,
                        R.layout.activity_chapters)
        binding.chapters.layoutManager = LinearLayoutManager(baseContext)
        binding.chapters.addItemDecoration(
                DividerItemDecoration(baseContext, LinearLayoutManager.VERTICAL))
        with(ViewModelProviders.of(this, ChaptersViewModelFactory())
                .get(ChaptersViewModel::class.java)) {
            val bindingModel = ChaptersDataBindingModel(OnItemBind { itemBinding, _, _ ->
                itemBinding?.set(BR.chapter, R.layout.item_chapter)
                itemBinding?.bindExtra(BR.model, this)
            })
            //Load initial UI state from view model
            bindingModel.filter.set(filter)
            bindingModel.chapters.addAll(filteredChapters?.value ?: emptyList())
            binding.model = bindingModel
            //Map LiveData to DataBinding Observables
            filteredChapters?.observe(this@ChaptersActivity, Observer {
                //bindingModel.chapters.clear()
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
            chapterClick.observe(this@ChaptersActivity, Observer {
                startActivity(Intent(baseContext, ChapterActivity::class.java)
                        .putExtra(ChapterActivity.EXTRA_CHAPTER, it))
            })
        }

    }
}