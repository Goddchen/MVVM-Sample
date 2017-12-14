package de.goddchen.android.mvvmsample

import android.arch.core.executor.testing.InstantTaskExecutorRule
import de.goddchen.android.mvvmsample.model.Chapter
import de.goddchen.android.mvvmsample.model.Country
import de.goddchen.android.mvvmsample.model.Geo
import de.goddchen.android.mvvmsample.view.chapter.ChapterFormatProvider
import de.goddchen.android.mvvmsample.view.chapter.ChapterViewModel
import org.assertj.core.api.Assertions.assertThat
import org.junit.Rule
import org.junit.Test

class ChapterViewModelTest {

    @Rule
    fun rule(): InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun formats() {
        val viewModel = ChapterViewModel()
        val chapter = Chapter(
                null,
                null,
                null,
                null,
                null,
                null,
                Country(null, "Country"),
                null,
                "City",
                "Name",
                null,
                listOf("Organizer1", "Organizer2"),
                Geo(1.0, 2.0)
        )
        val chapterFormatter = object : ChapterFormatProvider {
            override val formatOrganizerCount: String
                get() = "%d orgas"
            override val formatAddress: String
                get() = "%s, %s"
        }
        viewModel.init(chapter, chapterFormatter)
        assertThat(viewModel.addressText.value).isEqualTo("City, Country")
        assertThat(viewModel.organizerCountText.value).isEqualTo("2 orgas")
    }

}