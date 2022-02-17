package com.audiolly.storage.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.audiolly.models.Album

@Dao
interface AlbumDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(album: Album)

    @Query("SELECT * FROM album")
    fun findAll() : List<Album>

    @Query("SELECT * FROM album WHERE idAlbum=:id ")
    fun findById(id: String): Album?

    @Delete
    fun delete(album: Album)
}