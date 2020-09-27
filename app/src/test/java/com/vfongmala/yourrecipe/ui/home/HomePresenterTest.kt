package com.vfongmala.yourrecipe.ui.home

import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.vfongmala.yourrecipe.core.SchedulersFactory
import com.vfongmala.yourrecipe.domain_contract.SearchInteractor
import com.vfongmala.yourrecipe.domain_contract.entity.RecipeInfo
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
class HomePresenterTest {

    @Mock
    lateinit var view: HomeView

    @Mock
    lateinit var searchInteractor: SearchInteractor

    @Mock
    lateinit var recipeInfoMapper: Mapper<RecipeInfo, RecipePreview>

    @Mock
    lateinit var schedulersFactory: SchedulersFactory

    private lateinit var presenter: HomePresenter

    private val testScheduler = TestScheduler()

    @Before
    fun setup() {
        presenter = HomePresenter(
            view, searchInteractor, recipeInfoMapper, schedulersFactory
        )
        whenever(schedulersFactory.io()).thenReturn(testScheduler)
        whenever(schedulersFactory.main()).thenReturn(testScheduler)
    }

    @Test
    fun `test init`() {
        // Arrange
        val data = Mocks.recipeInfo
        whenever(searchInteractor.randomRecipes()).thenReturn(Observable.just(listOf(data)))
        val mappedData = Mocks.recipePreview
        whenever(recipeInfoMapper.map(data)).thenReturn(mappedData)

        // Act
        presenter.init()
        testScheduler.triggerActions()

        // Assert
        verify(searchInteractor).randomRecipes()
        verify(recipeInfoMapper).map(data)
        verify(view).updateData(listOf(mappedData))
    }

    @Test
    fun `test init with error`() {
        // Arrange
        whenever(searchInteractor.randomRecipes()).thenReturn(Observable.error(NullPointerException()))

        // Act
        presenter.init()
        testScheduler.triggerActions()

        // Assert
        verify(searchInteractor).randomRecipes()
        verify(view).showNoResult("Something went wrong, please try again.")
    }

    @Test
    fun `test selectRecipe`() {
        // Arrange
        val data = Mocks.recipePreview

        // Act
        presenter.selectRecipe(data)

        // Assert
        verify(view).openRecipe(data)
    }
}