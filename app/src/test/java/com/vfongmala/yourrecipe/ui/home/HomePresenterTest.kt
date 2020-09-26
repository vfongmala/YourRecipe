package com.vfongmala.yourrecipe.ui.home

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.vfongmala.yourrecipe.core.SchedulersFactory
import com.vfongmala.yourrecipe.domain_contract.SearchInteractor
import com.vfongmala.yourrecipe.domain_contract.entity.RecipeInfo
import com.vfongmala.yourrecipe.domain_contract.mapper.Mapper
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
    lateinit var lifecycleOwner: LifecycleOwner

    @Mock
    lateinit var viewModelProvider: ViewModelProvider

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
        val data = RecipeInfo(
            "credit",
            listOf(
                RecipeInfo.ExtendedIngredient("ingredient1", 1.1234, "gram")
            ),
            1234,
            "recipe1",
            10,
            1,
            "url1",
            "summary",
            listOf(
                RecipeInfo.Instruction(
                    "step1", listOf(
                        RecipeInfo.InstructionStep(1, "step1")
                    )
                )
            )
        )
        whenever(searchInteractor.randomRecipes()).thenReturn(Observable.just(listOf(data)))
        val mappedData = RecipePreview(1234, "recipe1", "url1")
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
        val data = RecipePreview(1, "recipe", "url")

        // Act
        presenter.selectRecipe(data)

        // Assert
        verify(view).openRecipe(data)
    }
}