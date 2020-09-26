package com.vfongmala.yourrecipe.ui.recipe

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import com.vfongmala.yourrecipe.R
import com.vfongmala.yourrecipe.core.SchedulersFactory
import com.vfongmala.yourrecipe.domain_contract.SearchInteractor
import com.vfongmala.yourrecipe.domain_contract.entity.RecipeInfo
import com.vfongmala.yourrecipe.domain_contract.mapper.Mapper
import com.vfongmala.yourrecipe.ui.entity.*

class RecipePresenter(
    private val view: RecipeView,
    private val lifecycleOwner: LifecycleOwner,
    private val viewModelProvider: ViewModelProvider,
    private val searchInteractor: SearchInteractor,
    private val schedulersFactory: SchedulersFactory,
    private val mapper: Mapper<RecipeInfo, RecipeDetail>
) {

    private lateinit var viewModel: RecipeViewModel

    fun init(name: String, image: String) {
        viewModel = viewModelProvider.get(RecipeViewModel::class.java)

        viewModel.data.observe(lifecycleOwner, {
            view.showRecipe(it)
        })
        viewModel.image.observe(lifecycleOwner, {
            view.showRecipeImage(it)
        })
        viewModel.name.observe(lifecycleOwner, {
            view.setRecipeTitle(it)
        })

        viewModel.image.value = image
        viewModel.name.value = name
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

    fun saveRecipe() {

    }

    private fun updateModel(result: RecipeDetail) {
        viewModel.data.value = transform(result)
    }

    private fun transform(result: RecipeDetail): List<ViewDataWrapper> {
        val summary = RecipeSummary(
            result.image,
            result.title,
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