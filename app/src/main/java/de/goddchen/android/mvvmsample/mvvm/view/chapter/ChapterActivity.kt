package de.goddchen.android.mvvmsample.mvvm.view.chapter

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import de.goddchen.android.mvvmsample.R
import de.goddchen.android.mvvmsample.databinding.ActivityChapterBinding
import de.goddchen.android.mvvmsample.mvvm.model.Chapter
import de.goddchen.android.mvvmsample.mvvm.viewmodel.ChapterViewModel
import de.goddchen.android.mvvmsample.mvvm.viewmodel.ChapterViewModelFactory

class ChapterActivity : AppCompatActivity() {

    companion object {
        val EXTRA_CHAPTER: String = "chapter"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val extraChapter = intent.getSerializableExtra(EXTRA_CHAPTER) as Chapter
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = extraChapter.name
        val binding: ActivityChapterBinding? =
                DataBindingUtil.setContentView(this, R.layout.activity_chapter)
        val dataBinding = ChapterDataBindingModel()
        binding?.model = dataBinding
        with(ViewModelProviders.of(this, ChapterViewModelFactory(baseContext, extraChapter))
                .get(ChapterViewModel::class.java)) {
            //Map ViewModel LiveData to DataBinding Observables
            addressText.observe(this@ChapterActivity,
                    Observer { dataBinding.addressText.set(it) })
            organizerCountText.observe(this@ChapterActivity,
                    Observer { dataBinding.organizerCountText.set(it) })
            dataBinding.chapter = extraChapter
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}