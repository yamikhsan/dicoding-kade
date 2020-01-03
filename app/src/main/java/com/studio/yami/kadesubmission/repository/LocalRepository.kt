package com.studio.yami.kadesubmission.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import android.util.Log
import com.studio.yami.kadesubmission.entity.ListEntity
import com.studio.yami.kadesubmission.entity.league.MatchEntity
import com.studio.yami.kadesubmission.sourcedata.local.database
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.doAsync

class LocalRepository(private val ctx: Context) {

//    EVENT
    fun showEventFavorite(): LiveData<List<MatchEntity.Event>?> {
        val events = MutableLiveData<List<MatchEntity.Event>?>()
        doAsync {

            ctx.database.use {
                val res = select(MatchEntity.Event.TABLE_NAME)
                val fav = res.parseList(classParser<MatchEntity.Event>())
                events.postValue(fav)
            }

        }
        return events
    }

    fun showEventFavoriteById(id: String): LiveData<Boolean> {
        val event = MutableLiveData<Boolean>()
        doAsync {
            ctx.database.use {
                val res = select(MatchEntity.Event.TABLE_NAME)
                    .whereArgs(
                        "(${MatchEntity.Event.ID_EVENT} = {id})",
                        "id" to id
                    )
                val fav = res.parseList(classParser<MatchEntity.Event>())
                val status = fav.isNotEmpty()
                event.postValue(status)
            }
        }
        return event
    }

    fun insertEventFavorite(e: MatchEntity.Event) {
        doAsync {
            try {

                ctx.database.use {
                    insert(
                        MatchEntity.Event.TABLE_NAME,
                        MatchEntity.Event.ID_EVENT to e.idEvent,
                        MatchEntity.Event.STR_LEAGUE to e.strLeague,
                        MatchEntity.Event.DATE_EVENT to e.dateEvent,
                        MatchEntity.Event.ID_HOME_TEAM to e.idHomeTeam,
                        MatchEntity.Event.ID_AWAY_TEAM to e.idAwayTeam,
                        MatchEntity.Event.STR_HOME_TEAM to e.strHomeTeam,
                        MatchEntity.Event.STR_AWAY_TEAM to e.strAwayTeam,
                        MatchEntity.Event.INT_HOME_SCORE to e.intHomeScore,
                        MatchEntity.Event.INT_AWAY_SCORE to e.intAwayScore
                    )
                }

            } catch (e: SQLiteConstraintException) {
                Log.d("League", e.toString())
            }
        }
    }

    fun deleteEventFavorite(id: String) {
        doAsync {
            try {

                ctx.database.use {
                    delete(
                        MatchEntity.Event.TABLE_NAME,
                        "(${MatchEntity.Event.ID_EVENT} = {id})",
                        "id" to id
                    )
                }

            } catch (e: SQLiteConstraintException) {
                Log.d("League", e.toString())
            }
        }
    }

//    TEAM
    fun showTeamFavorite(): LiveData<List<ListEntity>> {
        val teams = MutableLiveData<List<ListEntity>>()
        doAsync {

            ctx.database.use {
                val res = select(ListEntity.TABLE_NAME)
                val fav = res.parseList(classParser<ListEntity>())
                teams.postValue(fav)
            }

        }
        return teams
    }

    fun showTeamFavoriteById(id: String): LiveData<Boolean> {
        val team = MutableLiveData<Boolean>()
        doAsync {
            ctx.database.use {
                val res = select(ListEntity.TABLE_NAME)
                    .whereArgs(
                        "(${ListEntity.ID_TEAM} = {id})",
                        "id" to id
                    )
                val fav = res.parseList(classParser<ListEntity>())
                val status = fav.isNotEmpty()
                team.postValue(status)
            }
        }
        return team
    }

    fun insertTeamFavorite(t: ListEntity) {
        doAsync {
            try {

                ctx.database.use {
                    insert(
                        ListEntity.TABLE_NAME,
                        ListEntity.ID_TEAM to t.id,
                        ListEntity.STR_TEAM_BADGE to t.logo,
                        ListEntity.STR_TEAM to t.name
                    )
                }

            } catch (e: SQLiteConstraintException) {
                Log.d("League", e.toString())
            }
        }
    }

    fun deleteTeamFavorite(id: String) {
        doAsync {
            try {

                ctx.database.use {
                    delete(
                        ListEntity.TABLE_NAME,
                        "(${ListEntity.ID_TEAM} = {id})",
                        "id" to id
                    )
                }

            } catch (e: SQLiteConstraintException) {
                Log.d("League", e.toString())
            }
        }
    }

}