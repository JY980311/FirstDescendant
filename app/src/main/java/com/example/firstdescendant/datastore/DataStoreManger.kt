package com.example.firstdescendant.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.firstdescendant.data.user.basicinfo.UserBasicData
import com.example.firstdescendant.data.user.module.UserWeaponModuleInfo
import com.example.firstdescendant.data.user.weapon.UserWeaponData
import com.example.firstdescendant.data.user.weapon.UserWeaponInfo
import com.example.firstdescendant.data.user.weapon.UserWeaponModule
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import java.util.concurrent.TimeUnit


val Context.dataStore by preferencesDataStore(name = "app_preferences")

class DataStoreManger(private val context: Context) {

    suspend fun saveOuid(ouid: String) {
        context.dataStore.edit { preferences ->
            preferences[DataStoreKeys.LAST_OUID] = ouid
        }
    }

    val ouid: Flow<String> = context.dataStore.data.map { preferences ->
       preferences[DataStoreKeys.LAST_OUID] ?: ""
    }

    suspend fun saveLastBasicTime(time: Long) {
        context.dataStore.edit { preferences ->
            preferences[DataStoreKeys.LAST_BASIC_TIME] = time
        }
    }

    val lastBasicTime: Flow<Long> = context.dataStore.data.map { preferences ->
        preferences[DataStoreKeys.LAST_BASIC_TIME] ?: 0L
    }

    suspend fun saveLastWeaponTime(time: Long) {
        context.dataStore.edit { preferences ->
            preferences[DataStoreKeys.LAST_WEAPON_TIME] = time
        }
    }

    val lastWeaponTime: Flow<Long> = context.dataStore.data.map { preferences ->
        preferences[DataStoreKeys.LAST_WEAPON_TIME] ?: 0L
    }

    suspend fun saveLastReactorTime(time: Long) {
        context.dataStore.edit { preferences ->
            preferences[DataStoreKeys.LAST_REACTOR_TIME] = time
        }
    }

    val lastReactorTime: Flow<Long> = context.dataStore.data.map { preferences ->
        preferences[DataStoreKeys.LAST_REACTOR_TIME] ?: 0L
    }

    suspend fun saveLastDescendantTime(time: Long) {
        context.dataStore.edit { preferences ->
            preferences[DataStoreKeys.LAST_DESCENDANT_TIME] = time
        }
    }

    val lastDescendantTime: Flow<Long> = context.dataStore.data.map { preferences ->
        preferences[DataStoreKeys.LAST_DESCENDANT_TIME] ?: 0L
    }

    suspend fun saveLastExternalTime(time: Long) {
        context.dataStore.edit { preferences ->
            preferences[DataStoreKeys.LAST_EXTERNAL_TIME] = time
        }
    }

    val lastExternalTime: Flow<Long> = context.dataStore.data.map { preferences ->
        preferences[DataStoreKeys.LAST_EXTERNAL_TIME] ?: 0L
    }
}

/*
    suspend fun getOuidTimes(): Map<String, Long> {
        val ouidTimesString = context.dataStore.data.map { preferences ->
            preferences[DataStoreKeys.OUID_TIMES] ?: "{}"
        }.first()

        val type = object : TypeToken<Map<String, Long>>() {}.type
        return gson.fromJson(ouidTimesString, type)
    }

    suspend fun saveOuidTimes(ouidTimes: Map<String, Long>) {
        val ouidTimesString = gson.toJson(ouidTimes)
        context.dataStore.edit { preferences ->
            preferences[DataStoreKeys.OUID_TIMES] = ouidTimesString

        }
    }


    suspend fun addOuidTime(ouid: String, time: Long) {
        val ouidTimes = getOuidTimes().toMutableMap()
        ouidTimes[ouid] = time
        saveOuidTimes(ouidTimes)
    }

    suspend fun removeOldOuid() {
        val currentTime = System.currentTimeMillis()
        val ouidTimes = getOuidTimes().toMutableMap() // toMutableMap()은 Map을 MutableMap으로 변환하는 함수 -> 예를 들어 Map<String, Int>를 MutableMap<String, Int>로 변환

        ouidTimes.entries.removeAll { entry ->
            currentTime - entry.value > TimeUnit.MINUTES.toMillis(5)
        }

        saveOuidTimes(ouidTimes)
    }

    suspend fun saveUserBasicData(basicInfo: UserBasicData) {
        val basicInfoJson = gson.toJson(basicInfo)
        context.dataStore.edit {
            it[DataStoreKeys.USER_BASIC_INFO] = basicInfoJson
        }
    }

    suspend fun getUserBasicData(): UserBasicData? {
        val basicInfoJson = context.dataStore.data.map { it ->
            it[DataStoreKeys.USER_BASIC_INFO]
        }.firstOrNull()

        return basicInfoJson?.let {
            gson.fromJson(it, UserBasicData::class.java)
        }
    }

    suspend fun saveWeaponData(weaponInfo: UserWeaponData) {
        val weaponInfoJson = gson.toJson(weaponInfo)
        context.dataStore.edit {
            it[DataStoreKeys.USER_WEAPON_DATA] = weaponInfoJson
        }
    }


    suspend fun getWeaponData():UserWeaponData? {
        val weaponInfoJson = context.dataStore.data.map { it ->
            it[DataStoreKeys.USER_WEAPON_DATA]
        }.firstOrNull()

        return weaponInfoJson?.let { it ->
            gson.fromJson(it, UserWeaponData::class.java)
        }
    }

    suspend fun saveWeaponInfo(weaponInfo: List<UserWeaponInfo>) {
        val weaponInfoJson = gson.toJson(weaponInfo)
        context.dataStore.edit {
            it[DataStoreKeys.USER_WEAPON_INFO] = weaponInfoJson
        }
    }

    suspend fun getWeaponInfo(): List<UserWeaponInfo> {
        val weaponInfoJson = context.dataStore.data.map { preferences ->
            preferences[DataStoreKeys.USER_WEAPON_INFO]
        }.firstOrNull()

        return weaponInfoJson?.let {
            val type = object : TypeToken<List<UserWeaponInfo>>() {}.type
            gson.fromJson(it, type)
        } ?: emptyList() // null이면 빈 리스트 반환
    }

    // UserWeaponModuleInfo 리스트 저장 및 불러오기
    suspend fun saveWeaponModule(weaponModule: List<UserWeaponModuleInfo>) {
        val weaponInfoJson = gson.toJson(weaponModule)
        context.dataStore.edit {
            it[DataStoreKeys.USER_WEAPON_MODULE] = weaponInfoJson
        }
    }

    suspend fun getWeaponModule(): List<UserWeaponModuleInfo>? {
        val weaponModuleJson = context.dataStore.data.map { preferences ->
            preferences[DataStoreKeys.USER_WEAPON_MODULE]
        }.firstOrNull()

        return weaponModuleJson?.let {
            val type = object : TypeToken<List<UserWeaponModuleInfo>>() {}.type
            gson.fromJson(it, type)
        } ?: emptyList() // null이면 빈 리스트 반환
    }
*/
