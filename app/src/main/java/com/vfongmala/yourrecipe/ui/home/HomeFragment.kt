package com.vfongmala.yourrecipe.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.vfongmala.yourrecipe.R
import com.vfongmala.yourrecipe.entity.RecipePreview
import com.vfongmala.yourrecipe.ui.adapter.RecipePreviewAdapter
import com.vfongmala.yourrecipe.ui.search.SearchActivity
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class HomeFragment : Fragment(), HomeView {

    @Inject
    lateinit var presenter: HomePresenter

    private lateinit var rootView: View

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_home, container, false)

        rootView.findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            presenter.search()
        }

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.init()
    }

    override fun showResult(results: List<RecipePreview>) {
        rootView.findViewById<TextView>(R.id.error_text).apply {
            visibility = View.GONE
        }
        val recyclerView = rootView.findViewById<RecyclerView>(R.id.result_view).apply {
            visibility = View.VISIBLE
        }
        val adapter = RecipePreviewAdapter(results)
        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    override fun showNoResult(message: String) {
        rootView.findViewById<RecyclerView>(R.id.result_view).apply {
            visibility = View.GONE
        }
        rootView.findViewById<TextView>(R.id.error_text).apply {
            visibility = View.VISIBLE
            text = message
        }
    }

    override fun goToSearchActivity() {
        startActivity(Intent(this.context, SearchActivity::class.java))
    }
}