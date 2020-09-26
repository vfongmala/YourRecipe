package com.vfongmala.yourrecipe.domain

import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyZeroInteractions
import com.nhaarman.mockitokotlin2.whenever
import com.vfongmala.yourrecipe.data_contract.SearchApi
import com.vfongmala.yourrecipe.data_contract.entity.RandomRecipesResponse
import com.vfongmala.yourrecipe.data_contract.entity.RecipeInfoResponse
import com.vfongmala.yourrecipe.data_contract.entity.SearchResponse
import com.vfongmala.yourrecipe.domain_contract.SearchInteractor
import com.vfongmala.yourrecipe.domain_contract.entity.RecipeInfo
import com.vfongmala.yourrecipe.domain_contract.entity.SearchResult
import com.vfongmala.yourrecipe.domain_contract.mapper.Mapper
import io.reactivex.rxjava3.core.Observable
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SearchInteractorImplTest {

    @Mock
    lateinit var api: SearchApi

    @Mock
    lateinit var searchResponseMapper: Mapper<SearchResponse, SearchResult>

    @Mock
    lateinit var recipeInfoResponseMapper: Mapper<RecipeInfoResponse, RecipeInfo>

    private lateinit var interactor: SearchInteractor

    @Before
    fun setup() {
        interactor = SearchInteractorImpl(api, searchResponseMapper, recipeInfoResponseMapper)
    }

    @Test
    fun `test search`() {
        // Arrange
        val mockResponse = SearchResponse(listOf(),0, 0, 0)
        whenever(api.search("test")).thenReturn(
            Observable.just(mockResponse)
        )
        val expectData = SearchResult(listOf(), 0)
        whenever(searchResponseMapper.map(mockResponse)).thenReturn(expectData)

        // Act
        val result = interactor.search("test").blockingFirst()

        // Assert
        verify(api).search(eq("test"))
        verify(searchResponseMapper).map(mockResponse)
        assertThat(result, `is`(expectData))
    }

    @Test
    fun `test recipeInfo`() {
        // Arrange
        val mockResponse = RecipeInfoResponse(
            "", listOf(), 0, "", 0, 0, "", "", listOf()
        )
        whenever(api.recipeInfo(1234)).thenReturn(
            Observable.just(mockResponse)
        )
        val expectData = RecipeInfo(
            "", listOf(), 0, "", 0, 0, "", "", listOf()
        )
        whenever(recipeInfoResponseMapper.map(mockResponse)).thenReturn(expectData)

        // Act
        val result = interactor.recipeInfo(1234).blockingFirst()

        // Assert
        verify(api).recipeInfo(eq(1234))
        verify(recipeInfoResponseMapper).map(mockResponse)
        assertThat(result, `is`(expectData))
    }

    @Test
    fun `test randomRecipes`() {
        // Arrange
        val mockRecipeResponse = RecipeInfoResponse(
            "", listOf(), 0, "", 0, 0, "", "", listOf()
        )
        val mockResponse = RandomRecipesResponse(
            listOf(mockRecipeResponse)
        )
        whenever(api.randomRecipes(20)).thenReturn(
            Observable.just(mockResponse)
        )

        val expectRecipeInfoData = RecipeInfo(
            "", listOf(), 0, "", 0, 0, "", "", listOf()
        )
        val expectData = listOf(
            expectRecipeInfoData
        )
        whenever(recipeInfoResponseMapper.map(mockRecipeResponse)).thenReturn(expectRecipeInfoData)

        // Act
        val result = interactor.randomRecipes().blockingFirst()

        // Assert
        verify(api).randomRecipes(eq(20))
        verify(recipeInfoResponseMapper).map(mockRecipeResponse)
        assertThat(result, `is`(expectData))
    }

    @Test
    fun `test randomRecipes with null result`() {
        // Arrange
        val mockResponse = RandomRecipesResponse(null)
        whenever(api.randomRecipes(20)).thenReturn(
            Observable.just(mockResponse)
        )

        // Act
        interactor.randomRecipes().blockingFirst()

        // Assert
        verify(api).randomRecipes(eq(20))
        verifyZeroInteractions(recipeInfoResponseMapper)
    }
}