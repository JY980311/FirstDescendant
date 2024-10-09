package com.example.firstdescendant.data.user.reactor

data class UserReactor(
    val ouid: String,
    val reactor_additional_stat: List<ReactorAdditionalStat>,
    val reactor_enchant_level: Int,
    var reactor_id: String,
    val reactor_level: Int,
    val reactor_slot_id: String,
    val user_name: String
)