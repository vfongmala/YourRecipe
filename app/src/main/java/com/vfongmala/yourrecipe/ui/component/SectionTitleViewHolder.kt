package com.vfongmala.yourrecipe.ui.component

import android.view.View
import com.vfongmala.yourrecipe.databinding.ItemSectionTitleBinding
import com.vfongmala.yourrecipe.ui.entity.SectionTitle

class SectionTitleViewHolder(
    private val view: View
): BaseViewHolder<SectionTitle>(view) {
    override fun bind(data: SectionTitle) {
        val binding = ItemSectionTitleBinding.bind(view)
        binding.listTitle.setText(data.resId)
    }
}