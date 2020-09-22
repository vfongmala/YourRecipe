package com.vfongmala.yourrecipe.ui.component

import android.view.View
import com.vfongmala.yourrecipe.R
import com.vfongmala.yourrecipe.databinding.ItemIngredientBinding
import com.vfongmala.yourrecipe.ui.entity.RecipeIngredient
import com.vfongmala.yourrecipe.ui.utils.TextHighlightHelper.Companion.highlightText

class RecipeIngredientViewHolder(
    private val view: View
): BaseViewHolder<RecipeIngredient>(view) {
    override fun bind(data: RecipeIngredient) {
        ItemIngredientBinding.bind(view).run {
            ingredientName.text = data.name
            val ingredientText = itemView.context.getString(R.string.ingredient_amount,
                data.amount,data.unit)
            ingredientAmount.text = highlightText(ingredientText, data.amount)
        }
    }
}