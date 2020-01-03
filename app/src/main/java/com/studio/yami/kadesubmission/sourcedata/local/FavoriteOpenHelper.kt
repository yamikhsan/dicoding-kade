package com.studio.yami.kadesubmission.sourcedata.local

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.studio.yami.kadesubmission.entity.ListEntity
import com.studio.yami.kadesubmission.entity.league.MatchEntity
import org.jetbrains.anko.db.*

class FavoriteOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "Favorite.db", null, 1) {
    companion object {
        private var instance: FavoriteOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): FavoriteOpenHelper {
            if (instance == null) {
                instance = FavoriteOpenHelper(ctx.applicationContext)
            }
            return instance as FavoriteOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        eventTable(db)
        teamTable(db)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(MatchEntity.Event.TABLE_NAME, true)
        db.dropTable(ListEntity.TABLE_NAME, true)
    }

    private fun eventTable(db: SQLiteDatabase){
        db.createTable(
            MatchEntity.Event.TABLE_NAME, true,
            MatchEntity.Event.ID_ to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            MatchEntity.Event.ID_EVENT to TEXT + UNIQUE,
            MatchEntity.Event.STR_LEAGUE to TEXT,
            MatchEntity.Event.DATE_EVENT to TEXT,
            MatchEntity.Event.ID_HOME_TEAM to TEXT,
            MatchEntity.Event.ID_AWAY_TEAM to TEXT,
            MatchEntity.Event.STR_HOME_TEAM to TEXT,
            MatchEntity.Event.STR_AWAY_TEAM to TEXT,
            MatchEntity.Event.INT_HOME_SCORE to TEXT,
            MatchEntity.Event.INT_AWAY_SCORE to TEXT
        )
    }

    private fun teamTable(db: SQLiteDatabase){
        db.createTable(
            ListEntity.TABLE_NAME, true,
            ListEntity.ID_ to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            ListEntity.ID_TEAM to TEXT + UNIQUE,
            ListEntity.STR_TEAM_BADGE to TEXT + UNIQUE,
            ListEntity.STR_TEAM to TEXT
        )
    }
}

val Context.database: FavoriteOpenHelper
    get() = FavoriteOpenHelper.getInstance(applicationContext)