package com.example.firstdescendant.data.descendant

data class DescendantDataItem(
    val descendant_id: String,
    val descendant_image_url: String,
    val descendant_name: String,
    val descendant_skill: List<DescendantSkill>,
    val descendant_stat: List<DescendantStat>
)