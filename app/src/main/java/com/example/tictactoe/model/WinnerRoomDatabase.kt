package com.example.tictactoe.model

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import kotlinx.coroutines.experimental.CoroutineScope
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.IO
import kotlinx.coroutines.experimental.launch

@Database(entities = arrayOf(Winner::class), version = 1)
public abstract class WinnerRoomDatabase : RoomDatabase() {

    abstract fun winnerDao(): WinnerDao

    companion object {
        @Volatile
        private var INSTANCE: WinnerRoomDatabase? = null

        private class WinnerDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {

            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.winnerDao())
                    }
                }
            }
        }

        fun populateDatabase(winnerDao: WinnerDao) {
            winnerDao.deleteAll()

            /*
            TODO: remove dummy data after verification
            var winner = Winner(name = "Ola Nordmann")
            winnerDao.insert(winner)
            winner = Winner(name  = "Kari Nordmann")
            winnerDao.insert(winner)
            */
        }

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): WinnerRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WinnerRoomDatabase::class.java,
                    "Winner_database"
                ).addCallback(Companion.WinnerDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }


}