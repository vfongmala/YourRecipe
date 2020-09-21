package com.vfongmala.yourrecipe.ui.component

import android.view.View
import com.vfongmala.yourrecipe.databinding.ItemIngredientBinding
import com.vfongmala.yourrecipe.ui.entity.RecipeIngredient

class RecipeIngredientViewHolder(
    private val view: View
): BaseViewHolder<RecipeIngredient>(view) {
    override fun bind(data: RecipeIngredient) {
        ItemIngredientBinding.bind(view).run {
            ingredientName.text = data.name
            ingredientAmount.text = "${data.amount} ${data.unit}"
        }
    }
}