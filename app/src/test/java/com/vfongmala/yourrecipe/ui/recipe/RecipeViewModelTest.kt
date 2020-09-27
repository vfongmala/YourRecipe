package com.vfongmala.yourrecipe.ui.recipe

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.mock
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
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.robolectric.RobolectricTestRunner

@RunWith(MockitoJUnitRunner::class)
class RecipeViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var searchInteractor: SearchInteractor
    @Mock
    lateinit var schedulersFactory: SchedulersFactory
    @Mock
    lateinit var mapper: Mapper<RecipeInfo, RecipeDetail>
    @Mock
    lateinit var activity: RecipeActivity

    private val testScheduler = TestScheduler()

    lateinit var viewModel: RecipeViewModel

    @Before
    fun setup() {
        whenever(schedulersFactory.main()).thenReturn(testScheduler)
        whenever(schedulersFactory.io()).thenReturn(testScheduler)
        whenever(activity.applicationContext).thenReturn(mock())
        viewModel = RecipeViewModel(activity, searchInteractor, schedulersFactory, mapper)
    }

    @Test
    fun `test init`() {
        // Arrange
        whenever(searchInteractor.recipeInfo(1234)).thenReturn(Observable.just(Mocks.recipeInfo))
        whenever(mapper.map(Mocks.recipeInfo)).thenReturn(Mocks.recipeDetail)

        // Act
        viewModel.init(1234, "recipe", "url")
        testScheduler.triggerActions()

        // Assert
        assertThat(viewModel.data.value, `is`(listOf(
            Mocks.recipeSummary,
            SectionTitle(R.string.ingredients),
            Mocks.recipeIngredient,
            SectionTitle(R.string.instructions),
            SectionSubtitle("step1"),
            Mocks.recipeInstruction
        )))
        assertThat(viewModel.showError.value, `is`(false))
    }

    @Test
    fun `test init error`() {
        // Arrange
        whenever(searchInteractor.recipeInfo(1234)).thenReturn(Observable.error(Exception()))

        // Act
        viewModel.init(1234, "recipe", "url")
        testScheduler.triggerActions()

        // Assert
        assertNull(viewModel.data.value)
        assertThat(viewModel.showError.value, `is`(true))
    }
}