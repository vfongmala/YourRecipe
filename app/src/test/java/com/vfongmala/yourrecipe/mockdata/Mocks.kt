package com.vfongmala.yourrecipe.mockdata

import com.vfongmala.yourrecipe.domain_contract.entity.Recipe
import com.vfongmala.yourrecipe.domain_contract.entity.RecipeInfo
import com.vfongmala.yourrecipe.ui.entity.*

class Mocks {
    companion object {
        val recipeInfo = RecipeInfo(
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

        val recipePreview = RecipePreview(1, "recipe", "url")

        val recipeDetail = RecipeDetail(
            1234,
            "recipe1",
            "url1",
            10,
            1,
            "summary",
            "credit",
            listOf(
                RecipeDetail.Instruction("step1", listOf(RecipeDetail.InstructionStep("1) step1")))
            ),
            listOf(
                RecipeDetail.ExtendedIngredient("ingredient1", "1.1", "gram")
            )
        )

        val recipeSummary = RecipeSummary(
            "credit",
            1,
            10
        )

        val recipeIngredient = RecipeIngredient("ingredient1", "1.1", "gram")

        val recipeInstruction = RecipeInstruction("1) step1")

        val recipe = Recipe(1234, "recipe1", "url1")
    }
}