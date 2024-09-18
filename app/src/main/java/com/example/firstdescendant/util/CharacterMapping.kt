package com.example.firstdescendant.util

object CharacterMapping {

    //TODO : 헤일리 추가하기
    private val characterMap = mapOf(
        "101000001" to "레픽",
        "101000002" to "에이잭스",
        "101000003" to "비에사",
        "101000004" to "얼티밋 레픽",
        "101000005" to "제이버",
        "101000006" to "버니",
        "101000007" to "얼티밋 에이잭스",
        "101000008" to "프레이나",
        "101000009" to "글레이",
        "101000010" to "얼티밋 비에사",
        "101000011" to "샤렌",
        "101000012" to "블레어",
        "101000013" to "밸비",
        "101000014" to "카일",
        "101000015" to "에시모",
        "101000016" to "엔조",
        "101000017" to "유진",
        "101000019" to "얼티밋 버니",
        "101000020" to "얼티밋 글레이"
    )

    fun getCharacterNameById(id: String): String {
        return characterMap[id] ?: "Unknown Character"
    }
}