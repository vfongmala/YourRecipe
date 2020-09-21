package com.vfongmala.yourrecipe.mapper

import com.vfongmala.yourrecipe.domain_contract.entity.Recipe
import com.vfongmala.yourrecipe.domain_contract.mapper.Mapper
import com.vfongmala.yourrecipe.ui.entity.RecipePreview

class RecipeMapper: Mapper<Recipe, RecipePreview> {
    override fun map(input: Recipe): RecipePreview {
        return RecipePreview(
            input.id,
            input.title,
            input.image
        )
    }
}