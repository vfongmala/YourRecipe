package com.vfongmala.yourrecipe.ui.component

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<in T>(view: View): RecyclerView.ViewHolder(view) {
    abstract fun bind(data: T)
}

class RecipeItemType {
    companion object {
        const val SUMMARY = 1
        const val SECTION_TITLE = 2
        const val INGREDIENT_ITEM = 3
        const val INSTRUCTION_ITEM = 4
        const val SECTION_SUBTITLE = 5
    }
}