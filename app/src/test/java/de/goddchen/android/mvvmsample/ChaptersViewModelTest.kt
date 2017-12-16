package de.goddchen.android.mvvmsample

import android.app.Application
import android.arch.core.executor.testing.InstantTaskExecutorRule
import de.goddchen.android.mvvmsample.caching.CacheProvider
import de.goddchen.android.mvvmsample.data.chapters.ChaptersDataService
import de.goddchen.android.mvvmsample.mvvm.model.Chapter
import de.goddchen.android.mvvmsample.mvvm.viewmodel.ChaptersViewModel
import io.reactivex.Flowable
import io.reactivex.Single
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class ChaptersViewModelTest {

    private val cacheProvider = mock(CacheProvider::class.java)!!

    @Rule
    fun rule(): InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        `when`(cacheProvider.getChapters()).thenReturn(Flowable.empty())
    }

    @Test
    fun loadError() {
        val viewModel = ChaptersViewModel(mock(Application::class.java))
        val dataService: ChaptersDataService = mock(ChaptersDataService::class.java)
        `when`(dataService.getChapters()).thenReturn(Single.error(Exception()))
        viewModel.loadChapters(dataService, cacheProvider)
        assertThat(viewModel.isLoading.value).isEqualTo(false)
        assertThat(viewModel.filteredChapters.value).isNull()
    }

    @Test
    fun loadSuccess() {
        val viewModel = ChaptersViewModel(mock(Application::class.java))
        val dataService: ChaptersDataService = mock(ChaptersDataService::class.java)
        `when`(dataService.getChapters()).thenReturn(Single.just(listOf(
                Chapter("", null, null, null, null, null, null, null, null, "GDG Bodensee", null, null, null),
                Chapter("", null, null, null, null, null, null, null, null, "GDG Zürich", null, null, null)
        )))
        viewModel.loadChapters(dataService, cacheProvider)
        assertThat(viewModel.filteredChapters.value).hasSize(2)
    }

    @Test
    fun filter() {
        val viewModel = ChaptersViewModel(mock(Application::class.java))
        val dataService: ChaptersDataService = mock(ChaptersDataService::class.java)
        `when`(dataService.getChapters()).thenReturn(Single.just(listOf(
                Chapter("", null, null, null, null, null, null, null, null, "GDG Bodensee", null, null, null),
                Chapter("", null, null, null, null, null, null, null, null, "GDG Zürich", null, null, null)
        )))
        viewModel.loadChapters(dataService, cacheProvider)
        viewModel.filter = "Bod"
        assertThat(viewModel.filteredChapters.value).hasSize(1)
        assertThat(viewModel.filteredChapters.value?.get(0)?.name).isEqualTo("GDG Bodensee")
    }
}