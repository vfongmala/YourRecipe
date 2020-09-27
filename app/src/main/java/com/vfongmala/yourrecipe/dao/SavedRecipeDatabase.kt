package com.vfongmala.yourrecipe.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.vfongmala.yourrecipe.dao.entity.SavedRecipe

@Database(entities = [SavedRecipe::class], version = 1, exportSchema = false)
abstract class SavedRecipeDatabase: RoomDatabase() {

    abstract fun dao(): RecipeDao

    companion object {
        @Volatile
        private var INSTANCE: SavedRecipeDatabase? = null

        fun getDatabase(context: Context): SavedRecipeDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SavedRecipeDatabase::class.java,
                    "saved_recipe_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}