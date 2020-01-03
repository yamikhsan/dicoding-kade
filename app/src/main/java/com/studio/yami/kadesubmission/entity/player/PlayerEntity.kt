package com.studio.yami.kadesubmission.entity.player

import com.google.gson.annotations.SerializedName
import com.studio.yami.kadesubmission.entity.ListEntity

object PlayerEntity {

    data class Players(
        @SerializedName("players", alternate = ["player"])
        val players: List<Player>?
    ){

        fun getLists(): List<ListEntity>?{
            val list = mutableListOf<ListEntity>()
            return if(players != null){
                for(player in players){
                    list.add(player.getList())
                }
                list
            }else{
                null
            }
        }

    }

    data class Player(
        val idPlayer: String,
        val strPlayer: String,
        val strCutout: String?,
        val dateBorn: String?,
        val strGender: String?,
        val strPosition: String?,
        val strDescription: String?,
        val strNationality: String?
    ){
        fun getList(): ListEntity {
            val cutout = strCutout ?: "https://www.thesportsdb.com/images/media/player/cutout/5pywp41548775768.png"
            return ListEntity(
                null,
                idPlayer,
                cutout,
                strPlayer
            )
        }
    }

}