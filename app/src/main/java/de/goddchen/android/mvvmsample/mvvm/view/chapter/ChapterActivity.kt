package de.goddchen.android.mvvmsample.mvvm.view.chapter

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import de.goddchen.android.mvvmsample.R
import de.goddchen.android.mvvmsample.databinding.ActivityChapterBinding
import de.goddchen.android.mvvmsample.mvvm.model.Chapter
import de.goddchen.android.mvvmsample.mvvm.viewmodel.ChapterViewModel

class ChapterActivity : AppCompatActivity() {

    companion object {
        val EXTRA_CHAPTER: String = "chapter"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityChapterBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_chapter)
        val extraChapter = intent.getSerializableExtra(EXTRA_CHAPTER) as Chapter
        val dataBinding = ChapterDataBindingModel()
        binding.model = dataBinding
        with(ViewModelProviders.of(this).get(ChapterViewModel::class.java)) {
            //Map ViewModel LiveData to DataBinding Observables
            addressText.observe(this@ChapterActivity,
                    Observer { dataBinding.addressText.set(it) })
            organizerCountText.observe(this@ChapterActivity,
                    Observer { dataBinding.organizerCountText.set(it) })
            dataBinding.chapter = extraChapter
            init(extraChapter, ChapterFormatProviderAndroid(this@ChapterActivity.baseContext))
        }
    }
}