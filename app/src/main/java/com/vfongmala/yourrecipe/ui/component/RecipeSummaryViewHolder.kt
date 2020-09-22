package com.vfongmala.yourrecipe.ui.component

import android.view.View
import com.vfongmala.yourrecipe.R
import com.vfongmala.yourrecipe.databinding.ItemRecipeSummaryBinding
import com.vfongmala.yourrecipe.ui.entity.RecipeSummary
import com.vfongmala.yourrecipe.ui.utils.TextHighlightHelper.Companion.highlightText

class RecipeSummaryViewHolder(
    private val view: View
): BaseViewHolder<RecipeSummary>(view) {
    override fun bind(data: RecipeSummary) {
        val binding = ItemRecipeSummaryBinding.bind(view)
        val credit = binding.recipeCredit
        val servings = binding.recipeServing
        val minutes = binding.recipeTime

        credit.text = itemView.resources.getString(R.string.credit, data.credit)
        val servingsText = itemView.resources.getQuantityString(R.plurals.servings, data.servings, data.servings)
        servings.text = highlightText(servingsText, "${data.servings}")
        val minutesText = itemView.resources.getQuantityString(R.plurals.minutes, data.minutes, data.minutes)
        minutes.text = highlightText(minutesText, "${data.minutes}")
    }
}