package com.studio.yami.kadesubmission.dummydata

import com.studio.yami.kadesubmission.entity.league.LeagueEntity
import com.studio.yami.kadesubmission.entity.league.MatchEntity
import com.studio.yami.kadesubmission.entity.StatisticsEntity

class DummyData {

    fun fakeListEvent(): List<MatchEntity.Event>{
        return mutableListOf(
            MatchEntity.Event(1, " ", " ", " ", " ", " ", " ", " "," ", " "),
            MatchEntity.Event(2, " ", " ", " ", " ", " ", " ", " "," ", " "),
            MatchEntity.Event(3, " ", " ", " ", " ", " ", " ", " "," ", " ")
        )
    }

    fun fakeDetail(): LeagueEntity.League{
        return LeagueEntity.League(" ", " ", " "," "," ")
    }

    fun fakeStatistics(): StatisticsEntity.Event{
        return StatisticsEntity.Event(" ", " ", " ",   " "," ", " "," "," "," "," "," ")
    }
}