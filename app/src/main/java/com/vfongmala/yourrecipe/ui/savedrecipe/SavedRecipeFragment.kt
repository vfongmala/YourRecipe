package com.vfongmala.yourrecipe.ui.savedrecipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.vfongmala.yourrecipe.R

class SavedRecipeFragment : Fragment() {

    private lateinit var savedRecipeViewModel: SavedRecipeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        savedRecipeViewModel =
            ViewModelProvider(this).get(SavedRecipeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_saved_recipe, container, false)
        val textView: TextView = root.findViewById(R.id.text_gallery)
        savedRecipeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}