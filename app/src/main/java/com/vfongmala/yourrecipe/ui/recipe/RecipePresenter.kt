package com.vfongmala.yourrecipe.ui.recipe

import com.vfongmala.yourrecipe.R
import com.vfongmala.yourrecipe.core.SchedulersFactory
import com.vfongmala.yourrecipe.dao.SavedRecipeDatabase
import com.vfongmala.yourrecipe.dao.SavedRecipeRepository
import com.vfongmala.yourrecipe.dao.entity.SavedRecipe
import com.vfongmala.yourrecipe.domain_contract.SearchInteractor
import com.vfongmala.yourrecipe.domain_contract.entity.RecipeInfo
import com.vfongmala.yourrecipe.domain_contract.mapper.Mapper
import com.vfongmala.yourrecipe.ui.entity.*

class RecipePresenter(
    private val view: RecipeView,
    private val searchInteractor: SearchInteractor,
    private val schedulersFactory: SchedulersFactory,
    private val mapper: Mapper<RecipeInfo, RecipeDetail>
) {
    private var recipe: SavedRecipe? = null
    lateinit var repo: SavedRecipeRepository

    fun init(activity: RecipeActivity) {
        val dao = SavedRecipeDatabase.getDatabase(activity.applicationContext).dao()
        repo = SavedRecipeRepository(dao)
    }

    fun loadRecipe(id: Int) {
        searchInteractor.recipeInfo(id)
            .subscribeOn(schedulersFactory.io())
            .observeOn(schedulersFactory.main())
            .map {
                mapper.map(it)
            }.subscribe({
                updateModel(it)
            }, {
                view.showError()
            })
    }

    fun cacheRecipe(id: Int, name: String, url: String) {
        recipe = SavedRecipe(id, name, url)
    }

    fun saveRecipe() {
        recipe?.let {
            view.saveRecipe(it)
        }
    }

    private fun updateModel(result: RecipeDetail) {
        view.updateViewModel(transform(result))
    }

    private fun transform(result: RecipeDetail): List<ViewDataWrapper> {
        val summary = RecipeSummary(
            result.creditsText,
            result.servings,
            result.readyInMinutes
        )
        val ingredientTitle = SectionTitle(R.string.ingredients)
        val ingredients = result.extendedIngredients.map {
            RecipeIngredient(
                it.name,
                it.amount,
                it.unit
            )
        }

        val instructionTitle = SectionTitle(R.string.instructions)

        val list = mutableListOf(
            summary,
            ingredientTitle
        )
        list.addAll(ingredients)
        list.add(instructionTitle)

        for (instruction in result.analyzedInstructions) {
            if (instruction.name.isNotEmpty()) {
                list.add(SectionSubtitle(instruction.name))
            }
            list.addAll(instruction.steps.map {
                RecipeInstruction(it.step)
            })
        }

        return list
    }
}