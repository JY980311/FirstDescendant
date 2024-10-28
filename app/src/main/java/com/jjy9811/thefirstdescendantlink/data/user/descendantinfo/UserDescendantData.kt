package com.jjy9811.thefirstdescendantlink.data.user.descendantinfo

data class UserDescendantData(
    val descendant_id: String,
    val descendant_level: Int,
    val descendant_slot_id: String,
    val module: List<UserModule>,
    val module_capacity: Int,
    val module_max_capacity: Int,
    val ouid: String,
    val user_name: String
)