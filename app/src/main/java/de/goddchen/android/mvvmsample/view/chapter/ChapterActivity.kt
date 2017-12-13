package de.goddchen.android.mvvmsample.view.chapter

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import de.goddchen.android.mvvmsample.R
import de.goddchen.android.mvvmsample.databinding.ActivityChapterBinding
import de.goddchen.android.mvvmsample.model.Chapter

class ChapterActivity : AppCompatActivity() {

    companion object {
        val EXTRA_CHAPTER: String = "chapter"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityChapterBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_chapter)
        with(ViewModelProviders.of(this).get(ChapterViewModel::class.java)) {
            chapter = intent.getSerializableExtra(EXTRA_CHAPTER) as Chapter
            binding.model = this
        }
    }
}