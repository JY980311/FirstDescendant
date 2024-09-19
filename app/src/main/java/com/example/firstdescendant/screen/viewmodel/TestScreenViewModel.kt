package com.example.firstdescendant.screen.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firstdescendant.data.meta.reactor.ReactorData
import com.example.firstdescendant.data.meta.reactor.ReactorDataItem
import com.example.firstdescendant.data.user.basicinfo.UserBasic
import com.example.firstdescendant.data.user.descendantinfo.UserDescendant
import com.example.firstdescendant.data.user.ouid.UserOuid
import com.example.firstdescendant.data.user.weapon.UserWeapon
import com.example.firstdescendant.network.RetrofitClient
import com.example.firstdescendant.network.RetrofitClient2
import com.example.firstdescendant.util.CharacterMapping
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException

class TestScreenViewModel: ViewModel() {

    private val _test = MutableStateFlow(UserOuid(""))
    val test = _test.asStateFlow()

    private val _user_basicInfo = MutableStateFlow(UserBasic("",0,0,"","","","","",""))
    val basicInfo = _user_basicInfo.asStateFlow()

    private val _user_descendantInfo = MutableStateFlow(UserDescendant("",0,"", emptyList(),0,0,"",""))
    val descendantInfo = _user_descendantInfo.asStateFlow()

    private val _user_weaponInfo = MutableStateFlow(UserWeapon("","", emptyList()))
    val userWeaponInfo = _user_weaponInfo.asStateFlow()
    //TODO: TEST
    private val _all_reactor = MutableStateFlow<List<ReactorDataItem>>(emptyList())
    val all_reactor = _all_reactor.asStateFlow()

    private val _textField = MutableStateFlow("")
    val textField = _textField.asStateFlow()

    private val _errorMessage = MutableStateFlow("")
    val errorMessage = _errorMessage.asStateFlow()

    fun getText( newText:String ) {
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
            } catch (e : Exception) {
                if(e.message?.contains("400") == true) {
                    _errorMessage.value = "사용자를 찾을 수 없습니다."
                }
                else if(e.message?.contains("429") == true) {
                    _errorMessage.value = "요청이 너무 많습니다."
                }
                else if(e.message?.contains("500") == true) {
                    _errorMessage.value = "내부 서버에서 오류가 발생하였습니다."
                }
                else {
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
                    Log.e("ViewModel - getBasicInfo", "getBasicInfo called with invalid ouid: ${test.value.ouid}")
                }
            } catch (e: Exception) {
                Log.e("ViewModel - getBasicInfo[ERROR]", "error: ${e.message}", e)
            }
        }
    }

    fun getDescendantInfo() {
        viewModelScope.launch {
            val apiService = RetrofitClient.getDecendantApi()
            try {
                // test.value.ouid가 올바르게 설정되었는지 확인합니다.
                if (test.value.ouid.isNotEmpty() && test.value.ouid != "test") {
                    val apiResponse = apiService.getUserDescendantInfo(test.value.ouid)
                    _user_descendantInfo.value = apiResponse
                    Log.d("ViewModel - getDescendantInfo", "descendantInfo: ${descendantInfo.value}")
                } else {
                    Log.e("ViewModel - getDescendantInfo", "getDescendantInfo called with invalid ouid: ${test.value.ouid}")
                }
            } catch (e: Exception) {
                Log.e("ViewModel - getDescendantInfo[ERROR]", "error: ${e.message}", e)
            }
        }
    }

    fun getUserWeaponInfo() {
        viewModelScope.launch {
            val apiService = RetrofitClient.getDecendantApi()
            try {
                if (test.value.ouid.isNotEmpty() && test.value.ouid != "test") {
                    val apiResponse = apiService.getUserWeaponInfo("ko",test.value.ouid)
                    _user_weaponInfo.value = apiResponse
                    Log.d("ViewModel - getUserWeaponInfo", "userWeaponInfo: ${userWeaponInfo.value}")
                } else {
                    Log.e("ViewModel - getUserWeaponInfo", "getUserWeaponInfo called with invalid ouid: ${test.value.ouid}")
                }
            } catch (e: HttpException) {
                Log.e("ViewModel - getUserWeaponInfo[ERROR]", "error: ${e.message}", e)
                val errorBody = e.response()?.errorBody()?.string()
                Log.e("API_ERROR", "Error body: $errorBody")
            }
        }
    }

    //TODO: TEST
    fun getReactorData() {
        viewModelScope.launch {
            val apiService = RetrofitClient2.TestgetDecendantApi()
            try {
                val apiResponse = apiService.getReactor()
                _all_reactor.value = apiResponse
            } catch (e: HttpException) {
                Log.e("ViewModel - getReactorData[ERROR]", "error: ${e.message}", e)
                val errorBody = e.response()?.errorBody()?.string()
                Log.e("API_ERROR", "Error body: $errorBody")
            }
        }
    }

    /** ID를 통해 맵핑된 계승자의 이름으로 출력 */
    fun getCharacterNameById(id: String):String {
        return CharacterMapping.getCharacterNameById(id)
    }

    //TODO: 모듈 ID를 이름으로 변환하는 함수 아직 보류
    /*fun mapModuleIdsToNames(user: UserDescendantData): UserDescendantData {
        val updatedModules = user.module.map { module ->
            val moduleName = ModuleMapping.getModuleNameById(listOf(module.module_id)).firstOrNull() ?: "Unknown Module"
            module.copy(module_id = moduleName) // module_id를 모듈 이름으로 변환
        }
        return user.copy(module = updatedModules)
    }*/
}