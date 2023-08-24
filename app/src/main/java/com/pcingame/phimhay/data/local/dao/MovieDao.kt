package com.pcingame.phimhay.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pcingame.phimhay.data.entity.MovieEntity

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies")
    suspend fun getAll(): List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: MovieEntity)

    @Query("SELECT * FROM movies WHERE id = :movieId")
    suspend fun get(movieId: String): MovieEntity

    @Query("DELETE FROM movies WHERE id = :movieId")
    suspend fun delete(movieId: String)
}
