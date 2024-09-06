package com.example.firstdescendant.data.user.descendantinfo

data class UserDescendantData(
    val descendant_id: String,
    val descendant_level: Int,
    val descendant_slot_id: String,
    val module: List<Module>,
    val module_capacity: Int,
    val module_max_capacity: Int,
    val ouid: String,
    val user_name: String
)