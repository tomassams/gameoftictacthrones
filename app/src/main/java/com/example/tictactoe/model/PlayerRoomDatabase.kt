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

@Database(entities = arrayOf(Player::class), version = 1)
public abstract class PlayerRoomDatabase : RoomDatabase() {

    abstract fun playerDao(): PlayerDao

    companion object {
        @Volatile
        private var INSTANCE: PlayerRoomDatabase? = null

        private class PlayerDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {

            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.playerDao())
                    }
                }
            }
        }

        fun populateDatabase(playerDao: PlayerDao) {
            playerDao.deleteAll()

            var player = Player(name = "Tomas")
            playerDao.insert(player)
            player = Player(name  = "Maren")
            playerDao.insert(player)
        }

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): PlayerRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PlayerRoomDatabase::class.java,
                    "Player_database"
                ).addCallback(Companion.PlayerDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }


}