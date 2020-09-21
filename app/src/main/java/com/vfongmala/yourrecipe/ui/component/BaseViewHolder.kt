package com.vfongmala.yourrecipe.ui.component

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<in T>(view: View): RecyclerView.ViewHolder(view) {
    abstract fun bind(data: T)
}

enum class RecipeItemType(val value: Int) {
    SUMMARY(1),
    SECTION_TITLE(2),
    INGREDIENT_ITEM(3),
    INSTRUCTION_SECTION_TITLE(4),
    INSTRUCTION_ITEM(5),
    SECTION_SUBTITLE(6)
}