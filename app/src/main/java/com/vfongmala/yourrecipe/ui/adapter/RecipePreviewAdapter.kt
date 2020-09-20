package com.vfongmala.yourrecipe.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vfongmala.yourrecipe.R
import com.vfongmala.yourrecipe.entity.RecipePreview
import com.vfongmala.yourrecipe.ui.component.RecipePreviewViewHolder

class RecipePreviewAdapter(private val data: List<RecipePreview>): RecyclerView.Adapter<RecipePreviewViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipePreviewViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_recipe_preview, parent, false)
        return RecipePreviewViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipePreviewViewHolder, position: Int) {
        val recipe = data[position]
        holder.title.text = recipe.title
        Glide.with(holder.itemView).load(recipe.url).into(holder.thumbnail)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}