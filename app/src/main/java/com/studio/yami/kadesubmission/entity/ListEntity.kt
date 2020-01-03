package com.studio.yami.kadesubmission.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ListEntity(val id_: Long?, val id: String, val logo: String, val name: String): Parcelable {
    companion object {
        const val ID_: String = "ID_"
        const val TABLE_NAME: String = "TEAM_TABLE"
        const val ID_TEAM: String = "ID_TEAM"
        const val STR_TEAM_BADGE: String = "STR_TEAM_BADGE"
        const val STR_TEAM: String = "STR_TEAM"
    }
}