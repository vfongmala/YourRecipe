package com.vfongmala.yourrecipe.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vfongmala.yourrecipe.dao.entity.SavedRecipe

@Dao
interface RecipeDao {

    @Query("SELECT * FROM SAVED_RECIPE")
    fun loadAll(): LiveData<List<SavedRecipe>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun save(data: SavedRecipe)
}