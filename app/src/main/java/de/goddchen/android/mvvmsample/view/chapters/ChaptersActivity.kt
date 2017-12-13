package de.goddchen.android.mvvmsample.view.chapters

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import de.goddchen.android.mvvmsample.R
import de.goddchen.android.mvvmsample.databinding.ActivityChaptersBinding
import de.goddchen.android.mvvmsample.view.chapter.ChapterActivity

class ChaptersActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityChaptersBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_chapters)
        with(ViewModelProviders.of(this).get(ChaptersViewModel::class.java)) {
            binding.model = this
            loadChapters()
            clickedChapter.observe(this@ChaptersActivity, Observer {
                startActivity(Intent(this@ChaptersActivity, ChapterActivity::class.java)
                        .putExtra(ChapterActivity.EXTRA_CHAPTER, it))
            })
        }
    }
}