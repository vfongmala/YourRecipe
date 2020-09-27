package com.vfongmala.yourrecipe.ui.recipe

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.vfongmala.yourrecipe.R
import com.vfongmala.yourrecipe.core.Constants
import com.vfongmala.yourrecipe.databinding.ActivityRecipeBinding
import com.vfongmala.yourrecipe.ui.adapter.RecipeDetailAdapter
import com.vfongmala.yourrecipe.ui.entity.ViewDataWrapper
import dagger.android.AndroidInjection
import javax.inject.Inject

open class RecipeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecipeBinding

    private val listAdapter = RecipeDetailAdapter()

    @Inject
    lateinit var viewModel: RecipeViewModel

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

        viewModel.init(id, name, image)
    }

    private fun initView() {
        binding = ActivityRecipeBinding.inflate(layoutInflater)

        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        binding.fab.setOnClickListener {
            showSavedMessage()
            viewModel.save()
        }

        binding.contentView.adapter = listAdapter
        binding.contentView.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0 && binding.fab.isShown) {
                    binding.fab.hide()
                } else if (dy < 0 && !binding.fab.isShown) {
                    binding.fab.show()
                }
            }
        })
    }

    private fun initViewModel() {

        viewModel.data.observe(this, {
            updateRecipeData(it)
        })
        viewModel.image.observe(this, {
            showRecipeImage(it)
        })
        viewModel.name.observe(this, {
            setRecipeTitle(it)
        })
        viewModel.showError.observe(this, {
            setErrorMessageStatus(it)
        })
    }

    private fun setRecipeTitle(title: String) {
        supportActionBar?.title = title
    }

    private fun showRecipeImage(image: String) {
        Glide.with(this).load(image).into(binding.recipeImage)
    }

    private fun updateRecipeData(recipe: List<ViewDataWrapper>) {
        listAdapter.apply {
            data = recipe
            notifyDataSetChanged()
        }
    }

    private fun setErrorMessageStatus(show: Boolean) {
        binding.errorText.visibility = if (show) View.VISIBLE else View.GONE
        binding.fab.visibility = if (show) View.GONE else View.VISIBLE
    }

    private fun showSavedMessage() {
        Snackbar.make(binding.fab, R.string.recipe_saved, Snackbar.LENGTH_LONG).show()
    }
}