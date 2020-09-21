package com.vfongmala.yourrecipe.ui.entity

data class RecipeSummary(
    val image: String,
    val title: String,
    val credit: String,
    val servings: Int,
    val minutes: Int
): ViewDataWrapper