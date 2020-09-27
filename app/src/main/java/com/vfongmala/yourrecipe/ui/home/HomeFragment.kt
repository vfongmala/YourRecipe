package com.vfongmala.yourrecipe.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.vfongmala.yourrecipe.core.Constants
import com.vfongmala.yourrecipe.databinding.FragmentHomeBinding
import com.vfongmala.yourrecipe.ui.adapter.RecipePreviewAdapter
import com.vfongmala.yourrecipe.ui.entity.RecipePreview
import com.vfongmala.yourrecipe.ui.recipe.RecipeActivity
import com.vfongmala.yourrecipe.ui.search.SearchActivity
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class HomeFragment : Fragment(), HomeView {

    @Inject
    lateinit var presenter: HomePresenter

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    private val listAdapter = RecipePreviewAdapter()

    private lateinit var viewModel: HomeViewModel

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

        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        viewModel.list.observe(viewLifecycleOwner, {
            showResult(it)
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.init()
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

    override fun showNoResult(message: String) {
        binding.loadingView.visibility = View.GONE
        binding.resultView.visibility = View.GONE
        binding.errorText.apply {
            visibility = View.VISIBLE
            text = message
        }
    }

    override fun goToSearchActivity() {
        startActivity(Intent(this.context, SearchActivity::class.java))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun openRecipe(data: RecipePreview) {
        val intent = Intent(this.context, RecipeActivity::class.java).apply {
            putExtra(Constants.RECIPE_ID.value, data.id)
            putExtra(Constants.RECIPE_IMAGE.value, data.url)
            putExtra(Constants.RECIPE_NAME.value, data.title)
        }
        startActivity(intent)
    }

    override fun showLoading() {
        binding.loadingView.visibility = View.VISIBLE
    }

    override fun updateData(results: List<RecipePreview>) {
        viewModel.list.value = results
    }

    private fun initView() {
        listAdapter.onClickFunc = {
            presenter.selectRecipe(it)
        }
        binding.fab.setOnClickListener {
            presenter.search()
        }
        binding.resultView.adapter = listAdapter
    }
}