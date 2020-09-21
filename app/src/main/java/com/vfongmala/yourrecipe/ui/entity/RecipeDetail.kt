package com.vfongmala.yourrecipe.ui.entity

data class RecipeDetail(
    val id: Int,
    val title: String,
    val image: String,
    val readyInMinutes: Int,
    val servings: Int,
    val summary: String,
    val creditsText: String,
    val analyzedInstructions: List<Instruction>,
    val extendedIngredients: List<ExtendedIngredient>
) {
    data class ExtendedIngredient(
        val name: String,
        val amount: Double,
        val unit: String
    )

    data class Instruction(
        val name: String,
        val steps: List<InstructionStep>
    )

    data class InstructionStep(
        val step: String
    )
}