package com.vfongmala.yourrecipe.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.vfongmala.yourrecipe.databinding.FragmentHomeBinding
import com.vfongmala.yourrecipe.entity.RecipePreview
import com.vfongmala.yourrecipe.ui.adapter.RecipePreviewAdapter
import com.vfongmala.yourrecipe.ui.search.SearchActivity
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class HomeFragment : Fragment(), HomeView {

    @Inject
    lateinit var presenter: HomePresenter

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

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

        binding.fab.setOnClickListener { view ->
            presenter.search()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.init()
    }

    override fun showResult(results: List<RecipePreview>) {
        binding.errorText.apply {
            visibility = View.GONE
        }
        val adapter = RecipePreviewAdapter(results)
        binding.resultView.apply {
            visibility = View.VISIBLE
            this.adapter = adapter
        }
    }

    override fun showNoResult(message: String) {
        binding.resultView.apply {
            visibility = View.GONE
        }
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
}