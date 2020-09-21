package com.vfongmala.yourrecipe.ui.component

import android.view.View
import com.vfongmala.yourrecipe.databinding.ItemInstructionStepBinding
import com.vfongmala.yourrecipe.ui.entity.RecipeInstruction

class RecipeInstructionViewHolder(
    private val view: View
): BaseViewHolder<RecipeInstruction>(view) {
    override fun bind(data: RecipeInstruction) {
        val binding = ItemInstructionStepBinding.bind(view)
        binding.listItem.text = data.text
    }
}