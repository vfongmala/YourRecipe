package com.vfongmala.yourrecipe.ui.component

import android.view.View
import com.bumptech.glide.Glide
import com.vfongmala.yourrecipe.R
import com.vfongmala.yourrecipe.databinding.ItemRecipeSummaryBinding
import com.vfongmala.yourrecipe.ui.entity.RecipeSummary

class RecipeSummaryViewHolder(
    private val view: View
): BaseViewHolder<RecipeSummary>(view) {
    override fun bind(data: RecipeSummary) {
        val binding = ItemRecipeSummaryBinding.bind(view)
        val image = binding.recipeImage
        val title = binding.recipeName
        val credit = binding.recipeCredit
        val servings = binding.recipeServing
        val minutes = binding.recipeTime

        Glide.with(itemView).load(data.image).into(image)
        title.text = data.title
        credit.text = data.credit
        servings.text = itemView.resources.getQuantityString(R.plurals.servings, data.servings, data.servings)
        minutes.text = itemView.resources.getQuantityString(R.plurals.minutes, data.minutes, data.minutes)
    }
}