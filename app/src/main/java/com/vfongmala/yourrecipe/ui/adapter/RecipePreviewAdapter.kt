package com.vfongmala.yourrecipe.ui.adapter

import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vfongmala.yourrecipe.R
import com.vfongmala.yourrecipe.ui.entity.RecipePreview
import com.vfongmala.yourrecipe.ui.component.RecipePreviewViewHolder

class RecipePreviewAdapter: RecyclerView.Adapter<RecipePreviewViewHolder>() {

    var data: MutableList<RecipePreview> = mutableListOf()

    var onClickFunc: (RecipePreview) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipePreviewViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_recipe_preview, parent, false)
        return RecipePreviewViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipePreviewViewHolder, position: Int) {
        val recipe = data[position]
        holder.title.text = recipe.title
        val color = listOf(
            R.color.thumbnail_color_1,
            R.color.thumbnail_color_2,
            R.color.thumbnail_color_3,
            R.color.thumbnail_color_4,
            R.color.thumbnail_color_5,
            R.color.thumbnail_color_6,
            R.color.thumbnail_color_7
        ).random()
        holder.thumbnail.setImageDrawable(ColorDrawable(color))

        if (recipe.url.isNotEmpty()) {
            Glide.with(holder.itemView).load(recipe.url).into(holder.thumbnail)
        }
        holder.itemView.setOnClickListener {
            onClickFunc(recipe)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}