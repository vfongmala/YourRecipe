package com.vfongmala.yourrecipe.ui.adapter

import android.view.ViewGroup
import android.widget.FrameLayout
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.vfongmala.yourrecipe.ui.component.*
import com.vfongmala.yourrecipe.ui.entity.*
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

@RunWith(RobolectricTestRunner::class)
class RecipeDetailAdapterTest {

    private val adapter = RecipeDetailAdapter()
    private val context = RuntimeEnvironment.application

    @Test
    fun `test onCreateViewHolder`() {
        // Arrange
        val parent: ViewGroup = FrameLayout(context)

        // Act & Assert
        assertTrue(adapter.onCreateViewHolder(parent, RecipeItemType.SUMMARY) is RecipeSummaryViewHolder)
        assertTrue(adapter.onCreateViewHolder(parent, RecipeItemType.INGREDIENT_ITEM) is RecipeIngredientViewHolder)
        assertTrue(adapter.onCreateViewHolder(parent, RecipeItemType.INSTRUCTION_ITEM) is RecipeInstructionViewHolder)
        assertTrue(adapter.onCreateViewHolder(parent, RecipeItemType.SECTION_TITLE) is SectionTitleViewHolder)
        assertTrue(adapter.onCreateViewHolder(parent, RecipeItemType.SECTION_SUBTITLE) is SectionSubtitleViewHolder)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `test onCreateViewHolder with invalid viewType`() {
        // Arrange
        val parent: ViewGroup = FrameLayout(context)

        // Act & Assert
        adapter.onCreateViewHolder(parent, -1)
    }

    @Test
    fun `test onBindViewHolder`() {
        // Arrange
        val summary = RecipeSummary("url", "title", "credit", 1, 10)
        val ingredient = RecipeIngredient("ingredient", "1.0", "gram")
        val instruction = RecipeInstruction("step1")
        val title = SectionTitle(1)
        val subtitle = SectionSubtitle("subtitle")
        adapter.data = listOf(
            summary,
            ingredient,
            instruction,
            title,
            subtitle
        )

        // Act
        val summaryViewHolder: RecipeSummaryViewHolder = mock()
        adapter.onBindViewHolder(summaryViewHolder, 0)
        val ingredientViewHolder: RecipeIngredientViewHolder = mock()
        adapter.onBindViewHolder(ingredientViewHolder, 1)
        val instructionViewHolder: RecipeInstructionViewHolder = mock()
        adapter.onBindViewHolder(instructionViewHolder, 2)
        val titleViewHolder: SectionTitleViewHolder = mock()
        adapter.onBindViewHolder(titleViewHolder, 3)
        val subtitleViewHolder: SectionSubtitleViewHolder = mock()
        adapter.onBindViewHolder(subtitleViewHolder, 4)


        // Assert
        verify(summaryViewHolder).bind(summary)
        verify(ingredientViewHolder).bind(ingredient)
        verify(instructionViewHolder).bind(instruction)
        verify(titleViewHolder).bind(title)
        verify(subtitleViewHolder).bind(subtitle)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `test onBindViewHolder with invalid viewHolder`() {
        val viewHolder: BaseViewHolder<Any> = mock()

        // Act
        adapter.onBindViewHolder(viewHolder, 0)
    }

    @Test
    fun `test getItemCount`() {
        // Arrange
        val summary = RecipeSummary("url", "title", "credit", 1, 10)
        val ingredient = RecipeIngredient("ingredient", "1.0", "gram")
        val instruction = RecipeInstruction("step1")
        val title = SectionTitle(1)
        val subtitle = SectionSubtitle("subtitle")
        adapter.data = listOf(
            summary,
            ingredient,
            instruction,
            title,
            subtitle
        )

        // Act
        assertThat(adapter.itemCount, `is`(5))
    }

    @Test
    fun `test getItemViewType`() {
        // Arrange
        val summary = RecipeSummary("url", "title", "credit", 1, 10)
        val ingredient = RecipeIngredient("ingredient", "1.0", "gram")
        val instruction = RecipeInstruction("step1")
        val title = SectionTitle(1)
        val subtitle = SectionSubtitle("subtitle")
        adapter.data = listOf(
            summary,
            ingredient,
            instruction,
            title,
            subtitle
        )

        // Act & Assert
        assertThat(adapter.getItemViewType(0), `is`(RecipeItemType.SUMMARY))
        assertThat(adapter.getItemViewType(1), `is`(RecipeItemType.INGREDIENT_ITEM))
        assertThat(adapter.getItemViewType(2), `is`(RecipeItemType.INSTRUCTION_ITEM))
        assertThat(adapter.getItemViewType(3), `is`(RecipeItemType.SECTION_TITLE))
        assertThat(adapter.getItemViewType(4), `is`(RecipeItemType.SECTION_SUBTITLE))
    }

    @Test(expected = IllegalArgumentException::class)
    fun `test getItemViewType with invalid data`() {
        // Arrange
        val mockData: ViewDataWrapper = mock()
        adapter.data = listOf(
            mockData
        )

        // Act
        adapter.getItemViewType(0)
    }
}