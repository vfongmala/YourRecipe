package com.vfongmala.yourrecipe.ui.recipe

import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.vfongmala.yourrecipe.R
import com.vfongmala.yourrecipe.core.SchedulersFactory
import com.vfongmala.yourrecipe.domain_contract.SearchInteractor
import com.vfongmala.yourrecipe.domain_contract.entity.RecipeInfo
import com.vfongmala.yourrecipe.domain_contract.mapper.Mapper
import com.vfongmala.yourrecipe.mockdata.Mocks
import com.vfongmala.yourrecipe.ui.entity.RecipeDetail
import com.vfongmala.yourrecipe.ui.entity.SectionSubtitle
import com.vfongmala.yourrecipe.ui.entity.SectionTitle
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.lang.Exception

@RunWith(MockitoJUnitRunner::class)
class RecipePresenterTest {
    @Mock
    lateinit var view: RecipeView
    @Mock
    lateinit var searchInteractor: SearchInteractor
    @Mock
    lateinit var schedulersFactory: SchedulersFactory
    @Mock
    lateinit var mapper: Mapper<RecipeInfo, RecipeDetail>

    private val testScheduler = TestScheduler()

    lateinit var presenter: RecipePresenter

    @Before
    fun setup() {
        presenter = RecipePresenter(view, searchInteractor, schedulersFactory, mapper)
        whenever(schedulersFactory.main()).thenReturn(testScheduler)
        whenever(schedulersFactory.io()).thenReturn(testScheduler)
    }

    @Test
    fun `test loadRecipe`() {
        // Arrange
        whenever(searchInteractor.recipeInfo(1234)).thenReturn(Observable.just(Mocks.recipeInfo))
        whenever(mapper.map(Mocks.recipeInfo)).thenReturn(Mocks.recipeDetail)

        // Act
        presenter.loadRecipe(1234)
        testScheduler.triggerActions()

        // Assert
        verify(view).updateViewModel(listOf(
            Mocks.recipeSummary,
            SectionTitle(R.string.ingredients),
            Mocks.recipeIngredient,
            SectionTitle(R.string.instructions),
            SectionSubtitle("step1"),
            Mocks.recipeInstruction
        ))
    }

    @Test
    fun `test loadRecipe error`() {
        // Arrange
        whenever(searchInteractor.recipeInfo(1234)).thenReturn(Observable.error(Exception()))

        // Act
        presenter.loadRecipe(1234)
        testScheduler.triggerActions()

        // Assert
        verify(view).showError()
    }
}