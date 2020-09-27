package com.vfongmala.yourrecipe.ui.search

import com.nhaarman.mockitokotlin2.verify
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
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SearchPresenterTest {

    @Mock
    lateinit var view: SearchView
    @Mock
    lateinit var searchInteractor: SearchInteractor
    @Mock
    lateinit var schedulersFactory: SchedulersFactory
    @Mock
    lateinit var mapper: Mapper<Recipe, RecipePreview>

    private val testScheduler = TestScheduler()

    lateinit var presenter: SearchPresenter

    @Before
    fun setup() {
        presenter = SearchPresenter(view, searchInteractor, schedulersFactory, mapper)
        whenever(schedulersFactory.io()).thenReturn(testScheduler)
        whenever(schedulersFactory.main()).thenReturn(testScheduler)
    }

    @Test
    fun `test search`() {
        // Arrange
        whenever(searchInteractor.search("name")).thenReturn(Observable.just(SearchResult(listOf(Mocks.recipe), 1)))
        whenever(mapper.map(Mocks.recipe)).thenReturn(Mocks.recipePreview)

        // Act
        presenter.search("name")
        testScheduler.triggerActions()

        // Assert
        view.updateModel(listOf(Mocks.recipePreview))
    }

    @Test
    fun `test search no result`() {
        // Arrange
        whenever(searchInteractor.search("name")).thenReturn(Observable.just(SearchResult(listOf(), 1)))

        // Act
        presenter.search("name")
        testScheduler.triggerActions()

        // Assert
        view.showNoResult()
    }

    @Test
    fun `test search error`() {
        // Arrange
        whenever(searchInteractor.search("name")).thenReturn(Observable.error(Exception()))

        // Act
        presenter.search("name")
        testScheduler.triggerActions()

        // Assert
        view.showError("Something went wrong, please try again.")
    }

    @Test
    fun `test select recipe`() {
        // Act
        presenter.selectRecipe(Mocks.recipePreview)

        // Assert
        verify(view).openRecipe(Mocks.recipePreview)
    }
}