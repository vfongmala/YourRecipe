package com.vfongmala.yourrecipe.ui.recipe

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.vfongmala.yourrecipe.core.Constants
import com.vfongmala.yourrecipe.databinding.ActivityRecipeBinding
import com.vfongmala.yourrecipe.ui.adapter.RecipeDetailAdapter
import com.vfongmala.yourrecipe.ui.entity.ViewDataWrapper
import dagger.android.AndroidInjection
import javax.inject.Inject

class RecipeActivity : AppCompatActivity(), RecipeView {

    @Inject
    lateinit var presenter: RecipePresenter

    private lateinit var binding: ActivityRecipeBinding

    private val listAdapter = RecipeDetailAdapter()

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

        binding.fab.setOnClickListener {
            presenter.saveRecipe()
        }

        binding.contentView.adapter = listAdapter
    }

    override fun showRecipe(recipe: List<ViewDataWrapper>) {
        listAdapter.apply {
            data = recipe
            notifyDataSetChanged()
        }
    }

    override fun showError() {
        binding.errorText.visibility = View.VISIBLE
        binding.contentView.visibility = View.GONE
        binding.fab.visibility = View.GONE
    }
}