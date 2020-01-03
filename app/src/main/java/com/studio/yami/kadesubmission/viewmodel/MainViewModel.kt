package com.studio.yami.kadesubmission.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.studio.yami.kadesubmission.entity.ListEntity
import com.studio.yami.kadesubmission.entity.StatisticsEntity
import com.studio.yami.kadesubmission.entity.league.LeagueEntity
import com.studio.yami.kadesubmission.entity.league.MatchEntity
import com.studio.yami.kadesubmission.entity.league.StandingsEntity
import com.studio.yami.kadesubmission.entity.player.PlayerEntity
import com.studio.yami.kadesubmission.entity.team.TeamEntity
import com.studio.yami.kadesubmission.repository.MainRepository

class MainViewModel(private val repo: MainRepository): ViewModel(){

    fun eventNextLeague(id: String): LiveData<List<MatchEntity.Event>> = repo.eventsNextLeague(id)

    fun eventPastLeague(id: String): LiveData<List<MatchEntity.Event>> = repo.eventsPastLeague(id)

    fun lookUpLeague(id: String): LiveData<LeagueEntity.League> = repo.lookUpLeague(id)

    fun lookUpEvent(id: String): LiveData<StatisticsEntity.Event> = repo.lookUpEvent(id)

    fun searchEvents(id: String): LiveData<MatchEntity.Events> = repo.searchEvents(id)

    fun lookUpAllTeam(id: String): LiveData<TeamEntity.Teams> = repo.lookUpAllTeam(id)

    fun lookUpAllPlayer(id: String): LiveData<PlayerEntity.Players> = repo.lookUpAllPlayer(id)

    fun lookUpTeam(id: String): LiveData<TeamEntity.Teams> = repo.lookUpTeam(id)

    fun lookUpPlayer(id: String): LiveData<PlayerEntity.Players> = repo.lookUpPlayer(id)

    fun lookUpTable(id: String): LiveData<StandingsEntity.Tables> = repo.lookUpTable(id)

    fun searchTeam(id: String): LiveData<TeamEntity.Teams> = repo.searchTeam(id)

//  EVENT
    fun showEventFavorite(): LiveData<List<MatchEntity.Event>?> = repo.showEventFavorite()

    fun showEventFavoriteById(id: String): LiveData<Boolean> = repo.showEventFavoriteById(id)

    fun insertEventFavorite(e: MatchEntity.Event){
        repo.insertEventFavorite(e)
    }

    fun deleteEventFavorite(id: String){
        repo.deleteEventFavorite(id)
    }

//    TEAM
    fun showTeamFavorite(): LiveData<List<ListEntity>> = repo.showTeamFavorite()

    fun showTeamFavoriteById(id: String): LiveData<Boolean> = repo.showTeamFavoriteById(id)

    fun insertTeamFavorite(t: ListEntity) {
        repo.insertTeamFavorite(t)
    }

    fun deleteTeamFavorite(id: String) {
        repo.deleteTeamFavorite(id)
    }

}