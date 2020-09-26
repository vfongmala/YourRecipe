package com.vfongmala.yourrecipe.domain.mapper

import com.vfongmala.yourrecipe.data_contract.entity.RecipeResponse
import com.vfongmala.yourrecipe.data_contract.entity.SearchResponse
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class SearchResponseMapperTest {

    private val mapper = SearchResponseMapper()

    @Test
    fun `test map valid data`() {
        // Arrange
        val data = SearchResponse(
            listOf(
                RecipeResponse(
                    1, "recipe1", "url1"
                )
            ),
            3,2,1)

        // Act
        val result = mapper.map(data)

        // Assert
        assertThat(result.result.size, `is`(1))
        assertThat(result.result[0].id, `is`(1))
        assertThat(result.result[0].title, `is`("recipe1"))
        assertThat(result.result[0].image, `is`("url1"))
        assertThat(result.totalResults, `is`(1))
    }

    @Test
    fun `test map invalid data`() {
        // Arrange
        val data = SearchResponse(
            listOf(
                RecipeResponse(
                    null, null, null
                )
            ),
            null, null, null)

        // Act
        val result = mapper.map(data)

        // Assert
        assertThat(result.result.size, `is`(1))
        assertThat(result.result[0].id, `is`(0))
        assertThat(result.result[0].title, `is`(""))
        assertThat(result.result[0].image, `is`(""))
        assertThat(result.totalResults, `is`(0))
    }

    @Test
    fun `test map null list data`() {
        // Arrange
        val data = SearchResponse(
            null, null, null, null)

        // Act
        val result = mapper.map(data)

        // Assert
        assertThat(result.result.size, `is`(0))
        assertThat(result.totalResults, `is`(0))
    }


    @Test
    fun `test map filter id 0 data`() {
        // Arrange
        val data = SearchResponse(
            listOf(
                RecipeResponse(
                    0, "recipe0", "url0"
                ),
                RecipeResponse(
                    1, "recipe1", "url1"
                )
            ),
            3,2,1)

        // Act
        val result = mapper.map(data)

        // Assert
        assertThat(result.result.size, `is`(1))
        assertThat(result.result[0].id, `is`(1))
        assertThat(result.result[0].title, `is`("recipe1"))
        assertThat(result.result[0].image, `is`("url1"))
        assertThat(result.totalResults, `is`(1))
    }
}