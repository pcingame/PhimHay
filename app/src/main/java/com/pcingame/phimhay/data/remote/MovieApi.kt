package com.pcingame.phimhay.data.remote

import com.pcingame.phimhay.data.remote.response.BaseListResponse
import com.pcingame.phimhay.data.remote.response.GetCastAndCrewResponse
import com.pcingame.phimhay.data.remote.response.GetMovieImagesResponse
import com.pcingame.phimhay.data.remote.response.GetReviewMovieResponse
import com.pcingame.phimhay.data.remote.response.GetSimilarMovieResponse
import com.pcingame.phimhay.data.remote.response.MovieResponse
import retrofit2.http.*

interface MovieApi {

    @GET("discover/movie")
    suspend fun getDiscoverMovie(@QueryMap hashMap: HashMap<String, String> = HashMap()): BaseListResponse<MovieResponse>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(@Query("page") page: Int = 1): BaseListResponse<MovieResponse>

    @GET("movie/{movie_id}")
    suspend fun getMovie(@Path("movie_id") movieId: String): MovieResponse

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCredits(@Path("movie_id") movieId: String): GetCastAndCrewResponse

    @GET("movie/{movie_id}/images")
    suspend fun getMovieImages(@Path("movie_id") movieId: String): GetMovieImagesResponse

    @GET("movie/{movie_id}/similar")
    suspend fun getSimilarMovie(@Path("movie_id") movieId: String, @Query("page") page: Int): BaseListResponse<GetSimilarMovieResponse>

    @GET("movie/{movie_id}/reviews")
    suspend fun getReviewMovie(@Path("movie_id") movieId: String, @Query("page") page: Int): BaseListResponse<GetReviewMovieResponse>
}
