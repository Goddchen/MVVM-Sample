package de.goddchen.android.mvvmsample.mvvm.view.chapters

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import de.goddchen.android.mvvmsample.R
import de.goddchen.android.mvvmsample.databinding.ActivityChaptersBinding
import de.goddchen.android.mvvmsample.mvvm.view.chapter.ChapterActivity
import de.goddchen.android.mvvmsample.mvvm.viewmodel.ChaptersViewModel
import de.goddchen.android.mvvmsample.mvvm.viewmodel.ChaptersViewModelFactory

class ChaptersActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = getString(R.string.app_name)
        val binding: ActivityChaptersBinding? =
                DataBindingUtil.setContentView(this@ChaptersActivity,
                        R.layout.activity_chapters)
        binding?.setLifecycleOwner(this)
        binding?.chapters?.layoutManager = LinearLayoutManager(baseContext)
        binding?.chapters?.addItemDecoration(
                DividerItemDecoration(baseContext, LinearLayoutManager.VERTICAL))

        val viewModel = ViewModelProviders.of(this, ChaptersViewModelFactory())
                .get(ChaptersViewModel::class.java)
        binding?.model = viewModel
        viewModel.chapterClick.observe(this, Observer {
            startActivity(Intent(baseContext, ChapterActivity::class.java)
                    .putExtra(ChapterActivity.EXTRA_CHAPTER, it))
        })

    }
}