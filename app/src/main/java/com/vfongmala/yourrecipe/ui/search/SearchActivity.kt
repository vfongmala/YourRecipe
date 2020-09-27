package com.vfongmala.yourrecipe.ui.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.vfongmala.yourrecipe.core.Constants
import com.vfongmala.yourrecipe.databinding.ActivitySearchBinding
import com.vfongmala.yourrecipe.ui.adapter.RecipePreviewAdapter
import com.vfongmala.yourrecipe.ui.entity.RecipePreview
import com.vfongmala.yourrecipe.ui.recipe.RecipeActivity
import dagger.android.AndroidInjection
import javax.inject.Inject

class SearchActivity : AppCompatActivity(), SearchView {

    @Inject
    lateinit var presenter: SearchPresenter

    private lateinit var binding: ActivitySearchBinding

    private val listAdapter = RecipePreviewAdapter()
    private lateinit var viewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        initView()
        viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)

        viewModel.list.observe(this, {
            showResult(it)
        })
    }

    override fun updateModel(list: List<RecipePreview>) {
        viewModel.list.value = list

    }

    private fun showResult(list: List<RecipePreview>) {
        binding.contentSearch.loadingView.visibility = View.GONE
        binding.contentSearch.resultView.visibility = View.VISIBLE
        binding.contentSearch.resultView.apply {
            visibility = View.VISIBLE
        }

        listAdapter.apply {
            data.clear()
            data.addAll(list)
            notifyDataSetChanged()
        }
    }

    override fun showLoading() {
        binding.contentSearch.loadingView.visibility = View.VISIBLE
        binding.contentSearch.resultView.visibility = View.GONE
        binding.contentSearch.noResultText.visibility = View.GONE
    }

    override fun showNoResult() {
        binding.contentSearch.noResultText.visibility = View.VISIBLE
        binding.contentSearch.loadingView.visibility = View.GONE
    }

    override fun showError(message: String) {
        binding.contentSearch.noResultText.apply {
            visibility = View.VISIBLE
            text = message
        }
        binding.contentSearch.loadingView.visibility = View.GONE
    }

    override fun openRecipe(data: RecipePreview) {
        val intent = Intent(this, RecipeActivity::class.java).apply {
            putExtra(Constants.RECIPE_ID.value, data.id)
            putExtra(Constants.RECIPE_IMAGE.value, data.url)
            putExtra(Constants.RECIPE_NAME.value, data.title)
        }
        startActivity(intent)
    }

    override fun hideKeyboard() {
        val view = this.currentFocus
        view?.let {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }

    private fun initView() {
        binding = ActivitySearchBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        listAdapter.onClickFunc = {
            presenter.selectRecipe(it)
        }
        binding.contentSearch.searchButton.setOnClickListener {
            performSearch()
        }
        binding.contentSearch.resultView.adapter = listAdapter
        binding.contentSearch.searchBox.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                performSearch()
                true
            }
            false
        }
    }

    private fun performSearch() {
        presenter.search(binding.contentSearch.searchBox.text.toString())
    }
}