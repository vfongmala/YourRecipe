package com.vfongmala.yourrecipe.ui.utils

import android.graphics.Typeface
import android.text.SpannableString
import android.text.style.StyleSpan

class TextHighlightHelper {
    companion object {
        fun highlightText(displayString: String, highlightString: String): SpannableString {
            return SpannableString(displayString).apply {
                setSpan(StyleSpan(Typeface.BOLD), displayString.indexOf(highlightString), displayString.indexOf(highlightString) + highlightString.length, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
            }
        }
    }
}