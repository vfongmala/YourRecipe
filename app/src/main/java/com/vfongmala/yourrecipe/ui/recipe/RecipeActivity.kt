package com.vfongmala.yourrecipe.ui.recipe

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.vfongmala.yourrecipe.core.Constants
import com.vfongmala.yourrecipe.databinding.ActivityRecipeBinding
import com.vfongmala.yourrecipe.entity.RecipeInfo
import dagger.android.AndroidInjection
import javax.inject.Inject

class RecipeActivity : AppCompatActivity(), RecipeView {

    @Inject
    lateinit var presenter: RecipePresenter

    private lateinit var binding: ActivityRecipeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        initView()

        val id = intent.extras?.getInt(Constants.RECIPE_ID.value)?: 0

        presenter.init()
        presenter.loadRecipe(id)
    }

    private fun initView() {
        binding = ActivityRecipeBinding.inflate(layoutInflater)

        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun showRecipe(recipe: RecipeInfo) {
        Glide.with(this).load(recipe.image).into(binding.contentRecipe.recipeImage)
    }

    override fun showError() {
        TODO("Not yet implemented")
    }
}