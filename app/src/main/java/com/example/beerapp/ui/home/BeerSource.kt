package com.example.beerapp.ui.home

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.beerapp.model.Beer
import com.example.beerapp.repository.BeerRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BeerSource @Inject constructor(
    private val beerRepository: BeerRepository
) : PagingSource<Int, Beer>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Beer> = try {
        val nextPage = params.key ?: 1

        Log.d("PAGE_TEST", "nextPage " + nextPage)

        val beerListResponse = beerRepository.getBeersByPageForPaging(nextPage)

        LoadResult.Page(
            data = beerListResponse,
            prevKey = if (nextPage == 1) null else nextPage - 1,
            nextKey = nextPage.plus(1)
        )
    } catch (e: Exception) {
        LoadResult.Error(e)
    }

    override fun getRefreshKey(state: PagingState<Int, Beer>): Int? {
        Log.d("PAGE_TEST", "anchorPosition " + state.anchorPosition)
        return state.anchorPosition
    }
}
