package com.vfongmala.yourrecipe.di.ui

import com.vfongmala.yourrecipe.domain_contract.entity.Recipe
import com.vfongmala.yourrecipe.domain_contract.entity.RecipeInfo
import com.vfongmala.yourrecipe.domain_contract.mapper.Mapper
import com.vfongmala.yourrecipe.ui.entity.RecipeDetail
import com.vfongmala.yourrecipe.ui.entity.RecipePreview
import com.vfongmala.yourrecipe.mapper.RecipeDetailMapper
import com.vfongmala.yourrecipe.mapper.RecipePreviewMapper
import com.vfongmala.yourrecipe.mapper.RecipeMapper
import dagger.Module
import dagger.Provides

@Module
class PresentationModule {

    @Provides
    fun provideRecipeMapper(): Mapper<Recipe, RecipePreview> {
        return RecipeMapper()
    }

    @Provides
    fun provideRecipeInfoMapper(): Mapper<RecipeInfo, RecipePreview> {
        return RecipePreviewMapper()
    }

    @Provides
    fun provideRecipeDetailMapper(): Mapper<RecipeInfo, RecipeDetail> {
        return RecipeDetailMapper()
    }
}