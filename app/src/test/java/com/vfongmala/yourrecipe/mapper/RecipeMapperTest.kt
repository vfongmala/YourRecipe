package com.vfongmala.yourrecipe.mapper

import com.vfongmala.yourrecipe.domain_contract.entity.Recipe
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class RecipeMapperTest {
    private val mapper = RecipeMapper()

    @Test
    fun `test map`() {
        // Arrange
        val data = Recipe(
            12,
            "recipe",
            "url"
        )

        // Act
        val result = mapper.map(data)

        // Assert
        assertThat(result.id, `is`(12))
        assertThat(result.title, `is`("recipe"))
        assertThat(result.url, `is`("url"))
    }
}
