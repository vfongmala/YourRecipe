package com.vfongmala.yourrecipe.ui.component

import android.view.View
import com.vfongmala.yourrecipe.databinding.ItemSectionSubtitleBinding
import com.vfongmala.yourrecipe.databinding.ItemSectionTitleBinding
import com.vfongmala.yourrecipe.ui.entity.SectionSubtitle
import com.vfongmala.yourrecipe.ui.entity.SectionTitle

class SectionSubtitleViewHolder(
    private val view: View
): BaseViewHolder<SectionSubtitle>(view) {
    override fun bind(data: SectionSubtitle) {
        val binding = ItemSectionSubtitleBinding.bind(view)
        data.subtitle.let {
            binding.listSubtitle.text = it
        }
    }
}