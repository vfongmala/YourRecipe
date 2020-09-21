package com.vfongmala.yourrecipe.mapper

import com.vfongmala.yourrecipe.domain_contract.entity.RecipeInfo
import com.vfongmala.yourrecipe.domain_contract.mapper.Mapper
import com.vfongmala.yourrecipe.ui.entity.RecipeDetail

class RecipeDetailMapper: Mapper<RecipeInfo, RecipeDetail> {
    override fun map(input: RecipeInfo): RecipeDetail {
        return RecipeDetail(
            input.id,
            input.title,
            input.image,
            input.readyInMinutes,
            input.servings,
            input.summary,
            input.creditsText,
            mapInstructions(input.analyzedInstructions),
            mapIngredients(input.extendedIngredients)
        )
    }

    private fun mapInstructions(list: List<RecipeInfo.Instruction>): List<RecipeDetail.Instruction> {
        return list.map {
            RecipeDetail.Instruction(
                it.name,
                mapInstructionStep(it.steps)
            )
        }
    }

    private fun mapInstructionStep(list: List<RecipeInfo.InstructionStep>): List<RecipeDetail.InstructionStep> {
        return list.map {
            RecipeDetail.InstructionStep(
                "${it.number} ${it.step}"
            )
        }
    }

    private fun mapIngredients(list: List<RecipeInfo.ExtendedIngredient>): List<RecipeDetail.ExtendedIngredient> {
        return list.map {
            RecipeDetail.ExtendedIngredient(
                it.name,
                it.amount,
                it.unit
            )
        }
    }
}