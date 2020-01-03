package com.studio.yami.kadesubmission.entity

object StatisticsEntity{

    data class Events(val events: List<Event>)

    data class Event(
        val idEvent: String,
        val strHomeGoalDetails: String?,
        val strAwayGoalDetails: String?,
        val intHomeScore: String,
        val intAwayScore: String,
        val strHomeYellowCards: String?,
        val strAwayYellowCards: String?,
        val strHomeRedCards: String?,
        val strAwayRedCards: String?,
        val strHomeFormation: String?,
        val strAwayFormation: String?
    )

}
