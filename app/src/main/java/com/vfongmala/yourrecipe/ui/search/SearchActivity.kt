package com.vfongmala.yourrecipe.ui.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.vfongmala.yourrecipe.core.Constants
import com.vfongmala.yourrecipe.databinding.ActivitySearchBinding
import com.vfongmala.yourrecipe.ui.adapter.RecipePreviewAdapter
import com.vfongmala.yourrecipe.ui.entity.RecipePreview
import com.vfongmala.yourrecipe.ui.recipe.RecipeActivity
import dagger.android.AndroidInjection
import javax.inject.Inject

class SearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding

    private val listAdapter = RecipePreviewAdapter()

    @Inject
    lateinit var viewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        initView()
        initViewModel()
    }

    private fun initViewModel() {
        viewModel.list.observe(this, {
            showResult(it)
        })

        viewModel.showNoResult.observe(this, {
            setNoResultMessageStatus(it)
        })
        viewModel.showLoading.observe(this, {
            setLoadingStatus(it)
        })
        viewModel.showError.observe(this, {
            setErrorMessageStatus(it)
        })
    }

    private fun showResult(list: List<RecipePreview>) {
        if (list.isNotEmpty()) {
            binding.contentSearch.resultView.visibility = View.VISIBLE

            listAdapter.apply {
                data.clear()
                data.addAll(list)
                notifyDataSetChanged()
            }
        } else {
            binding.contentSearch.resultView.visibility = View.GONE
        }
    }

    private fun openRecipe(data: RecipePreview) {
        val intent = Intent(this, RecipeActivity::class.java).apply {
            putExtra(Constants.RECIPE_ID.value, data.id)
            putExtra(Constants.RECIPE_IMAGE.value, data.url)
            putExtra(Constants.RECIPE_NAME.value, data.title)
        }
        startActivity(intent)
    }

    private fun hideKeyboard() {
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
            openRecipe(it)
        }
        binding.contentSearch.searchButton.setOnClickListener {
            performSearch()
        }
        binding.contentSearch.resultView.adapter = listAdapter
        binding.contentSearch.searchBox.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                performSearch()
                true
            } else {
                false
            }
        }
    }

    private fun performSearch() {
        viewModel.search(binding.contentSearch.searchBox.text.toString())
        hideKeyboard()
    }

    private fun setLoadingStatus(show: Boolean) {
        binding.contentSearch.loadingView.visibility = if (show) View.VISIBLE else View.GONE
        binding.contentSearch.resultView.visibility = if (show) View.GONE else View.VISIBLE
    }

    private fun setErrorMessageStatus(show: Boolean) {
        binding.contentSearch.errorText.visibility = if (show) View.VISIBLE else View.GONE
        binding.contentSearch.resultView.visibility = if (show) View.GONE else View.VISIBLE
    }

    private fun setNoResultMessageStatus(show: Boolean) {
        binding.contentSearch.noResultText.visibility = if (show) View.VISIBLE else View.GONE
        binding.contentSearch.resultView.visibility = if (show) View.GONE else View.VISIBLE
    }

}