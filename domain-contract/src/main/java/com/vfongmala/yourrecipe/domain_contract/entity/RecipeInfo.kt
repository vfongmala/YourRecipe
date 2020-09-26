package com.vfongmala.yourrecipe.domain_contract.entity

data class RecipeInfo(
    val creditsText: String,
    val extendedIngredients: List<ExtendedIngredient>,
    val id: Int,
    val title: String,
    val readyInMinutes: Int,
    val servings: Int,
    val image: String,
    val summary: String,
    val analyzedInstructions: List<Instruction>
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
        val number: Int,
        val step: String
    )
}