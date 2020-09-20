package com.vfongmala.yourrecipe.ui.search

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.vfongmala.yourrecipe.core.Constants
import com.vfongmala.yourrecipe.databinding.ActivitySearchBinding
import com.vfongmala.yourrecipe.entity.RecipePreview
import com.vfongmala.yourrecipe.ui.adapter.RecipePreviewAdapter
import com.vfongmala.yourrecipe.ui.recipe.RecipeActivity
import dagger.android.AndroidInjection
import javax.inject.Inject

class SearchActivity : AppCompatActivity(), SearchView {

    @Inject
    lateinit var presenter: SearchPresenter

    private lateinit var binding: ActivitySearchBinding

    private val listAdapter = RecipePreviewAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        initView()

        presenter.init()
    }

    override fun showResult(list: List<RecipePreview>) {
        binding.contentSearch.resultView.apply {
            visibility = View.VISIBLE
        }

        listAdapter.apply {
            data = list
            notifyDataSetChanged()
        }
    }

    override fun showLoading() {
        TODO("Not yet implemented")
    }

    override fun showNoResult() {
        TODO("Not yet implemented")
    }

    override fun openRecipe(id: Int) {
        val intent = Intent(this, RecipeActivity::class.java).apply {
            putExtra(Constants.RECIPE_ID.value, id)
        }
        startActivity(intent)
    }

    private fun initView() {
        binding = ActivitySearchBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        listAdapter.onClickFunc = {
            presenter.selectRecipe(it)
        }
        binding.contentSearch.searchButton.setOnClickListener {
            presenter.search(binding.contentSearch.searchBox.text.toString())
        }
        binding.contentSearch.resultView.apply {
            this.adapter = listAdapter
        }
    }
}