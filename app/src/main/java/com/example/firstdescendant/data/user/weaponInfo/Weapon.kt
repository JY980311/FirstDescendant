package com.example.firstdescendant.data.user.weaponInfo

data class Weapon(
    val module: List<Module>,
    val module_capacity: Int,
    val module_max_capacity: Int,
    val perk_ability_enchant_level: Int,
    val weapon_additional_stat: List<WeaponAdditionalStat>,
    val weapon_id: String,
    val weapon_level: Int,
    val weapon_slot_id: String
)