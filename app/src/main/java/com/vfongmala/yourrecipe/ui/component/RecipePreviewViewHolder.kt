package com.vfongmala.yourrecipe.ui.component

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vfongmala.yourrecipe.R

class RecipePreviewViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val thumbnail: ImageView = view.findViewById(R.id.thumbnail)
    val title: TextView = view.findViewById(R.id.recipe_name)
}