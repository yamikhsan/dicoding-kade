package com.studio.yami.kadesubmission.entity.league

object LeagueEntity {

    data class Leagues(val leagues: List<League>)

    data class League(
        val strDescriptionEN: String,
        val strSport: String,
        val intFormedYear: String,
        val strGender: String,
        val strCountry: String
    )

}