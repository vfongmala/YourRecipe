package com.vfongmala.yourrecipe.ui.component

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vfongmala.yourrecipe.R
import com.vfongmala.yourrecipe.databinding.ViewRecipePreviewBinding

class RecipePreviewViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val binding = ViewRecipePreviewBinding.bind(view)
    val thumbnail: ImageView = binding.thumbnail
    val title: TextView = binding.recipeName
}