package com.example.firstdescendant.data.meta.reactor

data class ReactorSkillPower(
    val enchant_effect: List<EnchantEffect>,
    val level: Int,
    val skill_atk_power: Double,
    val skill_power_coefficient: List<Any>,
    val sub_skill_atk_power: Double
)