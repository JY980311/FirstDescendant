package com.example.firstdescendant.data.meta.reactor

data class ReactorDataItem(
    val image_url: String,
    val optimized_condition_type: String?,
    val reactor_id: String,
    val reactor_name: String,
    val reactor_skill_power: List<ReactorSkillPower>,
    val reactor_tier: String
)