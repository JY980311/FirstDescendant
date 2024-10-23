package com.example.firstdescendant.screen.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firstdescendant.data.user.basicinfo.UserBasicData
import com.example.firstdescendant.data.user.descendantinfo.UserDescendantData
import com.example.firstdescendant.data.user.descendantinfo.UserDescendantName
import com.example.firstdescendant.data.user.external.UserExternalData
import com.example.firstdescendant.data.user.external.UserExternalName
import com.example.firstdescendant.data.user.external.UserExternalStatName
import com.example.firstdescendant.data.user.external.UserExternalStatValue
import com.example.firstdescendant.data.user.module.UserDescendantModuleInfo
import com.example.firstdescendant.data.user.module.UserWeaponModuleInfo
import com.example.firstdescendant.data.user.ouid.UserOuid
import com.example.firstdescendant.data.user.reactor.ReactorSkillCoefficient
import com.example.firstdescendant.data.user.reactor.UserReactorData
import com.example.firstdescendant.data.user.reactor.UserReactorInfo
import com.example.firstdescendant.data.user.reactor.UserReactorSkillPower
import com.example.firstdescendant.data.user.weapon.UserWeaponData
import com.example.firstdescendant.data.user.weapon.UserWeaponInfo
import com.example.firstdescendant.datastore.DataStoreManger
import com.example.firstdescendant.navigation.DESCENDANTINFOSCREEN_ROUTE
import com.example.firstdescendant.navigation.EXTERNALINFOSCREEN_ROUTE
import com.example.firstdescendant.navigation.REACTORINFOSCREEN_ROUTE
import com.example.firstdescendant.navigation.WEAPONINFOSCREEN_ROUTE
import com.example.firstdescendant.network.RetrofitClient
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.util.concurrent.TimeUnit

class UserScreenViewModel(context: Context) : ViewModel() {

    private val dataStoreManager = DataStoreManger(context)

    /** OUID */
    private val _test = MutableStateFlow(UserOuid(""))
    val test = _test.asStateFlow()

    /** 사용자 기본 정보 */
    private val _user_basicInfo = MutableStateFlow(UserBasicData("", 0, 0, "", "", "", "", "", ""))
    val basicInfo = _user_basicInfo.asStateFlow()

    /** 사용자 계승자 정보 */
    private val _user_descendantInfo =
        MutableStateFlow(UserDescendantData("", 0, "", emptyList(), 0, 0, "", ""))
    val userDescendantInfo = _user_descendantInfo.asStateFlow()

    /** 계승자 필요한 데이터 받아오는 정보 (계승자 이름, 계승자 이미지) */
    private val _user_descendant = MutableStateFlow<UserDescendantName>(UserDescendantName("", ""))
    val userDescendant = _user_descendant.asStateFlow()

    /** 계승자 모듈 정보 */
    private val _user_DescendantModule =
        MutableStateFlow<List<UserDescendantModuleInfo>>(emptyList())
    val userDescendantModule = _user_DescendantModule.asStateFlow()

    /** 무기 모듈 정보 */
    private val _user_WeaponModule = MutableStateFlow<List<UserWeaponModuleInfo>>(emptyList())
    val userWeaponModule = _user_WeaponModule.asStateFlow()

    /** 사용자 무기 정보 */
    private val _user_weaponInfo = MutableStateFlow(UserWeaponData("", "", emptyList()))
    val userWeaponInfo = _user_weaponInfo.asStateFlow()

    /** 무기 필요한 데이터 받아오는 정보 (무기 고유 id, 한글 이름, 이미지 주소) */
    private val _user_weapon = MutableStateFlow<List<UserWeaponInfo>>(emptyList())
    val userWeapon = _user_weapon.asStateFlow()

    /** 사용자 반응로 정보 */
    private val _user_reactorInfo =
        MutableStateFlow(UserReactorData("", emptyList(), 0, "", 0, "", ""))
    val userReactorInfo = _user_reactorInfo.asStateFlow()

    /** 사용자 반응로 세부 정보 */
    private val _user_reactor = MutableStateFlow(UserReactorInfo(0, "", "", "", ""))
    val userReactorImage = _user_reactor.asStateFlow()

    /** 사용자 반응로 스킬 및 보조 공격 정보 */
    private val _user_reactor_SkillPower =
        MutableStateFlow(UserReactorSkillPower(0, 0, 0, 0.0, 0.0))
    val userReactorSkillPower = _user_reactor_SkillPower.asStateFlow()

    /** 반응로 추가 스텟 */
    private val _reactor_coefficient = MutableStateFlow(emptyList<ReactorSkillCoefficient>())
    val reactorCoefficient = _reactor_coefficient.asStateFlow()

    /** 사용자 외부 구성 요소 정보 */
    private val _user_externalInfo = MutableStateFlow(UserExternalData(emptyList(), "", ""))
    val userExternalInfo = _user_externalInfo.asStateFlow()

    /** 외장부품 필요한 데이터 받아오는 정보 (한글 이름, 이미지 주소) */
    private val _user_external = MutableStateFlow<List<UserExternalName>>(emptyList())
    val userExternal = _user_external.asStateFlow()

    private val _user_external_value = MutableStateFlow(emptyList<UserExternalStatValue>())
    val userExternalValue = _user_external_value.asStateFlow()

    private val _user_external_stat = MutableStateFlow(emptyList<UserExternalStatName>())
    val userExternalStat = _user_external_stat.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _nextScreenRoute = MutableStateFlow<String?>(null)
    val nextScreenRoute = _nextScreenRoute.asStateFlow()

    /** 사용자 입력 텍스트 */
    private val _textField = MutableStateFlow("")
    val textField = _textField.asStateFlow()

    /** 에러 메시지 */
    private val _errorMessage = MutableStateFlow("")
    val errorMessage = _errorMessage.asStateFlow()

    fun getText(newText: String) {
        _textField.update {
            newText
        }
    }

    fun getOuid() {
        viewModelScope.launch {
            _isLoading.value = true
            val apiService = RetrofitClient.getDecendantApi()
            try {
                val apiResponse: UserOuid = apiService.getUserOuid(textField.value)
                _test.update {
                    it.copy(ouid = apiResponse.ouid)
                }

                dataStoreManager.saveOuid(test.value.ouid)

                getUserBasicInfo()

                Log.d("ViewModel - getOuid", "ouid: ${test.value.ouid}")
            } catch (e: Exception) {
                _test.update {
                    it.copy(ouid = "")
                }
                when {
                    e.message?.contains("400") == true -> {
                        _errorMessage.value = "사용자를 찾을 수 없습니다."
                    }

                    e.message?.contains("429") == true -> {
                        _errorMessage.value = "요청이 너무 많습니다."
                    }

                    e.message?.contains("500") == true -> {
                        _errorMessage.value = "내부 서버에서 오류가 발생하였습니다."
                    }

                    else -> {
                        _errorMessage.value = "알 수 없는 오류가 발생하였습니다."
                    }
                }
            }
        }
    }

    fun getUserBasicInfo() {
        viewModelScope.launch {
            val apiService = RetrofitClient.getDecendantApi()
            val currentTime = System.currentTimeMillis()
            val currentOuid = test.value.ouid

            Log.d("getBasicInfo", "ouid: $currentOuid")

            if (currentOuid.isNotEmpty() && currentOuid != "test") {
                val lastBasicTime = dataStoreManager.lastBasicTime.first()
                Log.d("getBasicInfoTime", "lastBasicTime: $lastBasicTime")

                if (_user_basicInfo.value.ouid == currentOuid && currentTime - lastBasicTime < TimeUnit.MINUTES.toMillis(
                        5
                    )
                ) {
                    Log.d("getBasicInfoTime", "캐시된 데이터 사용: $currentOuid")
                    _isLoading.value = false
                    return@launch
                }
            }

            try {
                val apiResponse = apiService.getUserInfo(currentOuid)
                _user_basicInfo.value = apiResponse
                dataStoreManager.saveLastBasicTime(currentTime) // 호출 시간 저장

                Log.d("ViewModel - getBasicInfo", "기본 정보: ${basicInfo.value}")

                val minimumTime = System.currentTimeMillis() - currentTime
                if (minimumTime < 500) {
                    delay(500 - minimumTime)
                }
            } catch (e: Exception) {
                Log.e("ViewModel - getBasicInfo[ERROR]", "error: ${e.message}", e)
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun getUserWeaponInfo() {
        viewModelScope.launch {
            _isLoading.value = true
            val apiService = RetrofitClient.getDecendantApi()
            val currentTime = System.currentTimeMillis()
            val currentOuid = test.value.ouid

            if (currentOuid.isNotEmpty() && currentOuid != "test") {
                val lastWeaponTime = dataStoreManager.lastWeaponTime.first()

                if (_user_weaponInfo.value.ouid == currentOuid && currentTime - lastWeaponTime < TimeUnit.MINUTES.toMillis(
                        5
                    )
                ) {
                    Log.d("getWeaponInfoTime", "이미 사용된 oui: $currentOuid")
                    _isLoading.value = false
                    _nextScreenRoute.value = WEAPONINFOSCREEN_ROUTE
                    return@launch
                }
            }

            try {
                val apiResponse = apiService.getUserWeaponInfo("ko", currentOuid)

                _user_weaponInfo.value = apiResponse
                Log.d(
                    "ViewModel - getUserWeaponInfo",
                    "userWeaponInfo: ${userWeaponInfo.value}"
                )
                getWeaponInfo()
                getWeaponModule()
                dataStoreManager.saveLastWeaponTime(currentTime)

                val minimumTime = System.currentTimeMillis() - currentTime

                if (minimumTime < 500) {
                    delay(500 - minimumTime)
                } else {
                    Log.e(
                        "ViewModel - getUserWeaponInfo",
                        "getUserWeaponInfo called with invalid ouid: ${currentOuid}"
                    )
                }
            } catch (e: HttpException) {
                Log.e("ViewModel - getUserWeaponInfo[ERROR]", "error: ${e.message}", e)
                val errorBody = e.response()?.errorBody()?.string()
                Log.e("API_ERROR", "Error body: $errorBody")
            }
        }
    }

    private fun getWeaponInfo() {
        viewModelScope.launch {
            val apiService = RetrofitClient.getSupabaseApiService()
            try {
                val weaponIDs =
                    _user_weaponInfo.value.weapon.sortedBy { it.weapon_slot_id }
                        .map { it.weapon_id }
                val apiResponse = apiService.getUserWeaponNameImage(
                    select = "main_weapon_id,weapon_name,image_url,weapon_tier",
                    main_weapon_id = "in.(${weaponIDs.joinToString(",")})"
                )

                val sortedApiResponse = weaponIDs.map { id ->
                    apiResponse.find { it.main_weapon_id == id } // find 함수는 반환이 없다면 null을 반환
                }

                _user_weapon.value = sortedApiResponse.filterNotNull() // 그래서 밑에서 null 제거

                Log.d("ViewModel - getWeaponInfo", "user_weapon: ${userWeapon.value}")
            } catch (e: Exception) {
                Log.e("ViewModel - getWeaponInfo[ERROR]", "error: ${e.message}", e)
            }
        }
    }

    private fun getWeaponModule() {
        viewModelScope.launch {
            val apiService = RetrofitClient.getSupabaseApiService()
            try {
                val moduleIDs =
                    _user_weaponInfo.value.weapon.map { weapon -> weapon.module.map { module -> module.module_id } }
                Log.d("ViewModel - getWeaponModule", "moduleIDs: $moduleIDs")
                val apiResponse = apiService.getUserModuleWeapon(
                    select = "main_module_id,module_name,module_tier,image_url",
                    main_module_id = "in.(${moduleIDs.flatten().joinToString(",")})"
                )

                _user_WeaponModule.value = apiResponse
            } catch (e: Exception) {
                Log.e("ViewModel - getWeaponModule[ERROR]", "error: ${e.message}", e)
            } finally {
                _isLoading.value = false
                _nextScreenRoute.value = WEAPONINFOSCREEN_ROUTE
            }
        }
    }

    /** 유저 반응로 정보 조회 */
    fun getUserReactorInfo() {
        viewModelScope.launch {
            _isLoading.value = true
            val apiService = RetrofitClient.getDecendantApi()
            val currentTime = System.currentTimeMillis()
            val currentOuid = test.value.ouid
            if (currentOuid.isNotEmpty() && currentOuid != "test") {
                val lastReactorTime = dataStoreManager.lastReactorTime.first()
                if (_user_reactorInfo.value.ouid == currentOuid && currentTime - lastReactorTime < TimeUnit.MINUTES.toMillis(
                        5
                    )
                ) {
                    Log.d("getReactorInfoTime", "이미 사용된 oui: $currentOuid")
                    _isLoading.value = false
                    _nextScreenRoute.value = REACTORINFOSCREEN_ROUTE
                    return@launch
                }
            }

            try {
                val apiResponse = apiService.getUserReactorInfo("ko", currentOuid)
                _user_reactorInfo.value = apiResponse

                getReactorInfo(_user_reactorInfo.value.reactor_id)
                getReactorSkillPower(_user_reactorInfo.value.reactor_id)
                dataStoreManager.saveLastReactorTime(currentTime)

                val minimumTime = System.currentTimeMillis() - currentTime
                if (minimumTime < 500) {
                    delay(500 - minimumTime)
                } else {
                    Log.d(
                        "ViewModel - getUserReactorInfo",
                        "getUserReactorInfo called with invalid ouid: $currentOuid"
                    )
                }
            } catch (e: HttpException) {
                Log.e("ViewModel - getUserReactorInfo[ERROR]", "error: ${e.message}", e)
                val errorBody = e.response()?.errorBody()?.string()
                Log.e("API_ERROR", "Error body: $errorBody")
            }
        }
    }

    /** 반응로 ID에 맞춰서 이름으로 출력 */
    private fun getReactorInfo(reactorId: String) {
        viewModelScope.launch {
            val apiService = RetrofitClient.getSupabaseApiService()
            try {
                val apiResponse = apiService.getUserReactorName(
                    select = "main_reactor_id,reactor_name,image_url,reactor_tier,optimized_condition_type",
                    main_reactor_id = "eq.$reactorId"
                )

                _user_reactor.value = _user_reactor.value.copy(
                    main_reactor_id = apiResponse[0].main_reactor_id,
                    reactor_name = apiResponse[0].reactor_name,
                    image_url = apiResponse[0].image_url,
                    reactor_tier = apiResponse[0].reactor_tier,
                    optimized_condition_type = apiResponse[0].optimized_condition_type
                )

                Log.d("ViewModel - getUserReactorName", "user_reactor: ${userReactorInfo.value}")
            } catch (e: Exception) {
                Log.e("ViewModel - getUserReactorName[ERROR]", "error: ${e.message}", e)
            }
        }
    }

    private fun getReactorSkillPower(reactorId: String) {
        viewModelScope.launch {
            val apiService = RetrofitClient.getSupabaseApiService()
            try {
                val apiResponse = apiService.getUserReactorSkillPower(
                    select = "*",
                    reactor_id = "eq.${reactorId}",
                    level = "eq.${userReactorInfo.value.reactor_level}"
                )

                _user_reactor_SkillPower.value = _user_reactor_SkillPower.value.copy(
                    id = apiResponse[0].id,
                    reactor_id = apiResponse[0].reactor_id,
                    level = apiResponse[0].level,
                    skill_atk_power = apiResponse[0].skill_atk_power,
                    sub_skill_atk_power = apiResponse[0].sub_skill_atk_power
                )

                if (_user_reactorInfo.value.reactor_enchant_level == 1) {
                    _user_reactor_SkillPower.value = _user_reactor_SkillPower.value.copy(
                        skill_atk_power = 11392.79
                    )
                } else if (_user_reactorInfo.value.reactor_enchant_level == 2) {
                    _user_reactor_SkillPower.value = _user_reactor_SkillPower.value.copy(
                        skill_atk_power = 11724.62

                    )
                }

                getReactorSkillCoefficient()

                Log.d(
                    "ViewModel - getUserReactorSkillPower",
                    "user_reactor_skill_power: ${userReactorSkillPower.value}"
                )
            } catch (e: Exception) {
                Log.e("ViewModel - getUserReactorSkillPower[ERROR]", "error: ${e.message}", e)
            }
        }
    }

    private fun getReactorSkillCoefficient() {
        viewModelScope.launch {
            val apiService = RetrofitClient.getSupabaseApiService()
            try {
                val apiResponse = apiService.getReactorSkillCoefficient(
                    select = "coefficient_stat_id,coefficient_stat_value",
                    skill_power_coefficient_id = "eq.${_user_reactor_SkillPower.value.id}"
                )

                _reactor_coefficient.value = apiResponse

                Log.d(
                    "ViewModel - getReactorSkillCoefficient",
                    "reactor_additional_stat: ${reactorCoefficient.value}"
                )
            } catch (e: Exception) {
                Log.e("ViewModel - getReactorSkillCoefficient[ERROR]", "error: ${e.message}", e)
            } finally {
                _isLoading.value = _reactor_coefficient.value.isEmpty()
                _nextScreenRoute.value = REACTORINFOSCREEN_ROUTE
            }
        }
    }

    /** 계승자 정보 조회 */
    fun getUserDescendantInfo() {
        viewModelScope.launch {
            _isLoading.value = true
            val apiService = RetrofitClient.getDecendantApi()
            val currentTime = System.currentTimeMillis()
            val currentOuid = test.value.ouid

            if (currentOuid.isNotEmpty() && currentOuid != "test") {

                val lastDescendantTime = dataStoreManager.lastDescendantTime.first()

                if (_user_descendantInfo.value.ouid == currentOuid && currentTime - lastDescendantTime < TimeUnit.MINUTES.toMillis(
                        5
                    )
                ) {
                    Log.d("getDescendantInfoTime", "이미 사용된 oui: $currentOuid")
                    _isLoading.value = false
                    _nextScreenRoute.value = DESCENDANTINFOSCREEN_ROUTE
                    return@launch
                }
            }

            try {

                val apiResponse = apiService.getUserDescendantInfo(currentOuid)
                _user_descendantInfo.value = apiResponse

                getDescendantInfo(_user_descendantInfo.value.descendant_id)
                getDescendantModule()
                dataStoreManager.saveLastDescendantTime(currentTime)

                val minimumTime = System.currentTimeMillis() - currentTime
                if (minimumTime < 500) {
                    delay(500 - minimumTime)
                } else {
                    Log.e(
                        "ViewModel - getDescendantInfo",
                        "getDescendantInfo called with invalid ouid: ${currentOuid}"
                    )
                }
            } catch (e: Exception) {
                Log.e("ViewModel - getDescendantInfo[ERROR]", "error: ${e.message}", e)
            }
        }
    }

    /** 계승자 ID에 맞춰서 계승자 이름 출력 */
    private fun getDescendantInfo(descendantId: String) {
        viewModelScope.launch {
            val apiService = RetrofitClient.getSupabaseApiService()
            try {

                val apiResponse = apiService.getUserDescendantName(
                    select = "descendant_name,descendant_image_url",
                    main_descendant_id = "eq.$descendantId"
                )

                _user_descendant.value = _user_descendant.value.copy(
                    descendant_name = apiResponse[0].descendant_name,
                    descendant_image_url = apiResponse[0].descendant_image_url
                )

            } catch (e: Exception) {
                Log.e("ViewModel - getDescendantName[ERROR]", "error: ${e.message}", e)
            }
        }
    }

    /** 계승자 모듈 ID, 모듈 한글 이름, 등급, 이미지 가져오기 */
    private fun getDescendantModule() {
        viewModelScope.launch {
            _isLoading.value = true
            val apiService = RetrofitClient.getSupabaseApiService()
            try {
                val moduleIds = _user_descendantInfo.value.module.map { it.module_id }
                val apiResponse = apiService.getUserModuleDescendant(
                    select = "main_module_id,module_name,module_tier,image_url",
                    main_module_id = "in.(${moduleIds.joinToString(",")})" //in 연산자 사용 시, in (1,2,3) 형태로 넣어줘야 함
                )
                _user_DescendantModule.value = apiResponse
            } catch (e: Exception) {
                Log.e("ViewModel - getModuleNames[ERROR]", "error: ${e.message}", e)
            } finally {
                _isLoading.value = false
                _nextScreenRoute.value = DESCENDANTINFOSCREEN_ROUTE
            }
        }
    }

    fun getUserExternalInfo() {
        viewModelScope.launch {
            _isLoading.value = true
            val apiService = RetrofitClient.getDecendantApi()
            val currentTime = System.currentTimeMillis()
            val currentOuid = test.value.ouid

            if (currentOuid.isNotEmpty() && currentOuid != "test") {
                val lastExternalTime = dataStoreManager.lastExternalTime.first()
                if (_user_externalInfo.value.ouid == currentOuid && currentTime - lastExternalTime < TimeUnit.MINUTES.toMillis(
                        5
                    )
                ) {
                    Log.d("getExternalInfoTime", "이미 사용된 oui: $currentOuid")
                    _isLoading.value = false
                    _nextScreenRoute.value = EXTERNALINFOSCREEN_ROUTE
                    return@launch
                }
            }
            try {
                val apiResponse = apiService.getUserExternalInfo("ko", currentOuid)
                _user_externalInfo.value = apiResponse

                getExternalInfo()
                getExternalValue()
                delay(100)
                getExternalStat()
                dataStoreManager.saveLastExternalTime(currentTime)

                val minimumTime = System.currentTimeMillis() - currentTime
                if (minimumTime < 500) {
                    delay(500 - minimumTime)
                } else {
                    Log.e(
                        "ViewModel - getUserExternalInfo",
                        "getUserExternalInfo called with invalid ouid: ${currentOuid}"
                    )
                }
            } catch (e: Exception) {
                Log.e("ViewModel - getUserExternalInfo[ERROR]", "error: ${e.message}", e)
            }
        }
    }

    private fun getExternalInfo() {
        viewModelScope.launch {
            val apiService = RetrofitClient.getSupabaseApiService()
            try {
                val sortedExternalIDs =
                    _user_externalInfo.value.external_component.sortedBy { it.external_component_slot_id }
                        .map { it.external_component_id }

                val apiResponse = apiService.getUserExternalName(
                    select = "main_external_component_id,external_component_name,image_url,external_component_tier",
                    main_external_component_id = "in.(${sortedExternalIDs.joinToString(",")})"
                )

                val sortedApiResponse = sortedExternalIDs.map { id ->
                    apiResponse.find { it.main_external_component_id == id }
                }

                _user_external.value = sortedApiResponse.filterNotNull()

                Log.d("ViewModel - getExternalInfo", "user_external: ${userExternal.value}")

            } catch (e: Exception) {
                Log.e("ViewModel - getExternalInfo[ERROR]", "error: ${e.message}", e)
            }
        }
    }

    private suspend fun getExternalValue() {
        val apiService = RetrofitClient.getSupabaseApiService()
        try {
            val sortedExternalIDs =
                _user_externalInfo.value.external_component.sortedBy { it.external_component_slot_id }
                    .map { it.external_component_id }

            val apiResponse = apiService.getUserExterStatValue(
                select = "external_component_id,stat_id,stat_value",
                level = "in.(${
                    _user_externalInfo.value.external_component.map { it.external_component_level }
                        .joinToString(",")
                })",
                external_component_id = "in.(${
                    sortedExternalIDs.joinToString(",")
                })"
            )
            val sortedApiResponse = sortedExternalIDs.map { id ->
                apiResponse.find { it.external_component_id == id }
            }

            _user_external_value.value = sortedApiResponse.filterNotNull()
            Log.d("getExternalValue", "user_external_value: ${userExternalValue.value}")
        } catch (e: Exception) {
            Log.e("getExternalValue[ERROR]", "error: ${e.message}", e)
        }
    }

    private suspend fun getExternalStat() {
        val apiService = RetrofitClient.getSupabaseApiService()
        try {
            val statIds =
                _user_external_value.value.flatMap { listOf(it.stat_id, it.stat_id) } // 중복 추가
            val statIdString = statIds.joinToString(",")
            val apiResponse = apiService.getStatName(
                select = "stat_name,stat_id",
                stat_id = "in.($statIdString)"
            )

            _user_external_stat.value = apiResponse

            Log.d("getExternalStat", "user_external_stat: ${userExternalStat.value}")
        } catch (e: Exception) {
            Log.e("getExternalStat[ERROR]", "error: ${e.message}", e)
        } finally {
            _isLoading.value = false
            _nextScreenRoute.value = EXTERNALINFOSCREEN_ROUTE
        }
    }

    fun resetNextScreenRoute() {
        _nextScreenRoute.value = null
    }
}