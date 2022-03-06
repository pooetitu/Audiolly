package com.audiolly.storage

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.audiolly.models.Album
import com.audiolly.models.Artist
import com.audiolly.storage.dao.AlbumDao
import com.audiolly.storage.dao.ArtistDao

@Database(entities = [Album::class, Artist::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun artistDao(): ArtistDao
    abstract fun albumDao(): AlbumDao
}

class DatabaseManager(context: Context) {
    private val db = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "audiolly_DB"
    ).build()

    fun findAllAlbums(): List<Album> {
        return db.albumDao().findAll()
    }

    fun insertAlbum(album: Album) {
        return db.albumDao().insert(album)
    }

    fun findAlbumById(id: String): Album? {
        return db.albumDao().findById(id)
    }

    fun deleteAlbum(album: Album) {
        return db.albumDao().delete(album)
    }

    fun findAllArtists(): List<Artist> {
        return db.artistDao().findAll()
    }

    fun insertArtist(artist: Artist) {
        return db.artistDao().insert(artist)
    }

    fun findArtistById(id: String): Artist? {
        return db.artistDao().findById(id)
    }

    fun deleteArtist(artist: Artist) {
        return db.artistDao().delete(artist)
    }
}