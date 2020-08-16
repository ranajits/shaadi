package com.ranajit.shaadi.data.dao

import androidx.room.*
import com.ranajit.shaadi.model.MatchesModel

@Dao
interface MatchesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun setMatch(match: MatchesModel): Long

    @Update
    suspend fun updateMatch(match: MatchesModel): Int

    @Query("SELECT * FROM matches WHERE isAccepted = :acceptanceState")
    suspend fun getMatchesByAcceptanceState(acceptanceState: Int): List<MatchesModel>

    @Query("SELECT * FROM matches ORDER BY dateAdded DESC")
    suspend fun getAllMatches(): List<MatchesModel>

    @Query("SELECT * FROM matches ORDER BY dateAdded DESC")
    suspend fun getMatchesByAll(
    ): List<MatchesModel>

}