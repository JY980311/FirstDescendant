package com.example.firstdescendant.data.user.basicinfo

data class UserBasicData(
    val game_language: String,
    val mastery_rank_exp: Int,
    val mastery_rank_level: Int,
    val os_language: String,
    val ouid: String,
    val platform_type: String,
    val title_prefix_id: Any?,
    val title_suffix_id: Any?,
    val user_name: String
)