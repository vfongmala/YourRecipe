package com.vfongmala.yourrecipe.data

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import com.vfongmala.yourrecipe.data_contract.Api
import com.vfongmala.yourrecipe.data_contract.SearchApi
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SearchApiImplTest {

    @Mock
    lateinit var api: Api

    lateinit var searchApi: SearchApi

    @Before
    fun setup() {
        searchApi = SearchApiImpl(api)
    }

    @Test
    fun `test search`() {
        // Act
        searchApi.search("test")

        // Assert
        verify(api).search(eq("test"), any())
    }

    @Test
    fun `test recipeInfo`() {
        // Act
        searchApi.recipeInfo(1234)

        // Assert
        verify(api).recipeInfo(eq(1234), any())
    }

    @Test
    fun `test randomRecipes`() {
        // Act
        searchApi.randomRecipes(10)

        // Assert
        verify(api).randomRecipes(eq(10), any())
    }
}