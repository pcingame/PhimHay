package com.pcingame.phimhay.base.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.pcingame.phimhay.common.Constant

open class BasePagingSource<Item : Any>(
    private val onLoadData: suspend (Int) -> List<Item>
) : PagingSource<Int, Item>() {

    open val firstPage get() = Constant.DEFAULT_FIRST_PAGE

    override fun getRefreshKey(state: PagingState<Int, Item>): Int? {
        // Try to find the page key of the closest page to anchorPosition, from
        // either the prevKey or the nextKey, but you need to handle nullability
        // here:
        //  * prevKey == null -> anchorPage is the first page.
        //  * nextKey == null -> anchorPage is the last page.
        //  * both prevKey and nextKey null -> anchorPage is the initial page, so
        //    just return null.
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.inc() ?: anchorPage?.nextKey?.dec()
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Item> {
        return try {
            val page = params.key ?: firstPage
            LoadResult.Page(
                data = onLoadData(page),
                prevKey = if (page == firstPage) null else page - 1,
                nextKey = page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
