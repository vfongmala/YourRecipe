package com.vfongmala.yourrecipe.mapper

import com.vfongmala.yourrecipe.domain_contract.entity.RecipeInfo
import com.vfongmala.yourrecipe.domain_contract.mapper.Mapper
import com.vfongmala.yourrecipe.ui.entity.RecipePreview

class RecipePreviewMapper: Mapper<RecipeInfo, RecipePreview> {
    override fun map(input: RecipeInfo): RecipePreview {
        return RecipePreview(
            input.id,
            input.title,
            input.image
        )
    }
}