package com.vfongmala.yourrecipe.domain.mapper

import com.vfongmala.yourrecipe.data_contract.entity.RecipeInfoResponse
import com.vfongmala.yourrecipe.domain_contract.entity.RecipeInfo
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class RecipeInfoResponseMapperTest {
    private val mapper = RecipeInfoResponseMapper()

    @Test
    fun `test map valid data`() {
        // Arrange
        val data = RecipeInfoResponse(
            "credit",
            listOf(
                RecipeInfoResponse.ExtendedIngredient("ingredient1", 1.0, "gram")
            ),
            1234,
            "recipe1",
            10,
            1,
            "url1",
            "summary",
            listOf(
                RecipeInfoResponse.Instruction(
                    "step1", listOf(
                        RecipeInfoResponse.InstructionStep(1, "step1")
                    )
                )
            )
        )

        // Act
        val result = mapper.map(data)

        // Assert
        assertThat(result.creditsText, `is`("credit"))
        assertThat(result.extendedIngredients, `is`(listOf(RecipeInfo.ExtendedIngredient("ingredient1", 1.0, "gram"))))
        assertThat(result.id, `is`(1234))
        assertThat(result.title, `is`("recipe1"))
        assertThat(result.readyInMinutes, `is`(10))
        assertThat(result.servings, `is`(1))
        assertThat(result.image, `is`("url1"))
        assertThat(result.summary, `is`("summary"))
        assertThat(result.analyzedInstructions, `is`(listOf(
            RecipeInfo.Instruction(
                "step1", listOf(
                    RecipeInfo.InstructionStep(1, "step1")
                )
            )
        )))
    }

    @Test
    fun `test map invalid data`() {
        // Arrange
        val data = RecipeInfoResponse(
            null,
            listOf(
                RecipeInfoResponse.ExtendedIngredient(null, null, null)
            ),
            null,
            null,
            null,
            null,
            null,
            null,
            listOf(
                RecipeInfoResponse.Instruction(
                    null, listOf(
                        RecipeInfoResponse.InstructionStep(null, null)
                    )
                )
            )
        )

        // Act
        val result = mapper.map(data)

        // Assert
        assertThat(result.creditsText, `is`(""))
        assertThat(result.extendedIngredients, `is`(listOf()))
        assertThat(result.id, `is`(0))
        assertThat(result.title, `is`(""))
        assertThat(result.readyInMinutes, `is`(0))
        assertThat(result.servings, `is`(0))
        assertThat(result.image, `is`(""))
        assertThat(result.summary, `is`(""))
        assertThat(result.analyzedInstructions, `is`(listOf(
            RecipeInfo.Instruction(
                "", listOf(
                    RecipeInfo.InstructionStep(0, "")
                )
            )
        )))
    }

    @Test
    fun `test map null list data`() {
        // Arrange
        val data = RecipeInfoResponse(
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null
        )

        // Act
        val result = mapper.map(data)

        // Assert
        assertThat(result.creditsText, `is`(""))
        assertThat(result.extendedIngredients, `is`(listOf()))
        assertThat(result.id, `is`(0))
        assertThat(result.title, `is`(""))
        assertThat(result.readyInMinutes, `is`(0))
        assertThat(result.servings, `is`(0))
        assertThat(result.image, `is`(""))
        assertThat(result.summary, `is`(""))
        assertThat(result.analyzedInstructions, `is`(listOf()))
    }
}