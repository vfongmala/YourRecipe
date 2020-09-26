package com.vfongmala.yourrecipe.mapper

import com.vfongmala.yourrecipe.domain_contract.entity.RecipeInfo
import com.vfongmala.yourrecipe.ui.entity.RecipeDetail
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class RecipeDetailMapperTest {
    private val mapper = RecipeDetailMapper()

    @Test
    fun `test map`() {
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

        // Act
        val result = mapper.map(data)

        // Assert
        assertThat(result.id, `is`(1234))
        assertThat(result.title, `is`("recipe1"))
        assertThat(result.image, `is`("url1"))
        assertThat(result.readyInMinutes, `is`(10))
        assertThat(result.servings, `is`(1))
        assertThat(result.summary, `is`("summary"))
        assertThat(result.creditsText, `is`("credit"))
        assertThat(result.analyzedInstructions, `is`(listOf(
            RecipeDetail.Instruction(
                "step1",
                listOf(RecipeDetail.InstructionStep("1) step1"))
            )
        )))
        assertThat(result.extendedIngredients, `is`(listOf(
            RecipeDetail.ExtendedIngredient(
                "ingredient1",
                "1.1",
                "gram"
            )
        )))
    }
}