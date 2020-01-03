package com.studio.yami.kadesubmission.entity.team

import com.studio.yami.kadesubmission.entity.ListEntity

object TeamEntity {

    data class Teams(val teams: List<Team>?){

        fun getLists(): List<ListEntity>{
            val list = mutableListOf<ListEntity>()
            if(teams != null){
                for(team in teams){
                    list.add(team.getList())
                }
            }
            return list
        }
    }

    data class Team(
        val idTeam: String,
        val strTeam: String,
        val strTeamBadge: String?,
        val strAlternate: String?,
        val intFormedYear: String?,
        val strLeague: String?,
        val strStadium: String?,
        val strDescriptionEN: String?
    ){

        fun getList(): ListEntity{
            val badge = strTeamBadge ?: "https://www.thesportsdb.com/images/media/team/badge/uvxuqq1448813372.png"
            return ListEntity(
                null,
                idTeam,
                badge,
                strTeam
            )
        }
    }

}