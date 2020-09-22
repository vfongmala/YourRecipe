package com.vfongmala.yourrecipe.dao.entity

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "saved_recipe")
data class SavedRecipe(
    @PrimaryKey
    @NonNull
    private val id: Int,
    private val title: String,
    private val image: String
)