package com.vfongmala.yourrecipe.ui.recipe

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
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

    private lateinit var viewModel: RecipeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        val id = intent.extras?.getInt(Constants.RECIPE_ID.value)?: 0
        val name = intent.extras?.getString(Constants.RECIPE_NAME.value)?: ""
        val image = intent.extras?.getString(Constants.RECIPE_IMAGE.value)?: ""

        initView()
        initViewModel()

        viewModel.image.value = image
        viewModel.name.value = name

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

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(RecipeViewModel::class.java)

        viewModel.data.observe(this, {
            updateRecipeData(it)
        })
        viewModel.image.observe(this, {
            showRecipeImage(it)
        })
        viewModel.name.observe(this, {
            setRecipeTitle(it)
        })
    }

    private fun setRecipeTitle(title: String) {
        supportActionBar?.title = title
    }

    private fun showRecipeImage(image: String) {
        Glide.with(this).load(image).into(binding.recipeImage)
    }

    override fun updateViewModel(recipe: List<ViewDataWrapper>) {
        viewModel.data.value = recipe
    }

    private fun updateRecipeData(recipe: List<ViewDataWrapper>) {
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