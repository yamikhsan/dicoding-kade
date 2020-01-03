package com.studio.yami.kadesubmission.entity.league

import android.annotation.SuppressLint
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.text.SimpleDateFormat

object MatchEntity{

    data class Events(
        @SerializedName("events", alternate = ["event"])
        val events: List<Event>?
    )

    @Parcelize
    data class Event (
        val id: Long?,
        val idEvent: String,
        val strLeague: String,
        val dateEvent: String,
        val idHomeTeam: String,
        val idAwayTeam: String,
        val strHomeTeam: String,
        val strAwayTeam: String,
        val intHomeScore: String?,
        val intAwayScore: String?
    ): Parcelable {

        companion object {
            const val ID_: String = "ID_"
            const val TABLE_NAME: String = "EVENT_TABLE"
            const val ID_EVENT: String = "ID_EVENT"
            const val STR_LEAGUE: String = "STR_LEAGUE"
            const val DATE_EVENT: String = "DATE_EVENT"
            const val ID_HOME_TEAM: String = "ID_HOME_TEAM"
            const val ID_AWAY_TEAM: String = "ID_AWAY_TEAM"
            const val STR_HOME_TEAM: String = "STR_HOME_TEAM"
            const val STR_AWAY_TEAM: String = "STR_AWAY_TEAM"
            const val INT_HOME_SCORE: String = "INT_HOME_SCORE"
            const val INT_AWAY_SCORE: String = "INT_AWAY_SCORE"
        }

        fun getScore(): String {
            var res = "vs"
            if(intHomeScore != null && intAwayScore != null){
                res = "$intHomeScore vs $intAwayScore"
            }
            return res
        }

        @SuppressLint("SimpleDateFormat")
        fun getDate(): String {
            val date = SimpleDateFormat("yyyy-MM-dd").parse(dateEvent)
            val format = SimpleDateFormat("dd MMM yyyy")
            return format.format(date)
        }
    }

}

