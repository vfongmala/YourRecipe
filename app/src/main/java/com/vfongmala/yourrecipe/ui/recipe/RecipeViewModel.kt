package com.vfongmala.yourrecipe.ui.recipe

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vfongmala.yourrecipe.R
import com.vfongmala.yourrecipe.core.SchedulersFactory
import com.vfongmala.yourrecipe.dao.SavedRecipeDatabase
import com.vfongmala.yourrecipe.dao.SavedRecipeRepository
import com.vfongmala.yourrecipe.dao.entity.SavedRecipe
import com.vfongmala.yourrecipe.domain_contract.SearchInteractor
import com.vfongmala.yourrecipe.domain_contract.entity.RecipeInfo
import com.vfongmala.yourrecipe.domain_contract.mapper.Mapper
import com.vfongmala.yourrecipe.ui.entity.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class RecipeViewModel @Inject constructor(
    activity: RecipeActivity,
    private val searchInteractor: SearchInteractor,
    private val schedulersFactory: SchedulersFactory,
    private val mapper: Mapper<RecipeInfo, RecipeDetail>
) : ViewModel() {

    val data: MutableLiveData<List<ViewDataWrapper>> by lazy { MutableLiveData<List<ViewDataWrapper>>() }

    val image: MutableLiveData<String> by lazy { MutableLiveData<String>() }

    val name: MutableLiveData<String> by lazy { MutableLiveData<String>() }

    val showError: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }

    private var dataForSave: SavedRecipe? = null

    private val repo: SavedRecipeRepository

    init {
        val dao = SavedRecipeDatabase.getDatabase(activity).dao()
        repo = SavedRecipeRepository(dao)
    }

    fun init(id: Int, name: String, image: String) {
        dataForSave = SavedRecipe(id, name, image)
        loadRecipe(id)
    }

    private fun loadRecipe(id: Int) {
        searchInteractor.recipeInfo(id)
            .subscribeOn(schedulersFactory.io())
            .observeOn(schedulersFactory.main())
            .map {
                mapper.map(it)
            }.subscribe({
                showError.value = false
                data.value = transform(it)
            }, {
                showError.value = true
            })
    }

    fun save() = viewModelScope.launch(Dispatchers.IO) {
        dataForSave?.let {
            repo.save(it)
        }
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