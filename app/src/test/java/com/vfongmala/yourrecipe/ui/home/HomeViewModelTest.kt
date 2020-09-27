package com.vfongmala.yourrecipe.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyZeroInteractions
import com.nhaarman.mockitokotlin2.whenever
import com.vfongmala.yourrecipe.core.SchedulersFactory
import com.vfongmala.yourrecipe.domain_contract.SearchInteractor
import com.vfongmala.yourrecipe.domain_contract.entity.RecipeInfo
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

@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var searchInteractor: SearchInteractor

    @Mock
    lateinit var recipeInfoMapper: Mapper<RecipeInfo, RecipePreview>

    @Mock
    lateinit var schedulersFactory: SchedulersFactory

    private val testScheduler = TestScheduler()
    lateinit var viewModel: HomeViewModel

    @Before
    fun setup() {
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
        viewModel = HomeViewModel(searchInteractor, recipeInfoMapper, schedulersFactory)

        testScheduler.triggerActions()

        // Asserts
        verify(searchInteractor).randomRecipes()
        verify(recipeInfoMapper).map(data)
        assertThat(viewModel.list.value, `is`(listOf(mappedData)))
        assertThat(viewModel.showLoading.value, `is`(false))
        assertThat(viewModel.showError.value, `is`(false))
    }

    @Test
    fun `test init with error`() {
        // Arrange
        whenever(searchInteractor.randomRecipes()).thenReturn(Observable.error(Exception()))

        // Act
        viewModel = HomeViewModel(searchInteractor, recipeInfoMapper, schedulersFactory)

        testScheduler.triggerActions()

        // Asserts
        verify(searchInteractor).randomRecipes()
        verifyZeroInteractions(recipeInfoMapper)
        assertNull(viewModel.list.value)
        assertThat(viewModel.showLoading.value, `is`(false))
        assertThat(viewModel.showError.value, `is`(true))
    }
}