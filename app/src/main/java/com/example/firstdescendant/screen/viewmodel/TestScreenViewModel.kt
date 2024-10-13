package com.example.firstdescendant.screen.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firstdescendant.data.user.basicinfo.UserBasicData
import com.example.firstdescendant.data.user.descendantinfo.UserDescendantData
import com.example.firstdescendant.data.user.external.UserExternalData
import com.example.firstdescendant.data.user.external.UserExternalName
import com.example.firstdescendant.data.user.module.UserModuleInfo
import com.example.firstdescendant.data.user.ouid.UserOuid
import com.example.firstdescendant.data.user.reactor.UserReactorData
import com.example.firstdescendant.data.user.reactor.UserReactorImage
import com.example.firstdescendant.data.user.weapon.UserWeaponData
import com.example.firstdescendant.data.user.weapon.UserWeaponInfo
import com.example.firstdescendant.network.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException

class TestScreenViewModel : ViewModel() {

    /** OUID */
    private val _test = MutableStateFlow(UserOuid(""))
    val test = _test.asStateFlow()

    /** 사용자 기본 정보 */
    private val _user_basicInfo = MutableStateFlow(UserBasicData("", 0, 0, "", "", "", "", "", ""))
    val basicInfo = _user_basicInfo.asStateFlow()

    /** 사용자 계승자 정보 */
    private val _user_descendantInfo = MutableStateFlow(UserDescendantData("", 0, "", emptyList(), 0, 0, "", ""))
    val descendantInfo = _user_descendantInfo.asStateFlow()

    /** 사용자 모듈 정보 */
    private val _user_module = MutableStateFlow<List<UserModuleInfo>>(emptyList())
    val userModule = _user_module.asStateFlow()

    /** 사용자 무기 정보 */
    private val _user_weaponInfo = MutableStateFlow(UserWeaponData("", "", emptyList()))
    val userWeaponInfo = _user_weaponInfo.asStateFlow()

    private val _user_weapon = MutableStateFlow<List<UserWeaponInfo>>(emptyList())
    val userWeapon = _user_weapon.asStateFlow()

    /** 사용자 반응로 정보 */
    private val _user_reactor = MutableStateFlow(UserReactorData("", emptyList(), 0, "", 0, "", ""))
    val userReactorInfo = _user_reactor.asStateFlow()

    /** 사용자 반응로 이미지 정보 */
    private val _user_reactor_image = MutableStateFlow(UserReactorImage(""))
    val userReactorImage = _user_reactor_image.asStateFlow()

    private val _user_externalInfo = MutableStateFlow(UserExternalData(emptyList(),"", ""))
    val userExternalInfo = _user_externalInfo.asStateFlow()

    private val _user_external = MutableStateFlow<List<UserExternalName>>(emptyList())
    val userExternal = _user_external.asStateFlow()

    /** 반응로 이름 변환 완료 되었는지 체크 */
    private val _isReactorNameReady = MutableStateFlow(false)
    val isReactorNameReady = _isReactorNameReady.asStateFlow()

    /** 계승자 이름 변환 완료 되었는지 체크 */
    private val _isDescendantNameReady = MutableStateFlow(false)
    val isDescendantNameReady = _isDescendantNameReady.asStateFlow()

    /** 무기 이름 변환 완료 되었는지 체크 */
    private val _isWeaponNameReady = MutableStateFlow(false)
    val isWeaponNameReady = _isWeaponNameReady.asStateFlow()

    private val _isExternalNameReady = MutableStateFlow(false)
    val isExternalNameReady = _isExternalNameReady.asStateFlow()

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
            val apiService = RetrofitClient.getDecendantApi()
            try {
                val apiResponse: UserOuid = apiService.getUserOuid(textField.value)
                _test.update {
                    it.copy(ouid = apiResponse.ouid)
                }
                Log.d("ViewModel - getOuid", "ouid: ${test.value.ouid}")
            } catch (e: Exception) {
                if (e.message?.contains("400") == true) {
                    _errorMessage.value = "사용자를 찾을 수 없습니다."
                } else if (e.message?.contains("429") == true) {
                    _errorMessage.value = "요청이 너무 많습니다."
                } else if (e.message?.contains("500") == true) {
                    _errorMessage.value = "내부 서버에서 오류가 발생하였습니다."
                } else {
                    _errorMessage.value = "알 수 없는 오류가 발생하였습니다."
                }
            }
        }
    }

    fun getBasicInfo() {
        viewModelScope.launch {
            val apiService = RetrofitClient.getDecendantApi()
            try {
                // test.value.ouid가 올바르게 설정되었는지 확인합니다.
                if (test.value.ouid.isNotEmpty() && test.value.ouid != "test") {
                    val apiResponse = apiService.getUserInfo(test.value.ouid)
                    _user_basicInfo.value = apiResponse
                    Log.d("ViewModel - getBasicInfo", "basicInfo: ${basicInfo.value}")
                } else {
                    Log.e(
                        "ViewModel - getBasicInfo",
                        "getBasicInfo called with invalid ouid: ${test.value.ouid}"
                    )
                }
            } catch (e: Exception) {
                Log.e("ViewModel - getBasicInfo[ERROR]", "error: ${e.message}", e)
            }
        }
    }


    fun getUserWeaponInfo() {
        viewModelScope.launch {
            val apiService = RetrofitClient.getDecendantApi()
            try {
                if (test.value.ouid.isNotEmpty() && test.value.ouid != "test") {
                    val apiResponse = apiService.getUserWeaponInfo("ko", test.value.ouid)
                    _user_weaponInfo.value = apiResponse

                    Log.d(
                        "ViewModel - getUserWeaponInfo",
                        "userWeaponInfo: ${userWeaponInfo.value}"
                    )

                    _isWeaponNameReady.value = false

                    getWeaponInfo()
                    getWeaponModule()
                } else {
                    Log.e(
                        "ViewModel - getUserWeaponInfo",
                        "getUserWeaponInfo called with invalid ouid: ${test.value.ouid}"
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
                val weaponIDs = _user_weaponInfo.value.weapon.map { it.weapon_id }
                val apiResponse = apiService.getUserWeaponNameImage(
                    select = "main_weapon_id,weapon_name,image_url",
                    main_weapon_id = "in.(${weaponIDs.joinToString(",")})"
                )

                _user_weapon.value = apiResponse

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
                val moduleIDs = _user_weaponInfo.value.weapon.map { weapon -> weapon.module.map { module -> module.module_id } }
                Log.d("ViewModel - getWeaponModule", "moduleIDs: $moduleIDs")
                val apiResponse = apiService.getUserModuleName(
                    select = "main_module_id,module_name,module_tier,image_url",
                    main_module_id = "in.(${moduleIDs.flatten().joinToString(",")})"
                )

                _user_module.value = apiResponse

                _isWeaponNameReady.value = true

            } catch (e: Exception) {
                Log.e("ViewModel - getWeaponModule[ERROR]", "error: ${e.message}", e)
            }
        }
    }

    /** 유저 반응로 정보 조회 */
    fun getUserReactorInfo() {
        viewModelScope.launch {
            val apiService = RetrofitClient.getDecendantApi()
            try {
                if (test.value.ouid.isNotEmpty() && test.value.ouid != "test") {
                    val apiResponse = apiService.getUserReactorInfo("ko", test.value.ouid)
                    _user_reactor.value = apiResponse

                    _isReactorNameReady.value = false

                    getUserReactorImage(_user_reactor.value.reactor_id)
                    getUserReactorName(_user_reactor.value.reactor_id)

                    Log.d(
                        "ViewModel - getUserReactorInfo",
                        "user_reactor: ${userReactorInfo.value}"
                    )
                    Log.d(
                        "ViewModel - getUserReactorInfo",
                        "user_reactor_image: ${userReactorImage.value}"
                    )
                } else {
                    Log.d(
                        "ViewModel - getUserReactorInfo",
                        "getUserReactorInfo called with invalid ouid: ${test.value.ouid}"
                    )
                }
            } catch (e: HttpException) {
                Log.e("ViewModel - getUserReactorInfo[ERROR]", "error: ${e.message}", e)
                val errorBody = e.response()?.errorBody()?.string()
                Log.e("API_ERROR", "Error body: $errorBody")
            }
        }
    }

    /** 계승자 정보 조회 */
    fun getDescendantInfo() {
        viewModelScope.launch {
            val apiService = RetrofitClient.getDecendantApi()
            try {
                if (test.value.ouid.isNotEmpty() && test.value.ouid != "test") {
                    val apiResponse = apiService.getUserDescendantInfo(test.value.ouid)
                    _user_descendantInfo.value = apiResponse

                    _isDescendantNameReady.value = false

                    getDescendantName(_user_descendantInfo.value.descendant_id)
                    getDescendantModule()

                    Log.d(
                        "ViewModel - getDescendantInfo",
                        "descendantInfo: ${descendantInfo.value}"
                    )
                } else {
                    Log.e(
                        "ViewModel - getDescendantInfo",
                        "getDescendantInfo called with invalid ouid: ${test.value.ouid}"
                    )
                }
            } catch (e: Exception) {
                Log.e("ViewModel - getDescendantInfo[ERROR]", "error: ${e.message}", e)
            }
        }
    }

    fun getUserExternalInfo() {
        viewModelScope.launch {
            val apiService = RetrofitClient.getDecendantApi()
            try {
                if (test.value.ouid.isNotEmpty() && test.value.ouid != "test") {
                    val apiResponse = apiService.getUserExternalInfo("ko", test.value.ouid)
                    _user_externalInfo.value = apiResponse

                    _isExternalNameReady.value = false

                    getExternalInfo()

                    Log.d(
                        "ViewModel - getUserExternalInfo",
                        "user_externalInfo: ${userExternalInfo.value}"
                    )

                } else {
                    Log.e(
                        "ViewModel - getUserExternalInfo",
                        "getUserExternalInfo called with invalid ouid: ${test.value.ouid}"
                    )
                }
            } catch (e:Exception) {
                Log.e("ViewModel - getUserExternalInfo[ERROR]", "error: ${e.message}", e)
            }
        }
    }

    private fun getExternalInfo() {
        viewModelScope.launch {
            val apiService = RetrofitClient.getSupabaseApiService()
            try {
                val externalIDs = _user_externalInfo.value.external_component.map { it.external_component_id }
                val apiResponse = apiService.getUserExternalName(
                    select = "external_component_name,image_url",
                    main_external_component_id = "in.(${externalIDs.joinToString(",")})"
                )

                _user_external.value = apiResponse

                _isExternalNameReady.value = true

                Log.d("ViewModel - getExternalInfo", "user_external: ${userExternal.value}")
            } catch (e: Exception) {
                Log.e("ViewModel - getExternalInfo[ERROR]", "error: ${e.message}", e)
            }
        }
    }

    /** 계승자 모듈 ID, 모듈 한글 이름, 등급, 이미지 가져오기 */
    private fun getDescendantModule() {
        viewModelScope.launch {
            val apiService = RetrofitClient.getSupabaseApiService()
            try {
                val moduleIds = _user_descendantInfo.value.module.map { it.module_id }
                val apiResponse = apiService.getUserModuleName(
                    select = "main_module_id,module_name,module_tier,image_url",
                    main_module_id = "in.(${moduleIds.joinToString(",")})" //in 연산자 사용 시, in (1,2,3) 형태로 넣어줘야 함
                )
                _user_module.value = apiResponse

                _isDescendantNameReady.value = true

            } catch (e: Exception) {
                Log.e("ViewModel - getModuleNames[ERROR]", "error: ${e.message}", e)
            }
        }
    }

    /** 계승자 ID에 맞춰서 계승자 이름 출력 */
    private fun getDescendantName(descendantId: String) {
        viewModelScope.launch {
            val apiService = RetrofitClient.getSupabaseApiService()
            try {
                val apiResponse = apiService.getUserDescendantName(
                    select = "descendant_name",
                    main_descendant_id = "eq.$descendantId"
                )

                _user_descendantInfo.value = _user_descendantInfo.value.copy(
                    descendant_id = apiResponse[0].descendant_name
                )

                // _isDescendantNameReady.value= true

            } catch (e: Exception) {
                Log.e("ViewModel - getDescendantName[ERROR]", "error: ${e.message}", e)
            }
        }
    }

    /** 반응로 ID에 맞춰서 이름으로 출력 */
    private fun getUserReactorName(reactorId: String) {
        viewModelScope.launch {
            val apiService = RetrofitClient.getSupabaseApiService()
            try {
                val apiResponse = apiService.getUserReactorName(
                    select = "reactor_name",
                    main_reactor_id = "eq.$reactorId"
                )

                _user_reactor.value = _user_reactor.value.copy(
                    reactor_id = apiResponse[0].reactor_name
                )

                _isReactorNameReady.value = true

                Log.d("ViewModel - getUserReactorName", "user_reactor: ${userReactorInfo.value}")
            } catch (e: Exception) {
                Log.e("ViewModel - getUserReactorName[ERROR]", "error: ${e.message}", e)
            }
        }
    }

    /** 반응로 ID에 맞춰서 이미지 불러오기 */
    private fun getUserReactorImage(reactorId: String) {
        viewModelScope.launch {
            val apiService = RetrofitClient.getSupabaseApiService()
            try {
                val apiResponse = apiService.getUserReactorImage(
                    select = "image_url",
                    main_reactor_id = "eq.$reactorId"
                )

                _user_reactor_image.value = _user_reactor_image.value.copy(
                    image_url = apiResponse[0].image_url
                )

            } catch (e: Exception) {
                Log.e("ViewModel - getUserReactorImage[ERROR]", "error: ${e.message}", e)
            }
        }
    }
}