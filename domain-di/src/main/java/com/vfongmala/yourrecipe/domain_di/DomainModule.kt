package com.vfongmala.yourrecipe.domain_di

import com.vfongmala.yourrecipe.data_contract.SearchApi
import com.vfongmala.yourrecipe.data_contract.entity.RecipeInfoResponse
import com.vfongmala.yourrecipe.data_contract.entity.SearchResponse
import com.vfongmala.yourrecipe.domain.SearchInteractorImpl
import com.vfongmala.yourrecipe.domain.mapper.RecipeInfoResponseMapper
import com.vfongmala.yourrecipe.domain.mapper.SearchResponseMapper
import com.vfongmala.yourrecipe.domain_contract.SearchInteractor
import com.vfongmala.yourrecipe.domain_contract.entity.RecipeInfo
import com.vfongmala.yourrecipe.domain_contract.entity.SearchResult
import com.vfongmala.yourrecipe.domain_contract.mapper.Mapper
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideSearchInteractor(
        searchApi: SearchApi,
        searchResponseMapper: Mapper<SearchResponse, SearchResult>,
        recipeInfoResponseMapper: Mapper<RecipeInfoResponse, RecipeInfo>
    ): SearchInteractor {
        return SearchInteractorImpl(
            searchApi,
            searchResponseMapper,
            recipeInfoResponseMapper
        )
    }

    @Provides
    fun provideSearchResponseMapper(): Mapper<SearchResponse, SearchResult> {
        return SearchResponseMapper()
    }

    @Provides
    fun provideRecipeInfoResponseMapper(): Mapper<RecipeInfoResponse, RecipeInfo> {
        return RecipeInfoResponseMapper()
    }
}