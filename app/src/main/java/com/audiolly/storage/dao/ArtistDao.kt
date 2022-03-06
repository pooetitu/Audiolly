package com.audiolly.storage.dao

import androidx.room.*
import com.audiolly.models.Artist

@Dao
interface ArtistDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(artist: Artist)

    @Query("SELECT * FROM artist")
    fun findAll(): List<Artist>

    @Query("SELECT * FROM artist WHERE idArtist=:id ")
    fun findById(id: String): Artist?

    @Delete
    fun delete(artist: Artist)
}