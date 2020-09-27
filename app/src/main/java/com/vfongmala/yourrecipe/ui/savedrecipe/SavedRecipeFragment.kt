package com.vfongmala.yourrecipe.ui.savedrecipe

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.vfongmala.yourrecipe.core.Constants
import com.vfongmala.yourrecipe.dao.entity.SavedRecipe
import com.vfongmala.yourrecipe.databinding.FragmentSavedRecipeBinding
import com.vfongmala.yourrecipe.ui.adapter.RecipePreviewAdapter
import com.vfongmala.yourrecipe.ui.entity.RecipePreview
import com.vfongmala.yourrecipe.ui.recipe.RecipeActivity

class SavedRecipeFragment : Fragment() {

    private var _binding: FragmentSavedRecipeBinding? = null
    private val binding get() = _binding!!

    private val listAdapter = RecipePreviewAdapter()
    private lateinit var savedRecipeViewModel: SavedRecipeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSavedRecipeBinding.inflate(inflater, container, false)

        initView()
        initViewModel()

        return binding.root
    }

    private fun initView() {
        listAdapter.onClickFunc = {
            openRecipe(it)
        }

        binding.resultView.adapter = listAdapter
    }

    private fun initViewModel() {
        savedRecipeViewModel =
            ViewModelProvider(this).get(SavedRecipeViewModel::class.java)

        savedRecipeViewModel.data.observe(viewLifecycleOwner, {
            show(it)
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showLoading()
    }

    private fun show(result: List<SavedRecipe>) {
        if (result.isEmpty()) {
            showNoResult()
        } else {
            binding.emptyResultView.visibility = View.GONE
            binding.loadingView.visibility = View.GONE
            binding.resultView.visibility = View.VISIBLE

            listAdapter.apply {
                data.clear()
                data.addAll(result.map {
                    RecipePreview(it.id, it.title, it.image)
                })
                notifyDataSetChanged()
            }
        }
    }

    private fun showLoading() {
        binding.loadingView.visibility = View.VISIBLE
        binding.emptyResultView.visibility = View.GONE
        binding.resultView.visibility = View.GONE
    }

    private fun showNoResult() {
        binding.emptyResultView.visibility = View.VISIBLE
        binding.loadingView.visibility = View.GONE
        binding.resultView.visibility = View.GONE
    }

    private fun openRecipe(data: RecipePreview) {
        val intent = Intent(this.context, RecipeActivity::class.java).apply {
            putExtra(Constants.RECIPE_ID.value, data.id)
            putExtra(Constants.RECIPE_IMAGE.value, data.url)
            putExtra(Constants.RECIPE_NAME.value, data.title)
        }
        startActivity(intent)
    }
}