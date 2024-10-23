package com.example.thefirstdescendantlink.data.user.weapon

data class UserWeapon(
    val module: List<UserWeaponModule>,
    val module_capacity: Int,
    val module_max_capacity: Int,
    val perk_ability_enchant_level: Int?,
    val weapon_additional_stat: List<UserWeaponAdditionalStat>?,
    val weapon_id: Int,
    val weapon_level: Int,
    val weapon_slot_id: Int
)