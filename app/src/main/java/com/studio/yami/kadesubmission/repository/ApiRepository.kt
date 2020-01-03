package com.studio.yami.kadesubmission.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.studio.yami.kadesubmission.BuildConfig
import com.studio.yami.kadesubmission.entity.StatisticsEntity
import com.studio.yami.kadesubmission.entity.league.LeagueEntity
import com.studio.yami.kadesubmission.entity.league.MatchEntity
import com.studio.yami.kadesubmission.entity.league.StandingsEntity
import com.studio.yami.kadesubmission.entity.player.PlayerEntity
import com.studio.yami.kadesubmission.entity.team.TeamEntity
import com.studio.yami.kadesubmission.sourcedata.api.apiRequest
import com.studio.yami.kadesubmission.sourcedata.api.myUrl
import com.studio.yami.kadesubmission.utils.EspressoIdlingResource
import org.jetbrains.anko.doAsync

class ApiRepository {

    companion object{
        const val pId = "id"
    }
    private val pE = "e"
    private val pL = "l"
    private val pT = "t"

    fun eventsNextLeague(id: String): LiveData<List<MatchEntity.Event>>{
        val events = MutableLiveData<List<MatchEntity.Event>>()
        doAsync {
            val res = Gson().fromJson(
                apiRequest(
                    myUrl(BuildConfig.EVENTS_NEXT_LEAGUE, pId, id)
                ), MatchEntity.Events::class.java
            )
            events.postValue(res.events)
        }
        return events
    }

    fun eventsPastLeague(id: String): LiveData<List<MatchEntity.Event>>{
        val events = MutableLiveData<List<MatchEntity.Event>>()
        doAsync {
            val res = Gson().fromJson(
                apiRequest(
                    myUrl(BuildConfig.EVENTS_PAST_LEAGUE, pId, id)
                ), MatchEntity.Events::class.java
            )
            events.postValue(res.events)
        }
        return events
    }

    fun lookUpLeague(id: String): LiveData<LeagueEntity.League> {
        val league = MutableLiveData<LeagueEntity.League>()
        doAsync {
            val res = Gson().fromJson(
                apiRequest(
                    myUrl(BuildConfig.LOOKUP_LEAGUE, pId, id)
                ), LeagueEntity.Leagues::class.java
            )
            league.postValue(res.leagues[0])
        }
        return league
    }

    fun lookUpEvent(id: String): LiveData<StatisticsEntity.Event>{
        val event = MutableLiveData<StatisticsEntity.Event>()
        doAsync {
            val res = Gson().fromJson(
                apiRequest(
                    myUrl(BuildConfig.LOOKUP_EVENT, pId, id)
                ), StatisticsEntity.Events::class.java
            )
            event.postValue(res.events[0])
        }
        return event
    }

    fun searchEvents(id: String): LiveData<MatchEntity.Events>{
        val event = MutableLiveData<MatchEntity.Events>()
        EspressoIdlingResource.increment()
        doAsync {
            val res = Gson().fromJson(
                apiRequest(
                    myUrl(BuildConfig.SEARCH_EVENTS, pE, id)
                ), MatchEntity.Events::class.java
            )
            event.postValue(res)
            EspressoIdlingResource.decrement()
        }
        return event
    }

    fun lookUpAllTeam(id: String): MutableLiveData<TeamEntity.Teams>{
        val team = MutableLiveData<TeamEntity.Teams>()

        doAsync {
            val res = Gson().fromJson(
                apiRequest(
                    myUrl(BuildConfig.LOOKUP_ALL_TEAMS, pId, id)
                ), TeamEntity.Teams::class.java
            )
            team.postValue(res)
        }
        return team
    }

    fun lookUpAllPlayer(id: String): MutableLiveData<PlayerEntity.Players>{
        val team = MutableLiveData<PlayerEntity.Players>()
        doAsync {
            val res = Gson().fromJson(
                apiRequest(
                    myUrl(BuildConfig.LOOKUP_ALL_PLAYERS, pId, id)
                ), PlayerEntity.Players::class.java
            )
            team.postValue(res)
        }
        return team
    }

    fun lookUpTeam(id: String): MutableLiveData<TeamEntity.Teams>{
        val team = MutableLiveData<TeamEntity.Teams>()
        doAsync {
            val res = Gson().fromJson(
                apiRequest(
                    myUrl(BuildConfig.LOOKUP_TEAM, pId, id)
                ), TeamEntity.Teams::class.java
            )
            team.postValue(res)
        }
        return team
    }

    fun lookUpPlayer(id: String): MutableLiveData<PlayerEntity.Players>{
        val player = MutableLiveData<PlayerEntity.Players>()
        doAsync {
            val res = Gson().fromJson(
                apiRequest(
                    myUrl(BuildConfig.LOOKUP_PLAYER, pId, id)
                ), PlayerEntity.Players::class.java
            )
            player.postValue(res)
        }
        return player
    }

    fun lookUpTable(id: String): MutableLiveData<StandingsEntity.Tables>{
        val team = MutableLiveData<StandingsEntity.Tables>()
        doAsync {
            val res = Gson().fromJson(
                apiRequest(
                    myUrl(BuildConfig.LOOKUP_TABLE, pL, id)
                ), StandingsEntity.Tables::class.java
            )
            team.postValue(res)
        }
        return team
    }

    fun searchTeam(id: String): MutableLiveData<TeamEntity.Teams>{
        val team = MutableLiveData<TeamEntity.Teams>()
        doAsync {
            val res = Gson().fromJson(
                apiRequest(
                    myUrl(BuildConfig.SEARCH_TEAMS, pT, id)
                ), TeamEntity.Teams::class.java
            )
            team.postValue(res)
        }
        return team
    }

}