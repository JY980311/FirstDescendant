package com.jjy9811.thefirstdescendantlink.data.user.reactor

data class UserReactorData(
    val ouid: String,
    val reactor_additional_stat: List<UserReactorAdditionalStat>,
    val reactor_enchant_level: Int,
    var reactor_id: String,
    val reactor_level: Int,
    val reactor_slot_id: String,
    val user_name: String
)