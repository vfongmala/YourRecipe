package com.vfongmala.yourrecipe.ui.adapter

import android.view.ViewGroup
import android.widget.FrameLayout
import com.vfongmala.yourrecipe.ui.component.RecipePreviewViewHolder
import com.vfongmala.yourrecipe.ui.entity.RecipePreview
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

@RunWith(RobolectricTestRunner::class)
class RecipePreviewAdapterTest {
    private val adapter = RecipePreviewAdapter()
    private val context = RuntimeEnvironment.application

    @Test
    fun `test onCreateViewHolder`() {
        // Arrange
        val parent: ViewGroup = FrameLayout(context)

        // Act & Assert
        assertThat(adapter.onCreateViewHolder(parent,0), instanceOf(RecipePreviewViewHolder::class.java))
    }

    @Test
    fun `test onBindViewHolder`() {
        // Arrange
        val parent: ViewGroup = FrameLayout(context)
        val viewHolder = adapter.onCreateViewHolder(parent,0)

        val data = RecipePreview(1, "recipe", "url")
        adapter.data.add(data)

        // Act
        adapter.onBindViewHolder(viewHolder, 0)
        viewHolder.itemView.performClick()

        // Assert
        assertThat(viewHolder.title.text, `is`("recipe"))
    }
}