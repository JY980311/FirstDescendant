package com.example.thefirstdescendantlink.datastore

import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object DataStoreKeys {
    val LAST_BASIC_TIME = longPreferencesKey("last_basic_time")
    val LAST_WEAPON_TIME = longPreferencesKey("last_weapon_time")
    val LAST_REACTOR_TIME = longPreferencesKey("last_reactor_time")
    val LAST_DESCENDANT_TIME = longPreferencesKey("last_descendant_time")
    val LAST_EXTERNAL_TIME = longPreferencesKey("last_external_time")
    val LAST_OUID = stringPreferencesKey("last_ouid")
}

/*
    val OUID_TIMES = stringPreferencesKey("ouid_times")

    val USER_BASIC_INFO = stringPreferencesKey("user_basic_info")

    val USER_WEAPON_DATA = stringPreferencesKey("user_weapon_data")
    val USER_WEAPON_INFO = stringPreferencesKey("user_weapon_info")
    val USER_WEAPON_MODULE = stringPreferencesKey("user_weapon_module")

   val USER_REACTOR_DATA = stringPreferencesKey("user_reactor_data")
    val USER_REACTOR_INFO = stringPreferencesKey("user_reactor_info")
    val USER_REACTOR_ADDITIONAL_STAT = stringPreferencesKey("user_reactor_additional_stat")
    val USER_REACTOR_SKILL_COEFFICIENT = stringPreferencesKey("user_reactor_skill_coefficient")

    val USER_DESCENDANT_DATA = stringPreferencesKey("user_descendant_data")
    val USER_DESCENDANT_INFO = stringPreferencesKey("user_descendant_info")
    val USER_DESCENDANT_MODULE = stringPreferencesKey("user_descendant_module")

    val USER_EXTERNAL_DATA = stringPreferencesKey("user_external_data")
    val USER_EXTERNAL_INFO = stringPreferencesKey("user_external_info")
    */