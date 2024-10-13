package com.example.firstdescendant.data.user.external

data class UserExternalData(
    val external_component: List<ExternalComponent>,
    val ouid: String,
    val user_name: String
)