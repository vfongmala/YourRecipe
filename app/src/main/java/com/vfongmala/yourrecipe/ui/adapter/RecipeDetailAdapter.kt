package com.vfongmala.yourrecipe.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vfongmala.yourrecipe.R
import com.vfongmala.yourrecipe.ui.component.*
import com.vfongmala.yourrecipe.ui.entity.*

class RecipeDetailAdapter: RecyclerView.Adapter<BaseViewHolder<*>>() {

    var data: List<ViewDataWrapper> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            RecipeItemType.SUMMARY -> {
                val view = inflater.inflate(R.layout.item_recipe_summary, parent, false)
                RecipeSummaryViewHolder(view)
            }
            RecipeItemType.INGREDIENT_ITEM -> {
                val view = inflater.inflate(R.layout.item_ingredient, parent, false)
                RecipeIngredientViewHolder(view)
            }
            RecipeItemType.INSTRUCTION_ITEM -> {
                val view = inflater.inflate(R.layout.item_instruction_step, parent, false)
                RecipeInstructionViewHolder(view)
            }
            RecipeItemType.SECTION_TITLE -> {
                val view = inflater.inflate(R.layout.item_section_title, parent, false)
                SectionTitleViewHolder(view)
            }
            RecipeItemType.SECTION_SUBTITLE -> {
                val view = inflater.inflate(R.layout.item_section_subtitle, parent, false)
                SectionSubtitleViewHolder(view)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder) {
            is RecipeSummaryViewHolder -> holder.bind(data[position] as RecipeSummary)
            is RecipeIngredientViewHolder -> holder.bind(data[position] as RecipeIngredient)
            is RecipeInstructionViewHolder -> holder.bind(data[position] as RecipeInstruction)
            is SectionTitleViewHolder -> holder.bind(data[position] as SectionTitle)
            is SectionSubtitleViewHolder -> holder.bind(data[position] as SectionSubtitle)
            else -> throw IllegalArgumentException("Invalid view holder type")
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemViewType(position: Int): Int {
        return when(data[position]) {
            is RecipeSummary -> RecipeItemType.SUMMARY
            is SectionTitle -> RecipeItemType.SECTION_TITLE
            is RecipeIngredient -> RecipeItemType.INGREDIENT_ITEM
            is SectionSubtitle -> RecipeItemType.SECTION_SUBTITLE
            is RecipeInstruction -> RecipeItemType.INSTRUCTION_ITEM
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }
}