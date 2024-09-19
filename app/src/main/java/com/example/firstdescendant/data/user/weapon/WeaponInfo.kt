package com.example.firstdescendant.data.user.weapon

/**
 * @param weapon_name 무기 이름
 * @param weapon_tier 무기 등급
 * @param weapon_rounds_type 무기 탄종
 * @param image_url 무기 이미지 URL
 */
data class WeaponInfo(
    val weapon_name : String,
    val weapon_tier : String,
    val weapon_rounds_type : String,
    val image_url : String
)