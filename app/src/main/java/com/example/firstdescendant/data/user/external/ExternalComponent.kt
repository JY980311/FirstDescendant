package com.example.firstdescendant.data.user.external

data class ExternalComponent(
    val external_component_additional_stat: List<ExternalComponentAdditionalStat>,
    val external_component_id: String,
    val external_component_level: Int,
    val external_component_slot_id: String
)