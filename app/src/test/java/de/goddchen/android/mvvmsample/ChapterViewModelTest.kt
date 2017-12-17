package de.goddchen.android.mvvmsample

import android.arch.core.executor.testing.InstantTaskExecutorRule
import de.goddchen.android.mvvmsample.mvvm.model.Chapter
import de.goddchen.android.mvvmsample.mvvm.model.Country
import de.goddchen.android.mvvmsample.mvvm.model.Geo
import de.goddchen.android.mvvmsample.mvvm.view.chapter.ChapterFormatProvider
import de.goddchen.android.mvvmsample.mvvm.viewmodel.ChapterViewModel
import org.assertj.core.api.Assertions.assertThat
import org.junit.Rule
import org.junit.Test

class ChapterViewModelTest {

    @Rule
    fun rule(): InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun formats() {
        val chapterFormatter = object : ChapterFormatProvider {
            override val formatOrganizerCount: String
                get() = "%d orgas"
            override val formatAddress: String
                get() = "%s, %s"
        }
        val chapter = Chapter(
                "",
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
        val viewModel = ChapterViewModel(chapterFormatter, chapter)
        assertThat(viewModel.addressText.value).isEqualTo("City, Country")
        assertThat(viewModel.organizerCountText.value).isEqualTo("2 orgas")
    }

}