package com.vfongmala.yourrecipe.mapper

import com.vfongmala.yourrecipe.domain_contract.entity.RecipeInfo
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class RecipePreviewMapperTest {
    private val mapper = RecipePreviewMapper()

    @Test
    fun `test map`() {
        // Act
        val result = mapper.map(RecipeInfo(
            "credit",
            listOf(
                RecipeInfo.ExtendedIngredient("ingredient1", 1.0, "gram")
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
        ))

        // Assert
        assertThat(result.id, `is`(1234))
        assertThat(result.title, `is`("recipe1"))
        assertThat(result.url, `is`("url1"))
    }
}