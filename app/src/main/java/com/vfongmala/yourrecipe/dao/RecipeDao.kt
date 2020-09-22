package com.vfongmala.yourrecipe.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.vfongmala.yourrecipe.dao.entity.SavedRecipe

@Dao
interface RecipeDao {

    @Query("SELECT * FROM SAVED_RECIPE ORDER BY ID")
    fun loadAll(): LiveData<List<SavedRecipe>>

    @Insert
    fun save(data: SavedRecipe)

    @Delete
    fun unsave(data: SavedRecipe)
}