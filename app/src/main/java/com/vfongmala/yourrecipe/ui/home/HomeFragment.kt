package com.vfongmala.yourrecipe.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.vfongmala.yourrecipe.R
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

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.init()
    }

    override fun setText(text: String) {
        val textView: TextView = rootView.findViewById(R.id.text_home)
        textView.text = text
    }
}