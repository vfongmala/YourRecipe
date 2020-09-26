package com.vfongmala.yourrecipe.domain.mapper

import com.vfongmala.yourrecipe.data_contract.entity.RecipeInfoResponse
import com.vfongmala.yourrecipe.domain_contract.entity.RecipeInfo
import com.vfongmala.yourrecipe.domain_contract.mapper.Mapper

class RecipeInfoResponseMapper : Mapper<RecipeInfoResponse, RecipeInfo> {
    override fun map(input: RecipeInfoResponse): RecipeInfo {
        return RecipeInfo(
            input.creditsText ?: "",
            mapExtendedIngredients(input.extendedIngredients),
            input.id ?: 0,
            input.title ?: "",
            input.readyInMinutes ?: 0,
            input.servings ?: 0,
            input.image ?: "",
            input.summary ?: "",
            mapAnalyzedInstructions(input.analyzedInstructions)
        )
    }

    private fun mapExtendedIngredients(list: List<RecipeInfoResponse.ExtendedIngredient>?): List<RecipeInfo.ExtendedIngredient> {
        return list?.filter {
            !it.name.isNullOrEmpty()
        }?.map {
            RecipeInfo.ExtendedIngredient(
                it.name ?: "",
                it.amount ?: 0.0,
                it.unit ?: ""
            )
        } ?: listOf()
    }

    private fun mapAnalyzedInstructions(list: List<RecipeInfoResponse.Instruction>?): List<RecipeInfo.Instruction> {
        return list?.filter {
            !it.steps.isNullOrEmpty()
        }?.map {
            RecipeInfo.Instruction(
                it.name ?: "",
                mapInstructionStep(it.steps)
            )
        } ?: listOf()
    }

    private fun mapInstructionStep(list: List<RecipeInfoResponse.InstructionStep>?): List<RecipeInfo.InstructionStep> {
        return list?.map {
            RecipeInfo.InstructionStep(
                it.number ?: 0,
                it.step ?: ""
            )
        } ?: listOf()
    }
}
