package com.example.beerapp.ui.home

import android.graphics.Movie
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.beerapp.model.Beer
import com.example.beerapp.repository.BeerRepository

class BeerSource(
    private val beerRepository: BeerRepository
) : PagingSource<Int, Beer>() {

    override suspend fun load(params: PagingSource.LoadParams<Int>): LoadResult<Int, Beer> {
        return try {
            val nextPage = params.key ?: 1
            val beerListResponse = beerRepository.getB(nextPage)

            LoadResult.Page(
                data = movieListResponse.results,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = movieListResponse.page.plus(1)
            )
        } catch (e: Exception) {
            PagingSource.LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Beer>): Int? = state.anchorPosition
}
