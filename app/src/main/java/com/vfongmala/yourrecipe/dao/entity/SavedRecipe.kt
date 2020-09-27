package com.vfongmala.yourrecipe.dao.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "saved_recipe")
data class SavedRecipe(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    val id: Int,
    val title: String,
    val image: String
)