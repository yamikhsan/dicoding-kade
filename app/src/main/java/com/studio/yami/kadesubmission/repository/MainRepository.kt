package com.studio.yami.kadesubmission.repository

import android.arch.lifecycle.LiveData
import com.studio.yami.kadesubmission.entity.ListEntity
import com.studio.yami.kadesubmission.entity.StatisticsEntity
import com.studio.yami.kadesubmission.entity.league.LeagueEntity
import com.studio.yami.kadesubmission.entity.league.MatchEntity
import com.studio.yami.kadesubmission.entity.league.StandingsEntity
import com.studio.yami.kadesubmission.entity.player.PlayerEntity
import com.studio.yami.kadesubmission.entity.team.TeamEntity

class MainRepository(private val server: ApiRepository, private val local: LocalRepository) {

    fun eventsNextLeague(id: String): LiveData<List<MatchEntity.Event>> = server.eventsNextLeague(id)

    fun eventsPastLeague(id: String): LiveData<List<MatchEntity.Event>> = server.eventsPastLeague(id)

    fun lookUpLeague(id: String): LiveData<LeagueEntity.League> = server.lookUpLeague(id)

    fun lookUpEvent(id: String): LiveData<StatisticsEntity.Event> = server.lookUpEvent(id)

    fun searchEvents(id: String): LiveData<MatchEntity.Events> = server.searchEvents(id)

    fun lookUpAllTeam(id: String): LiveData<TeamEntity.Teams> = server.lookUpAllTeam(id)

    fun lookUpAllPlayer(id: String): LiveData<PlayerEntity.Players> = server.lookUpAllPlayer(id)

    fun lookUpTeam(id: String): LiveData<TeamEntity.Teams> = server.lookUpTeam(id)

    fun lookUpPlayer(id: String): LiveData<PlayerEntity.Players> = server.lookUpPlayer(id)

    fun lookUpTable(id: String): LiveData<StandingsEntity.Tables> = server.lookUpTable(id)

    fun searchTeam(id: String): LiveData<TeamEntity.Teams> = server.searchTeam(id)

//  EVENT
    fun showEventFavorite(): LiveData<List<MatchEntity.Event>?> = local.showEventFavorite()

    fun showEventFavoriteById(id: String): LiveData<Boolean> = local.showEventFavoriteById(id)

    fun insertEventFavorite(e: MatchEntity.Event){
        local.insertEventFavorite(e)
    }

    fun deleteEventFavorite(id: String){
        local.deleteEventFavorite(id)
    }

//    TEAM
    fun showTeamFavorite(): LiveData<List<ListEntity>> = local.showTeamFavorite()

    fun showTeamFavoriteById(id: String): LiveData<Boolean> = local.showTeamFavoriteById(id)

    fun insertTeamFavorite(t: ListEntity) {
        local.insertTeamFavorite(t)
    }

    fun deleteTeamFavorite(id: String) {
        local.deleteTeamFavorite(id)
    }

}