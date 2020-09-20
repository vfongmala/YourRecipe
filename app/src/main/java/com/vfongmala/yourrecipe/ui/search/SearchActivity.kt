package com.vfongmala.yourrecipe.ui.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.vfongmala.yourrecipe.R
import com.vfongmala.yourrecipe.databinding.ActivitySearchBinding
import com.vfongmala.yourrecipe.entity.RecipePreview
import com.vfongmala.yourrecipe.ui.adapter.RecipePreviewAdapter
import dagger.android.AndroidInjection
import javax.inject.Inject

class SearchActivity : AppCompatActivity(), SearchView {

    @Inject
    lateinit var presenter: SearchPresenter

    private lateinit var binding: ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.searchButton.setOnClickListener {
            presenter.search(binding.searchBox.text.toString())
        }

        presenter.init()
    }

    override fun showResult(list: List<RecipePreview>) {
        val recyclerView = binding.resultView.findViewById<RecyclerView>(R.id.result_view).apply {
            visibility = View.VISIBLE
        }
        val adapter = RecipePreviewAdapter(list)
        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    override fun showLoading() {
        TODO("Not yet implemented")
    }

    override fun showNoResult() {
        TODO("Not yet implemented")
    }
}