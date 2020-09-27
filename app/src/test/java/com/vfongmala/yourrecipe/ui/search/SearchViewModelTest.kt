package com.vfongmala.yourrecipe.ui.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.whenever
import com.vfongmala.yourrecipe.core.SchedulersFactory
import com.vfongmala.yourrecipe.domain_contract.SearchInteractor
import com.vfongmala.yourrecipe.domain_contract.entity.Recipe
import com.vfongmala.yourrecipe.domain_contract.entity.SearchResult
import com.vfongmala.yourrecipe.domain_contract.mapper.Mapper
import com.vfongmala.yourrecipe.mockdata.Mocks
import com.vfongmala.yourrecipe.ui.entity.RecipePreview
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.TestScheduler
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.lang.Exception

@RunWith(MockitoJUnitRunner::class)
class SearchViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var searchInteractor: SearchInteractor

    @Mock
    lateinit var mapper: Mapper<Recipe, RecipePreview>

    @Mock
    lateinit var schedulersFactory: SchedulersFactory

    private val testScheduler = TestScheduler()
    lateinit var viewModel: SearchViewModel

    @Before
    fun setup() {
        whenever(schedulersFactory.io()).thenReturn(testScheduler)
        whenever(schedulersFactory.main()).thenReturn(testScheduler)

        viewModel = SearchViewModel(searchInteractor, schedulersFactory, mapper)
    }

    @Test
    fun `test search`() {
        // Arrange
        whenever(searchInteractor.search("name")).thenReturn(
            Observable.just(SearchResult(listOf(Mocks.recipe), 1)))
        whenever(mapper.map(Mocks.recipe)).thenReturn(Mocks.recipePreview)

        // Act
        viewModel.search("name")
        testScheduler.triggerActions()

        // Assert
        assertThat(viewModel.showLoading.value, `is`(false))
        assertThat(viewModel.showError.value, `is`(false))
        assertThat(viewModel.showNoResult.value, `is`(false))
        assertThat(viewModel.list.value, `is`(listOf(Mocks.recipePreview)))
    }

    @Test
    fun `test search with no result`() {
        // Arrange
        whenever(searchInteractor.search("name")).thenReturn(
            Observable.just(SearchResult(listOf(), 0)))

        // Act
        viewModel.search("name")
        testScheduler.triggerActions()

        // Assert
        assertThat(viewModel.showLoading.value, `is`(false))
        assertThat(viewModel.showError.value, `is`(false))
        assertThat(viewModel.showNoResult.value, `is`(true))
        assertNull(viewModel.list.value)
    }

    @Test
    fun `test search with error`() {
        // Arrange
        whenever(searchInteractor.search("name")).thenReturn(Observable.error(Exception()))

        // Act
        viewModel.search("name")
        testScheduler.triggerActions()

        // Assert
        assertThat(viewModel.showLoading.value, `is`(false))
        assertThat(viewModel.showError.value, `is`(true))
        assertThat(viewModel.showNoResult.value, `is`(false))
        assertNull(viewModel.list.value)
    }
}