package de.goddchen.android.mvvmsample.mvvm.view.chapter

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import de.goddchen.android.mvvmsample.R
import de.goddchen.android.mvvmsample.databinding.ActivityChapterBinding
import de.goddchen.android.mvvmsample.mvvm.model.Chapter
import de.goddchen.android.mvvmsample.mvvm.viewmodel.ChapterViewModel
import de.goddchen.android.mvvmsample.mvvm.viewmodel.ChapterViewModelFactory
import java.util.*

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
        binding?.setLifecycleOwner(this)
        val viewModel = ViewModelProviders.of(this,
                ChapterViewModelFactory(baseContext, extraChapter))
                .get(ChapterViewModel::class.java)
        binding?.model = viewModel
        viewModel.addressClick.observe(this, Observer {
            startActivity(Intent(Intent.ACTION_VIEW,
                    Uri.parse(String.format(Locale.US, "geo:%.6f,%.6f?q=%.6f,%.6f",
                            it?.geo?.lat, it?.geo?.lng,
                            it?.geo?.lat, it?.geo?.lng))))
        })
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