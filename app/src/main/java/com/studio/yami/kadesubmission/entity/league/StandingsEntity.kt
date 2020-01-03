package com.studio.yami.kadesubmission.entity.league

object StandingsEntity {

    data class Tables(val table: List<Table>)

    data class Table(
        val teamId: String,
        val name: String,
        val played: Int,
        val goalsdifference: Int,
        val win: Int,
        val draw: Int,
        val loss: Int,
        val total: Int
    )
}