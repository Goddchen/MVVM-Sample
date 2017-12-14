package de.goddchen.android.mvvmsample

import android.arch.core.executor.testing.InstantTaskExecutorRule
import de.goddchen.android.mvvmsample.data.chapters.ChaptersDataService
import de.goddchen.android.mvvmsample.model.Chapter
import de.goddchen.android.mvvmsample.view.chapters.ChaptersViewModel
import io.reactivex.Single
import org.assertj.core.api.Assertions.assertThat
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class ChaptersViewModelTest {

    @Rule
    fun rule(): InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun loadError() {
        val viewModel = ChaptersViewModel()
        val dataService: ChaptersDataService = mock(ChaptersDataService::class.java)
        `when`(dataService.getChapters()).thenReturn(Single.error(Exception()))
        viewModel.loadChapters(dataService)
        assertThat(viewModel.isLoading.value).isEqualTo(false)
        assertThat(viewModel.filteredChapters.value).isNull()
    }

    @Test
    fun loadSuccess() {
        val viewModel = ChaptersViewModel()
        val dataService: ChaptersDataService = mock(ChaptersDataService::class.java)
        `when`(dataService.getChapters()).thenReturn(Single.just(listOf(
                Chapter(null, null, null, null, null, null, null, null, null, "GDG Bodensee", null, null, null),
                Chapter(null, null, null, null, null, null, null, null, null, "GDG Zürich", null, null, null)
        )))
        viewModel.loadChapters(dataService)
        assertThat(viewModel.filteredChapters.value).hasSize(2)
    }

    @Test
    fun filter() {
        val viewModel = ChaptersViewModel()
        val dataService: ChaptersDataService = mock(ChaptersDataService::class.java)
        `when`(dataService.getChapters()).thenReturn(Single.just(listOf(
                Chapter(null, null, null, null, null, null, null, null, null, "GDG Bodensee", null, null, null),
                Chapter(null, null, null, null, null, null, null, null, null, "GDG Zürich", null, null, null)
        )))
        viewModel.loadChapters(dataService)
        viewModel.filter = "Bod"
        assertThat(viewModel.filteredChapters.value).hasSize(1)
        assertThat(viewModel.filteredChapters.value?.get(0)?.name).isEqualTo("GDG Bodensee")
    }
}