package com.vfongmala.yourrecipe.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.vfongmala.yourrecipe.core.Constants
import com.vfongmala.yourrecipe.databinding.FragmentHomeBinding
import com.vfongmala.yourrecipe.ui.adapter.RecipePreviewAdapter
import com.vfongmala.yourrecipe.ui.entity.RecipePreview
import com.vfongmala.yourrecipe.ui.recipe.RecipeActivity
import com.vfongmala.yourrecipe.ui.search.SearchActivity
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class HomeFragment : Fragment() {

    @Inject
    lateinit var viewModel: HomeViewModel

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    private val listAdapter = RecipePreviewAdapter()

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        initView()

        viewModel.list.observe(viewLifecycleOwner, {
            showResult(it)
        })

        viewModel.showLoading.observe(viewLifecycleOwner, {
            setLoadingStatus(it)
        })

        viewModel.showError.observe(viewLifecycleOwner, {
            setErrorMessageStatus(it)
        })

        return binding.root
    }

    private fun showResult(results: List<RecipePreview>) {
        binding.loadingView.visibility = View.GONE
        binding.errorText.visibility = View.GONE
        binding.resultView.visibility = View.VISIBLE
        listAdapter.apply {
            data.clear()
            data.addAll(results)
            notifyDataSetChanged()
        }
    }

    private fun goToSearchActivity() {
        startActivity(Intent(this.context, SearchActivity::class.java))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun openRecipe(data: RecipePreview) {
        val intent = Intent(this.context, RecipeActivity::class.java).apply {
            putExtra(Constants.RECIPE_ID.value, data.id)
            putExtra(Constants.RECIPE_IMAGE.value, data.url)
            putExtra(Constants.RECIPE_NAME.value, data.title)
        }
        startActivity(intent)
    }

    private fun setLoadingStatus(show: Boolean) {
        binding.loadingView.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun setErrorMessageStatus(show: Boolean) {
        binding.errorText.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun initView() {
        listAdapter.onClickFunc = {
            openRecipe(it)
        }
        binding.fab.setOnClickListener {
            goToSearchActivity()
        }
        binding.resultView.adapter = listAdapter
    }
}